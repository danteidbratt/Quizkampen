package quizkampen;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionListener;


public class GameScreen extends JPanel{
    JPanel gameStatsPanel = new JPanel();
    JPanel gameScreen = new JPanel();
	JPanel questionPanel = new JPanel();
	JPanel answerPanel = new JPanel();
	JPanel topPanel = new JPanel();
    JTextField points = new JTextField("Poäng: 1");
    JTextField questionNumber = new JTextField("Fråga: 1/2");
    JTextField rond = new JTextField("Rond: 2/2");
    JButton questionButton = new JButton("Vilken huvudstad är störst i Norden?");
	JButton answer1Button = new JButton("1. Oslo");
	JButton answer2Button = new JButton("2. Stockholm");
	JButton answer3Button = new JButton("3. Helsingfors");
	JButton answer4Button = new JButton("4. Göteborg");

    public GameScreen(ActionListener al) {
		setPanel();
		setActionListener(al);
    }
	
	public void setPanel() {
		setLayout(new GridLayout(3, 1));
        questionPanel.add(questionButton);
        answerPanel.add(answer1Button);
        answerPanel.add(answer2Button);
        answerPanel.add(answer3Button);
        answerPanel.add(answer4Button);

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
		add(gameScreen);
	}
	
	public void setActionListener(ActionListener al) {
		questionButton.addActionListener(al);
		answer1Button.addActionListener(al);
		answer2Button.addActionListener(al);
		answer3Button.addActionListener(al);
		answer4Button.addActionListener(al);
	}
}