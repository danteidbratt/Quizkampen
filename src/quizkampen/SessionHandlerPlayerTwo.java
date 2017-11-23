package quizkampen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionHandlerPlayerTwo {

    private SessionQ sessionQ;
    protected Window w;

    public SessionHandlerPlayerTwo(Window w) {
        this.w = w;
    }

    public void checkGame() {
//        try {
//            switch (session.getState()) {
//
//
//                case WAITING4P2USERNAME: // UserTwo skriver in UserName
//                    System.out.println("waiting4p2username");
//                    session.setUserNameTwo(w.user);
//                    w.setPlayerNumber(2);
//                    w.ls2.opponentLabel.setText(w.session.getUserNameOne().getUserName());
//                    w.add(w.ls2);
//                    w.session.setState(State.WAITINGFORCHOICE);
//                    w.outGameServer.writeObject(session);   // skickar session -> server från P2
//                    w.revalidate();
//                    w.repaint();
//                    break;
//
//
//                case CHOICESENT:
//                    System.out.println("Choice sent");
//                    w.ls2.subjectButton.setText("Valt ämne?");
//                    w.ls2.setActionListener(w.ah);
//                    w.ls2.readyIconPanel.add(w.ls2.readyButton);
//                    w.ls2.subjectIconPanel.add(w.ls2.subjectButton);
//
//                    w.session.setState(State.PLAYGAME);
//                    w.ls2.revalidate();
//                    w.ls2.repaint();
//                    break;
//
//                case PLAYGAME://Chosing Subject, Talar om vems tur de är- väljer ämne.UserTwo får info om ämne.
//                    if (session.getUserChosing() == w.user) {
//                        session.ChangeUserChosing();
//                    }
//                    // Set resultat
//                    // skriver ut resultat till andra spelaren
//                    w.outGameServer.writeObject(w.session);
//
//                    break;
//
//                case GAMEOVER:
//                    break;
//
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
