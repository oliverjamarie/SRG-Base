import java.util.ArrayList;

import Answer.Answer;

public class Dialog {
	private ArrayList<Answer> variables;
	private String dialogName;
	
	public Dialog () {
		variables = new ArrayList<Answer>();
		dialogName = new String ();
	}
	
	public Dialog (String dialogName) {
		variables = new ArrayList<Answer>();
		this.dialogName = new String (dialogName);
	}
	
	public void addVariable(String varName) {
		variables.add(new Answer(varName));
	}
	
	public void removeVariable (String varName) {
		variables.remove(varName);
	}
	
	public String getDialogName() {
		return dialogName;
	}
	
	public ArrayList<Answer> getVariables(){
		return variables;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(dialogName + "\n");
		
		for (Answer var : variables) {
			buffer.append("\t" + var.getName() + "\n");
		}
		
		buffer.append("\n");
		return buffer.toString();
	}
}
