package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class ResultScreen extends MasterPanel{
    
    private int numberOfRounds;
    JPanel centerPanel = new JPanel();
    
    JPanel scoreBoard = new JPanel();
    JPanel namePanel = new JPanel();
    JLabel leftName = new JLabel("Jesus");
    JLabel namePanelspace = new JLabel(" ");
    JLabel rightName = new JLabel("Satan");
    JPanel numberDisplay = new JPanel();
    JLabel leftNumber = new JLabel("0");
    JLabel dash = new JLabel("-");
    JLabel rightNumber = new JLabel("0");
    
    JPanel roundPanel = new JPanel();
    JPanel[] rounds = new JPanel[6];
    JLabel space1 = new JLabel("Round 1");
    JLabel space2 = new JLabel("Round 2");
    JLabel space3 = new JLabel("Round 3");
    JLabel space4 = new JLabel("Round 4");
    JLabel space5 = new JLabel("Round 5");
    JLabel space6 = new JLabel("Round 6");
    JPanel stats1 = new JPanel();
    JPanel stats2 = new JPanel();
    JPanel stats3 = new JPanel();
    JPanel stats4 = new JPanel();
    JPanel stats5 = new JPanel();
    JPanel stats6 = new JPanel();
    
    JPanel[][] threes = new JPanel[6][2];
    JLabel[][] boxes = new JLabel[6][6];
    JLabel[] subjects = new JLabel[6];
    
    JPanel botPanel = new JPanel();
    JButton nextButton = new JButton("Next Round");

    public ResultScreen(int numberOfRounds){
        this.numberOfRounds = numberOfRounds;
    }
    
    @Override
    public void setPanel() {
        setLayout(new BorderLayout(0, 20));
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
        namePanel.setPreferredSize(new Dimension(0, 66));
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
        setRounds();
        
        centerPanel.add(scoreBoard, BorderLayout.NORTH);
        centerPanel.add(roundPanel, BorderLayout.CENTER);
        
        botPanel.setBackground(backgroundColor);
        botPanel.setPreferredSize(new Dimension(0, 90));
        botPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.setPreferredSize(new Dimension(180, 70));
        nextButton.setFont(buttonFont);
        botPanel.add(nextButton);
        
        add(logo, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(botPanel, BorderLayout.SOUTH);
    }
    
    private void setRounds(){
        int lCounter = 0;
        int pCounter = 1;
        List<JLabel> LabelList = new ArrayList<> ();
        LabelList = Arrays.asList(space1, space2, space3, space4, space5, space6);
        LabelList.forEach(e -> {e.setBackground(backgroundColor); e.setFont(new Font("SansSerif", 2, 22));
                                e.setForeground(infoTextColor); e.setVisible(true);
                                e.setHorizontalAlignment(SwingConstants.CENTER); e.setOpaque(true);});
        
        List<JPanel> PanelList = new ArrayList<> ();
        PanelList = Arrays.asList(stats1, stats2, stats3, stats4, stats5, stats6);
        PanelList.forEach(e -> {e.setLayout(new GridLayout(1, 3, 5, 0)); e.setBackground(backgroundColor);});
        
        for (int i = 0; i < rounds.length; i++) {
            rounds[i] = new JPanel();
            rounds[i].setLayout(new GridLayout(2, 1, 0, 0));
            rounds[i].setBackground(backgroundColor);
//            rounds[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            rounds[i].add(LabelList.get(i));
            rounds[i].add(PanelList.get(i));
            roundPanel.add(rounds[i]);
            
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
                threes[i][j].setLayout(new GridLayout(1, 3));
                threes[i][j].setBackground(backgroundColor);
                threes[i][j].add(boxes[counter1][counter2++]);
                threes[i][j].add(boxes[counter1][counter2++]);
                threes[i][j].add(boxes[counter1][counter2++]);
            }
            counter1++;
        }
        
        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = new JLabel("- Mat -");
            subjects[i].setBackground(backgroundColor);
            subjects[i].setOpaque(true);
            subjects[i].setHorizontalAlignment(SwingConstants.CENTER);
            subjects[i].setFont(new Font("SansSerif", 1, 15));
            subjects[i].setForeground(infoTextColor);
        }
        
        for (int i = 0; i < PanelList.size(); i++) {
            int counterHej = 0;
            PanelList.get(i).add(threes[i][counterHej++]);
            PanelList.get(i).add(subjects[i]);
            PanelList.get(i).add(threes[i][counterHej]);
        }
    }

    @Override
    public void setActionListener(ActionListener al) {
    }
}