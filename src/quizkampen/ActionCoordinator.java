package quizkampen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionCoordinator implements ActionListener{
	WelcomeScreen a = new WelcomeScreen();
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == a.ok) {
			
		}
	}

}
