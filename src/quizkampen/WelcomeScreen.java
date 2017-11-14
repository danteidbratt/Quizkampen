package quizkampen;

import java.awt.GridLayout;
import javax.swing.*;

public class WelcomeScreen extends JPanel{
	private JLabel logo = new JLabel("QuizFights");
	private JTextField userNameInput = new JTextField("Enter username to start");
	private JButton ok = new JButton("OK");
	private JButton exit = new JButton("EXIT");
	
	public WelcomeScreen() {
		setLayout(new GridLayout(5, 1));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		add(logo);
		add(userNameInput);
		add(ok);
		add(exit);
	}
}
