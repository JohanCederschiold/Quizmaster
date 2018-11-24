package questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Asker {
	
	private Map<String, String> questionsAndAnswers;
	private List <String> questions;
	private String filePath = "src\\questions\\qanda.txt";
	
//	Konstruktor
	public Asker() {
		
		questionsAndAnswers = new HashMap<>();
		questions = new ArrayList<>();
		readFile();
		makeListOfQuestions();
		
		
	}
	
	private void readFile() {
		
//		The method read questions and answers from a textfile
		
		Scanner fileScanner = null;
		
		try {
			fileScanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return;
		}
		
		Scanner finder = null;
		 
		while (fileScanner.hasNextLine()) {
			String row = fileScanner.nextLine();
			finder = new Scanner(row).useDelimiter(":");
			questionsAndAnswers.put(finder.next(), finder.next());
			
			
		}
		
//		System.out.println(questionsAndAnswers.get("What are dancing "));
		finder.close();
		
	}
	
	
	private void makeListOfQuestions () {
//		Creates an ArrayList of the questions to be able to remove correctly answered questions.
		
		for (Map.Entry<String, String> entry : questionsAndAnswers.entrySet()) {
			questions.add(entry.getKey());

		}

	}
	
	public String getQuestion() {
		
		if (!questions.isEmpty()) {
			Random random = new Random();
			return questions.get(random.nextInt(questions.size()));
		}
		
		return null;
	}
	
	public String getAnswer (String key) {
		
		return questionsAndAnswers.get(key);
		
	}
	
	public void removeQuestion (String question) {
		
		questions.remove(question);
		
	}
	

}
