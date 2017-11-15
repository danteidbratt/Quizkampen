package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

public class WelcomeScreen extends JPanel{
	private final JPanel loginPanel = new JPanel();
	private final JPanel centerPanel = new JPanel();
	private final JPanel exitPanel = new JPanel();
	
	private final JLabel logo = new JLabel("QuizFights");
	private final JLabel loginText = new JLabel("Login");
	private final JLabel centerSpace = new JLabel("");
	private final JLabel sideSpaceWest = new JLabel("");
	private final JLabel sideSpaceEast = new JLabel("");
	private final JLabel bottomSpace = new JLabel("");
	
	JTextField userNameInput = new JTextField("Enter username to start");
	JButton okButton = new JButton("OK");
	JButton exitButton = new JButton("EXIT");
	
	Color backgroundColor;
        Font buttonFont;
	
	public WelcomeScreen(ActionListener al, Font buttonFont, Color backgroundColor) {
                this.backgroundColor = backgroundColor;
                this.buttonFont = buttonFont;
		setPanel();
		setActionListener(al);
	}
	
	public void setPanel() {
		setLayout(new BorderLayout());
		setBackground(backgroundColor);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setPreferredSize(new Dimension(0, 200));
		logo.setForeground(Color.YELLOW);
		logo.setFont(new Font("SansSarif", 2, 80));
		
		
		sideSpaceWest.setPreferredSize(new Dimension(100, 0));
		sideSpaceEast.setPreferredSize(new Dimension(100, 0));
		bottomSpace.setPreferredSize(new Dimension(0, 200));
		
		loginPanel.setLayout(new GridLayout(3, 1, 0, 5));
		loginPanel.setBackground(backgroundColor);
		loginText.setHorizontalAlignment(SwingConstants.CENTER);
		loginText.setFont(new Font("SansSerif", 3, 30));
		loginText.setForeground(Color.GREEN);
                userNameInput.setHorizontalAlignment(SwingConstants.CENTER);
                okButton.setFont(buttonFont);
		loginPanel.add(loginText);
		loginPanel.add(userNameInput);
		loginPanel.add(okButton);
		
		exitPanel.setLayout(new GridLayout(3, 1));
		exitPanel.setBackground(backgroundColor);
                exitButton.setFont(buttonFont);
		exitPanel.add(exitButton);
                exitPanel.revalidate();
                exitPanel.repaint();
		
		centerPanel.setLayout(new GridLayout(3, 1));
		centerPanel.setBackground(backgroundColor);
		centerPanel.add(loginPanel);
		centerPanel.add(centerSpace);
		centerPanel.add(exitPanel);
		
		add(logo, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(sideSpaceWest, BorderLayout.WEST);
		add(sideSpaceEast, BorderLayout.EAST);
		add(bottomSpace, BorderLayout.SOUTH);
		
		FocusAdapter a = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				userNameInput.setText("");
			}
		};
		userNameInput.addFocusListener(a);
		okButton.requestFocusInWindow();
                
                revalidate();
                repaint();
	}
	public void setActionListener(ActionListener al) {
		okButton.addActionListener(al);
		exitButton.addActionListener(al);
	}
}
