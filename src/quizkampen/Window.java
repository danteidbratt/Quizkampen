package quizkampen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener{
	WelcomeScreen ws = new WelcomeScreen(this);
	MenuScreen ms = new MenuScreen(this);
	GameMenuScreen gms = new GameMenuScreen(this);
	
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
			revalidate();
			repaint();
		}
		else if(e.getSource() == ws.exitButton || e.getSource() == ms.exitButton) {
			System.exit(0);
		}
		else if(e.getSource() == ms.newGameButton) {
			remove(ms);
			add(ms);
			revalidate();
			repaint();
			
		}
	}
}
