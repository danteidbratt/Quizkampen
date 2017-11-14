package quizkampen;
import java.awt.BorderLayout;
import javax.swing.*;

public class LobbyScreen extends JPanel{
    protected JLabel opponentIs = new JLabel("Your opponent is...");
    protected JButton subjectOne= new JButton("Subject 1");
    protected JButton subjectTwo = new JButton("Subject 2");
    protected JButton subjectThree = new JButton("Subject 3");
    protected JButton start = new JButton("Start");
    
    public LobbyScreen(){
        this.setLayout(new BorderLayout());
        this.add(opponentIs, BorderLayout.NORTH);
        this.add(subjectOne, BorderLayout.WEST);
        this.add(subjectTwo, BorderLayout.CENTER);
        this.add(subjectThree, BorderLayout.EAST);
        this.add(start, BorderLayout.SOUTH);
    }
}
