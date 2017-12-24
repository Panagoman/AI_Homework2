import java.util.*;

public class Variable extends FOL_Entity {
	
	public boolean negation;

	public Variable() {

		value = null;
		negation = false;
	}


	public Variable(String input) {

		char not_symbol = 172;
		if (input.startsWith(Character.toString(not_symbol))) {

			value = input.replaceAll(Character.toString(not_symbol),"");
			negation = true;
		} else {

			value = input;
			negation = false;
		}

	}

	public Variable(Variable v) {

		value = v.value;
		negation = v.negation;
	}

	public boolean equalsTo(FOL_Entity other) {

		if (value.equals(other.value) && this.isNegation() == other.isNegation()) {

			return true;
		}
		
		return false;

	}

	public boolean isNegation() {

		return negation;
	}

	public String toString() {

		char not_symbol = 172;
		if (negation) {

			return not_symbol+value;
		}

		return value;
	}

	public void print() {

		if (negation) {

			System.out.println("¬" + value);
		} else {

			System.out.println(value);
		}
		
	}
}