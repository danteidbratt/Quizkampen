package quizkampen.client.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import quizkampen.server.MasterPanel;

public class MenuScreen extends MasterPanel{

    private final JPanel centerPanel = new JPanel();

    JButton newGameButton = new JButton("New Game");
    JButton settingsButton = new JButton("Settings");
    JButton statsButton = new JButton("Stats");
    JButton logoutButton = new JButton("Logout");
    JButton exitButton = new JButton("Exit");

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
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }

    @Override
    public void setActionListener(ActionListener al) {
        newGameButton.addActionListener(al);
        settingsButton.addActionListener(al);
        statsButton.addActionListener(al);
        logoutButton.addActionListener(al);
        exitButton.addActionListener(al);
    }
}