package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsScreen extends JPanel{
	private final JLabel logo = new JLabel("QuizFights");
    private final JPanel centerPanel = new JPanel();
	private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
	private final JRadioButton blue = new JRadioButton("Blue", true);
	private final JRadioButton red = new JRadioButton("Red", false);
	private final JRadioButton yellow = new JRadioButton("Yellow", false);
	ButtonGroup buttonGroup = new ButtonGroup();
    JButton backButton = new JButton("Back"); 
    
    Color backgroundColor;
    Font buttonFont;
    
    public SettingsScreen(ActionListener al, Font buttonFont, Color backgroundColor) {
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
        bottomSpace.setPreferredSize(new Dimension(0, 120));
		
		centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.setBackground(backgroundColor);
		backButton.setFont(buttonFont);
		blue.setFont(buttonFont);
		red.setFont(buttonFont);
		yellow.setFont(buttonFont);
		blue.setOpaque(false);
		red.setOpaque(false);
		yellow.setOpaque(false);
		centerPanel.add(blue);
		centerPanel.add(red);
		centerPanel.add(yellow);
		centerPanel.add(backButton);
		buttonGroup.add(blue);
		buttonGroup.add(red);
		buttonGroup.add(yellow);
		
        add(logo, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
        add(sideSpaceWest, BorderLayout.WEST);
        add(sideSpaceEast, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }
    
    public void setActionListener(ActionListener al){
		blue.addActionListener(al);
		red.addActionListener(al);
		yellow.addActionListener(al);
        backButton.addActionListener(al);
    }
}
