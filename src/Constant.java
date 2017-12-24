import java.util.*;

public class Constant extends FOL_Entity {

	public Constant() {

		value = null;
	}


	public Constant(String input) {

		value = input;
	}

	public Constant(Constant c) {

		value = c.value;
	}

	public boolean equalsTo(FOL_Entity other) {

		if (value.equals(other.value)) {

			return true;
		}
		
		return false;

	}

	public void print() {

		System.out.println(value);	
	}
}