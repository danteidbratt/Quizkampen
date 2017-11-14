package quizkampen;

import javax.swing.JFrame;

public class Window extends JFrame{
	WelcomeScreen ws = new WelcomeScreen();
	public Window() {
		add(ws);
		setSize(800, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
