package quizkampen;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsScreen extends JPanel{
	private final JLabel loga = new JLabel("Settings");
	private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
	private final JRadioButton blue = new JRadioButton("Blue", true);
	private final JRadioButton red = new JRadioButton("Red", false);
	private final JRadioButton yellow = new JRadioButton("Yellow", false);
	ButtonGroup buttonGroup = new ButtonGroup();
    JButton backButton = new JButton("Back"); 
    
    Color backgroundColor;
    Font buttonFont;
    
    public SettingsScreen(ActionListener al, Font buttonFont, Color backgroundColor) {
                this.backgroundColor = backgroundColor;
                this.buttonFont = buttonFont;
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
