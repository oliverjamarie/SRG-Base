
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.sql.*;

import Answer.Answer;
import Answer.MultipleChoice;


//Grabs data from Interpeter class and publishes it 

public class Publisher  {
	public static void publishXML(Interpreter interpret) throws IOException {
		String init = initTxt();
		ArrayList<Answer> answers =	interpret.answerList.getAnswers();
		PrintWriter output = null;

		try {
			output = new PrintWriter("DataFile.txt");
			System.out.println(initTxt());
			output.println(init);
			for (Answer answer : answers) {
				if (!answer.getName().equals("(ANSWER FILE HISTORY)")
						&& !(answer instanceof MultipleChoice)) {
					System.out.println(answer);
					//output.println(answer);
				}
			}
		} finally {
			output.append("</AnswerSet>");
			if (output != null) {
				output.close();
			}
		}
	}
	
	//Used in publishXML method
	private static String initTxt() {
		StringBuffer msg = new StringBuffer();

		msg.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"true\"?>\n");
		msg.append("<AnswerSet title=\"data\" version=\"1.1\">\n");



		return msg.toString();
	}

	//Publishes to server
	public static void publishToWeb(Interpreter interpret) throws IOException {
		try {
			URL url = new URL("http://localhost/Hotdocs/Upload%20To%20Database.php");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);


			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);

			StringBuffer data = new StringBuffer();

			data.append(URLEncoder.encode("Command", "UTF-8") + "=");

			ArrayList<Answer> list = interpret.answerList.getAnswers();
			int count = 0;

			for (Answer answer : list) {
				if (!(answer.getName().equals("(ANSWER_FILE_HISTORY)"))
						&& !(answer.getName().equals("(ANSWER_FILE_DESCRIPTION)"))
						&& !(answer instanceof MultipleChoice)) {
					if (count == 0) {
						data.append(URLEncoder.encode(
								"arr[]="+answer.toString(), "UTF-8"));
						count ++;
					}else {
						data.append(URLEncoder.encode("&" + 
								"arr[]="+answer.toString(), "UTF-8"));
					}
				}
			}

			out.println(data.toString());

			out.close();


			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							connection.getInputStream()));
			String decodedString;
			
			while ((decodedString = in.readLine()) != null) {
				System.out.println(decodedString);
			}
			in.close();
		} 
		catch (MalformedURLException e) { 
			e.printStackTrace();
		} 
		catch (IOException e) {   
			e.printStackTrace();
		}	

	}
	
	//If a file has been updated in the past day, this updates the values in the sql database
	public static void updateWebsite(Interpreter interpret) {
		try {
			URL url = new URL("http://localhost/Hotdocs/Update%20Database.php");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);

			StringBuffer data = new StringBuffer();

			data.append(URLEncoder.encode("Command", "UTF-8") + "=");

			ArrayList<Answer> list = interpret.answerList.getAnswers();
			int count = 0;

			for (Answer answer : list) {
				if (!(answer.getName().equals("(ANSWER_FILE_HISTORY)"))
						&& !(answer.getName().equals("(ANSWER_FILE_DESCRIPTION)"))
						&& !(answer instanceof MultipleChoice)) {
					if (count == 0) {
						data.append(URLEncoder.encode(
								"arr[]="+answer.toString(), "UTF-8"));
						count ++;
					}else {
						data.append(URLEncoder.encode("&" + 
								"arr[]="+answer.toString(), "UTF-8"));
					}
				}
			}

			out.println(data.toString());

			out.close();

			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							connection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				System.out.println(decodedString);
			}
			
			in.close();
		} 
		catch (MalformedURLException e) { 
			e.printStackTrace();
		} 
		catch (IOException e) {   
			e.printStackTrace();
		}	

	}

	//Publishes Directly To Database
	//NOT YET COMPLETE  (EXCEPTION THROWN THAT DRIVER CAN'T BE FOUND)
	public static void publishToDatabase(Interpreter interpret) throws IOException{
		String url = new String ("jdbc:mysql://localhost:3306");
		
		String user = "root";
		
		String pwd = "";
		
		try {
			//SET UP CONNECTION
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url,user,pwd);
			
			Statement state = con.createStatement();
			
			state.execute("CREATE DATABASE IF NOT EXSITS hotdocs1");
			state.execute("USE hotdocs1");
			System.out.println("Connection Succesful");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
