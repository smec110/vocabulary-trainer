package Project;

public class Question {

	String question;
	String option1;
	String option2;
	String option3;
	String answer;
	
	//Constructor for Question class, set the member variables
	public Question(String question, String option1, String option2, String option3, String answer) {
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.answer = answer;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String theQuestion) {
		question = theQuestion;
	}

	public String getOption1() {
		return this.option1;
	}

	public void setOption1(String theOption1) {
		answer = theOption1;
	}

	public String getOption2() {
		return this.option2;
	}

	public void setOption2(String theOption2) {
		answer = theOption2;
	}

	public String getOption3() {
		return this.option3;
	}

	public void setOption3(String theOption3) {
		answer = theOption3;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String theAnswer) {
		answer = theAnswer;
	}
	
	//Output string to print details of question object
	public String toString()
	{
		return "\nNew word: " + question + "\n1. " + option1 + "\n2. " + option2 + "\n3. " + option3+ "\n4. " + answer +"\n5. I'm not sure";
	}
}
