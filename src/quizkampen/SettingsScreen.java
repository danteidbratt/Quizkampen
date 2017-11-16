package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsScreen extends JPanel implements IPanel {

    private final JLabel logo = new JLabel("QuizFights");
    private final JPanel centerPanel = new JPanel();
    private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
	final JRadioButton blue;
	final JRadioButton red;
	final JRadioButton green;
	final ButtonGroup buttonGroup;
    final JButton backButton;
    
    Color backgroundColor;
    Font buttonFont;
    
    public SettingsScreen(Font buttonFont, Color backgroundColor) {
		blue = new JRadioButton("Blue", true);
		red = new JRadioButton("Red", false);
		green = new JRadioButton("Green", false);
		buttonGroup = new ButtonGroup();
		backButton = new JButton("Back");
        this.backgroundColor = backgroundColor;
        this.buttonFont = buttonFont;
    }

    @Override
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
        green.setFont(buttonFont);
        blue.setOpaque(false);
        red.setOpaque(false);
        green.setOpaque(false);
        centerPanel.add(blue);
        centerPanel.add(red);
        centerPanel.add(green);
        centerPanel.add(backButton);
        buttonGroup.add(blue);
        buttonGroup.add(red);
        buttonGroup.add(green);

        add(logo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(sideSpaceWest, BorderLayout.WEST);
        add(sideSpaceEast, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }

    @Override
    public void setActionListener(ActionListener al) {
        blue.addActionListener(al);
        red.addActionListener(al);
        green.addActionListener(al);
        backButton.addActionListener(al);
    }
}
