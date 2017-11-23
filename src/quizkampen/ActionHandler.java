package quizkampen;

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
                w.gameServerSocket = new Socket("127.0.0.1", w.portGame);   // ÖPPNAR STRÖM TILL GAME-SERVERN
                w.outGameServer = new ObjectOutputStream(w.gameServerSocket.getOutputStream());
                w.inGameServer = new ObjectInputStream(w.gameServerSocket.getInputStream());
                w.session = (SessionQ) w.inGameServer.readObject();
                System.out.println("hej1");

                w.playerNumber = w.session.getPlayerNumber();

                if (w.playerNumber == 1) {      // sätter p1 och p2 + deras sessionHandlers
                    w.session.tempQuestions = new Question[w.session.getTotalQsInRound()];
                    w.session.setPlayerNameOne(w.getUser().getUserName());
                    w.outGameServer.writeObject(w.session);
                    for (int i = 0; i < 3; i++) {
                        w.tempSubjects[i] = w.session.getSubject();
                    }
                    w.ls.setSubjectButtons(w.tempSubjects);
                    w.sh1 = new SessionHandlerPlayerOne(w);
                    w.add(w.ls);
                    System.out.println("hej2");
                    w.revalidate();
                    w.repaint();
                    w.sh1.checkGame();

                } else {
                    w.session.setPlayerNameTwo(w.getUser().getUserName());
                    w.outGameServer.writeObject(w.session);
                    w.ls2.opponentLabel.setText(w.session.getPlayerNameOne());
                    w.sh2 = new SessionHandlerPlayerTwo(w);
                    w.sh2.checkGame();
                }
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == w.ls.startButton) {
            if (w.playerNumber == 1) {
                w.rs.setResultScreen(w.session.getTotalQsInRound(), w.session.getTotalRounds(), w.user.getUserName(), w.session.getPlayerNameTwo());
            } else {
                w.rs.setResultScreen(w.session.getTotalQsInRound(), w.session.getTotalRounds(), w.user.getUserName(), w.session.getPlayerNameOne());
            }
            w.rs.setPanel();
            w.rs.setActionListener(this);
            w.remove(w.ls);
            w.gs.setNumberofQuestions(w.session.getTotalQsInRound());
            w.gs.roundBoxLabel.setText(String.valueOf(w.roundCounter + 1) + "/" + String.valueOf(w.session.getTotalRounds()));
            w.add(w.gs);
        } else if (e.getSource() == w.ls2.readyButton) {    // När spelare 2 trycker redo
            w.remove(w.ls2);

            w.gs.setNumberofQuestions(w.session.getTotalQsInRound());
            w.gs.roundBoxLabel.setText(String.valueOf(w.roundCounter + 1) + "/" + String.valueOf(w.session.getTotalRounds()));

            w.add(w.gs);
        } else if (e.getSource() == w.gs.nextQuestionButton) {
            if (w.questionCounter < w.session.getTotalQsInRound() - 1) {
                w.gs.setNextQuestion(w.session.tempQuestions[++w.questionCounter]);
                w.gs.setButtonActionListener(this);

            } else {
//
//                try {
//                    w.session.setState(State.CHOICESENT);       // När P1 är klar - skicka ämne till P2
//                    w.session.chosenSubjectName = w.tempSubjects[w.tempIndex];
//                    w.outGameServer.writeObject(w.session);
//                } catch (IOException ex) {
//                    Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }

                w.gs.setButtonActionListener(this);
                w.gs.resetColors();
                w.remove(w.gs);
                if (w.roundCounter == w.session.getTotalRounds() - 1) {
                    w.rs.nextRoundButton.setText("YOU WIN");
                }

                // Skicka tillbaka valt ämne + resultat från P1 till P2
                if (w.sh1 != null) {          // KOLLAR STATE i SessionHandler
                    w.sh1.checkGame();
                } else if (w.sh2 != null) {
                    w.sh2.checkGame();
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
                for (int j = 0; j < w.session.tempQuestions.length; j++) {
                    w.session.tempQuestions[j] = new Question();
                    w.session.tempQuestions[j] = w.tempSubjects[i].getQuestion();
                }
                w.ls.subjectButtons[i].setBackground(Color.YELLOW);
                w.ls.subjectButtons[i].setBorderPainted(false);
                w.ls.subjectButtons[i].setOpaque(true);
                w.ls.startButton.setVisible(true);
                w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);

                w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                w.session.chosenSubjectName = w.tempSubjects[i].getName();
                try { // skickar valt ämne till server -> till P2
                    w.outGameServer.writeObject(w.session);
                } catch (IOException ex) {
                    Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
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
