import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
	private ArrayList<String> commands = new ArrayList<String>();
	private BufferedReader in;

	public Reader () throws IOException{
		in = new BufferedReader (new FileReader("///E:/Projects/Project-10/proj10/example-friend-data.html"));
	}

	public Reader (String filePath) throws IOException {
		in = new BufferedReader (new FileReader(filePath));
	}

	private void process() throws IOException{
		String s;

		while ((s= in.readLine()) != null) {
			commands.add(s);
		}
	}

	public ArrayList<String> getInputs(){
		try {
			process();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return commands;
	}

	public void closeReader() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
