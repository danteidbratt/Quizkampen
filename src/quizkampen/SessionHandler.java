package quizkampen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionHandler {

    private SessionQ sessionQ;
    protected Window w;

    public SessionHandler(Window w) {
        this.w = w;
    }

    public void checkGame(SessionQ session) {
        this.sessionQ = session;
        try {
            switch (session.getState()) {
                case WAITING4P1USERNAME: // Servern skapas. UserOne skriver in UserName
                    System.out.println("waiting4p1userName");
                    session.setUserNameOne(w.user);
                    w.setPlayerNumber(1);
                    w.rs.setResultScreen(session.getTotalQsInRound(), session.getTotalRounds(),w.getUser().getUserName(), "David");
                    w.rs.setPanel();
                    w.rs.setActionListener(w.ah);

                    w.ls.subjectButtons[0].setText(w.session.getSubject().getName());
                    w.ls.subjectButtons[1].setText(w.session.getSubject().getName());
                    w.ls.subjectButtons[2].setText(w.session.getSubject().getName());

                    w.tempQuestions = new Question[w.session.getTotalQsInRound()];
                    for (int i = 0; i < 3; i++) {
                        w.tempSubjects[i] = w.session.getSubject();
                    }
                    w.ls.setSubjectButtons(w.tempSubjects);
                    w.ls2.opponentLabel.setText(w.session.getUserNameOne().getUserName());

                    w.session.setState(State.WAITING4P2USERNAME);

                    w.add(w.ls);

                    w.outGameServer.writeObject(session); // skickar session -> server från P1
                    w.revalidate();
                    w.repaint();
                    break;

                case WAITING4P2USERNAME: // UserTwo skriver in UserName
                    System.out.println("waiting4p2username");
                    session.setUserNameTwo(w.user);
                    w.setPlayerNumber(2);
                    w.add(w.ls2);
                    w.session.setState(State.WAITINGFORCHOICE);
                    w.outGameServer.writeObject(session);   // skickar session -> server från P2
                    w.revalidate();
                    w.repaint();
                    break;

                case WAITINGFORCHOICE:  // P1 får P2 namn. väljer ämne och spelar
                    System.out.println("Waiting4Choice - should be only P1");
                    w.ls2.opponentLabel.setText(w.session.getUserNameTwo().getUserName());
                    // skickar tillbaka session i ActionHandler när P1 spelat
                    w.session.setState(State.CHOICESENT);
                    w.revalidate();
                    w.repaint();
                    break;

                case CHOICESENT:
                    System.out.println("Choice sent");
                    w.ls2.subjectButton.setText("Valt ämne?");
                    w.ls2.setActionListener(w.ah);
                    w.ls2.readyIconPanel.add(w.ls2.readyButton);
                    w.ls2.subjectIconPanel.add(w.ls2.subjectButton);

                    w.session.setState(State.PLAYGAME);
                    w.ls2.revalidate();
                    w.ls2.repaint();
                    break;

                case PLAYGAME://Chosing Subject, Talar om vems tur de är- väljer ämne.UserTwo får info om ämne.
                    if (session.getUserChosing() == w.user) {
                        session.ChangeUserChosing();
                    }
                    // Set resultat
                    // skriver ut resultat till andra spelaren
                    w.outGameServer.writeObject(w.session);

                    break;

                case GAMEOVER:
                    break;

            }

        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
