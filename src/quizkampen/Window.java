package quizkampen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener{
        private Font buttonFont = new Font("SansSarif", Font.BOLD, 16);
        private Color backgroundColor = new Color(0,0,255);
        
	WelcomeScreen ws = new WelcomeScreen(this, buttonFont, 0,0,255);
	MenuScreen ms = new MenuScreen(this, buttonFont, backgroundColor);
	GameMenuScreen gms = new GameMenuScreen(this, buttonFont, backgroundColor);
	SettingsScreen ses = new SettingsScreen(this, buttonFont, backgroundColor);
	StatsScreen sts = new StatsScreen(this, buttonFont, backgroundColor);
	LobbyScreen ls = new LobbyScreen(this, buttonFont, backgroundColor);
	GameScreen gs = new GameScreen(this, buttonFont, backgroundColor);
	
	public Window() {
		setTitle("QuizFights");
		add(ws);
		setSize(500, 809);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ws.okButton) {
			remove(ws);
			add(ms);
		}
		else if(e.getSource() == ms.newGameButton) {
			remove(ms);
			add(gms);
		}
		else if(e.getSource() == gms.randomPlayerButton) {
			remove(gms);
			add(ls);
		}
		else if(e.getSource() == ls.subjectOneButton ||
				e.getSource() == ls.subjectTwoButton ||
				e.getSource() == ls.subjectThreeButton) {
			ls.startButton.addActionListener(this);
		}
		else if(e.getSource() == ls.startButton) {
			remove(ls);
			add(gs);
		}
		else if(e.getSource() == ms.settingsButton) {
			remove(ms);
			add(ses);
		}
		else if(e.getSource() == gms.backButton) {
			remove(gms);
			add(ms);
		}
		else if(e.getSource() == ls.backButton) {
			remove(ls);
			add(gms);
		}
		else if(e.getSource() == ses.backButton) {
			remove(ses);
			add(ms);
		}
		else if(e.getSource() == ms.statsButton) {
			remove(ms);
			add(sts);
		}
                else if(e.getSource() == ms.logoutButton) {
                        remove(ms);
                        add(ws);
                }
		else if(e.getSource() == sts.backButton) {
			remove(sts);
			add(ms);
		}
		else if(e.getSource() == ws.exitButton || e.getSource() == ms.exitButton || e.getSource() == gms.exitButton) {
			System.exit(0);
		}
		revalidate();
		repaint();
	}
}
