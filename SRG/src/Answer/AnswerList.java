package Answer;
import java.util.ArrayList;

public class AnswerList {
	public static ArrayList<Answer> answers;

	public AnswerList() {
		answers = new ArrayList<Answer>();
	}
	
	//MUTIPLE CHOICE VARIABLES ARE SPECIAL CASES
	public void addAnswer(String type, String name, String content) {
		Answer answer = new Answer();
		
		if (type.equals("TextValue")) {
			answer = new Text(name,content);
		}else if (type.equals("TFValue")) {
			boolean value = content.equals("true");
			answer = new TrueFalse(name,value);
		}else if (type.equals("NumValue")){
			answer = new Number(name,content);
		}else if (type.equals("DateValue")) {
			answer = new Date(name,content);
		}else if (type.equals("MCValue")) {
			answer = new MultipleChoice(name,content);
		}
		
		
		
		answers.add(answer);
	}
	
	public ArrayList<Answer> getAnswers(){
		return answers;
	}
	
	public String toString() {
		StringBuffer msg = new StringBuffer();
		
		for (Answer answer : answers) {
			msg.append(answer.toString() + "\n");
		}
		
		return msg.toString();
	}

}
