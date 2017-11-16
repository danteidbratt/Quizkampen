package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameMenuScreen extends JPanel implements IPanel {

    private final JLabel logo = new JLabel("QuizFigths");
    private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
    private final JPanel centerPanel = new JPanel();

    JButton randomPlayerButton = new JButton("Random player");
    JButton searchButton = new JButton("Search player");
    JButton backButton = new JButton("Back");
    JButton exitButton = new JButton("Exit");

    private Color backgroundColor = new Color(0, 0, 255);
    private final Font buttonFont;

    public GameMenuScreen(Font buttonFont, Color backgroundColor) {
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

        centerPanel.setLayout(new GridLayout(5, 1, 0, 7));
        centerPanel.setBackground(backgroundColor);
        randomPlayerButton.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        centerPanel.add(randomPlayerButton);
        centerPanel.add(searchButton);
        centerPanel.add(backButton);
        centerPanel.add(exitButton);

        add(logo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(sideSpaceWest, BorderLayout.WEST);
        add(sideSpaceEast, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }

    @Override
    public void setActionListener(ActionListener al) {
        randomPlayerButton.addActionListener(al);
        searchButton.addActionListener(al);
        backButton.addActionListener(al);
        exitButton.addActionListener(al);
    }
    
    @Override
    public void setCustomColor(Color c) {
        backgroundColor = c;
        repaint();
    }
}
