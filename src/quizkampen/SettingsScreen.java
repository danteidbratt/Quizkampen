package quizkampen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsScreen extends JPanel{
	JLabel loga = new JLabel("Settings");
    JButton backButton = new JButton("Back"); 
    
    Color backgroundColor;
    Font buttonFont;
    
    public SettingsScreen(ActionListener al, Font buttonFont, Color backgroundColor) {
                this.backgroundColor = backgroundColor;
                this.buttonFont = buttonFont;
	}
    
    public void setPanel() {
        setLayout(new GridLayout(5, 1));
        loga.setHorizontalAlignment(SwingConstants.CENTER);
        add(loga);
        add(backButton);
    }
    
    public void setActionListener(ActionListener al){
        backButton.addActionListener(al);
    }
}
