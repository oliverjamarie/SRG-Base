package Answer;
public class Text extends Answer {
	private String answerTxt;
	
	private Text (String nameIN) {
		super(nameIN);
	}
	
	public Text (String nameIN, String answerTxt) {
		super(nameIN);
		this.answerTxt = new String(answerTxt);
	}
	
	public void setAnswerTxt(String answer) {
		answerTxt = new String(answer);
	}
	
	@Override
	public String toString() {
		
		/*return "<Answer name= \""+super.getName()+"\">"
				+ "\t<TextValue>"+answerTxt+"</TextValue></Answer>";*/
		
		return super.getName()+"&arr[]="+answerTxt;
	}
	
	public String getAnswer() {
		return answerTxt;
	}
	
}
