package quizkampen.client.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import quizkampen.server.Subject;
import quizkampen.client.Window;

public class LobbyScreen extends MasterPanel implements Runnable {

    Window w;
    public Thread animation = new Thread(this);

    JPanel centerPanel = new JPanel();

    JPanel opponentPanel = new JPanel();
    JPanel topBottomPanel = new JPanel();
    JLabel topBottomSpace1 = new JLabel("");
    JLabel nextOpponentIs = new JLabel("- Next Opponent -");
    public JLabel opponentLabel = new JLabel("");

    JPanel centerCenterPanel = new JPanel();
    public JLabel chooseSubjectLabel = new JLabel("Choose Subject");
    JLabel centerBotSpace = new JLabel();
    JPanel subjectPanel = new JPanel();
    public JButton[] subjectButtons = new JButton[3];

    JPanel bottomPanel = new JPanel();
    JLabel bottomTopSpace = new JLabel("");
    JLabel bottomBottomSpace = new JLabel("");
    JPanel buttonPanel = new JPanel();
    public JButton startButton = new JButton("Start");

    public boolean loopAnimation = true;

    public LobbyScreen(Window w) {
        this.w = w;
    }
    
    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        topSpace.setPreferredSize(new Dimension(0, 80));
        rightSpace.setPreferredSize(new Dimension(50, 0));
        leftSpace.setPreferredSize(new Dimension(50, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 60));

        opponentPanel.setLayout(new BorderLayout(0, 10));
        opponentPanel.setBackground(backgroundColor);
        topBottomPanel.setLayout(new GridLayout(2, 1, 0, 5));
        topBottomPanel.setBackground(backgroundColor);
        opponentLabel.setBackground(nuance);
        opponentLabel.setOpaque(true);
        opponentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        opponentLabel.setForeground(infoTextColor);
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
            subjectButtons[i].setFont(new Font("SansSarif", Font.BOLD, 14));
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
        startButton.addActionListener(w.ah);
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
        for (JButton sb : subjectButtons) {
            sb.addActionListener(al);
        }
    }
    
    public void removeActionListener(ActionListener al){
        for (JButton sb : subjectButtons) {
            sb.removeActionListener(al);
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
        opponentLabel.setText(w.session.getPlayerNameTwo());
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
