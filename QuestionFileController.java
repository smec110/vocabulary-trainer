package Project;
import java.io.*;
import java.util.*;

public class QuestionFileController {

	//Return ArrayList of question objects from file
	public static ArrayList<Question> readQuestions(String fileName) {
		//Instantiate ArrayList of Question objects
		ArrayList<Question> questions = new ArrayList<>();
		//Open file from which to read questions
		File file = new File(fileName);
		try {
			//Instantiate Scanner with file object
			Scanner inputStream = new Scanner(file);
			//Read file one line at a time until end of data
			while (inputStream.hasNextLine()) {
				//Split lines at comma and store in array lineComponents
				String questionLine = inputStream.nextLine();
				String[] lineComponents = questionLine.split(",");

				//Create question object
				if (lineComponents.length == 5) {

					String question = lineComponents[0];
					String option1 = lineComponents[1];
					String option2 = lineComponents[2];
					String option3 = lineComponents[3];
					String answer = lineComponents[4];

					Question q = new Question(question,option1,option2,option3,answer);
					//Add question object to the ArrayList
					questions.add(q);
				}
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return questions;
	}
}