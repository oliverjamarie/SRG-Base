package Answer;
import java.lang.*;

public class Number extends Answer {
	private double num;


	public Number(String nameIN) {
		super(nameIN);
	}

	public Number (String nameIN, double numIN) {
		super (nameIN);
		num = numIN;
	}

	public Number (String nameIN, String numIN) {
		super(nameIN);
		
		num = Double.parseDouble(numIN);
	}

	public double getValue() {
		return num;
	}

	public void setValue(double in) {
		num = in;
	}

	@Override
	public String toString() {
		//return "NUMBER\t" + super.getName() + "\t" + num;
		/*return "<Answer name=\"" +super.getName()+"\"> <TextValue>"
				+ num + "</TextValue></Answer>";*/
		
		return super.getName()+"&arr[]="+num;
	}
	
	public void setAnswer(double num) {
		this.num = num;
	}
	
	public void setAnswer(String num) {
		this.num = Double.parseDouble(num);
	}
	
	public double getNum() {
		return num;
	}

}
