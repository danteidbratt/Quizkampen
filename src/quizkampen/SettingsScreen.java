package quizkampen;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsScreen extends JPanel{
	private final JLabel loga = new JLabel("Settings");
    JButton backButton = new JButton("Back"); 
    
    public SettingsScreen(ActionListener al) {
        setPanel();
        setActionListener(al);
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
