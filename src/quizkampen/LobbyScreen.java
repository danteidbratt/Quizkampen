package quizkampen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LobbyScreen extends JPanel{
    private final JLabel topSpace = new JLabel("");
    private final JLabel sideSpaceWest = new JLabel("");
    private final JLabel sideSpaceEast = new JLabel("");
    private final JLabel bottomSpace = new JLabel("");
    
    JPanel centerPanel = new JPanel();
    
    JPanel opponentPanel = new JPanel();
    JPanel topBottomPanel = new JPanel();
    JLabel topBottomSpace1 = new JLabel("");
    JLabel topBottomSpace2 = new JLabel("");
    JLabel nextOpponentIs = new JLabel("Your opponent is...");
    JLabel opponentLabel = new JLabel("God");
    
    JPanel subjectPanel = new JPanel();
    JButton subjectOneButton = new JButton("Subject 1");
    JButton subjectTwoButton = new JButton("Subject 2");
    JButton subjectThreeButton = new JButton("Subject 3");
    
    JPanel bottomPanel = new JPanel();
    JLabel bottomTopSpace = new JLabel("");
    JLabel bottomBottomSpace = new JLabel("");
    JPanel buttonPanel = new JPanel();
    JButton startButton = new JButton("Start");
    JButton backButton = new JButton("Back");
    private Color backgroundColor = new Color(0,0,255);
    
    public LobbyScreen(ActionListener al) {
        setPanel();
        setActionListener(al);
    }
	public void setPanel() {
            setLayout(new BorderLayout());
            setBackground(backgroundColor);
            
            topSpace.setPreferredSize(new Dimension(0, 100));
            sideSpaceEast.setPreferredSize(new Dimension(100, 0));
            sideSpaceWest.setPreferredSize(new Dimension(100, 0));
            bottomSpace.setPreferredSize(new Dimension(0, 120));
            
            opponentPanel.setLayout(new BorderLayout(0, 20));
            opponentPanel.setBackground(backgroundColor);
            topBottomPanel.setLayout(new GridLayout(2, 1, 0, 5));
            topBottomPanel.setBackground(backgroundColor);
            opponentLabel.setBackground(backgroundColor);
            opponentLabel.setOpaque(true);
            opponentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            opponentLabel.setForeground(Color.BLACK);
            opponentLabel.setHorizontalAlignment(SwingConstants.CENTER);
            opponentLabel.setForeground(Color.WHITE);
            opponentLabel.setFont(new Font("SansSerif", 3, 30));
            topBottomSpace1.setBackground(backgroundColor);
            topBottomSpace1.setOpaque(true);
//            topBottomSpace2.setBackground(backgroundColor);
//            topBottomSpace2.setOpaque(true);
            topBottomPanel.add(opponentLabel);
            topBottomPanel.add(topBottomSpace1);
//            topBottomPanel.add(topBottomSpace2);
            nextOpponentIs.setFont(new Font("SansSerif", 1, 15));
            nextOpponentIs.setForeground(Color.GREEN);
            
            opponentPanel.add(nextOpponentIs, BorderLayout.NORTH);
            opponentPanel.add(topBottomPanel, BorderLayout.CENTER);
            
            centerPanel.setLayout(new GridLayout(3, 1, 0, 20));
            centerPanel.setBackground(backgroundColor);
            subjectPanel.setLayout(new GridLayout(1, 3, 10, 0));
            subjectPanel.setBackground(backgroundColor);
            subjectPanel.add(subjectOneButton);
            subjectPanel.add(subjectTwoButton);
            subjectPanel.add(subjectThreeButton);
            
            bottomPanel.setLayout(new GridLayout(3, 1, 0, 0));
            bottomPanel.setBackground(backgroundColor);
            buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
            buttonPanel.setBackground(backgroundColor);
            buttonPanel.add(backButton);
            buttonPanel.add(startButton);
            bottomPanel.add(bottomTopSpace);
            bottomPanel.add(buttonPanel);
            bottomPanel.add(bottomBottomSpace);
            
            centerPanel.add(opponentPanel);
            centerPanel.add(subjectPanel);
            centerPanel.add(bottomPanel);
        
            add(topSpace, BorderLayout.NORTH);
            add(sideSpaceEast, BorderLayout.EAST);
            add(sideSpaceWest, BorderLayout.WEST);
            add(bottomSpace, BorderLayout.SOUTH);
            add(centerPanel, BorderLayout.CENTER);
	}
	
	public void setActionListener(ActionListener al) {
		subjectOneButton.addActionListener(al);
		subjectTwoButton.addActionListener(al);
		subjectThreeButton.addActionListener(al);
	}
}
