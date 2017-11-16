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

public class GameScreen extends JPanel implements IPanel {

    private final JPanel centerPanel = new JPanel();
    private JPanel roundPanel = new JPanel();
    private JPanel questionsPanel = new JPanel();
    private final JLabel logo = new JLabel("QuizFights");
    private final JLabel topSpace = new JLabel("");
    private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
    private final JPanel cardsPanel = new JPanel();
    private final JPanel answerCardsPanel= new JPanel();

    JLabel roundTextLabel = new JLabel("Round");
    JLabel roundBoxLabel = new JLabel("  1/2  ");
    JLabel roundSpace = new JLabel("");
    List<JLabel> questionBoxes = new ArrayList<>();
    JButton questionButton = new JButton("Vilken huvudstad är störst i Norden?");
    JButton answer1Button = new JButton("1. Oslo");
    JButton answer2Button = new JButton("2. Stockholm");
    JButton answer3Button = new JButton("3. Helsingfors");
    JButton answer4Button = new JButton("4. Göteborg");

    Color backgroundColor;
    Font buttonFont;
    int amountOfQuestions = 5;

    public GameScreen(Font buttonFont, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.buttonFont = buttonFont;
    }

    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        topSpace.setPreferredSize(new Dimension(0, 50));
        sideSpaceWest.setPreferredSize(new Dimension(70, 0));
        sideSpaceEast.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 80));

        centerPanel.setLayout(new BorderLayout(0, 50));
        centerPanel.setBackground(backgroundColor);

        roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.Y_AXIS));
        roundPanel.setBackground(backgroundColor);
        roundTextLabel.setFont(buttonFont);
        roundTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roundBoxLabel.setBackground(Color.WHITE);
        roundBoxLabel.setOpaque(true);
        roundBoxLabel.setPreferredSize(new Dimension(100, 37));
        roundBoxLabel.setFont(buttonFont);
        roundBoxLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        roundBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionsPanel.setLayout(new FlowLayout());
        questionsPanel.setBackground(backgroundColor);
        for (int i = 0; i < amountOfQuestions; i++) {
            questionBoxes.add(new JLabel(""));
            questionBoxes.get(i).setPreferredSize(new Dimension(60, 37));
            questionBoxes.get(i).setOpaque(true);
            questionBoxes.get(i).setBackground(Color.WHITE);
            questionBoxes.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            questionsPanel.add(questionBoxes.get(i));
        }

        roundSpace.setPreferredSize(new Dimension(0, 20));
        roundPanel.add(roundTextLabel);
        roundPanel.add(roundBoxLabel);
        roundPanel.add(roundSpace);
        roundPanel.add(questionsPanel);
        
        cardsPanel.setLayout(new GridLayout(2, 1, 0, 50));
        cardsPanel.setBackground(backgroundColor);
        answerCardsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        answerCardsPanel.setBackground(backgroundColor);
        answer1Button.setFont(buttonFont);
        answer2Button.setFont(buttonFont);
        answer3Button.setFont(buttonFont);
        answer4Button.setFont(buttonFont);
        answer1Button.setBackground(Color.GREEN);
        answer1Button.setOpaque(true);
        answer1Button.setBorderPainted(false);
        answerCardsPanel.add(answer1Button);
        answerCardsPanel.add(answer2Button);
        answerCardsPanel.add(answer3Button);
        answerCardsPanel.add(answer4Button);
        questionButton.setFont(new Font("SansSarif", Font.BOLD, 15));
        cardsPanel.add(questionButton);
        cardsPanel.add(answerCardsPanel);
        
        logo.setBackground(backgroundColor);
        logo.setOpaque(true);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setForeground(Color.YELLOW);
        logo.setFont(new Font("SansSarif", 3, 60));

        centerPanel.add(roundPanel, BorderLayout.NORTH);
        centerPanel.add(cardsPanel);
        centerPanel.add(logo, BorderLayout.SOUTH);
        
        add(topSpace, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(sideSpaceWest, BorderLayout.WEST);
        add(sideSpaceEast, BorderLayout.EAST);
        add(bottomSpace, BorderLayout.SOUTH);
    }

    @Override
    public void setActionListener(ActionListener al) {
        questionButton.addActionListener(al);
        answer1Button.addActionListener(al);
        answer2Button.addActionListener(al);
        answer3Button.addActionListener(al);
        answer4Button.addActionListener(al);
    }
}
