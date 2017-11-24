package quizkampen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionHandlerPlayerOne extends Thread {

    protected Window w;
    static State s;

    public SessionHandlerPlayerOne(Window w) {
        this.w = w;
    }

    @Override
    public void run() {
        try {
            while (true) {
                w.session = (SessionQ) w.inGameServer.readObject();
                switch (w.session.getState()) {
                    case 0: // CHOOSESUBJECT
                        w.ls.loopAnimation = false;
                        w.ls.resetPanel();
                        System.out.println("vad fan");
                        w.rs.nextRoundButton.setVisible(true);
                        for (int i = 0; i < 3; i++) {
                            w.tempSubjects[i] = w.session.getSubject();
                        }
                        w.ls.setSubjectButtons(w.tempSubjects);
                        break;
                    case 1: // SHOWSUBJECT
                        w.rs.subjects[w.session.roundCounter].setText("- " + w.session.chosenSubjectName + " -");
                        w.session.setState(w.session.ANSWERQUESTIONS1);
                        w.outGameServer.writeObject(w.session);
                    case 2: // ANSWERQUESTIONS1
                        if (w.session.roundCounter > 0) {
                            w.rs.subjects[w.session.roundCounter].setText("- " + w.session.chosenSubjectName + " -");
                            w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        }
                        w.ls2.readyButton.setVisible(true);
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        break;
                    case 4: // SHOWOPPONENTANSWERS
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.session.setState(w.session.CHOOSESUBJECT);
                        w.session.roundCounter++;
                        w.outGameServer.writeObject(w.session);
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