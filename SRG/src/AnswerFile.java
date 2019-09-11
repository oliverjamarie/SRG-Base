import java.io.File;
import java.util.Date;


public class AnswerFile {
	private boolean uploaded;
	private File file;
	private String name;

	public AnswerFile(String filePath) {
		file = new File(filePath);
		name = file.getName();
		uploaded = false;
	}

	public File getFile() {
		return file;
	}

	public String getName() {
		return file.getName();
	}

	public boolean getStatus() {
		return uploaded;
	}

	public boolean needsToUpdate() {
		return outOfDate() && uploaded;
	}

	private boolean outOfDate() {
		Date dateFile = new Date(file.lastModified()); //Date of when the file was last modified 
		Date dateToday = new Date(); // Today's date

		if (dateFile.getYear() == dateToday.getYear()) {
			if (dateFile.getMonth() == dateToday.getMonth()) {
				return dateToday.getDate() == dateFile.getDate()  + 1;
			}
			if (dateToday.getMonth() == dateFile.getMonth() - 1) {//Testing if the months 
				if (dateToday.getMonth() % 2 == 1) {
					return dateToday.getDate() == 31 &&
							dateFile.getDate() == 1;
				}else {
					if (dateToday.getMonth() == 2) {// Testing for month of February
						if (dateToday.getDate() == 29) {// Testing for leap year
							return dateFile.getDate() == 1;
						}
						if (dateToday.getDate() == 28) {
							return dateFile.getDate() == 1;
						}
					}else {
						return dateToday.getDate() == 30 &&
								dateFile.getDate() == 1;
					}
				}
			}
		}

		return false;
	}

	//sets uploaded to true
	public void upload() {
		uploaded = true;
	}
}


