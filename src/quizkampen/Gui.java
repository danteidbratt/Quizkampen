package quizkampen;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private Client client;

    /* Skiss
     *  En frame -> två paneler.
     *  En för spelet och en för knapparna. */
    Gui(Client client) {
        this.client = client;
        startGui();
    }
    
    public void startGui() {
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

        /* Buttons */
        JButton forfeit = new JButton("Ge upp");
        JButton findOpponent = new JButton("Hitta motståndare");

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

         /* Button panel */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

        buttonPanel.add(forfeit);
        buttonPanel.add(findOpponent);

        /* Game panel */
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));

        /* Gamepanel */
        gamePanel.add(gameStatsPanel);
        gamePanel.add(questionPanel);
        gamePanel.add(answerPanel);

        /* Frame */
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizkampen");
        setLocationRelativeTo(null);

        add(gamePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.LINE_END);
    }
}