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
	
	
	
	private JLabel lblCorrect;
	private JLabel lblAsked;
	private JTextArea txaQandA;
	private JButton btnCorrect;
	private JButton btnWrong;
	private JButton btnExit;
	private String [] temp = {"Hej", "Bulle"};
	private JComboBox jcbChoose = new JComboBox(temp);
	
	private JPanel panelOne;
	private JPanel panelTwo;
	private JPanel panelThree;
	
	
	
	private Font font = new Font("Arial Bold", Font.PLAIN, 16);
	
	
//	Constructor
	public UserInterfaceWIP () {
		
//		Instantiate Asker
		Asker asker = new Asker();
		
		setLayout(new GridLayout(3, 1));//Three rows one column. 
		
//		First panel (for first "row" in gridlayout).
		panelOne = new JPanel();
		lblCorrect.setText(String.format("Correct: %d", asker.getCorrectAnswers()));
		lblAsked.setText(String.format("Asked: %d", asker.getQuestionsAsked()));
		
		panelOne.add(lblCorrect);
		panelOne.add(lblAsked);
		
//		Second panel (for second "row" in gridlayout).
		panelTwo = new JPanel();
		txaQandA = new JTextArea(4,4);
		panelTwo.add(txaQandA);
		
//		Third panel (for second "row" in gridlayout).
		
		
//		Pack and set visible. 
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	
	
	
	
	
	

}
