package quizkampen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WelcomeScreen extends JPanel{
	private final JLabel logo = new JLabel("QuizFights");
	private final JTextField userNameInput = new JTextField("Enter username to start");
	JButton okButton = new JButton("OK");
	JButton exitButton = new JButton("EXIT");
//	private ActionCoordinator ac = new ActionCoordinator();
	
	public WelcomeScreen(ActionListener al) {
		setPanel();
		setActionListener(al);
	}
	
	public void setPanel() {
		setLayout(new GridLayout(5, 1));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		add(logo);
		add(userNameInput);
		add(okButton);
		add(exitButton);
	}
	public void setActionListener(ActionListener al) {
		okButton.addActionListener(al);
		exitButton.addActionListener(al);
	}
}
