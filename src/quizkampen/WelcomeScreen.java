package quizkampen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WelcomeScreen extends JPanel{
	private final JLabel logo = new JLabel("QuizFights");
	private final JTextField userNameInput = new JTextField("Enter username to start");
	private final JButton ok = new JButton("OK");
	private final JButton exit = new JButton("EXIT");
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
		add(ok);
		add(exit);
		setVisible(false);
	}
	public void setActionListener(ActionListener al) {
		ok.addActionListener(al);
		exit.addActionListener(al);
	}
}
