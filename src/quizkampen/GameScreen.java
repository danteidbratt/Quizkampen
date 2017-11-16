package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.Year;

public class GameScreen extends JPanel implements IPanel {

    private final JPanel centerPanel = new JPanel();
    private final JPanel roundPanel = new JPanel();
    private final JPanel questionPanel = new JPanel();
    private final JLabel topSpace = new JLabel("");
    private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");

    JLabel roundTextLabel = new JLabel("Round");
    JLabel roundBoxLabel = new JLabel("1/2");
    JLabel roundSpace = new JLabel("");
    JPanel roundQuestionsPanel = new JPanel();
    JLabel questionBox1 = new JLabel("");
    JLabel questionBox2 = new JLabel("");
    JButton questionButton = new JButton("Vilken huvudstad är störst i Norden?");
    JButton answer1Button = new JButton("1. Oslo");
    JButton answer2Button = new JButton("2. Stockholm");
    JButton answer3Button = new JButton("3. Helsingfors");
    JButton answer4Button = new JButton("4. Göteborg");

    Color backgroundColor;
    Font buttonFont;

    public GameScreen(Font buttonFont, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.buttonFont = buttonFont;
    }

    @Override
    public void setPanel() {
        setLayout(new BorderLayout());
        topSpace.setPreferredSize(new Dimension(0, 50));
        sideSpaceWest.setPreferredSize(new Dimension(70, 0));
        sideSpaceEast.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 120));

        centerPanel.setLayout(new GridLayout(3, 1));

        roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.Y_AXIS));
        roundPanel.
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
