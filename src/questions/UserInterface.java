package questions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class UserInterface extends JFrame {
	
	private Asker asker;
	private JPanel panelForButtons;
	private JPanel panelForQuestion;
	private JLabel lblQuestion;
	private JLabel lblAnswer;
	private JButton btnStart;
	private JButton btnCorrect;
	private JButton btnWrong;
	private JButton btnReveal;
	private JButton btnExit;
	private Font font = new Font("Arial Black", Font.PLAIN, 16 );
	private String secretAnswer = "----------------------------------------------";
	private String correspondingAnswer;
	
	public UserInterface () {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(500, 100));
		
		mainPanel.setLayout(new GridLayout(2, 1));
		add(mainPanel);
		
		mainPanel.add(panelForQuestion = new JPanel());
		mainPanel.add(panelForButtons = new JPanel());
		
		asker = new Asker();
		
//		Questiona and answerpanel. 
		panelForQuestion.setLayout(new GridLayout(2 , 1));
		lblQuestion = new JLabel("Här kommer frågorna. Starta med \"start\"!");
		lblQuestion.setBorder(new EtchedBorder());
		lblQuestion.setFont(font);
		lblAnswer = new JLabel (secretAnswer);
		lblAnswer.setBorder(new EtchedBorder());
		lblAnswer.setFont(font);
		panelForQuestion.add(lblQuestion);
		panelForQuestion.add(lblAnswer);

//		Button Panel layout
		panelForButtons.setLayout(new FlowLayout());
		btnStart = new JButton("Start");
		btnReveal = new JButton("Reveal");
		btnCorrect = new JButton("Correct");
		btnWrong = new JButton("Wrong");
		
		btnExit = new JButton("Exit");
		panelForButtons.add(btnStart);
		panelForButtons.add(btnReveal);
		panelForButtons.add(btnCorrect);
		panelForButtons.add(btnWrong);
		panelForButtons.add(btnExit);
		
//		Addactions
		btnReveal.addActionListener(l);
		btnStart.addActionListener(l);
		btnCorrect.addActionListener(l);
		btnWrong.addActionListener(l);
		btnExit.addActionListener(l);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	ActionListener l = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == btnReveal) {
				lblAnswer.setText(correspondingAnswer);
				btnCorrect.setEnabled(true);
				btnWrong.setEnabled(true);
			} else if (e.getSource() == btnStart) {
				getNewQuestion();
			} else if (e.getSource() == btnCorrect) {
				asker.removeQuestion(lblQuestion.getText());
				getNewQuestion();
			} else if (e.getSource() == btnWrong) {
				getNewQuestion();
			} else if (e.getSource() == btnExit) {
				System.exit(0);
			}
			
			
		}
		
	};
	
	
	public void getNewQuestion () {
//		Method to get a new question (and answer).
		String question = asker.getQuestion();
		btnCorrect.setEnabled(false);
		btnWrong.setEnabled(false);
		
		if (question != null) { //No more questions
			correspondingAnswer = asker.getAnswer(question);
			lblQuestion.setText(question);
		} else {
			lblQuestion.setText("Frågorna är slut.");
		}
		lblAnswer.setText(secretAnswer); //To signify the answer is hidden.

		
	}
	

	

}
