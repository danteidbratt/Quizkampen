
package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuScreen extends JPanel{
    private final JLabel logo = new JLabel("QuizFigths");
    private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
    private final JPanel centerPanel = new JPanel();
	
    JButton newGameButton = new JButton("New Game");
    JButton settingsButton = new JButton("Settings");
    JButton statsButton = new JButton("Stats");
    JButton logoutButton = new JButton("Logout");
    JButton exitButton = new JButton("Exit");
	
    private Color backgroundColor;
    private Font buttonFont;
    
    
    public MenuScreen(ActionListener al, Font buttonFont, Color backgroundColor) {
                this.backgroundColor = backgroundColor;
                this.buttonFont = buttonFont;
		setPanel();
		setActionListener(al);
	}
    
    private void setPanel(){
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(0, 200));
        logo.setForeground(Color.YELLOW);
        logo.setFont(new Font("SansSarif", 2, 80));

        sideSpaceWest.setPreferredSize(new Dimension(100, 0));
        sideSpaceEast.setPreferredSize(new Dimension(100, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 120));

        centerPanel.setLayout(new GridLayout(5, 1, 0, 7));
        centerPanel.setBackground(backgroundColor);
        newGameButton.setFont(buttonFont);
        settingsButton.setFont(buttonFont);
        statsButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        centerPanel.add(newGameButton);
        centerPanel.add(settingsButton);
        centerPanel.add(statsButton);
        centerPanel.add(logoutButton);
        centerPanel.add(exitButton);
		
        add(logo, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(sideSpaceWest, BorderLayout.WEST);
		add(sideSpaceEast, BorderLayout.EAST);
		add(bottomSpace, BorderLayout.SOUTH);
    }
    
    private void setActionListener(ActionListener al){
        newGameButton.addActionListener(al);
        settingsButton.addActionListener(al);
        statsButton.addActionListener(al);
        logoutButton.addActionListener(al);
        exitButton.addActionListener(al);
    }
}