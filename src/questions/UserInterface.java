package questions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class UserInterface extends JFrame {
	
	private JPanel panelForButtons;
	private JPanel panelForQuestion;
	private JLabel lblQuestion;
	private JLabel lblAnswer;
	private JButton btnNext;
	private JButton btnReveal;
	private JButton btnExit;
	private Font font = new Font("Arial Black", Font.PLAIN, 20 );
	
	public UserInterface () {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(400, 100));
		
		mainPanel.setLayout(new GridLayout(2, 1));
		add(mainPanel);
		
		mainPanel.add(panelForQuestion = new JPanel());
		mainPanel.add(panelForButtons = new JPanel());
		
//		Questiona and answerpanel. 
		panelForQuestion.setLayout(new GridLayout(2 , 1));
		lblQuestion = new JLabel("Här kommer frågorna");
		lblQuestion.setBorder(new EtchedBorder());
		lblQuestion.setFont(font);
		lblAnswer = new JLabel ("Här kommer svaren");
		lblAnswer.setBorder(new EtchedBorder());
		lblAnswer.setFont(font);
		panelForQuestion.add(lblQuestion);
		panelForQuestion.add(lblAnswer);

//		Button Panel layout
		panelForButtons.setLayout(new FlowLayout());
		btnReveal = new JButton("Reveal");
		btnNext = new JButton("Next");
		btnExit = new JButton("Exit");
		panelForButtons.add(btnReveal);
		panelForButtons.add(btnNext);
		panelForButtons.add(btnExit);
		
		
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
	}
	

}
