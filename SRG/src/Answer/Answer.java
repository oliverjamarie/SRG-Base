package Answer;

import java.util.ArrayList;

/*Answer has child classes
 * 		Text
 * 		Number
 * 		Multiple Choice
 * 		True Or False
 * 		Date
 */	
public  class Answer {
	private String name;
	private ArrayList <String> variables;//List of valid variables for sql database
	
	public Answer () {
		name = new String();
		variables = new ArrayList<String>();
	}
	
	public Answer(String nameIN) {
		name = new String (nameIN);
		variables = new ArrayList<String>();
	}
	
	public String getName() {
		return new String (name);
	}
	
	public ArrayList<String> getLegalVariables() {
		return variables;
	}
	
	public void addLegalVariable(String variableName) {
		if (!variables.contains(variableName)) {
			variables.add(variableName);
		}
	}
	
	public String toString() {
		return "_";
	}
	
	public boolean equals(Object o) {
		if (o instanceof Answer) {
			return ((Answer) o).getName().equals(name);
		}
		return false;
	}
	
}
