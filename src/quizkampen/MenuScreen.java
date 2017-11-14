
package quizkampen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuScreen extends JPanel{
    
    JLabel logo = new JLabel("QuizFigths");
    JButton newGameButton = new JButton("New Game");
    JButton settingsButton = new JButton("Settings");
    JButton statsButton = new JButton("Stats");
    JButton exitButton = new JButton("Exit");
    
    public MenuScreen(ActionListener al){
        setActionListener(al);
        setPanel();
    }
    
    private void setPanel(){
        setLayout(new GridLayout(5, 1));
        add(logo);
        add(newGameButton);
        add(settingsButton);
        add(statsButton);
        add(exitButton);
    }
    
    private void setActionListener(ActionListener al){
        newGameButton.addActionListener(al);
        settingsButton.addActionListener(al);
        statsButton.addActionListener(al);
        exitButton.addActionListener(al);
    }
}