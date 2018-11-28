package questions;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserInterfaceWIP extends JFrame {
	
	/* 	This is an experimental Class under the branch alternativeUI
	 * 
	 */
	
	private Asker asker;
	
	private JLabel lblCorrect;
	private JLabel lblAsked;
	private JTextArea txaQandA;
	private JButton btnReveal;
	private JButton btnCorrect;
	private JButton btnWrong;
	private JButton btnExit;
	private JComboBox jcbChoose;
	
	private JPanel panelOne;
	private JPanel panelTwo;
	private JPanel panelThree;
	
	
	
	private Font fontS = new Font("Arial Bold", Font.PLAIN, 16);
	private Font fontL = new Font("Arial Bold", Font.PLAIN, 20);
	
	
//	Constructor
	public UserInterfaceWIP () {
		
//		Instantiate Asker
		asker = new Asker();
		
		setLayout(new GridLayout(3, 1));//Three rows one column. 
		
//		First panel (for first "row" in gridlayout).
		panelOne = new JPanel();
		lblCorrect = new JLabel();	lblAsked = new JLabel();
		updateScore();
		lblCorrect.setFont(fontL);lblAsked.setFont(fontL);
		
		panelOne.add(lblCorrect);
		panelOne.add(lblAsked);
		
//		Second panel (for second "row" in gridlayout).
		panelTwo = new JPanel();
		txaQandA = new JTextArea(4,40);
		txaQandA.setText("Choose a topic to begin");
		txaQandA.setFont(fontS);
		panelTwo.add(txaQandA);
		
//		Third panel (for second "row" in gridlayout).
		panelThree = new JPanel();
		panelThree.add(jcbChoose = new JComboBox(asker.getOptions()));
		panelThree.add(btnReveal = new JButton("Reveal"));
		panelThree.add(btnCorrect = new JButton("Correct"));
		panelThree.add(btnWrong = new JButton("Wrong"));
		panelThree.add(btnExit = new JButton("Exit"));
		
//		Add listeners
//		btnCorrect.addActionListener(l);
//		btnWrong.addActionListener(l);
//		btnExit.addActionListener(l);
		btnReveal.addActionListener(e -> revealAnswer());
		jcbChoose.addActionListener(e -> startNewRound());
		
//		Set up buttons (enabled not enabled)
		btnCorrect.setEnabled(false);
		btnWrong.setEnabled(false);
		
		
//		Add panels to frame;
		add(panelOne); add(panelTwo); add(panelThree);
		
		
//		Pack and set visible. 
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
//	The user has chosen a subject in the choose menu.
	public void startNewRound () {
		
//		Set the questions. 
		asker.prepareQuestions(jcbChoose.getSelectedIndex());
		
//		Present first question in window. 
		txaQandA.setText(asker.getQuestion());
		
//		Enable buttons
		btnCorrect.setEnabled(true);
		btnWrong.setEnabled(true);
		
	}
	
	public void correctAnswer () {
		
		
		
	}
	
	public void revealAnswer () {
		
		String question = txaQandA.getText();
		txaQandA.append("\n----------------------------------------------\n");
		txaQandA.append(asker.getAnswer(question));
		
		btnCorrect.setEnabled(true);
		btnWrong.setEnabled(true);
		
		updateScore();
		
		
		
	}
	
	public void wrongAnswer () {
		
	}
	
	private void updateScore () {
		
		lblCorrect.setText(String.format("Correct: %d", asker.getCorrectAnswers()));
		lblAsked.setText(String.format("Asked: %d", asker.getQuestionsAsked()));
		
	}
 	
	
	
	
	
	

}
