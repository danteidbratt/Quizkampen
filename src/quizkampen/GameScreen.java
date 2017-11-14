package quizkampen;

import javax.swing.*;
import java.awt.*;

/*
Hämta JPanel med metoden getGameScreen()
 */
public class GameScreen {
    JPanel gameStatsPanel = new JPanel();
    JPanel gameScreen = new JPanel();
	JPanel questionPanel = new JPanel();
	JPanel answerPanel = new JPanel();
	JPanel topPanel = new JPanel();
    JTextField points = new JTextField("Poäng: 1");
    JTextField questionNumber = new JTextField("Fråga: 1/2");
    JTextField rond = new JTextField("Rond: 2/2");
    JButton question = new JButton("Vilken huvudstad är störst i Norden?");
	JButton answer1 = new JButton("1. Oslo");
	JButton answer2 = new JButton("2. Stockholm");
	JButton answer3 = new JButton("3. Helsingfors");
	JButton answer4 = new JButton("4. Göteborg");

    public GameScreen() {
        questionPanel.add(question);
        answerPanel.add(answer1);
        answerPanel.add(answer2);
        answerPanel.add(answer3);
        answerPanel.add(answer4);

        gameStatsPanel.add(points);
        gameStatsPanel.add(questionNumber);
        gameStatsPanel.add(rond);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.add(gameStatsPanel);
        topPanel.add(questionPanel);

        gameScreen.setLayout(new BoxLayout(gameScreen, BoxLayout.PAGE_AXIS));
        gameScreen.add(topPanel);
        gameScreen.add(answerPanel);
		points.setEditable(false);
        rond.setEditable(false);
		questionNumber.setEditable(false);
    }
}