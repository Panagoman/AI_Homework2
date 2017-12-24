import java.util.*;

public class Function extends FOL_Entity {
	
	public FOL_Entity definition;

	public Function() {

		compound = true;
		value = null;
		definition = null;
	}


	public Function(String input) {

		compound = true;
		value = Character.toString(input.charAt(0));

		String temp = input.substring(2);
		temp = temp.substring(0, temp.length() - 1);

		if (Character.isLowerCase(temp.charAt(0))) {

			definition = new Variable(temp);
		} else {

			definition = new Constant(temp);
		}
		
	}

	public Function(Function f) {

		value = f.value;
		compound = f.compound;
		
		String s = definition.identify();

		if (s.equals("Variable")) {

			definition = new Variable(f.definition.toString());
		} else if (s.equals("Constant")) {

			definition = new Constant(f.definition.toString());
		} else if (s.equals("FOL_Type")) {

			definition = new FOL_Type(f.definition.toString());
		} else {

			definition = new FOL_Entity(f.definition.toString());
		}
	}

	public FOL_Entity getDefinition() {

		return definition;
	}

	public FOL_Entity [] args() {

		FOL_Entity [] def = new FOL_Entity[1];
		def[0] = definition;

		return def;
	}

	public boolean contains(Variable b) {

		if (definition.equalsTo(b)) {

			return true;
		} 
		
		return false;
	}


	public boolean equalsTo(FOL_Entity other) {

		FOL_Entity def = other.getDefinition();
		if (value.equals(other.value) && definition.equalsTo(def)) {

			return true;
		}
		
		return false;
	}

	public void print() {

		System.out.println(value + "(" + definition.toString() + ")");		
	}

	public String toString() {

		String temp = "";
		temp = temp + value + "(" + definition.toString() + ")";
		return temp;
	}

}