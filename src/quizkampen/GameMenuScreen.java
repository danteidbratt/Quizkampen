
package quizkampen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameMenuScreen extends JPanel {
    
    JLabel loga = new JLabel("QuizFigths");
    JButton randomPlayerButton = new JButton("Random player"); 
    JButton searchButton = new JButton("Search player"); 
    JButton backButton = new JButton("Back"); 
    JButton exitButton = new JButton("Exit"); 
    
    GameMenuScreen(ActionListener al) {
        setPanel();
        setActionListener(al);
    }   
    
    public void setPanel(){
        setLayout(new GridLayout(5, 1));
        loga.setHorizontalAlignment(SwingConstants.CENTER);
        add(loga);
        add(randomPlayerButton);
        add(searchButton);
        add(backButton);
        add(exitButton);
    }
    
    public void setActionListener(ActionListener al){
        randomPlayerButton.addActionListener(al);
        searchButton.addActionListener(al);
        backButton.addActionListener(al);
        exitButton.addActionListener(al);
    }
}