package quizkampen.client.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameMenuScreen extends MasterPanel{

    private final JPanel centerPanel = new JPanel();

    public JButton randomPlayerButton = new JButton("Random player");
    JButton searchButton = new JButton("Search player");
    public JButton backButton = new JButton("Back");
    public JButton exitButton = new JButton("Exit");

    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(0, 200));
        logo.setForeground(logoColor);
        logo.setFont(logoFont);

        leftSpace.setPreferredSize(new Dimension(100, 0));
        rightSpace.setPreferredSize(new Dimension(100, 0));
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
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }

    @Override
    public void setActionListener(ActionListener al) {
        randomPlayerButton.addActionListener(al);
        searchButton.addActionListener(al);
        backButton.addActionListener(al);
        exitButton.addActionListener(al);
    }
}