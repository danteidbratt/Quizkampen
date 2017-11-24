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
                        System.out.println("0");
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
                        System.out.println("1");
                        w.rs.subjects[w.session.roundCounter].setText("- " + w.session.chosenSubjectName + " -");
                        w.session.setState(w.session.ANSWERQUESTIONS1);
                        w.outGameServer.writeObject(w.session);
                        break;
                    case 2: // ANSWERQUESTIONS1
                        System.out.println("2");
                        w.rs.nextRoundButton.setVisible(true);
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        break;
                    case 3: // ANSWERQUESTIONS2
                        System.out.println("3");
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        w.gs.roundBoxLabel.setText((w.session.roundCounter + 1) + "/" + w.session.getTotalRounds());
                        w.rs.nextRoundButton.setVisible(true);
                        break;
                    case 4: // SHOWOPPONENTANSWERS
                        System.out.println("4");
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