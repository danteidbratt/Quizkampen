package quizkampen;

import javax.swing.*;
import java.awt.*;

/*
Hämta JPanel med metoden getGameScreen()
 */
public class GameScreen {
    private JPanel gameScreen = new JPanel();

    GameScreen() {
        /* Statistik */
        JPanel gameStatsPanel = new JPanel();

        /* Stats
        * TODO hämta data */
        JTextField points = new JTextField("Poäng: 1");
        points.setEditable(false);

        JTextField questionNumber = new JTextField("Fråga: 1/2");
        questionNumber.setEditable(false);

        JTextField rond = new JTextField("Rond: 2/2");
        rond.setEditable(false);

        /* TODO hämta data */
        JButton question = new JButton("Vilken huvudstad är störst i Norden?");

        JButton answer1 = new JButton("1. Oslo");
        JButton answer2 = new JButton("2. Stockholm");
        JButton answer3 = new JButton("3. Helsingfors");
        JButton answer4 = new JButton("4. Göteborg");

        JPanel questionPanel = new JPanel();
        questionPanel.add(question);

        JPanel answerPanel = new JPanel();
        answerPanel.add(answer1);
        answerPanel.add(answer2);
        answerPanel.add(answer3);
        answerPanel.add(answer4);

        gameStatsPanel.add(points);
        gameStatsPanel.add(questionNumber);
        gameStatsPanel.add(rond);

        /* Top panel */
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.add(gameStatsPanel);
        topPanel.add(questionPanel);

        gameScreen.setLayout(new BoxLayout(gameScreen, BoxLayout.PAGE_AXIS));
        gameScreen.add(topPanel);
        gameScreen.add(answerPanel);
    }

    public JPanel getGameScreen() {
        return gameScreen;
    }
}