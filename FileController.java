package Project;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FileController {

	//Return ArrayList of Player object from text file
	public static ArrayList<Player> readplayerinfo(String fileName) {
		//Instantiate ArrayList of Player objects
		ArrayList<Player> registeredPlayers = new ArrayList<>();
		try {
			//Instantiate FileWriter object
			FileReader fr = new FileReader(fileName);
			// Create BufferedReader from FileReader object
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			// Loop through data in BufferedReader to extract String data for ArrayList
			while((line=br.readLine()) != null) {
				//Instantiate Scanner with line data String
				Scanner s = new Scanner(line);
				//Read Player data as Strings
				String fname = s.next();
				String lname = s.next();
				String name = s.next();
				String password = s.next();
				Integer score = s.nextInt();
				Integer gameNum = s.nextInt();
				//Create Player object
				Player nextPlayer = new Player(fname, lname, name, password, score, gameNum);
				//Add player object to ArrayList of players
				registeredPlayers.add(nextPlayer);				
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return registeredPlayers;
	}

	//Write player information to a text file
	public static void savePlayerInfo(String fileName, ArrayList<Player> theRegisteredPlayers) throws Exception {
		//Initialize PrintWriter object
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter (new FileOutputStream (fileName));
			//Loop through theRegisteredPlayers ArrayList and write data of Player objects to a file
			for (Player p : theRegisteredPlayers) {
				outputStream.println(p);  
			}
			//Close outputStream
			outputStream.close();
		}catch (FileNotFoundException e) {
			e.getMessage();                   
		}
	}
}