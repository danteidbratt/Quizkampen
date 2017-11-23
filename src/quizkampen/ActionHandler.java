package quizkampen;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionHandler implements ActionListener {

    Window w;

    public ActionHandler(Window window) {
        this.w = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == w.ws.okButton || e.getSource() == w.ws.userNameInput) {
            String userName = w.ws.userNameInput.getText();
            try {
                if (userName != null) {
                    w.outUserServer.writeObject(userName);
                }
                if ((w.user = (User) w.inUserServer.readObject()) != null) {
                    w.setUser(w.user);
                    System.out.println(w.user.getUserName());
                }
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            w.remove(w.ws);
            w.add(w.ms);
        } else if (e.getSource() == w.ms.newGameButton) {
            w.remove(w.ms);
            w.add(w.gms);
        } else if (e.getSource() == w.gms.randomPlayerButton) {
            try {
                w.remove(w.gms);

                w.gameServerSocket = new Socket("127.0.0.1", w.portGame);
                w.outGameServer = new ObjectOutputStream(w.gameServerSocket.getOutputStream());
                w.inGameServer = new ObjectInputStream(w.gameServerSocket.getInputStream());
                w.session = (SessionQ) w.inGameServer.readObject();
                if (w.session.getUserNameOne() == null) {
                    w.session.setUserNameOne(w.user);
                    w.setPlayerNumber(1);
                } else {
                    w.session.setUserNameTwo(w.user);
                    w.setPlayerNumber(2);
                }
                w.outGameServer.writeObject(w.session);

                System.out.println("Du är spelare nr: " + w.getPlayerNumber());

//                SessionHandler sessionHandler = new SessionHandler(w.session);

                w.outGameServer.writeObject(w.session);

                w.rs.setResultScreen(w.session.getTotalQsInRound(), w.session.getTotalRounds(), "Pronut", "David");
                w.rs.setPanel();
                w.rs.setActionListener(this);

                w.tempQuestions = new Question[w.session.getTotalQsInRound()];
                for (int i = 0; i < 3; i++) {
                    w.tempSubjects[i] = w.session.getSubject();
                }
                w.ls.setSubjectButtons(w.tempSubjects);

                w.add(w.ls);
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == w.ls.startButton) {
            w.remove(w.ls);
            w.gs.setNumberofQuestions(w.session.getTotalQsInRound());
            w.gs.roundBoxLabel.setText(String.valueOf(w.roundCounter + 1) + "/" + String.valueOf(w.session.getTotalRounds()));
            w.add(w.gs);
        } else if (e.getSource() == w.gs.nextQuestionButton) {
            if (w.questionCounter < w.session.getTotalQsInRound() - 1) {
                w.gs.setNextQuestion(w.tempQuestions[w.questionCounter++]);
                w.gs.setButtonActionListener(this);

            } else {
                w.gs.setButtonActionListener(this);
                w.gs.resetColors();
                w.remove(w.gs);
                if (w.roundCounter == w.session.getTotalRounds() - 1) {
                    w.rs.nextRoundButton.setText("YOU WIN");
                }
                w.add(w.rs);
            }
        } else if (e.getSource() == w.rs.nextRoundButton) {
            w.remove(w.rs);
            w.roundCounter++;
            w.questionCounter = 0;
            w.ls.resetPanel();
            for (int i = 0; i < 3; i++) {
                w.tempSubjects[i] = w.session.getSubject();
            }
            w.ls.setSubjectButtons(w.tempSubjects);
            w.add(w.ls);
        } else if (e.getSource() == w.ms.settingsButton) {
            w.remove(w.ms);
            w.add(w.ses);
        } else if (e.getSource() == w.gms.backButton) {
            w.remove(w.gms);
            w.add(w.ms);
        } else if (e.getSource() == w.ses.backButton) {
            w.remove(w.ses);
            w.add(w.ms);
        } else if (e.getSource() == w.ms.statsButton) {
            w.remove(w.ms);
            w.add(w.sts);
        } else if (e.getSource() == w.ms.logoutButton) {
            w.remove(w.ms);
            w.add(w.ws);
        } else if (e.getSource() == w.sts.backButton) {
            w.remove(w.sts);
            w.add(w.ms);
        } else if (e.getSource() == w.ws.exitButton || e.getSource() == w.ms.exitButton || e.getSource() == w.gms.exitButton) {
            System.exit(0);
        } else if (e.getSource() == w.ses.blue) {
            w.panelList.forEach(x -> x.setCustomColor(new Color(20, 0, 150), Color.YELLOW, Color.WHITE));
        } else if (e.getSource() == w.ses.green) {
            w.panelList.forEach(x -> x.setCustomColor(new Color(80, 180, 0), Color.WHITE, Color.WHITE));
        } else if (e.getSource() == w.ses.red) {
            w.panelList.forEach(x -> x.setCustomColor(new Color(190, 0, 0), Color.WHITE, Color.WHITE));
        }

        for (int i = 0; i < w.ls.subjectButtons.length; i++) {
            if (e.getSource() == w.ls.subjectButtons[i]) {
                for (int j = 0; j < w.tempQuestions.length; j++) {
                    w.tempQuestions[j] = w.tempSubjects[i].getQuestion();
                }
                w.ls.subjectButtons[i].setBackground(Color.YELLOW);
                w.ls.subjectButtons[i].setBorderPainted(false);
                w.ls.subjectButtons[i].setOpaque(true);
                w.ls.startButton.setVisible(true);
                w.tempIndex = i;
                w.gs.setNextQuestion(w.tempQuestions[w.questionCounter++]);
            }
        }

        for (int i = 0; i < w.gs.answerButtons.length; i++) {
            if (e.getSource() == w.gs.answerButtons[i]) {
                w.gs.colorChosenButton(w.gs.answerButtons[i]);
                w.gs.revealCorrectAnswer();
                if (w.gs.answerButtons[i].getIsCorrect()) {
                    w.rs.increasePlayerScore();
                    w.rs.boxes[w.roundCounter][w.questionCounter].setBackground(Color.GREEN);
                    w.gs.questionBoxes.get(w.questionCounter).setBackground(Color.GREEN);
                } else {
                    w.rs.boxes[w.roundCounter][w.questionCounter].setBackground(Color.RED);
                    w.gs.questionBoxes.get(w.questionCounter).setBackground(Color.RED);
                }
                w.gs.nextQuestionButton.setVisible(true);
                w.gs.removeActionListeners(this);
                if (w.questionCounter == w.session.getTotalQsInRound() - 1) {
                    w.gs.nextQuestionButton.setText("Show Results");
                }
            }
        }
        w.revalidate();
        w.repaint();
    }

}
