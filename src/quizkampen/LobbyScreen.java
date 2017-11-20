package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LobbyScreen extends MasterPanel implements Runnable{
    
    public Thread animation = new Thread(this);
    
    JPanel centerPanel = new JPanel();
    
    JPanel opponentPanel = new JPanel();
    JPanel topBottomPanel = new JPanel();
    JLabel topBottomSpace1 = new JLabel("");
    JLabel nextOpponentIs = new JLabel("- Next Opponent -");
    JLabel opponentLabel = new JLabel("...");
    
    JPanel centerCenterPanel = new JPanel();
    JLabel centerTopSpace = new JLabel("Choose Subject");
    JLabel centerBotSpace = new JLabel();
    JPanel subjectPanel = new JPanel();
    JButton subjectOneButton = new JButton("");
    JButton subjectTwoButton = new JButton("");
    JButton subjectThreeButton = new JButton("");
    
    JPanel bottomPanel = new JPanel();
    JLabel bottomTopSpace = new JLabel("");
    JLabel bottomBottomSpace = new JLabel("");
    JPanel buttonPanel = new JPanel();
    JButton startButton = new JButton("Start");
    JButton backButton = new JButton("Back");
    
    public boolean loopAnimation = true;
    
    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        topSpace.setPreferredSize(new Dimension(0, 80));
        rightSpace.setPreferredSize(new Dimension(70, 0));
        leftSpace.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 120));

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
        centerTopSpace.setHorizontalAlignment(SwingConstants.CENTER);
        centerTopSpace.setFont(infoTextFontBig);
        centerTopSpace.setForeground(infoTextColor);
        centerTopSpace.setBackground(backgroundColor);
        centerTopSpace.setOpaque(true);
        centerTopSpace.setPreferredSize(new Dimension(0, 50));
        centerBotSpace.setBackground(backgroundColor);
        centerBotSpace.setOpaque(true);
        centerBotSpace.setPreferredSize(new Dimension(0, 30));
        subjectPanel.setLayout(new GridLayout(1, 3, 10, 0));
        subjectPanel.setBackground(backgroundColor);
        subjectOneButton.setFont(buttonFont);
        subjectTwoButton.setFont(buttonFont);
        subjectThreeButton.setFont(buttonFont);
        subjectPanel.add(subjectOneButton);
        subjectPanel.add(subjectTwoButton);
        subjectPanel.add(subjectThreeButton);
        centerCenterPanel.add(centerTopSpace, BorderLayout.NORTH);
        centerCenterPanel.add(subjectPanel, BorderLayout.CENTER);
        centerCenterPanel.add(centerBotSpace, BorderLayout.SOUTH);

        bottomPanel.setLayout(new GridLayout(3, 1, 0, 0));
        bottomPanel.setBackground(backgroundColor);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setBackground(backgroundColor);
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(new Dimension(160, 40));
        startButton.setFont(buttonFont);
        startButton.setPreferredSize(new Dimension(160, 40));
        buttonPanel.add(backButton);
        bottomPanel.add(bottomTopSpace);
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
            backButton.addActionListener(al);
            startButton.addActionListener(al);
            subjectOneButton.addActionListener(al);
            subjectTwoButton.addActionListener(al);
            subjectThreeButton.addActionListener(al);
    }

    @Override
    public void run() {
        while(loopAnimation){
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
    }
}