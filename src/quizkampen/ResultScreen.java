package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ResultScreen extends MasterPanel{
    
    JPanel centerPanel = new JPanel();
    JPanel scoreBoard = new JPanel();
    JPanel namePanel = new JPanel();
    JLabel leftName = new JLabel("");
    JLabel namePanelspace = new JLabel(" ");
    JLabel rightName = new JLabel("");
    JPanel numberDisplay = new JPanel();
    JLabel leftNumber = new JLabel("");
    JLabel dash = new JLabel("-");
    JLabel rightNumber = new JLabel("");
    
    JPanel roundPanel = new JPanel();
    JPanel[] rounds = new JPanel[6];
    JLabel[] roundSpaces = new JLabel[6];
    JPanel[] stats = new JPanel[6];
    JPanel[][] threes = new JPanel[6][2];
    JLabel[][] boxes;
    JLabel[] subjects = new JLabel[6];
    
    JPanel botPanel = new JPanel();
    JButton nextRoundButton = new JButton("Next Round");
    Color nuance;
    
    int numberOfQuestions;
    private int numberOfRounds;

    public ResultScreen(){
        leftNumber.setText("0");
        rightNumber.setText("0");
    }
    
    @Override
    public void setPanel() {
        setLayout(new BorderLayout(0, 0));
        setBackground(backgroundColor);
        rightSpace.setPreferredSize(new Dimension(50, 0));
        leftSpace.setPreferredSize(new Dimension(50, 0));
        
        logo.setBackground(backgroundColor);
        logo.setForeground(logoColor);
        logo.setFont(new Font("SansSarif", 2, 50));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(backgroundColor);
        
        scoreBoard.setLayout(new BorderLayout());
        scoreBoard.setBackground(backgroundColor);
        scoreBoard.setPreferredSize(new Dimension(0, 120));
        namePanel.setLayout(new GridLayout(1, 3));
        namePanel.setBackground(backgroundColor);
        namePanel.setPreferredSize(new Dimension(0, 50));
        numberDisplay.setLayout(new GridLayout(1, 3));
        numberDisplay.setBackground(backgroundColor);
        
        namePanel.setLayout(new GridLayout(1, 3));
        namePanel.setBackground(backgroundColor);
        leftName.setBackground(backgroundColor);
        leftName.setForeground(infoTextColor);
        leftName.setFont(infoTextFontBig);
        leftName.setHorizontalAlignment(SwingConstants.CENTER);
        rightName.setBackground(backgroundColor);
        rightName.setForeground(infoTextColor);
        rightName.setFont(infoTextFontBig);
        rightName.setHorizontalAlignment(SwingConstants.CENTER);
        namePanel.add(leftName);
        namePanel.add(namePanelspace);
        namePanel.add(rightName);
        
        leftNumber.setBackground(backgroundColor);
        leftNumber.setHorizontalAlignment(SwingConstants.CENTER);
        leftNumber.setForeground(infoTextColor);
        leftNumber.setFont(new Font("SansSerif", 1, 60));
        rightNumber.setBackground(backgroundColor);
        rightNumber.setHorizontalAlignment(SwingConstants.CENTER);
        rightNumber.setForeground(infoTextColor);
        rightNumber.setFont(new Font("SansSerif", 1, 60));
        dash.setBackground(backgroundColor);
        dash.setForeground(infoTextColor);
        dash.setFont(new Font("SansSerif", 1, 60));
        dash.setHorizontalAlignment(SwingConstants.CENTER);
        numberDisplay.add(leftNumber);
        numberDisplay.add(dash);
        numberDisplay.add(rightNumber);
        
        scoreBoard.add(namePanel, BorderLayout.NORTH);
        scoreBoard.add(numberDisplay, BorderLayout.CENTER);
        
        roundPanel.setLayout(new GridLayout(6, 1, 0, 30));
        roundPanel.setBackground(backgroundColor);
        
        centerPanel.add(scoreBoard, BorderLayout.NORTH);
        centerPanel.add(roundPanel, BorderLayout.CENTER);
        
        botPanel.setBackground(backgroundColor);
        botPanel.setPreferredSize(new Dimension(0, 90));
        botPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextRoundButton.setPreferredSize(new Dimension(180, 70));
        nextRoundButton.setFont(buttonFont);
        nextRoundButton.setVisible(false);
        botPanel.add(nextRoundButton);
        
        add(logo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(botPanel, BorderLayout.SOUTH);
        
        setNumberOfRounds();
    }
    
    public void setRounds(){
        for (int i = 0; i < roundSpaces.length; i++) {
            roundSpaces[i] = new JLabel("- Round " + String.valueOf(i+1) + " -");
            roundSpaces[i].setBackground(nuance);
            roundSpaces[i].setOpaque(true);
            roundSpaces[i].setForeground(infoTextColor);
            roundSpaces[i].setFont(new Font("SansSerif", 2, 22));
            roundSpaces[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
        
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                boxes[i][j] = new JLabel("");
                boxes[i][j].setBackground(Color.WHITE);
                boxes[i][j].setOpaque(true);
                boxes[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        }
        
        int counter1 = 0;
        for (int i = 0; i < threes.length; i++) {
            int counter2 = 0;
            for (int j = 0; j < threes[i].length; j++) {
                threes[i][j] = new JPanel();
                threes[i][j].setLayout(new GridLayout(1, numberOfQuestions));
                threes[i][j].setBackground(nuance);
//                threes[i][j].setOpaque(true);
                for (int k = 0; k < numberOfQuestions; k++) {
                    threes[i][j].add(boxes[counter1][counter2++]);
                }
            }
            counter1++;
        }
        
        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = new JLabel(" ");
            subjects[i].setBackground(nuance);
//            subjects[i].setOpaque(true);
            subjects[i].setHorizontalAlignment(SwingConstants.CENTER);
            subjects[i].setFont(new Font("SansSerif", 1, 15));
            subjects[i].setForeground(infoTextColor);
        }
        
        for (int i = 0; i < stats.length; i++) {
            stats[i] = new JPanel();
            stats[i].setLayout(new GridLayout(1, 3));
            stats[i].setBackground(nuance);
//            stats[i].setOpaque(true);
            int j = 0;
            stats[i].add(threes[i][j++]);
            stats[i].add(subjects[i]);
            stats[i].add(threes[i][j]);
        }
        
        for (int i = 0; i < rounds.length; i++) {
            rounds[i] = new JPanel();
            rounds[i].setLayout(new GridLayout(2, 1, 0, 0));
            rounds[i].setBackground(nuance);
            rounds[i].setOpaque(true);
            rounds[i].setBorder(BorderFactory.createLineBorder(nuance, 6));
            rounds[i].add(roundSpaces[i]);
            rounds[i].add(stats[i]);
            roundPanel.add(rounds[i]);
        }
    }
    
    private void setNumberOfRounds(){
        for (int i = rounds.length-1; i >= numberOfRounds; i--) {
            rounds[i].setVisible(false);
        }
    }
    
    public void setPlayerName(String playerName){
        leftName.setText(playerName);
    }
    
    public void setOpponentName(String opponentName){
        rightName.setText(opponentName);
    }
    
    public void increasePlayerScore(){
        leftNumber.setText(String.valueOf(Integer.parseInt(leftNumber.getText()) + 1));
    }
    
    public void increaseOpponentScore(){
        rightNumber.setText(String.valueOf(Integer.parseInt(rightNumber.getText()) + 1));
    }
    
    public void setSubject(String subject, int roundCounter){
        subjects[roundCounter].setText(subject);
    }
    
    public void setRightName(String player2){   // FÖR ATT SÄTTA Opponent I RESULTS
        rightName.setText(player2);
    }
    
    public void setResultScreen(int numberOfQuestions, int numberOfRounds, String player1, String player2) {
        leftName.setText(player1);
        rightName.setText(player2);
        this.numberOfQuestions = numberOfQuestions;
        this.numberOfRounds = numberOfRounds;
        boxes = new JLabel[6][numberOfQuestions*2];
        setRounds();
    }
            
    @Override
    public void setActionListener(ActionListener al) {
        nextRoundButton.addActionListener(al);
    }
    
    public void setOpponentBoxes(boolean[] b, int roundCounter, int questionsInRound){
        for (int i = 0; i < b.length; i++) {
            if(b[i]){
                boxes[roundCounter][i+questionsInRound].setBackground(Color.GREEN);
                increaseOpponentScore();
            }
            else 
                boxes[roundCounter][i+questionsInRound].setBackground(Color.RED);
        }
    }
    
    public void setCustomColor(Color backgroundColor, Color logoColor, Color infoTextColor, Color nuance) {
        this.backgroundColor = backgroundColor;
        this.logoColor = logoColor;
        this.infoTextColor = infoTextColor;
        this.nuance = nuance;
        revalidate();
        repaint();
    }
}