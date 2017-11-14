package quizkampen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener{
	WelcomeScreen ws = new WelcomeScreen(this);
	MenuScreen ms = new MenuScreen(this);
	GameMenuScreen gms = new GameMenuScreen(this);
	SettingsScreen ses = new SettingsScreen(this);
	StatsScreen sts = new StatsScreen(this);
	
	public Window() {
		add(ws);
		setSize(800, 800);
		setLocationRelativeTo(null);
		setVisible(true);
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
		else if(e.getSource() == ms.settingsButton) {
			remove(ms);
			add(ses);
		}
		else if(e.getSource() == ses.backButton) {
			remove(ses);
			add(ms);
		}
		else if(e.getSource() == gms.backButton) {
			remove(gms);
			add(ms);
		}
		else if(e.getSource() == ws.exitButton || e.getSource() == ms.exitButton || e.getSource() == gms.exitButton) {
			System.exit(0);
		}
		revalidate();
		repaint();
	}
}
