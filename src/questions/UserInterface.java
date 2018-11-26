package questions;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JLabel lblStats;
	private JComboBox jcbChoose;
	private JButton btnStart;
	private JButton btnCorrect;
	private JButton btnWrong;
	private JButton btnReveal;
	private JButton btnExit;
	private Font font = new Font("Arial Black", Font.PLAIN, 16 );
	private String secretAnswer = "----------------------------------------------";
	private String correspondingAnswer;
	private String [] options = {"Databaser", "Git"};
	
	public UserInterface () {
		
		setSize(new Dimension(700, 100));
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(700, 100));
		
		mainPanel.setLayout(new GridLayout(2, 1));
		add(mainPanel);
		
		mainPanel.add(panelForQuestion = new JPanel());
		mainPanel.add(panelForButtons = new JPanel());
		
		asker = new Asker();
		asker.prepareQuestions(0);
		
//		Question and answerpanel. 
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
		lblStats = new JLabel(String.format("%d / %d", asker.getCorrectAnswers(), asker.getQuestionsAsked()));
		lblStats.setFont(font);
		btnStart = new JButton("Start");
		btnReveal = new JButton("Reveal");
		btnCorrect = new JButton("Correct");
		btnWrong = new JButton("Wrong");
		
		btnExit = new JButton("Exit");
		panelForButtons.add(jcbChoose = new JComboBox(options));
		panelForButtons.add(btnStart);
		panelForButtons.add(btnReveal); btnReveal.setEnabled(false);
		panelForButtons.add(btnCorrect);
		panelForButtons.add(btnWrong);
		panelForButtons.add(lblStats);
		panelForButtons.add(btnExit);
		
//		Addactions
		jcbChoose.addActionListener(l);
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
				btnReveal.setEnabled(false);
			} else if (e.getSource() == btnStart) {
				btnStart.setEnabled(false);
				asker.resetCorrectAnswers();
				asker.resetQuestionsAsked();
				getNewQuestion();
			} else if (e.getSource() == btnCorrect) {
				asker.removeQuestion(lblQuestion.getText());
				upDateScore();
				getNewQuestion();
			} else if (e.getSource() == btnWrong) {
				upDateScore();
				getNewQuestion();
			} else if (e.getSource() == btnExit) {
				System.exit(0);
			} else if (e.getSource() == jcbChoose) {
				btnStart.setEnabled(true);
				asker.prepareQuestions(jcbChoose.getSelectedIndex());
			}
			
			
			
		}
		
	};
	
	
	public void getNewQuestion () {
//		Method to get a new question (and answer).
		String question = asker.getQuestion();
		btnReveal.setEnabled(true);
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
	
	public void upDateScore() {
		lblStats.setText(String.format("%d / %d", asker.getCorrectAnswers(), asker.getQuestionsAsked()));
	}
	

	

}
