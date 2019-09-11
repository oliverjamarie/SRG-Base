import java.io.IOException;
import java.util.ArrayList;

public class PublishingManager {
	private FileManager manager;
	public PublishingManager() {
		manager = new FileManager();
	}

	//Goes through the output of the Action method from the FileManager class
	public void publish() {
		ArrayList<String> files = manager.action();
		for (int i = 0; i < files.size(); i ++) {
			Interpreter interpret = new Interpreter(files.get(i));
			interpret.interpretCommands();
			i++;
			if (files.get(i).equals("upload")) {
				try {					
					Publisher.publishToWeb(interpret);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if (files.get(i).equals("update")) {
				Publisher.updateWebsite(interpret);
			}
		}
	}
	
	//Does the same as publish() but tries to do it in a different way
	public void publish2() {
		ArrayList<AnswerFile> files = manager.getFiles();
		
		for (AnswerFile file : files) {
			
		}
	}
}
