package questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Asker {
	
	private Map<String, String> questionsAndAnswers;
	private List <String> questions;
	private String filePath = "C:\\Users\\ceder\\Documents\\Yrgo filer\\Programfiler\\Questions\\";
	private String [] file;
	private String [] options;
	private String lastQuestionAsked;
	
//	Counters
	private int questionsAsked = 0;
	private int correctAnswers = 0;
	private int counter = 0; //This counter is only used to mark problems while reading a questionfile. 

	
//	Konstruktor
	public Asker() throws FileNotFoundException {
		
		createMenu();
			
	}
	
	
	public void prepareQuestions (int index) throws NoSuchElementException {
		
		questionsAndAnswers = new HashMap<>();
		questions = new ArrayList<>();
		readFile(index);
		makeListOfQuestions();
		
	}
	
	public void createMenu () throws FileNotFoundException {
//		This method reads the text file and creates menu to allow for user to add subject outside IDE.
		Scanner fileScanner = null;
		List<String> tempMenu = new ArrayList<>(); 		//intermediary storage of files
		List<String> tempFilename = new ArrayList<>();	//intermediary storage of files
		
		
		fileScanner = new Scanner(new File(filePath + "menuoptions.txt"), "UTF-8");


		
		Scanner finder = null;
		 
		while (fileScanner.hasNextLine()) {
			String row = fileScanner.nextLine();
			finder = new Scanner(row).useDelimiter(":");
			tempMenu.add(finder.next());
			tempFilename.add(finder.next());	
		}
		fileScanner.close();
		finder.close();
		
		file = new String [tempMenu.size()]; 
		options = new String [tempFilename.size()];
		
		for (int i = 0 ; i < tempMenu.size(); i++ ) {
			options [i] = tempMenu.get(i);
			file [i] = tempFilename.get(i);
		}
		
		
	}
	
	
	private void readFile(int indexNo) throws NoSuchElementException {
		
//		The method read questions and answers from a textfile
		Scanner fileScanner = null;
		
		try {
			fileScanner = new Scanner(new File(filePath + file[indexNo]), "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return;
		}
		
		Scanner finder = null;
		 
		while (fileScanner.hasNextLine()) {
			String row = fileScanner.nextLine();
			finder = new Scanner(row).useDelimiter(":");
			++counter;
			questionsAndAnswers.put(finder.next(), finder.next());
			
			
		}
		fileScanner.close();
		finder.close();
		
	}
	
	
	private void makeListOfQuestions () {
//		Creates an ArrayList of the questions to be able to remove correctly answered questions.
		
		for (Map.Entry<String, String> entry : questionsAndAnswers.entrySet()) {
			questions.add(entry.getKey());

		}

	}
	
	public String getQuestion() {
		
		questionsAsked++; //Increments the counter of questions asked
		
		if (!questions.isEmpty()) {
			Random random = new Random();
			lastQuestionAsked = questions.get(random.nextInt(questions.size()));
			return lastQuestionAsked;
		}
		
		return null;
	}
	
	public String getAnswer (String key) {
		
		return questionsAndAnswers.get(key);
		
	}
	
	public void removeQuestion () {
		
		correctAnswers++; //Questions are only removed if answered correctly
		questions.remove(lastQuestionAsked);
		
	}
	
	public int getQuestionsAsked () {
		return questionsAsked;
	}
	
	public int getCorrectAnswers () {
		return correctAnswers;
	}
	
	public void resetQuestionsAsked () {
		questionsAsked = 0;
	}
	
	public void resetCorrectAnswers () {
		correctAnswers = 0;
	}
	
	public String [] getOptions () {
		return options;
	}
	
	public int getCounter() {
		return counter;
	}
	

}
