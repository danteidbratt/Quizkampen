package quizkampen;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LobbyScreen extends JPanel{
    private JLabel nextOpponentIs = new JLabel("Your opponent is...");
    JButton subjectOneButton = new JButton("Subject 1");
    JButton subjectTwoButton = new JButton("Subject 2");
    JButton subjectThreeButton = new JButton("Subject 3");
    JButton startButton = new JButton("Start");
    JButton backButton = new JButton("Back");
    
    public LobbyScreen(ActionListener al) {
		setPanel();
		setActionListener(al);
    }
	public void setPanel() {
        setLayout(new BorderLayout());
        add(nextOpponentIs, BorderLayout.NORTH);
        add(subjectOneButton, BorderLayout.WEST);
        add(subjectTwoButton, BorderLayout.CENTER);
        add(subjectThreeButton, BorderLayout.EAST);
        add(startButton, BorderLayout.SOUTH);	
	}
	
	public void setActionListener(ActionListener al) {
		subjectOneButton.addActionListener(al);
		subjectTwoButton.addActionListener(al);
		subjectThreeButton.addActionListener(al);
	}
}
