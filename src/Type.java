/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Type {
	
	public String value; // The name of the type...

	public Type(String in) {

		value = in;

	}

	public Type() {

		value = null;
	}

	public Type(Type copy) {

		this.value = copy.value;
	}

	public boolean equalsTo(Type other) {

		if (value.equals(other.value)) {

			return true;
		}
		
		return false;

	}

	public boolean isNegationOf(Type other) {

		char not_symbol = 172;
		String temp;

		if (other.value.startsWith(Character.toString(not_symbol))) {

			temp = other.value.replaceAll(Character.toString(not_symbol),"");
			if (temp.equals(value)) {

				return true;
			}

			return false;

		} else if(value.startsWith(Character.toString(not_symbol))) {

			temp = value.replaceAll(Character.toString(not_symbol),"");
			if (temp.equals(other.value)) {

				return true;
			}

			return false;
		}

		return false;
	}

	public boolean isNegative() { // A function tha return true if a type is ¬ (negative)

		char not_symbol = 172;

		if (value.startsWith(Character.toString(not_symbol))) {

			return true;
		}

		return false;
	}

	public String toString() {

		char not_symbol = 172;
		if (this.isNegative()) {

			return "!" + value.replaceAll(Character.toString(not_symbol),"");
		}
		
		return value;
	}

}