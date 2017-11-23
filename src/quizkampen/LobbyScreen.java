package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LobbyScreen extends MasterPanel implements Runnable {

    Window w;

    public Thread animation = new Thread(this);

    JPanel centerPanel = new JPanel();

    JPanel opponentPanel = new JPanel();
    JPanel topBottomPanel = new JPanel();
    JLabel topBottomSpace1 = new JLabel("");
    JLabel nextOpponentIs = new JLabel("- Next Opponent -");
    JLabel opponentLabel = new JLabel("...");

    JPanel centerCenterPanel = new JPanel();
    JLabel chooseSubjectLabel = new JLabel("Choose Subject");
    JLabel centerBotSpace = new JLabel();
    JPanel subjectPanel = new JPanel();
    JButton[] subjectButtons = new JButton[3];

    JPanel bottomPanel = new JPanel();
    JLabel bottomTopSpace = new JLabel("");
    JLabel bottomBottomSpace = new JLabel("");
    JPanel buttonPanel = new JPanel();
    JButton startButton = new JButton("Start");
//    JButton backButton = new JButton("Back");

    public boolean loopAnimation = true;

    public LobbyScreen(Window w) {
        this.w = w;
    }

    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        topSpace.setPreferredSize(new Dimension(0, 80));
        rightSpace.setPreferredSize(new Dimension(70, 0));
        leftSpace.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 60));

        opponentPanel.setLayout(new BorderLayout(0, 10));
        opponentPanel.setBackground(backgroundColor);
        topBottomPanel.setLayout(new GridLayout(2, 1, 0, 5));
        topBottomPanel.setBackground(backgroundColor);
        opponentLabel.setBackground(backgroundColor);
        opponentLabel.setOpaque(true);
        opponentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        opponentLabel.setForeground(Color.WHITE);
        opponentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        opponentLabel.setFont(infoTextFontBig);
        topBottomSpace1.setBackground(backgroundColor);
        topBottomSpace1.setOpaque(true);
        topBottomPanel.add(opponentLabel);
        topBottomPanel.add(topBottomSpace1);
        nextOpponentIs.setHorizontalAlignment(SwingConstants.CENTER);
        nextOpponentIs.setFont(infoTextFontSmall);
        nextOpponentIs.setForeground(infoTextColor);

        opponentPanel.add(nextOpponentIs, BorderLayout.NORTH);
        opponentPanel.add(topBottomPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new GridLayout(3, 1, 0, 20));
        centerPanel.setBackground(backgroundColor);

        centerCenterPanel.setLayout(new BorderLayout());
        chooseSubjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseSubjectLabel.setFont(infoTextFontBig);
        chooseSubjectLabel.setForeground(infoTextColor);
        chooseSubjectLabel.setBackground(backgroundColor);
        chooseSubjectLabel.setOpaque(true);
        chooseSubjectLabel.setVisible(false);
        chooseSubjectLabel.setPreferredSize(new Dimension(0, 50));
        centerBotSpace.setBackground(backgroundColor);
        centerBotSpace.setOpaque(true);
        centerBotSpace.setPreferredSize(new Dimension(0, 30));
        subjectPanel.setLayout(new GridLayout(1, 3, 10, 0));
        subjectPanel.setBackground(backgroundColor);
        for (int i = 0; i < subjectButtons.length; i++) {
            subjectButtons[i] = new JButton();
            subjectButtons[i].setFont(buttonFont);
            subjectButtons[i].setFont(buttonFont);
            subjectButtons[i].setFont(new Font("SansSarif", Font.BOLD, 15));
            subjectButtons[i].setVisible(false);
            subjectPanel.add(subjectButtons[i]);
        }
        centerCenterPanel.add(chooseSubjectLabel, BorderLayout.NORTH);
        centerCenterPanel.add(subjectPanel, BorderLayout.CENTER);
        centerCenterPanel.add(centerBotSpace, BorderLayout.SOUTH);

        bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));
        bottomPanel.setBackground(backgroundColor);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setBackground(backgroundColor);
        startButton.setFont(buttonFont);
        startButton.setPreferredSize(new Dimension(170, 70));
        startButton.setVisible(false);
        buttonPanel.add(startButton);
        bottomPanel.add(buttonPanel);
        bottomPanel.add(bottomBottomSpace);

        centerPanel.add(opponentPanel);
        centerPanel.add(centerCenterPanel);
        centerPanel.add(bottomPanel);

        add(topSpace, BorderLayout.NORTH);
        add(rightSpace, BorderLayout.EAST);
        add(leftSpace, BorderLayout.WEST);
        add(bottomSpace, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void setActionListener(ActionListener al) {
        startButton.addActionListener(al);
        for (JButton sb : subjectButtons) {
            sb.addActionListener(al);
        }
    }

    @Override
    public void run() {
        while (loopAnimation) {
            try {
                opponentLabel.setText("");
                Thread.sleep(400);
                opponentLabel.setText(".");
                Thread.sleep(400);
                opponentLabel.setText(". .");
                Thread.sleep(400);
                opponentLabel.setText(". . .");
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        if(w.playerNumber == 1)
            opponentLabel.setText(w.session.getPlayerNameTwo());
        else
            opponentLabel.setText(w.session.getPlayerNameOne());
        chooseSubjectLabel.setVisible(true);
        for (int i = 0; i < subjectButtons.length; i++) {
            subjectButtons[i].setVisible(true);
        }
    }

    public void resetPanel() {
        for (JButton sb : subjectButtons) {
            sb.setOpaque(false);
            sb.setBorderPainted(true);
        }
        startButton.setVisible(false);
    }

    public void setSubjectButtons(Subject[] s) {
        for (int i = 0; i < subjectButtons.length; i++) {
            subjectButtons[i].setText(s[i].getName());
        }
    }
}
