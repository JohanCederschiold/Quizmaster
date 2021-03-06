package questions;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserInterface extends JFrame {
	
	/* 	This is an experimental Class under the branch alternativeUI
	 * 
	 */
	
	private Asker asker;
	
//	Components. 
	private JLabel lblCorrect;
	private JLabel lblAsked;
	private JTextArea txaQandA;
	private JButton btnReveal;
	private JButton btnCorrect;
	private JButton btnWrong;
	private JButton btnExit;
	private JComboBox <String> jcbChoose;
	
//	Panels
	private JPanel panelOne;
	private JPanel panelTwo;
	private JPanel panelThree;
	
//	Fonts
	private Font fontS = new Font("Arial Bold", Font.PLAIN, 16);
	private Font fontL = new Font("Arial Bold", Font.PLAIN, 20);
	
	
//	Constructor
	public UserInterface () {
		
//		Instantiate Asker
		try {
			asker = new Asker();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Couldn't find menuoptions.txt. Please create a file called \"menuoptions.txt\".\n "
					+ "In the file please place items according to \"<your_chosen_name>:nameoffile.txt\". "
					+ "Create menufile and restart program.");
		} 
		
		
		setLayout(new BorderLayout());//Three rows one column. 
		
//		First panel (for first "row" in gridlayout).
		panelOne = new JPanel();
		lblCorrect = new JLabel();	lblAsked = new JLabel();
		updateScore();
		lblCorrect.setFont(fontL);lblAsked.setFont(fontL);
		
		panelOne.add(lblCorrect);
		panelOne.add(lblAsked);
		
//		Second panel (for second "row" in gridlayout).
		panelTwo = new JPanel();
		txaQandA = new JTextArea(6,60);
		txaQandA.setText("Choose a topic to begin");
		txaQandA.setFont(fontS);
		panelTwo.add(txaQandA);
		
//		Set textarea to automatically do linebreaks. 
		txaQandA.setLineWrap(true); //Line break lines in string
		txaQandA.setWrapStyleWord(true); //Do linebreak with whole world. 
		
		
		
//		Third panel (for second "row" in gridlayout).
		panelThree = new JPanel();
		panelThree.add(jcbChoose = new JComboBox(asker.getOptions()));
		panelThree.add(btnReveal = new JButton("Reveal"));
		panelThree.add(btnCorrect = new JButton("Correct"));
		panelThree.add(btnWrong = new JButton("Wrong"));
		panelThree.add(btnExit = new JButton("Exit"));
		
//		Add listeners
		btnCorrect.addActionListener(f -> correctAnswer());
		btnWrong.addActionListener(f -> wrongAnswer());
		btnExit.addActionListener( f -> endProgram());
		btnReveal.addActionListener(f -> revealAnswer());
		jcbChoose.addActionListener(f -> startNewRound());
		
//		Set up buttons (enabled not enabled)
		btnCorrect.setEnabled(false);
		btnWrong.setEnabled(false);
		btnReveal.setEnabled(false);
		
//		Add panels to frame;
		add(panelOne, BorderLayout.NORTH); add(panelTwo, BorderLayout.CENTER); add(panelThree, BorderLayout.SOUTH);
		
		
//		Pack and set visible. 
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
	}
	
	
//	The user has chosen a subject in the choose menu.
	public void startNewRound () {
		
		
//		Set the questions. 
		try {
			asker.prepareQuestions(jcbChoose.getSelectedIndex());
			
//			Present first question in window. 
			getNextQuestion();
			
		} catch (NoSuchElementException nse) {
			txaQandA.setText(String.format("There is a problem reading the requested file. This is most likely due to one, or several,"
					+ "questions having the wrong format. Please check the file with questions. The problem should be"
					+ " somewhere around row %d.", asker.getCounter()));
		}
		
		
//		Reset score;
		asker.resetCorrectAnswers();
		asker.resetQuestionsAsked();
		
//		Enable revealbutton
		btnReveal.setEnabled(true);
		

		
		
	}
	
	public void correctAnswer () {
		
		asker.removeQuestion();
//		All else is the same procedures as with wrong answers
		wrongAnswer();
		
		
		
	}
	
	public void revealAnswer () {
		
		String question = txaQandA.getText();
		txaQandA.append("\n----------------------------------------------\n");
		txaQandA.append(asker.getAnswer(question));
		
		btnCorrect.setEnabled(true);
		btnWrong.setEnabled(true);
		btnReveal.setEnabled(false);
		
		updateScore();
		
		
		
	}
	
	public void wrongAnswer () {
		
		getNextQuestion();
		btnCorrect.setEnabled(false);
		btnWrong.setEnabled(false);
		updateScore();
		
		
	}
	
	private void updateScore () {
		
		lblCorrect.setText(String.format("Correct: %d", asker.getCorrectAnswers()));
		lblAsked.setText(String.format("Asked: %d", asker.getQuestionsAsked()));
		
	}
	
	private void endProgram ( ) {
		
		int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Quit?", JOptionPane.OK_CANCEL_OPTION);
		
		if (confirm == 0 ) {
			System.exit(0);
		}
		
	}
	
	private void getNextQuestion() {
		
		String nextQuestion = asker.getQuestion();
		
		if (nextQuestion != null) {
			txaQandA.setText(nextQuestion);
			btnReveal.setEnabled(true);
		} else {
			txaQandA.setText("End of questions\nAnd the world?");
			btnReveal.setEnabled(false);
		}
		
		
	}
 	


}
