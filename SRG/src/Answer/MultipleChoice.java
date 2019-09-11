package Answer;
import java.util.ArrayList;

//TEMPORARELY DISABLED TO ACCOMODATE FOR IT TO WORK WITH A DATABASE
//ITS EXISTENCE IS NOT VITAL FOR THE SCOPE OF THE PROJECT BUT WOULD BE NECESSARY
//IF SCOPE WERE GROW
//ONLY GIVES THE VARIABLE TYPE AND NAME
public class MultipleChoice extends Answer {
	
	private String answer;
	private ArrayList<String> answers;
	
	//exists to remove default constructor
	private  MultipleChoice() {	}

	

	public MultipleChoice (String nameIN, String textIN) {
		super(nameIN);
		answer = new String(textIN);
		answers = new ArrayList<String>();
		format();
	}
	
	private void format() {
		StringBuffer buffer = new StringBuffer();
		
		//Starts at index 1 because the first character of
		//answer is \n
		for (int i = 1; i < answer.length(); i ++) {
			if (answer.charAt(i) != '\n') {
				buffer.append(answer.charAt(i));
			}else {//End of line found
				answers.add(buffer.toString());
				buffer = new StringBuffer(); // Reset StringBuffer
			}
		}
	}


	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(super.getName());
		buffer.append("&arr[]=enum[] ");
		boolean isFirst = true;
		
		for (String msg : answers) {
			if (isFirst) {
				buffer.append(msg);
				isFirst = false;
			}else {
				buffer.append("&enum[] " + msg);
			}
		}
		return buffer.toString();		
	}

}
