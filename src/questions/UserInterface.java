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
	private JButton btnNext;
	private JButton btnReveal;
	private JButton btnExit;
	private Font font = new Font("Arial Black", Font.PLAIN, 20 );
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
		lblQuestion = new JLabel("Här kommer frågorna. Starta med Next");
		lblQuestion.setBorder(new EtchedBorder());
		lblQuestion.setFont(font);
		lblAnswer = new JLabel (secretAnswer);
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
		
//		Addactions
		btnReveal.addActionListener(l);
		btnNext.addActionListener(l);
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
			} else if (e.getSource() == btnNext) {
				getNewQuestion();
			} else if (e.getSource() == btnExit) {
				System.exit(0);
			}
			
			
		}
		
	};
	
	
	public void getNewQuestion () {
		String question = asker.getQuestion();
		correspondingAnswer = asker.getAnswer(question);
		System.out.println(correspondingAnswer);
		lblQuestion.setText(question);
		lblAnswer.setText(secretAnswer);
		
	}
	

}
