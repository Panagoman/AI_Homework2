import java.util.*;

public class FOL_Entity {
	
	public String value;
	public boolean compound;
	//public boolean negation;
	//public ArrayList<FOL_Entity> definition;

	public FOL_Entity() {

		value = null;
		//negation = false;
		//definition = null;
	}

	public FOL_Entity(String in) {

		value = in;
		//negation = false;
		//definition = null;
	}

	public FOL_Entity(FOL_Entity f) {

		value = f.value;
	}



	/*public String identify() {

		if (Character.isLowerCase(value.charAt(0)) && value.length() == 1) {

			return "Variable";
		} else if (!value.contains("(")) {

			return "Constant";
		} else {

			int i = value.indexOf("(");
			if (i == 1) {

				return "Function";
			} else {

				return "FOL_Type";
			}
		}
	}*/
	
	public FOL_Entity[] args() {

		return null;
	}

	public FOL_Entity getDefinition() {

		return null;
	}

	public boolean isNegation() {

		return false;
	}

	public boolean contains(Variable b) {

		return false;
	}

	public boolean equalsTo(FOL_Entity other) {

		if (value.equals(other.value)) {

			return true;
		}
		
		return false;
	}

	public String identify() {

		if (this instanceof Variable ) {

			return "Variable";
		} else if (this instanceof Constant) {

			return "Constant";
		} else if (this instanceof Function) {

			return "Function";
		} else if (this instanceof FOL_Type) {

			return "FOL_Type";
		} else {

			return "FOL_Entity";
		}
	}


	public String toString() {

		return value;
	}

	public void print() {

		System.out.println(value);	
	}
}