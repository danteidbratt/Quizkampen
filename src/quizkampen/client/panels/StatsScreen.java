package quizkampen.client.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import quizkampen.client.Window;

public class StatsScreen extends MasterPanel {

    Window w;
    private final JPanel centerPanel = new JPanel();

    JLabel test1 = new JLabel("Name: ");
    JLabel test2 = new JLabel("Games Played: ");
    JLabel test3 = new JLabel("Wins: ");
    JLabel test4 = new JLabel("Losses: ");
    JLabel test5 = new JLabel("Draws: ");
    public JButton backButton = new JButton("Back");

    public StatsScreen(Window w) {
        this.w = w;
    }

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

        centerPanel.setLayout(new GridLayout(6, 1));
        centerPanel.setBackground(backgroundColor);

        backButton.setFont(buttonFont);
        test1.setFont(buttonFont); test1.setForeground(infoTextColor);
        test2.setFont(buttonFont); test2.setForeground(infoTextColor);
        test3.setFont(buttonFont); test3.setForeground(infoTextColor);
        test4.setFont(buttonFont); test4.setForeground(infoTextColor);
        test5.setFont(buttonFont); test5.setForeground(infoTextColor);

        centerPanel.add(test1);
        centerPanel.add(test2);
        centerPanel.add(test3);
        centerPanel.add(test4);
        centerPanel.add(test5);
        centerPanel.add(backButton);

        add(logo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }

    @Override
    public void setActionListener(ActionListener al) {
        backButton.addActionListener(al);
    }

    public void setUserData() {
        test1.setText("Name: " + w.user.getUserName());
        test2.setText("Games played: " + w.user.getTotalGames());
        test3.setText("Wins: " + w.user.getWins());
        test4.setText("Losses: " + w.user.getLosses());
        test5.setText("Draws: " + w.user.getDraws());
    }
}
