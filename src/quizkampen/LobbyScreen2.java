package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LobbyScreen2 extends MasterPanel{
    
    JPanel centerPanel = new JPanel();
    
    JPanel centerTopPanel = new JPanel();
    JLabel textLabel1 = new JLabel("- Next Opponent -");
    JLabel opponentLabel = new JLabel("");
    JLabel centerTopBotSpace = new JLabel(" ");
    JPanel centerMidPanel = new JPanel();
    JLabel textLabel2 = new JLabel("Chosen Subject");
    JPanel subjectIconPanel = new JPanel();
    JButton subjectButton = new JButton("");
    JPanel centerBotPanel = new JPanel();
    JPanel readyIconPanel = new JPanel();
    JLabel centerBotTopSpace = new JLabel(" ");
    JButton readyButton = new JButton("Ready");
    
    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        
        topSpace.setPreferredSize(new Dimension(0, 80));
        rightSpace.setPreferredSize(new Dimension(70, 0));
        leftSpace.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 90));
        
        centerPanel.setLayout(new GridLayout(3, 1, 0, 0));
        centerPanel.setBackground(backgroundColor);
        
        centerTopPanel.setLayout(new BorderLayout(0, 10));
        centerTopPanel.setBackground(backgroundColor);
        textLabel1.setFont(infoTextFontSmall);
        textLabel1.setForeground(infoTextColor);
        textLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        opponentLabel.setBackground(nuance);
        opponentLabel.setOpaque(true);
        opponentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        opponentLabel.setForeground(infoTextColor);
        opponentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        opponentLabel.setFont(infoTextFontBig);
        centerTopBotSpace.setBackground(backgroundColor);
        centerTopBotSpace.setPreferredSize(new Dimension(0, 70));
        centerTopPanel.add(textLabel1, BorderLayout.NORTH);
        centerTopPanel.add(opponentLabel, BorderLayout.CENTER);
        centerTopPanel.add(centerTopBotSpace, BorderLayout.SOUTH);
        
        centerMidPanel.setLayout(new BorderLayout(0, 10));
        centerMidPanel.setBackground(backgroundColor);
        textLabel2.setFont(infoTextFontBig);
        textLabel2.setForeground(infoTextColor);
        textLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        subjectIconPanel.setBackground(backgroundColor);
        subjectIconPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subjectButton.setPreferredSize(new Dimension(130, 110));
        subjectButton.setFont(buttonFont);
        subjectButton.setVisible(false);
        subjectIconPanel.add(subjectButton);
        centerMidPanel.add(textLabel2, BorderLayout.NORTH);
        centerMidPanel.add(subjectIconPanel, BorderLayout.CENTER);
        
        centerBotPanel.setLayout(new BorderLayout());
        centerBotPanel.setBackground(backgroundColor);
        centerBotPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerBotTopSpace.setPreferredSize(new Dimension(0, 60));
        readyButton.setFont(buttonFont);
        readyButton.setPreferredSize(new Dimension(180, 70));
        readyButton.setVisible(false);
        readyIconPanel.setBackground(backgroundColor);
        readyIconPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        readyIconPanel.add(readyButton);      // Händer i sessionHandler
        centerBotPanel.add(centerBotTopSpace, BorderLayout.NORTH);
        centerBotPanel.add(readyIconPanel, BorderLayout.CENTER);
        
        topSpace.setBackground(backgroundColor);
        leftSpace.setBackground(backgroundColor);
        rightSpace.setBackground(backgroundColor);
        bottomSpace.setBackground(backgroundColor);
        
        centerPanel.add(centerTopPanel, BorderLayout.NORTH);
        centerPanel.add(centerMidPanel, BorderLayout.CENTER);
        centerPanel.add(centerBotPanel, BorderLayout.SOUTH);
        
        add(centerPanel, BorderLayout.CENTER);
        add(topSpace, BorderLayout.NORTH);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }
    
    public void addButtons(){
        subjectIconPanel.add(subjectButton);
        readyIconPanel.add(readyButton);
        revalidate();
        repaint();
    }

    @Override
    public void setActionListener(ActionListener al) {
        readyButton.addActionListener(al);
    }
    
//    @Override
//    public void setCustomColor(Color backgroundColor, Color logoColor, Color infoTextColor, Color nuance) {
//        this.backgroundColor = backgroundColor;
//        this.logoColor = logoColor;
//        this.infoTextColor = infoTextColor;
//        this.nuance = nuance;
//        setPanel();
//        repaint();
//    }
}