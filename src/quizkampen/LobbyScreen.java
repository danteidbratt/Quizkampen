package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LobbyScreen extends JPanel implements Runnable{
    public Thread animation = new Thread(this);
    private final JLabel topSpace = new JLabel("");
    private final JLabel leftSpace = new JLabel("");
    private final JLabel rightSpace = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
    
    JPanel centerPanel = new JPanel();
    
    JPanel opponentPanel = new JPanel();
    JPanel topBottomPanel = new JPanel();
    JLabel topBottomSpace1 = new JLabel("");
    JLabel nextOpponentIs = new JLabel("  Your opponent is...");
    JLabel opponentLabel = new JLabel("...");
    
    JPanel centerCenterPanel = new JPanel();
    JLabel centerTopSpace = new JLabel();
    JLabel centerBotSpace = new JLabel();
    JPanel subjectPanel = new JPanel();
    JButton subjectOneButton = new JButton("Subject 1");
    JButton subjectTwoButton = new JButton("Subject 2");
    JButton subjectThreeButton = new JButton("Subject 3");
    
    JPanel bottomPanel = new JPanel();
    JLabel bottomTopSpace = new JLabel("");
    JLabel bottomBottomSpace = new JLabel("");
    JPanel buttonPanel = new JPanel();
    JButton startButton = new JButton("Start");
    JButton backButton = new JButton("Back");
    
    boolean loopAnimation;
    
    private Color backgroundColor;
    Font buttonFont;
    
    public LobbyScreen(ActionListener al, Font buttonFont, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.buttonFont = buttonFont;
        loopAnimation = true;
    }
    
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        topSpace.setPreferredSize(new Dimension(0, 100));
        rightSpace.setPreferredSize(new Dimension(70, 0));
        leftSpace.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 120));

        opponentPanel.setLayout(new BorderLayout(0, 20));
        opponentPanel.setBackground(backgroundColor);
        topBottomPanel.setLayout(new GridLayout(2, 1, 0, 5));
        topBottomPanel.setBackground(backgroundColor);
        opponentLabel.setBackground(backgroundColor);
        opponentLabel.setOpaque(true);
        opponentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        opponentLabel.setForeground(Color.BLACK);
        opponentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        opponentLabel.setFont(new Font("SansSerif", 3, 30));
        topBottomSpace1.setBackground(backgroundColor);
        topBottomSpace1.setOpaque(true);
        topBottomPanel.add(opponentLabel);
        topBottomPanel.add(topBottomSpace1);
        nextOpponentIs.setFont(new Font("SansSerif", 1, 15));
        nextOpponentIs.setForeground(Color.GREEN);

        opponentPanel.add(nextOpponentIs, BorderLayout.NORTH);
        opponentPanel.add(topBottomPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new GridLayout(3, 1, 0, 20));
        centerPanel.setBackground(backgroundColor);

        centerCenterPanel.setLayout(new BorderLayout());
        centerTopSpace.setBackground(backgroundColor);
        centerTopSpace.setOpaque(true);
        centerTopSpace.setPreferredSize(new Dimension(0, 30));
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
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(backgroundColor);
        backButton.setFont(buttonFont);
        startButton.setFont(buttonFont);
        buttonPanel.add(backButton);
        buttonPanel.add(startButton);
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

    public void setActionListener(ActionListener al) {
            backButton.addActionListener(al);
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