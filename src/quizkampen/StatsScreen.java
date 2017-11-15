package quizkampen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StatsScreen extends JPanel{
    
    JLabel test1 = new JLabel("Name: ");
    JLabel test2 = new JLabel("Games Played: ");
    JLabel test3 = new JLabel("Wins: ");
    JLabel test4 = new JLabel("Losses: ");
    JLabel test5 = new JLabel("Age?");
    JButton backButton = new JButton("Back");
    
    private Color backgroundColor = new Color(0,0,255);
    Font buttonFont;

    public StatsScreen(ActionListener al, Font buttonFont, Color backgroundColor) {
                this.backgroundColor = backgroundColor;
                this.buttonFont = buttonFont;
		setPanel();
		setActionListener(al);
	}
    
    public void setPanel(){
        setLayout(new GridLayout(6, 1));
        add(test1);
        add(test2);
        add(test3);
        add(test4);
        add(test5);
        add(backButton);
    }
    
    public void setActionListener(ActionListener al){
        backButton.addActionListener(al);
    }
}
