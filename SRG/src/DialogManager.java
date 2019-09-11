import java.io.IOException;
import java.util.ArrayList;

//Parses through component file to grab what variables are in a specified 
//dialog

public class DialogManager {
	private Reader read;
	private ArrayList<Dialog> dialogs;
	
	//Default component file is Facade Template (just for testing)
	public DialogManager() {
		dialogs = new ArrayList<Dialog>();
		try {
			read = new Reader ("C:\\Users\\Oliver Marie\\Documents\\HotDocs\\"
					+ "Templates\\Facade Template.cmp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Goes through the specified 
	public DialogManager(String filePath) {
		dialogs = new ArrayList<Dialog>();
		try {
			read = new Reader (filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Dialog> getDialogs(){
		ArrayList<String> inputs = read.getInputs();
		boolean found = false;
		int index = 0;
		Dialog temp = new Dialog();
		
		for (String msg : inputs) {
			if (found = true) {
				if (msg.contains("<hd:item name=\"")) {
					temp.addVariable(parseName(msg));
					index ++;
				}
			}
			
			if (msg.contains("<hd:dialog name=")) {
				found = true;
				temp = new Dialog(parseName(msg));
			}else if (msg.contains("</hd:dialog>")) {
				found = false;
				dialogs.add(temp);
				temp = new Dialog();
				index = 0;
			}
		}
		
		return dialogs;
	}
	
	private String parseName(String in) {
		StringBuffer buffer = new StringBuffer();
		boolean found = false;
		
		for (int i = 0; i < in.length(); i++) {
			if (found) {
				if(in.charAt(i) == '\"') {
					break;
				}else {
					buffer.append(in.charAt(i));
				}
			}else {
				if(in.charAt(i) == '\"') {
					found = true;
				}
			}
		}
		
		return buffer.toString();
	}

}
