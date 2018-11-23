package questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Asker {
	
	private Map<String, String> questionsAndAnswers;
	private String filePath = "src\\questions\\qanda.txt";
	
//	Konstruktor
	public Asker() {
		
		
	}
	
	public void readFile() {
		
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
			System.out.println(finder.next());
			System.out.println(finder.next());
			
		}
		
		finder.close();
		
	}
	

}
