package Project;

import java.util.Scanner;

//Represents a player
public class Player implements Comparable<Player>{

	public String name;
	public String fname;
	public String lname;
	public String password;
	public Integer score;
	public Integer gameNum;

	//Empty constructor for Player class
	public Player() {}

	//Constructor for Player class, set the member variables
	public Player(String fname, String lname, String name, String password, Integer score, Integer gameNum) {
		this.fname = fname;
		this.lname = lname;
		this.name = name;
		this.password = password;
		this.score = score;
		this.gameNum = gameNum;
	}

	//Return name of player
	public String getName() {
		return this.name;
	}

	//Set name of player
	public void setName(String theName) {
		name = theName;
	}

	//Return first name of player
	public String getFirstName() {
		return this.name;
	}

	//Set first name of player        
	public void setFirstName(String theFirstName) {
		fname = theFirstName;
	}

	//Return last name of player
	public String getLastName() {
		return this.lname;
	}

	//Set last name of player
	public void setLastName(String theLastName) {
		lname = theLastName;
	}

	//Return password of player
	public String getPassword() {
		return this.password;
	}

	//Return score of player
	public Integer getScore() {
		return this.score;
	}

	//Return number of game a player played
	public Integer getGameNum() {
		return this.gameNum;
	}

	//Set password of player
	public void setPassword(String thePassword) {
		password = thePassword;
	} 

	//Set score 0 when creating new Player object
	public void setScore(Integer theScore) {
		score = theScore;
	}    

	//Set number of games played 0 when creating new Player object
	public void setGameNum(Integer theGameNum) {
		gameNum = theGameNum;
	}    

	//Output string to print the details of the Player object
	@Override
	public String toString() {
		return fname + " " + lname + " " + name + " " + password+ " " + score + " " + gameNum;
	}  

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Player otherPlayer = (Player) otherObject;
		return (this.name.compareTo(otherPlayer.name) == 0);
	}
	
	//Sorts the ArrayList according to percentage scores
	@Override
	public int compareTo(Player other) {
		int thisP = this.score*10/this.gameNum;
        int otherP = other.score*10/other.gameNum;
        if (thisP == otherP)
            return 0;
        else 
            return thisP>otherP ? -1 : 1;
	}
}