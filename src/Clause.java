
import java.util.*;

public class Clause {
	
	public Type[] types;

	public Clause(Type[] types) {

		this.types = types;
	}

	public Clause(ArrayList<Type> altypes) {

		types = new Type[altypes.size()];

		for (int i = 0; i < altypes.size(); i++) {

			types[i] = altypes.get(i);
		}
	}

	public Clause(Type type) {

		Type[] temp = new Type[1];
		temp[0] = type;
		types = temp;
	}

	public Clause() {

		types = null;
	}

	public Clause(Clause copy) {

		types = new Type[copy.types.length];
		for (int i = 0; i < this.types.length; i++) {

			this.types[i] = copy.types[i];
		}
	}

	public boolean isEmpty() {

		if (types.length == 0) return true;

		return false;
	}

	/*public void combine(Clause other) {

		Type[] temp = new Type[types.length + other.types.length];

		int i = 0;

		for (int j = 0; j < types.length; j++) {

			temp[i] = types[j];
			i++;
		}

		for (int j = 0; j < other.types.length; j++) {

			temp[i] = other.types[j];
			i++;
		}

		types = temp;
	}*/

	/*public Type[] remove(int k) {

		Type[] temp = new Type[types.length-1];
		for (int i = 0; i < types.length; i++) {

			if(i != k) {
				temp[i] = types[i];
			}
		}

		return temp;
	}*/


	public Clause mate(Clause other) {

		ArrayList<Type> tempi = new ArrayList<>();
		ArrayList<Type> tempj = new ArrayList<>();

		for (int i = 0; i < types.length; i++) {

			tempi.add(types[i]);
		}

		for (int i = 0; i < other.types.length; i++) {

			tempj.add(other.types[i]);
		}

		for (int i = 0; i < types.length; i++) {

			for (int j = 0; j < other.types.length; j++) {

				if (types[i].isNegationOf(other.types[j])) {

					tempi.remove(i);
					tempj.remove(j);

					for (int k = 0; k < tempi.size(); k++) {

						for (int l = 0; l < tempj.size(); l++) {

							if (tempi.get(k).equalsTo(tempj.get(l))) {

								tempj.remove(l);
							}
						}
					}

					tempi.addAll(tempj);

					return new Clause (tempi);	
				}
			}
		}

		// Ara den mporei na kanei mate, epomenws epistrefoume null...
		
		return null;

	}

	public boolean equalsTo(Clause other) {

		int count = 0;
		if (types.length != other.types.length) return false;
		for (int i = 0; i < types.length; i++) {

			for (int j = 0; j < other.types.length; j++) {

				if (types[i].equalsTo(other.types[j])) {

					count++;
					break;
				}
			}
		}	

		if (count == types.length) {

			return true;
		} else {

			return false;
		}
	}

	public void print() {

		System.out.print("(");
		for (int i = 0; i < types.length; i++) {

			System.out.print(types[i].toString() + " ");
		}
		System.out.println(")");
	}

   
}