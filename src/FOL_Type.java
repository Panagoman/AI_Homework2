import java.util.*;

public class FOL_Type extends FOL_Entity {
	
	public boolean negation;
	public ArrayList<FOL_Entity> definition = new ArrayList<FOL_Entity>();

	

	public FOL_Type(String input) {

		compound = true;
		value = input;
		char not_symbol = 172;
		if (value.startsWith(Character.toString(not_symbol))) {

			value = value.replaceAll(Character.toString(not_symbol),"");
			negation = true;

		} else {

			value = input;
			negation = false;
		}

		value = value.substring(0, value.length() - 1); // removing the last ")"...

		int i = value.indexOf("("); // getting the location of the first "("...

		String temp = value;
		value = temp.substring(0, i); // This is our value (everything till the first occurance of "(" )...

		temp = temp.substring(i+1, temp.length()); // We take the rest

		String [] temparray = temp.split(","); // we split it where "," are...


		// We add the variables, functions, constants or fol_types to the definition Arraylist...
		for (int j = 0; j < temparray.length; j++) {

			if (Character.isLowerCase(temparray[j].charAt(0))) { // Variable

				definition.add(new Variable(temparray[j]));

			} else if (!temparray[j].contains("(")) { // Constant

				definition.add(new Constant(temparray[j]));

			} else {

				int k = temparray[j].indexOf("(");

				if (k == 1) { // Function

					definition.add(new Function(temparray[j]));

				} else {

					definition.add(new FOL_Type(temparray[j]));
				}
			}
			
		}

	}

	public FOL_Type() {

		compound = true;
		value = null;
		negation = false;
		definition = null;
	}

	public FOL_Type(FOL_Type f) {

		value = f.value;
		compound = f.compound;
		negation = f.negation;

		ArrayList<FOL_Entity> temp = new ArrayList<FOL_Entity>();
		String s;

		for (int i = 0; i < f.definition.size(); i++) {

			s = f.definition.get(i).identify();

			if (s.equals("Variable")) {

				temp.add(new Variable(f.definition.get(i).toString()));
			} else if (s.equals("Constant")) {

				temp.add(new Constant(f.definition.get(i).toString()));
			} else if (s.equals("FOL_Type")) {

				temp.add(new FOL_Type(f.definition.get(i).toString()));
			} else {

				temp.add(new FOL_Entity(f.definition.get(i).toString()));
			}
		}

		definition = temp;
	}

	public boolean isNegation() {

		return negation;
	}

	public boolean equalsTo(FOL_Entity other) {

		if (!(other instanceof FOL_Type)) {

			return false;
		}

		if (value.equals(other.value) && this.isNegation() == other.isNegation()) {

			FOL_Entity [] def = other.args();

			if (def == null || def.length != definition.size()) return false;

			int count = 0;
			for (int i = 0; i < definition.size(); i++) {

				if (definition.get(i).equalsTo(def[i])) {

					count++;
				}
			}

			if (count == definition.size()) return true;

		}
		

		return false;
	}

	public boolean isNegationOf(FOL_Entity other) {

		System.out.println("IN SIDE IS NEGATIO OF");

		if (!(other instanceof FOL_Type)) {

			return false;
		}

		if (value.equals(other.value) && this.isNegation() != other.isNegation()) {

			FOL_Entity [] def = other.args();

			if (def == null || def.length != definition.size()) return false;

			int count = 0;
			for (int i = 0; i < definition.size(); i++) {

				if (definition.get(i).equalsTo(def[i])) {

					count++;
				}
			}

			if (count == definition.size()) return true;

		}
		

		return false;
	}

	public void swap(Variable var, FOL_Entity new_val) {

		if (!this.contains(var)) return;

		for (int i = 0; i < definition.size(); i++) {

			if (definition.get(i).equalsTo(var)) {

				definition.set(i,new_val);
			}

			if (definition.get(i).contains(var)) {


				((Function) definition.get(i)).definition = new_val;
			}
		}
	}


	public FOL_Entity [] args() {

		FOL_Entity [] def = new FOL_Entity[definition.size()];
		for (int i = 0; i < definition.size(); i++) {

			def[i] = definition.get(i);
		}

		return def;
	}

	public boolean contains(Variable b) {

		for (int i = 0; i < definition.size(); i++) {

			if (definition.get(i).equalsTo(b)) {

				return true;
			} 
		}

		return false;
	}

	public String toString() {

		String temp = "";
		char not_symbol = 172;

		if (negation) {

			temp = temp + not_symbol;
		} 

		temp = temp+value+"(";

		for (int i = 0; i < definition.size()-1; i++) {

			temp = temp+definition.get(i).toString()+",";
		}
		temp = temp + definition.get(definition.size()-1) + ")";

		return temp;
	}

	public void print() {

		if (negation) {

			System.out.print("!");
		}
		System.out.print(value + "(");
		for (int i = 0; i < definition.size()-1; i++) {

			System.out.print(definition.get(i).toString() + ",");
		}
		System.out.print(definition.get(definition.size()-1));
		System.out.println(")");
	}

}