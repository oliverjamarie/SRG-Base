package Answer;
public class TrueFalse extends Answer {
	private boolean value;
	
	private TrueFalse (String nameIN) {
		super (nameIN);
	}
	
	public TrueFalse (String nameIN, boolean value) {
		super (nameIN);
		this.value = value;
	}
	
	@Override
	public String toString() {
		//return "TRUE/FALSE\t" + super. getName() + "\t" + value;
		
		return super.getName()+"&arr[]="+value;
	}
	
	public boolean getValue() {
		return value;
	}
}
