import java.io.IOException;
import java.util.ArrayList;

import Answer.AnswerList;

public class Interpreter {
	private ArrayList <String> cmd;
	public AnswerList answerList;

	public Interpreter(String filePath) {
		answerList = new AnswerList();
		try {
			Reader fileReader = new Reader (filePath);
			cmd = fileReader.getInputs();
		} catch (IOException e) {
			System.out.println("File Not Found or cannot be read");
			e.printStackTrace();
		}
	}

	//Reads through file thanks to Reader's getInputs() method
	//Looks for variables and sends their info to AnswerList class
	//
	//MULTIPLE CHOICE ANSWERS ARE SPECIAL CASES
	public void interpretCommands () {
		boolean foundStart = false;
		StringBuffer answerTxt = new StringBuffer();
		String variableType = new String();
		String variableName = new String();
		
		// stores whether the end of a variable has been reached
		boolean foundStartVariableContent = false;

		for (String msg : cmd) {
			if (!foundStart) {
				if (msg.contains("<Answer ")) {
					//Get Variable name
					foundStart = true;
					variableName= new String(getVariableName(msg));
				}
			}else {
				//Handle Variable's content

				if (getVariableType(msg) != null) {
					variableType = new String(getVariableType(msg));
				}

				if (msg.contains("<" + variableType + ">") 
						&& msg.contains("</"+ variableType + ">")) {
					answerTxt.append(formatText(msg));
				}else {
					if (msg.contains("<" + variableType+">")) {
						foundStartVariableContent = true;
					}

					if (msg.contains("</" + variableType + ">")) {
						foundStartVariableContent = false;
					}

					if (foundStartVariableContent) {
						answerTxt.append(formatText(msg)+ "\n");
					}

					if (msg.contains("</Answer>")) {
						foundStart = false;
						variableName = formatText(variableName);
						answerList.addAnswer(variableType, variableName,
								answerTxt.toString());
						answerTxt = new StringBuffer();
					}
				}
			}
		}
	}

	private String getVariableType(String text) {
		if (text.contains("<TextValue>")) {
			return "TextValue";
		}else if (text.contains("<TFValue>")){
			return "TFValue";			
		}else if(text.contains("<MCValue>")) {
			return "MCValue";
		}else if (text.contains("<NumValue>")) {
			return "NumValue";
		}else if (text.contains("<DateValue>")) {
			return "DateValue";
		}

		return null;
	}

	//Used to remove elements such as < , >  or "
	private String formatText(String text) {
		StringBuffer answer = new StringBuffer();
		boolean foundContent = true;

		for (int i = 0; i < text.length(); i ++) {

			if (text.charAt(i)=='<') {
				foundContent = false;
			}

			if (foundContent && text.charAt(i) !='\t' 
					&& text.charAt(i) != '"') {
				answer.append(text.charAt(i));
			}

			if (text.charAt(i)=='>') {
				foundContent = true;
			}
		}
		return answer.toString();
	}

	//Used in method interpretCommands()
	private String getVariableName (String txt) {
		StringBuffer ans = new StringBuffer();
		boolean foundStart = false;


		for (int i = 0; i < txt.length(); i ++) {
			if (foundStart) {
				if (txt.charAt(i) == ' ') {
					ans.append("_");
				}else {
					ans.append(txt.charAt(i));
				}
			}

			if (txt.charAt(i)=='"') {
				if (!foundStart) {
					foundStart = true;
				}else {
					foundStart = false;
				}
			}
		}
		return ans.toString();
	}

}
