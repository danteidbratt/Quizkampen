package quizkampen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen extends JPanel {
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
	JPanel questionsPanel = new JPanel();
	List<JLabel> questionBoxes = new ArrayList<>();
//	JLabel questionBox1 = new JLabel("");
//	JLabel questionBox2 = new JLabel("");
//	JLabel questionBox3 = new JLabel("");
//	JLabel questionBox4 = new JLabel("");
//	JLabel questionBox5 = new JLabel("");
	
    JButton questionButton = new JButton("Vilken huvudstad är störst i Norden?");
    JButton answer1Button = new JButton("1. Oslo");
    JButton answer2Button = new JButton("2. Stockholm");
    JButton answer3Button = new JButton("3. Helsingfors");
    JButton answer4Button = new JButton("4. Göteborg");

    Color backgroundColor;
    Font buttonFont;
	int amountOfQuestions = 2;

    public GameScreen(Font buttonFont, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.buttonFont = buttonFont;
    }

    public void setPanel() {
        setLayout(new BorderLayout());
		setBackground(backgroundColor);
		topSpace.setPreferredSize(new Dimension(0, 50));
		sideSpaceWest.setPreferredSize(new Dimension(70, 0));
        sideSpaceEast.setPreferredSize(new Dimension(70, 0));
        bottomSpace.setPreferredSize(new Dimension(0, 120));
		
		centerPanel.setLayout(new GridLayout(3, 1));
		centerPanel.add(roundPanel);
		
		roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.Y_AXIS));
		roundPanel.setBackground(backgroundColor);
		roundTextLabel.setFont(buttonFont);
		roundBoxLabel.setPreferredSize(new Dimension(60, 60));
		roundBoxLabel.setFont(buttonFont);
		roundBoxLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		questionsPanel.setLayout(new FlowLayout());
		questionsPanel.setBackground(backgroundColor);
		for(int i = 0; i < amountOfQuestions; i++) {
			questionBoxes.add(new JLabel(""));
			questionBoxes.get(i).setPreferredSize(new Dimension(60, 37));
			questionBoxes.get(i).setOpaque(true);
			questionBoxes.get(i).setBackground(Color.gray);
			questionBoxes.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
			questionPanel.add(questionBoxes.get(i));
		}
//		questionBox1.setPreferredSize(new Dimension(60, 37));
//		questionBox1.setOpaque(true);
//		questionBox1.setBackground(Color.gray);
//		questionBox1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
//		questionBox2.setPreferredSize(new Dimension(60, 37));
//		questionBox2.setOpaque(true);
//		questionBox2.setBackground(Color.gray);
//		questionBox2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//		questionBox3.setPreferredSize(new Dimension(60, 37));
//		questionBox3.setOpaque(true);
//		questionBox3.setBackground(Color.gray);
//		questionBox3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//		questionBox4.setPreferredSize(new Dimension(60, 37));
//		questionBox4.setOpaque(true);
//		questionBox4.setBackground(Color.gray);
//		questionBox4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//		questionBox5.setPreferredSize(new Dimension(60, 37));
//		questionBox5.setOpaque(true);
//		questionBox5.setBackground(Color.gray);
////		questionBox5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//		questionsPanel.add(questionBox1);
//		questionsPanel.add(questionBox2);
//		questionsPanel.add(questionBox3);
//		questionsPanel.add(questionBox4);
//		questionsPanel.add(questionBox5);
		roundSpace.setPreferredSize(new Dimension(0, 20));
		roundPanel.add(roundTextLabel);
		roundPanel.add(roundBoxLabel);
		roundPanel.add(roundSpace);
		roundPanel.add(questionsPanel);
		
		add(topSpace, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
        add(sideSpaceWest, BorderLayout.WEST);
        add(sideSpaceEast, BorderLayout.EAST);
		add(bottomSpace, BorderLayout.SOUTH);
    }

    public void setActionListener(ActionListener al) {
        questionButton.addActionListener(al);
        answer1Button.addActionListener(al);
        answer2Button.addActionListener(al);
        answer3Button.addActionListener(al);
        answer4Button.addActionListener(al);
    }
}
