package Answer;
public class Date extends Answer {
	private String date;

	public Date() {
		date = new String();
	}

	public Date(String nameIN) {
		super(nameIN);
		date = new String();
	}

	public Date (String nameIN, String dateIN) {
		super(nameIN);
		date = new String(dateIN);
	}

	@Override
	public String toString() {
		//return "DATE\t" + super.getName() + "\t" + date; 

		return super.getName() + "&arr[]=" + date;
	}

	public void setAnswer (int day, int month ,int year) {
		if (month <= 12 && day <= 31) {
			date = new String(month+"/"+date+"/"+ year);
		}
	}
	
	public String getDate() {
		return date;
	}

}
