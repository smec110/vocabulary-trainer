package Project;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class GameProg {

	public static DecimalFormat twoDecPlcFormatter = new DecimalFormat("0.00");

	public static void main (String[] args) {
		//Instantiate scanner and player objects
		Scanner scan = new Scanner(System.in);
		Player thePlayer = null;
		int numberOfPlayersRegistered = 0;

		//Instantiate ArrayList of Player and Question objects
		ArrayList <Player> registeredPlayers = new ArrayList<Player>();
		ArrayList<Question> questionList = new ArrayList<Question>();
		ArrayList <Question> selectedQuestionsList = new ArrayList<Question>();  

		//Start Up Phase                              
		try {            
			//Construct ArrayList of Questions from mini_quiz.csv file
			questionList = QuestionFileController.readQuestions("mini_quiz.csv");
			//Select 5 questions from questionList
			selectedQuestionsList = selectSetOfQuestions(questionList);
			//Construct ArrayList of Players from PlayerData.txt file
			registeredPlayers = FileController.readplayerinfo("PlayerData.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Initialize selection
		char selection = 'a';
		//While loop to display menu until player enters q to quit
		while (selection != 'q') {

			System.out.println("\tWelcome to the Word Game");
			System.out.println("\t\tRegister (R) ");
			System.out.println("\t\tLogin (L) ");            
			System.out.println("\t\tAbout (A) ");
			System.out.println("\t\tPlay the Game (P) ");
			System.out.println("\t\tShow the Leader Board (B)");
			System.out.println("\t\tQuit (Q)\n");
			System.out.print("\t\tPlease  choose an option: ");

			selection = scan.next().toLowerCase().charAt(0);
			switch (selection)
			{
			case 'r':
				numberOfPlayersRegistered = register(registeredPlayers, numberOfPlayersRegistered);
				break;
			case 'l':
				thePlayer = login(thePlayer, registeredPlayers,numberOfPlayersRegistered);
				break;                    
			case 'a':
				about();
				break;
			case 'p':
				play(selectedQuestionsList, thePlayer);
				break;
			case 'b':
				showLeaderBoard(registeredPlayers);
				break;
				//Q terminates the application
			case 'q':
				quit(registeredPlayers);
				break;            
			}
		}
	}

	//Create new player object and add it to the ArrayList of registeredPlayers
	public static int register (ArrayList<Player> theRegisteredPlayers, int numberOfPlayersRegistered) {          
		//Collect Registration information (username, password, first name, last name)
		Player aPlayer = new Player();
		inputDetailsRegistration(aPlayer);

		// Check whether the player is already registered
		boolean registered = checkRegistered(aPlayer, theRegisteredPlayers, numberOfPlayersRegistered);

		if (registered) {
			System.out.print("\n\tYou have already registered.");
		}
		else {
			theRegisteredPlayers.add(aPlayer);
			numberOfPlayersRegistered++;
		}             
		return numberOfPlayersRegistered;
	}

	//Validate entered username and password
	public static Player login (Player thePlayer, ArrayList<Player> theRegisteredPlayers,int numberOfPlayersRegistered) {

		//Player cannot login if no one has registered before, PlayerData.txt contains only dummy 
		if (theRegisteredPlayers.size() == 0) {
			System.out.println("\nYou must register before you login.");
		}

		//Check if player is already logged in
		else if (thePlayer == null) {
			//Get username and password
			Player aPlayer = new Player();
			inputDetails(aPlayer);   

			boolean registered = checkRegistered(aPlayer, theRegisteredPlayers, numberOfPlayersRegistered);

			if (!registered) {
				System.out.print("\t\tYou haven't registered yet, please register.");
			}
			else {
				//Get index of and return logged in Player object
				int index = theRegisteredPlayers.indexOf(aPlayer);
				thePlayer = theRegisteredPlayers.get(index);
			}
		}
		else {  
			System.out.println(thePlayer.toString() + ", has already logged in.");
		}    
		return thePlayer;
	}

	//Print 10 randomly selected questions
	public static void play(ArrayList<Question> selectedQuestions, Player p) {
		Scanner scan = new Scanner(System.in);
		//Initialise variables
		int correctNum = 0;
		int skipNum = 0;
		int wrongNum = 0;
		//For loop to print questions on at a time
		for (Question q : selectedQuestions) {
			System.out.println(q);
			boolean optionChoice = true;
			System.out.print("\n\nPlease choose an option: ");
			//While loop till player's choice is 1,2,3,4,5
			while(optionChoice) {
				int option = scan.nextInt();
				//If answer is correct, increase correctNum
				if(option == 4) {
					correctNum++;
					System.out.println("Correct!\n");
					optionChoice = false;
				}
				//If answer is skip, increase skipNum
				else if(option == 5) {
					skipNum++;
					System.out.println("Skipped!\n");
					optionChoice = false;
				}
				//If answer is wrong, increase wrongNum
				else if(option == 1 || option == 2 || option == 3) {
					wrongNum++;
					System.out.println("Wrong!\n");
					optionChoice = false;
				}
				//Player has to enter an option again
				else {
					System.out.println("Try again!\nPlease choose an option: ");
					optionChoice = true;
				}
			}
			//Display solution
			System.out.println("\t"+q.question+" means "+q.answer+"\n");
			//Print feedback after each question
			feedback(correctNum, wrongNum, skipNum);
			scan.next();
		}
		System.out.println("Game over!\n\n");
		//Print final feedback
		feedback(correctNum, wrongNum, skipNum);
		scan.next();
		//Get current score
		int currentScore = p.getScore();
		//Calculating new score by adding correctNum to current score
		int newScore = currentScore + correctNum;
		//Update currentScore with newScore
		p.setScore(newScore);
		int currentGameNumber = p.getGameNum();
		//Update currentGameNumber by adding 1 to it
		p.setGameNum(currentGameNumber+1);
		System.out.print("\nPress any key to continue: ");
		char anything = scan.next().charAt(0); 
	}

	//Display instructions for the game
	public static void about() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Instructions\n"
				+ "\nYou will play the game for 10 rounds. Each round you will be given a \"new word\" and a choice of four other words."
				+ "\nPlease chose the word from this list that you believe matches the \"new word\" most accurately by typing in the corresponding number."
				+ "\nAfter ten rounds you will be given a score calculated by the percentage of correct word choices."
				+ "\nYou will be able to compare your overall score to that of other players on the Leader Board at the end of the game."
				+ "\nGood luck! \n");
		System.out.print("Press any character to go back to the main menu. ");
		char anything = scan.next().charAt(0);
	}

	//Terminate the application
	public static void showLeaderBoard(ArrayList<Player> registeredPlayers) {        
		Scanner scan = new Scanner(System.in);
		System.out.print("\n\t\tLeader Board");
		System.out.print("\n\t\tShowing percentage of questions correct\n\n");
		Collections.sort(registeredPlayers);
		//Loops through sorted array
		for (Player p : registeredPlayers) {
			//Calculate percentage and print with name
			int percentage = p.score*10/p.gameNum;
			System.out.print("\t\t");
			System.out.print(p.fname + " " + p.lname + ": ");
			System.out.print(twoDecPlcFormatter.format(percentage) + " %");
			System.out.println();
		}
		System.out.print("\n\t\tPress any key to continue: ");
		char anything = scan.next().charAt(0); 
	}

	//Write the player information to the file and terminate the application
	public static void quit (ArrayList<Player> theRegisteredPlayers) {
		try {
			FileController.savePlayerInfo("PlayerData.txt", theRegisteredPlayers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	//This is for registration where the player also inputs his first and last name
	private static void inputDetailsRegistration(Player aPlayer) {
		Scanner scan = new Scanner(System.in);

		System.out.print("\t\tEnter your first name: ");
		aPlayer.setFirstName(scan.nextLine());

		System.out.print("\t\tEnter your last name: ");
		aPlayer.setLastName(scan.nextLine());

		System.out.print("\t\tCreate a username: ");
		aPlayer.setName(scan.nextLine());

		System.out.print("\t\tCreate a password: ");
		aPlayer.setPassword(scan.nextLine());

		//Set score and number of games played 0
		aPlayer.setScore(0);
		aPlayer.setGameNum(0);
	}

	//This is for login where the player is only asked for his username and password
	private static void inputDetails(Player aPlayer) {
		Scanner scan = new Scanner(System.in);

		System.out.print("\t\tEnter your username: ");
		aPlayer.setName(scan.nextLine());
		System.out.print("\t\tEnter your password: ");
		aPlayer.setPassword(scan.nextLine());
	}

	//Boolean to check if a player has registered
	private static boolean checkRegistered(Player aPlayer, ArrayList<Player> theRegisteredPlayers, int numberOfPlayersRegistered) {

		return theRegisteredPlayers.contains(aPlayer); 
	}

	//Return an ArrayList of question objects
	public static ArrayList<Question> selectSetOfQuestions(ArrayList<Question> a) {
		ArrayList<Question> selectedList = new ArrayList<Question>();
		for(int i = 0; i<10; i++) {
			final int MAX = a.size();
			final int MIN = 0;
			int index= ((int)(Math.random() * (MAX + 1 - MIN))) + MIN;
			Question b = a.get(index);
			selectedList.add(b);
		} 
		return selectedList;
	}

	public static void feedback(int correct, int wrong, int skip) {
		int total = correct + wrong + skip;
		System.out.print("Questions answered: "+total+
				"/10\nQuestions skipped: "+skip+
				"/10\n\nCurrent score: "+correct+
				"/10\n\nPress any key to continue: ");	
	}
}