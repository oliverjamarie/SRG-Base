import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {
	private ArrayList<AnswerFile> files;
	private ArrayList<AnswerFile> filesNotUploaded;
	private PrintWriter writer;

	public FileManager() {
		initialize("C:\\Users\\Oliver Marie\\Documents\\HotDocs\\Answers");
		if (files.size() == 0) {
			initialize("C:\\Users\\omarie\\Documents\\HotDocs\\Answers");
		}
	}

	//ONLY USED IN CONSTRUCTOR
	private void initialize(String filePath) {
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();

		files = new ArrayList<AnswerFile>();
		filesNotUploaded = new ArrayList<AnswerFile>();
		ArrayList<String> temp = getUploadedFiles();

		for (File file : listOfFiles) {
			if (file.getName().contains(".anx") &&
					!file.getName().contains(".xml")) {

				AnswerFile tempAnswer = new AnswerFile(file.getAbsolutePath());
				files.add(tempAnswer);

				if (temp.contains(file.getName())) {
					filesNotUploaded.add(tempAnswer);
				}
			}
		}
	}

	//Grabs all the file names that were saved in the text 
	//file "Uploaded Files.txt"
	private ArrayList<String> getUploadedFiles() {
		Reader read;
		try {
			read = new Reader ("Uploaded Files.txt");
			return read.getInputs();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String> ();
		}		
	}
	

	
	//Gets the AnswerFile from the the files ArrayList at the specified index
	public AnswerFile get(int index) {
		return files.get(index);
	}

	//Saves the names of the files that have been uploaded
	private void saveFiles() {
		try {
			ArrayList<String> uploadedFiles =  getUploadedFiles();

			writer = new PrintWriter("Uploaded Files.txt");
			for (AnswerFile file : files) {
				if (file.getStatus() && !uploadedFiles.contains(file)) {
					writer.println(file.getName());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//Tells the PublishingManager what it should do by returning an ArrayList
	//Even indices (except 0) refer to the file path
	//Odd indices refer to what the PublsihingManager should do (upload or update)
	public ArrayList<String> action() {
		ArrayList<String> result = new ArrayList<String>();

		
		for (AnswerFile file: files) {
			if (file.getStatus() == false ) {//File needs to be uploaded
				result.add(file.getFile().getAbsolutePath());
				result.add("upload");
				file.upload();
			} else if (file.needsToUpdate()) {//File needs to be updated
				result.add(file.getFile().getAbsolutePath());
				result.add("update");
			}
		}

		saveFiles();
		return result;
	}

	public ArrayList<AnswerFile> getFiles(){
		return files;
	}


}
