package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends MasterPanel {

    private final JPanel centerPanel = new JPanel();
    private final JPanel roundPanel = new JPanel();
    private final JPanel questionsPanel = new JPanel();
    private final JPanel cardsPanel = new JPanel();
    private final JPanel answerCardsPanel = new JPanel();
    private final JPanel centerBotPanel = new JPanel();

    private final JLabel roundTextLabel = new JLabel("Round");
    public JLabel roundBoxLabel = new JLabel();
    private final JLabel roundSpace = new JLabel("");
    List<JLabel> questionBoxes = new ArrayList<>();
    JButton questionButton = new JButton("");
    AnswerButton[] answerButtons = new AnswerButton[4];
    JPanel buttonPanel = new JPanel();
    JButton nextQuestionButton = new JButton("Next Question");

    int numberOfQuestions = 5;

    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        topSpace.setPreferredSize(new Dimension(0, 50));
        leftSpace.setPreferredSize(new Dimension(70, 0));
        rightSpace.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 50));

        centerPanel.setLayout(new BorderLayout(0, 30));
        centerPanel.setBackground(backgroundColor);

        roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.Y_AXIS));
        roundPanel.setBackground(backgroundColor);
        roundTextLabel.setFont(buttonFont);
        roundTextLabel.setForeground(infoTextColor);
        roundTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roundBoxLabel.setBackground(Color.WHITE);
        roundBoxLabel.setOpaque(true);
        roundBoxLabel.setPreferredSize(new Dimension(100, 37));
        roundBoxLabel.setFont(buttonFont);
        roundBoxLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        roundBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionsPanel.setLayout(new FlowLayout());
        questionsPanel.setBackground(backgroundColor);

        roundSpace.setPreferredSize(new Dimension(0, 20));
        roundPanel.add(roundTextLabel);
        roundPanel.add(roundBoxLabel);
        roundPanel.add(roundSpace);
        roundPanel.add(questionsPanel);

        cardsPanel.setLayout(new GridLayout(2, 1, 0, 50));
        cardsPanel.setBackground(backgroundColor);
        answerCardsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        answerCardsPanel.setBackground(backgroundColor);
        setAnswerButtons();
        
        questionButton.setFont(new Font("SansSarif", Font.BOLD, 15));
        cardsPanel.add(questionButton);
        cardsPanel.add(answerCardsPanel);

        centerBotPanel.setLayout(new BorderLayout(0, 0));
        centerBotPanel.setBackground(backgroundColor);
        centerBotPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setPreferredSize(new Dimension(0, 100));
        nextQuestionButton.setFont(buttonFont);
        nextQuestionButton.setVisible(false);
        nextQuestionButton.setPreferredSize(new Dimension(180, 80));
        buttonPanel.add(nextQuestionButton);
        logo.setBackground(backgroundColor);
        logo.setOpaque(true);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setForeground(logoColor);
        logo.setFont(new Font("SansSarif", 2, 60));
        
        centerBotPanel.add(buttonPanel, BorderLayout.NORTH);
        centerBotPanel.add(logo, BorderLayout.CENTER);

        centerPanel.add(roundPanel, BorderLayout.NORTH);
        centerPanel.add(cardsPanel);
        centerPanel.add(centerBotPanel, BorderLayout.SOUTH);

        add(topSpace, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }
    
    public void setNumberofQuestions(int amountOfQuestions) {
        this.numberOfQuestions = amountOfQuestions;
        for (int i = 0; i < numberOfQuestions; i++) {
            questionBoxes.add(new JLabel(""));
            questionBoxes.get(i).setPreferredSize(new Dimension(60, 37));
            questionBoxes.get(i).setOpaque(true);
            questionBoxes.get(i).setBackground(Color.WHITE);
            questionBoxes.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            questionsPanel.add(questionBoxes.get(i));
        }
    }

    @Override
    public void setActionListener(ActionListener al){
        nextQuestionButton.addActionListener(al);
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].addActionListener(al);
        }
    }
    public void setButtonActionListener(ActionListener al) {
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].addActionListener(al);
        }
    }

    public void revealCorrectAnswer() {
        for (int i = 0; i < answerButtons.length; i++) {
            if (answerButtons[i].getIsCorrect()){
                answerButtons[i].setBackground(Color.GREEN);
                answerButtons[i].setOpaque(true);
                answerButtons[i].setBorderPainted(false); 
            }
        }
    }
    
    private void setAnswerButtons(){
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new AnswerButton();
            answerButtons[i].setFont(buttonFontSmall);
            answerButtons[i].setFont(buttonFontSmall);
            answerButtons[i].setFont(buttonFontSmall);
            answerButtons[i].setFont(buttonFontSmall);
            answerButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
            answerCardsPanel.add(answerButtons[i]);
        }
    }
    
    public void colorChosenButton(AnswerButton ab){
        ab.setBackground(Color.RED);
        ab.setOpaque(true);
        ab.setBorderPainted(false);
    }
    
    public void setNextQuestion(Question q){
        questionButton.setText("<html><p>" + q.getQuestionQ() + "</p></html>");
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setButton(q.getAnswerAlternatives().get(i));
            answerButtons[i].setBorderPainted(true);
            answerButtons[i].setBackground(new JButton().getBackground());
        }
        nextQuestionButton.setVisible(false);
    }
    
    public void removeActionListeners(ActionListener al){
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].removeActionListener(al);
        }
    }
    
    public void resetColors(){
        questionBoxes.forEach(e -> e.setBackground(Color.WHITE));
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setBorderPainted(true);
            answerButtons[i].setBackground(backgroundColor);
        }
        nextQuestionButton.setText("Next Question");
        nextQuestionButton.setVisible(false);
    }
}