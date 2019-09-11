import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//Priority for this program is to make it store background information on a project
public class Driver {


	public static void main (String[] args) throws IOException {
		PublishingManager manager = new PublishingManager ();
		//manager.publish();
		
		Date date = new Date();
		System.out.println(date);

		inputCommands();

		DialogManager dialog = new DialogManager();
		ArrayList<Dialog> variables = dialog.getDialogs();
	}

	private static void inputCommands() {
		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Input command ");
			String cmd = in.nextLine();
			if (cmd.equals("last modified")) {
				System.out.println("Running last modified\nWhat is the file name?");
				String filePath = "C:\\Users\\Oliver Marie\\Documents\\HotDocs\\"
						+ "Answers\\" + in.nextLine();

				File file = new File (filePath);





				if (file.lastModified() == 0) {
					System.out.println("Invalid File");
				}else {
					int 	countDays = 0,
							countHours = 0,
							countMin = 0;

					long lastModified = file.lastModified();
					Date date = new Date (lastModified);
					System.out.println(date.getDate());
					
					
				}
			}else if (cmd.equals("dir")) {
				System.out.println("Getting directory");
				String filePath = "C:\\Users\\Oliver Marie\\Documents\\HotDocs\\"
						+ "Answers";

				File folder = new File (filePath);
				for (File file : folder.listFiles()) {
					System.out.println(file.getName());
				}
			}else if (cmd.equals("help")) {
				System.out.println("Command List");
				System.out.println("last modified - How long has it been since"
						+ " the file has been modified?");
				System.out.println("dir - displays the files found in the default folder");
			}else if (cmd.equals("stop")){
				break;
			}else {
				System.out.println("Invalid Command");
			}
			System.out.println("\n\n\n");
		}
	}

}
