import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.*;

public class AIClass2 {
	
    public static void main(String[] args) throws FileNotFoundException {
		
		// Getting the user's input.
		System.out.println("Welcome");
		System.out.println("Please choose operating mode: 1 - 2 - 3 - 4");
		System.out.println("Mode 1 ---> Resolution ");
		System.out.println("Mode 2 ---> Horn  ");
		System.out.println("Mode 3 ---> FOL Resolution ");
		System.out.println("Mode 4 ---> Exit ");
		System.out.println("--- (Please enter 1, 2, 3 or 4) ---");

		Scanner input = new Scanner(System.in);
       	int mode = input.nextInt();

       	if (mode == 1) {

       		System.out.println("You have chosen mode: 1");
       		System.out.println("Input will be read from file: KnowledgeBase.txt");

       		ArrayList<String> inputList = read_input_from_file("KnowledgeBase.txt", 1);
	       if (inputList == null) { // If an error occures when reading the input file the program will terminate...

	       		System.out.println("An unexpected error occured - Program terminated.");
	       		return;
	       }

	        // After reading the input file, we are going to make an arraylist out of the given knowledge base, to make it easier for us to work with...

	        ArrayList<String []> KB = new ArrayList<>(); // KB stands for Knowledge Base
	        String [] temparray;

	        for (int i = 0; i < inputList.size(); i++) {

	        	temparray = inputList.get(i).split("V");
	        	KB.add(temparray);
	        }

	        // Now we are going to get the user's input:

	        System.out.println("--- Resolution ---");
	        System.out.println("Please enter the type you want to prove:");
	        System.out.println("(NOTE: If you want to add a negative type, please type the character ! as the negation symbol. For instance: !P1,2)");
	        System.out.println("(Please add the type you want to prove and not it's negation)");

	        Scanner answerinput = new Scanner(System.in);
	    	String answer = answerinput.nextLine();

	        char not_symbol = 172;
	        Type a;

	        if (answer.startsWith("!")) {

	        	answer = answer.substring(1); // Here we cut the "!" symbol in order to make it a positive type
	        } else {

	        	answer = Character.toString(not_symbol) + answer; // Here we add the "¬" symbol in order to make it a negative type.
	        }

	        a = new Type(answer);

	        System.out.println("Result = " + PL_Resolution(KB, a));

       	} else if (mode == 2) {

       		System.out.println("You have chosen mode: 2");
       		System.out.println("Input will be read from file: HornKnowledgeBase.txt");

       		ArrayList<String> Input = read_input_from_file("HornKnowledgeBase.txt",2);
       		if (Input == null) { // If an error occures when reading the input file the program will terminate...

	       		System.out.println("An unexpected error occured - Program terminated.");
	       		return;
	       }

       		// Now we are going to get the user's input:
          	System.out.println("--- Horn ---");
          	System.out.println("Please enter the proposition you want to prove:");

	        Scanner answerinput = new Scanner(System.in);
	    	String q = answerinput.nextLine();

	        Stack<String> agenda = new Stack<>();
	        ArrayList<String> Checked = new ArrayList<>();
	        ArrayList<String> DefiniteClauses = new ArrayList<>();

	        for(String Line : Input){

	            if(!Line.contains(">")){

	                agenda.add(Line);
	            } else {

	                DefiniteClauses.add(Line);
	            }
	        }

	        int[] count = new int[DefiniteClauses.size()];
	        int i = 0;

	        for(String Clause : DefiniteClauses){

	            String[] ClauseDivided = Clause.split(">");
	            String ClausePremise = ClauseDivided[0];
	            ClausePremise = ClausePremise.replace("^","");
	            count[i] = ClausePremise.length();
	            i++;

	        }

	        while(!agenda.isEmpty()){

	            String p = agenda.pop();
	            if(p.equals(q)){

	                System.out.println("Proposition : "+q+" is true.");
	                break;
	            }

	            if(!Checked.contains(p)){

	                Checked.add(p);
	                i = 0;

	                for(String Clause : DefiniteClauses){

	                    String[] ClauseDivided = Clause.split(">");
	                    String ClausePremise = ClauseDivided[0];

	                    if(ClausePremise.contains(p)){

	                        count[i]--;
	                        if(count[i] == 0){

	                            agenda.add(ClauseDivided[1]);
	                        }

	                    }
	                    i++;
	                }

	            }

	        }

       	} else if (mode == 3) {

       		System.out.println("You have chosen mode: 3");
       		System.out.println("Input will be read from file: FOLKnowledgeBase.txt");

       		ArrayList<String> inputList = read_input_from_file("FOLKnowledgeBase.txt", 3);
	       if (inputList == null) { // If an error occures when reading the input file the program will terminate...

	       		System.out.println("An unexpected error occured - Program terminated.");
	       		return;
	       }

	       for (int i = 0; i < inputList.size(); i++) {

	       		System.out.println(inputList.get(i));
	       }

	       // After reading the input file, we are going to make an arraylist out of the given knowledge base, to make it easier for us to work with...

	        ArrayList<FOL_Entity []> KB = new ArrayList<>(); // KB stands for Knowledge Base
	        FOL_Entity [] tempentarray;
	        FOL_Type tempo;
	        String [] temparray;

	        for (int i = 0; i < inputList.size(); i++) {

	        	temparray = inputList.get(i).split("V");
	        	tempentarray = new FOL_Entity[temparray.length];
	        	for (int j = 0; j < temparray.length; j++) {

	        		tempo = new FOL_Type(temparray[j]);
	        		tempentarray[j] = tempo;
	        	}
	        	KB.add(tempentarray);
	        }

	        // Now we are going to get the user's input:

	        System.out.println("--- FOL Resolution ---");
	        System.out.println("Please enter the type you want to prove:");
	        System.out.println("(NOTE: If you want to add a negative type, please type the character ! as the negation symbol. For instance: !Kills(Curiosity,Tuna))");
	        System.out.println("(Please add the type you want to prove and not it's negation)");

	        Scanner answerinput = new Scanner(System.in);
	    	String answer = answerinput.nextLine();
	    	answer = answer.replaceAll("\\s+",""); // Removing all spaces...
	    	FOL_Type a;
	        char not_symbol = 172;

	        if (answer.startsWith("!")) {

	        	answer = answer.substring(1); // Here we cut the "!" symbol in order to make it a positive type
	        } else {

	        	answer = Character.toString(not_symbol) + answer; // Here we add the "¬" symbol in order to make it a negative type.
	        }

	        a = new FOL_Type(answer);
	        a.print();

	        tempentarray = new FOL_Entity[1];
	        tempentarray[0] = a;

	        KB.add(tempentarray);
	       	System.out.println("Result = " + FOL_Resolution(KB));


	        /*System.out.println("-------------Testing area here:: ------------");
	        HashMap<Variable,FOL_Entity> theta = new HashMap<Variable,FOL_Entity>();

	        System.out.println("Unifying: " + KB.get(1)[0].toString() + " with : " + KB.get(3)[1].toString());
	        FOL_Entity [] z = new FOL_Entity[1];
	        FOL_Entity [] j = new FOL_Entity[1];
	        j[0] = KB.get(3)[1];
	        z[0] = KB.get(1)[0];

	        System.out.println("The result of our unify is: ");
	        theta = unify(z,j,theta);
	        if (theta == null) {

	        	System.out.println("NO Unification");
	        }else {

	        	Set<Variable> sett = sett = theta.keySet();
	        	System.out.println("IT IS NOT NULL WHICH IS GOOD THANK GOD");
	        	System.out.println("size : " + theta.size());
	        	for (Variable name: theta.keySet()){

		            String key = name.toString();
		            String value = theta.get(name).toString();  
		            System.out.println("key =" + key + ", value =  " + value);  
				}     	
	        }*/



       		

       	} else {

       		System.out.print("Program terminated.");
       	}

      


    }

    // TO DO hasNegation...
    // TO DO canUnifyWith...

    public static boolean hasNegation (FOL_Entity[] a, FOL_Entity[] b) {

    	List<FOL_Entity> list;

    	for (int i = 0; i < a.length; i++) {

    		for (int j = 0; j < b.length; j++) {

    			if (((FOL_Type)a[i]).isNegationOf((FOL_Type)b[i])) { // Removing elements from arrays...

    				System.out.println("YES IT IS NEGATION");

    				list = new ArrayList<FOL_Entity>(Arrays.asList(a));
					list.remove(i);
					a = list.toArray(a);

					list = new ArrayList<FOL_Entity>(Arrays.asList(b));
					list.remove(j);
					b = list.toArray(b);



					return true;
    			}
    		}
    	}

    	System.out.println("NO IT IS NOT NEGATION");

    	return false;
    }

    public static void fix_A(FOL_Entity [] a, FOL_Entity [] b, Variable var, FOL_Entity new_val) {

    	for (int i = 0; i < a.length; i++) {

    		((FOL_Type)a[i]).swap(var, new_val);
    	}

    	for (int i = 0; i < b.length; i++) {

    		((FOL_Type)b[i]).swap(var, new_val);
    	}
    }

    public static boolean canUnifyWith(FOL_Entity[] a, FOL_Entity[] b) {

    	HashMap<Variable,FOL_Entity> theta;
    	List<FOL_Entity> list;
    	FOL_Entity[] t1, t2;

    	for (int i = 0; i < a.length; i++) {

			for (int j = 0; j < b.length; j++) {

				theta = new HashMap<Variable,FOL_Entity>();
				t1 = new FOL_Entity[1];
				t1[0] = a[i];
				t2 = new FOL_Entity[1];
				t2[0] = b[i];

				theta = unify(t1,t2,theta); // ------------- HERE WE UNIFY --------------- \\

				if (theta != null) {
			
					list = new ArrayList<FOL_Entity>(Arrays.asList(a));
					list.remove(i);
					a = list.toArray(a);

					list = new ArrayList<FOL_Entity>(Arrays.asList(b));
					list.remove(j);
					b = list.toArray(b);

					for (Variable v: theta.keySet()){

			            Variable key = v;
			            FOL_Entity value = theta.get(v);  
			            System.out.println(key.toString() + "/ " + value.toString());

			            fix_A(a,b,key,value); // here we put value where key
					}

					return true; 
				}
			}

    	}

    	return false;


    }

    public static boolean is_A_Empty(ArrayList<FOL_Entity []> KB) {

    	if (KB.size() == 0 || KB == null) return true;

    	for (int i = 0; i < KB.size(); i++) {

    		if (KB.get(i).length > 1) return false;
    	}

    	return true;
    }


    public static boolean FOL_Resolution(ArrayList<FOL_Entity []> KB) {

    	// We print the intitial KB
    	System.out.println("Initial Knowledge Base: ");
    	for (int i = 0; i < KB.size(); i++) {

    		for (int j = 0; j < KB.get(i).length-1; j++) {

    			System.out.print(KB.get(i)[j].toString() + "V");
    		}
    		System.out.println(KB.get(i)[KB.get(i).length-1].toString());

    	}

    	int max = 1000; // Maximum loops that are allowed so as to not reach an endless loop...

    	while (max > 0) {

    		//theta = new HashMap<Variable,FOL_Entity>();
    		for (int i = 0; i < KB.size()-1; i++) {

    			for (int j = i+1; j < KB.size(); j++) {

    				if (hasNegation(KB.get(i),KB.get(j))) {

    					if (is_A_Empty(KB)) return true;
    					break;
    				}

    				if (canUnifyWith(KB.get(i),KB.get(j))) {

    					if (is_A_Empty(KB)) return true;
    					break;
    				}

    			}

    		}

    		max--;

    	}

    	/*while (true) {

    		for (int i = 0; i < clauses.size()-1; i++) {

    			for (int j = i+1; j < clauses.size(); j++) {
    				
    				resolvents = clauses.get(i).mate(clauses.get(j));
    				if (resolvents != null) { // resolvents == null, otan den mporoun na kanoun mate...

    					if (resolvents.isEmpty()) return true;
    					newc.add(resolvents);
    				}
    			}
    			
    		}
    		System.out.println("--- New clauses: ---");
    		if (!add_All(clauses, newc)) return false;
    		
    	}

    	return false;*/
    	// = new HashMap<Variable,FOL_Entity>();
    	return false;

    }

    //---------------->  TO DO COPY CONSTRUCTORS!!!!

    public static HashMap<Variable,FOL_Entity> unify(FOL_Entity [] z, FOL_Entity [] j, HashMap<Variable,FOL_Entity> theta) {

    	System.out.println("IN UNIFY");

    	for (int i = 0; i < z.length; i++) {

    		System.out.println("z[" + i + "] = " + z[i].toString());
    	}

    	System.out.println("z length = " + z.length);

    	System.out.println("j length = " + j.length);

    	for (int i = 0; i < j.length; i++) {

    		System.out.println("j[" + i + "] = " + j[i].toString());
    	}

    	if (theta == null) {

    		System.out.println("theta is null");
			return null; 
    	} else if (isEqualTo(z,j)) {

    		System.out.println("z is equal to j");
    		return theta;
    	} else if (z.length == 1 && j.length == 1 && z[0] instanceof Variable) {

    		System.out.println("IN");

			System.out.println("z is var, calling unify_var");

			return unify_var((Variable)z[0], j[0], theta);
    		
    		
    	} else if (z.length == 1 && j.length == 1 && j[0] instanceof Variable) {

    		System.out.println("IN2");

			System.out.println("j is var, calling unify_var");
			return unify_var((Variable) j[0], z[0], theta);
    		

    	} else if (z.length == 1 && j.length == 1 && z[0].compound && j[0].compound) {

    		System.out.println("IN3");
    		System.out.println("compound = " + z[0].compound + ", " + j[0].compound);

			System.out.println("both are compound");
			if (!z[0].value.equals(j[0].value)){

				System.out.println("buuuut, not unifyable... z = " + z[0].toString() + " || " + j[0].toString());
				return null; // (unify ops...)
			} 
			System.out.println("unifyable!!, calling unify for args...");
			return unify(z[0].args(), j[0].args(), theta);


			
		} else if (z.length > 1 && j.length > 1) {
			System.out.println("IN4");

			System.out.println("both are lists...");
			FOL_Entity[] first1 = new FOL_Entity[1];
			FOL_Entity[] first2 = new FOL_Entity[1];

			FOL_Entity[] rest1 = new FOL_Entity[z.length-1];
			FOL_Entity[] rest2 = new FOL_Entity[j.length-1];

			first1[0] = z[0];
			first2[0] = j[0];

			for (int i = 0; i < z.length-1; i++) {

				rest1[i] = z[i+1];
			}

			for (int i = 0; i < j.length-1; i++) {

				rest2[i] = j[i+1];
			}

			return unify(rest1, rest2, unify(first1,first2,theta));
	
    	}

    	System.out.println("they are fuck all...");

    	return null;
    }

    public static HashMap<Variable,FOL_Entity> unify_var(Variable b, FOL_Entity j, HashMap<Variable,FOL_Entity> theta) {

    	if (theta.containsKey(b)) {

    		FOL_Entity [] temp = new FOL_Entity[1];
    		FOL_Entity [] temp2 = new FOL_Entity[1];
    		temp[0] = theta.get(b);
    		temp2[0] = j;
    		return unify(temp, temp2, theta);

    	} else if (theta.containsKey(j)) {

    		FOL_Entity [] temp = new FOL_Entity[1];
    		FOL_Entity [] temp2 = new FOL_Entity[1];
    		temp[0] = b;
    		temp2[0] = theta.get(j);
    		return unify(temp, temp2, theta);

    	} else if (occur_Check(b, j)) {

    		return null;

    	} else {

    		System.out.println("Now we put b/j in theta...");
    		theta.put(b, j);
    		return theta;
    	}

    }

    public static boolean occur_Check(Variable b, FOL_Entity j) {

    	if (j.contains(b)) {

    		return true;
    	} else {

    		return false;
    	}
    }

    public static boolean isEqualTo(FOL_Entity[] z, FOL_Entity[] j) {

    	if (z.length != j.length) return false;

    	String zi, ji;

    	for (int i = 0; i < z.length; i++) {

    		zi = z[i].identify();
    		ji = j[i].identify();

    		if (!zi.equals(ji)) {

    			return false;
    		}

    		if (zi.equals("Variable")) {

    			if (!((Variable)z[i]).equalsTo((Variable)j[i])) return false;

    		} else if (zi.equals("Constant")) {

    			if (!((Constant)z[i]).equalsTo((Constant)j[i])) return false;

    		} else if (zi.equals("Function")) {

    			if (!((Function)z[i]).equalsTo((Function)j[i])) return false;

    		} else {

    			if (!((FOL_Type)z[i]).equalsTo((FOL_Type)j[i])) return false;
    		}

    		
    	}

    	return true;
    }

    public static boolean PL_Resolution(ArrayList<String[]> KB, Type a) {

    	// Here we build our arraylist containing all of the given clauses
    	ArrayList<Clause> clauses = new ArrayList<>();
    	Type t;
    	Type[] ta;
    	Clause temp;

    	for (int i = 0; i < KB.size(); i++) {

    		ta = new Type[KB.get(i).length];
    		for (int j = 0; j < KB.get(i).length; j++) {

    			t = new Type(KB.get(i)[j]);
    			ta[j] = t;
			}
			temp = new Clause(ta);
			clauses.add(temp);
    	}

    	clauses.add(new Clause(a)); // we add a to the list...

    	// We print the intitial clauses
    	System.out.println("Initial clauses: ");
    	for (int i = 0; i < clauses.size(); i++) {

    		clauses.get(i).print();
    	}

    	ArrayList<Clause> newc = new ArrayList<>();
    	Clause resolvents;

    	while (true) {

    		for (int i = 0; i < clauses.size()-1; i++) {

    			for (int j = i+1; j < clauses.size(); j++) {
    				
    				resolvents = clauses.get(i).mate(clauses.get(j));
    				if (resolvents != null) { // resolvents == null, otan den mporoun na kanoun mate...

    					if (resolvents.isEmpty()) return true;
    					newc.add(resolvents);
    				}
    			}
    			
    		}
    		System.out.println("--- New clauses: ---");
    		if (!add_All(clauses, newc)) return false;
    		
    	}
    	
    }

  	public static boolean add_All(ArrayList<Clause> clauses, ArrayList<Clause> newc) {

    	
    	int initial_size = clauses.size();
    	int count = 0;

    	for (int i = 0; i < newc.size(); i++) {

    		if (!contains(clauses, newc.get(i))) {

    			newc.get(i).print();
    			clauses.add(newc.get(i));
    			count++;
    		}
    	}

    	if (initial_size == clauses.size()) {

    		return false;
    	} else {

    		return true;
    	}
    }

    public static boolean contains(ArrayList<Clause> clauses, Clause new_clause) {

    	for (int i = 0; i < clauses.size(); i++) {

    		if (clauses.get(i).equalsTo(new_clause)) return true;	
		}

		return false;
    }

    public static  ArrayList<String> read_input_from_file(String name, int mode) {

    	 ArrayList<String> inputList = new ArrayList<>(); // This list contains input from the KnowledgeBase.txt and represents our Knoledge Base...
		
		// We read the input (inputList initialization)

        try(BufferedReader br = new BufferedReader(new FileReader(name))){

        	if (mode == 1) {

        		br.readLine();
			
	            String Line = br.readLine();
	            String type;
	            int beg = 0, finish = 0;

	            while (Line != null) {
					
					if (Line.startsWith("//")) {

						Line = br.readLine();
					} else {

						for (int i = 0; i < Line.length(); i++){
							
							char temp = Line.charAt(i);

							if (temp == '(') {

								beg = i;
							}

							if (temp == ')') {

								finish = i;

								if (beg < finish) {

									type = Line.substring(beg+1, finish);
									type = type.replaceAll("\\s+","");
									inputList.add(type);
								}	
							}
						}

						Line = br.readLine();
					}
	                
	            }

	            return inputList;

        	} else if (mode == 2) {

        		ArrayList<String> DefiniteClauses = new ArrayList<String>();
	            String line;

	            while ((line = br.readLine()) != null){

	                DefiniteClauses.add(line);
	            }

	            return DefiniteClauses;

        	} else if (mode == 3) {

        		ArrayList<String> FOLClauses = new ArrayList<String>();
        		String line = br.readLine();
        		String [] temparray;
        		String temp = "";
        		int q = 0;

	            while (line != null) {

	            	//System.out.println(line);
					
					if (line.startsWith("//")) {

						line = br.readLine();
					} else {

						temp = temp + line.replaceAll("\\s+","");
						
						line = br.readLine();
					}
	                
	            }

	            temparray = temp.split("/");
	            for (int i = 0; i < temparray.length; i++) {

	            	//System.out.println(temparray[i]);
	            	FOLClauses.add(temparray[i]);
	            }

	            return FOLClauses;

        	}

        	return null;
            

        }catch (IOException ex){
		
            System.out.println("ERROR - Code: 1");
            return null;
        }
    }
}