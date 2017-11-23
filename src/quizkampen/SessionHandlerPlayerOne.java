/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 *  och öppna templates :)
 */
package quizkampen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionHandlerPlayerOne {

    private SessionQ sessionQ;
    protected Window w;

    public SessionHandlerPlayerOne(Window w) {
        this.w = w;
    }

    public void checkGame() {
        try {
            while (true) {
                w.session = (SessionQ) w.inGameServer.readObject();
                switch (w.session.getState()) {
                    case CHOOSESUBJECT: 
                        w.ls.opponentLabel.setText(w.session.getPlayerNameTwo());
                        for (int i = 0; i < w.ls.subjectButtons.length; i++) {
                            w.ls.subjectButtons[i].setVisible(true);
                        }
//                        w.session.setState(State.PLAYGAME);
//                        w.outGameServer.writeObject(w.session); // skickar session -> server från P1
                        break;

                    case WAITINGFORCHOICE:  // P1 får P2 namn. väljer ämne och spelar
                        System.out.println("Waiting4Choice - should be only P1");
                        w.ls2.opponentLabel.setText(w.session.getPlayerNameTwo());
                        // skickar tillbaka session i ActionHandler när P1 spelat
                        w.session.setState(State.CHOICESENT);
                        w.revalidate();
                        w.repaint();
                        break;

                    case PLAYGAME://Chosing Subject, Talar om vems tur de är- väljer ämne.UserTwo får info om ämne.

                        // Set resultat
                        // skriver ut resultat till andra spelaren
                        w.outGameServer.writeObject(w.session);

                        break;

                    case GAMEOVER:
                        break;

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessionHandlerPlayerOne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
