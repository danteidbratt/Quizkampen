package quizkampen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
                w.rs = new ResultScreen();
                w.ls = new LobbyScreen(w);
                w.ls2 = new LobbyScreen2();
                w.gs = new GameScreen();
                List<MasterPanel> gamePanels = new ArrayList<>();
                gamePanels = Arrays.asList(w.ls, w.ls2, w.gs);
                gamePanels.forEach(a -> {
                    a.setPanel();
                    a.setActionListener(this);
                });
                w.gameServerSocket = new Socket("127.0.0.1", w.portGame);
                w.outGameServer = new ObjectOutputStream(w.gameServerSocket.getOutputStream());
                w.inGameServer = new ObjectInputStream(w.gameServerSocket.getInputStream());
                w.session = (SessionQ) w.inGameServer.readObject();
                if (w.session.getState() == w.session.FIRST) {
                    w.session.setPlayerNameOne(w.getUser().getUserName());
                    w.session.setState(w.session.SECOND);
                    w.outGameServer.writeObject(w.session);
                    w.session.tempQuestions = new Question[w.session.getTotalQsInRound()];
                    w.remove(w.gms);
                    w.ls.animation.start();
                    w.ls.chooseSubjectLabel.setVisible(false);
                    for (int i = 0; i < w.ls.subjectButtons.length; i++) {
                        w.ls.subjectButtons[i].setVisible(false);
                    }
                    w.add(w.ls);
                    w.sh1 = new SessionHandlerPlayerOne(w);
                    w.sh1.start();
                } else {
                    w.session.setPlayerNameTwo(w.getUser().getUserName());
                    w.session.setState(w.session.CHOOSESUBJECT);
                    w.outGameServer.writeObject(w.session);
                    w.ls2.opponentLabel.setText(w.session.getPlayerNameOne());
                    w.ls.opponentLabel.setText(w.session.playerNameOne);
                    w.remove(w.gms);
                    w.add(w.ls2);
                    w.revalidate();
                    w.repaint();
                    w.sh2 = new SessionHandlerPlayerTwo(w);
                    w.sh2.start();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not connect to server. \nPlease try again later.", "QuizFights - Server problem", JOptionPane.PLAIN_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == w.ls.startButton) {
            for (int i = 0; i < 3; i++) {
                w.ls.subjectButtons[i].setBackground(new JButton().getBackground());
            }
            w.rs.setActionListener(this);
            w.rs.setSubject(w.session.chosenSubjectName, w.session.roundCounter);
            w.remove(w.ls);
            w.gs.setNumberofQuestions(w.session.getTotalQsInRound());
            w.gs.roundBoxLabel.setText(String.valueOf(w.session.roundCounter + 1) + "/" + String.valueOf(w.session.getTotalRounds()));
            w.add(w.gs);
        } else if (e.getSource() == w.ls2.readyButton) {
            w.session.clearOpponentAnswers();
            w.remove(w.ls2);
            w.gs.setNumberofQuestions(w.session.getTotalQsInRound());
            w.gs.roundBoxLabel.setText(String.valueOf(w.session.roundCounter + 1) + "/" + String.valueOf(w.session.getTotalRounds()));
            w.add(w.gs);
        } else if (e.getSource() == w.gs.nextQuestionButton) {
            w.gs.setButtonActionListener(this);
            if (w.questionCounter < (w.session.getTotalQsInRound() - 1)) {
                w.gs.setNextQuestion(w.session.tempQuestions[++w.questionCounter]);
            } else {
                w.gs.resetColors();
                w.questionCounter = 0;
                w.remove(w.gs);
                w.add(w.rs);
                if (w.session.getState() == w.session.ANSWERQUESTIONS1) {
                    w.session.setState(w.session.ANSWERQUESTIONS2);
                    w.rs.nextRoundButton.setVisible(false);
                } else if (w.session.getState() == w.session.ANSWERQUESTIONS2) {
                    w.session.setState(w.session.SHOWOPPONENTANSWERS);
                }
                if (w.session.roundCounter >= w.session.getTotalRounds() - 1) {
                    if (Integer.parseInt(w.rs.leftNumber.getText()) == Integer.parseInt(w.rs.rightNumber.getText())) {
                        w.rs.nextRoundButton.setText("Draw");
                    } else if (Integer.parseInt(w.rs.leftNumber.getText()) > Integer.parseInt(w.rs.rightNumber.getText())) {
                        w.rs.nextRoundButton.setText("You Win");
                    } else {
                        w.rs.nextRoundButton.setText("You Lose");
                    }
                }
                try {
                    w.outGameServer.writeObject(w.session);
                } catch (IOException ex) {
                    Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (e.getSource() == w.rs.nextRoundButton) {
            w.session.clearOpponentAnswers();
            if ((w.session.roundCounter >= w.session.getTotalRounds())) {
                w.remove(w.rs);
                w.add(w.ms);
            } else if (w.session.getState() == w.session.ANSWERQUESTIONS2) {
                w.remove(w.rs);
                w.add(w.gs);
            } else if (w.session.getState() == w.session.CHOOSESUBJECT) {
                w.remove(w.rs);
                w.ls.resetPanel();
                w.ls.setActionListener(this);
                w.add(w.ls);
            }
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
        } else {
            for (int i = 0; i < w.ls.subjectButtons.length; i++) {
                if (e.getSource() == w.ls.subjectButtons[i]) {
                    for (int j = 0; j < w.session.tempQuestions.length; j++) {
                        w.session.tempQuestions[j] = w.tempSubjects[i].getQuestion();
                    }
                    w.ls.subjectButtons[i].setBackground(Color.YELLOW);
                    w.ls.subjectButtons[i].setOpaque(true);
                    w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                    w.session.chosenSubjectName = w.tempSubjects[i].getName();
                    try {
                        w.session.setState(w.session.SHOWSUBJECT);
                        w.outGameServer.writeObject(w.session);
                    } catch (IOException ex) {
                        Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    w.ls.removeActionListener(this);
                    w.ls.startButton.setVisible(true);
                    w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                }
            }
            for (int i = 0; i < w.gs.answerButtons.length; i++) {
                if (e.getSource() == w.gs.answerButtons[i]) {
                    w.gs.colorChosenButton(w.gs.answerButtons[i]);
                    w.gs.revealCorrectAnswer();
                    if (w.gs.answerButtons[i].getIsCorrect()) {
                        w.session.opponentsAnswers[w.questionCounter] = true;
                        w.rs.increasePlayerScore();
                        w.rs.boxes[w.session.roundCounter][w.questionCounter].setBackground(Color.GREEN);
                        w.gs.questionBoxes.get(w.questionCounter).setBackground(Color.GREEN);
                    } else {
                        w.rs.boxes[w.session.roundCounter][w.questionCounter].setBackground(Color.RED);
                        w.gs.questionBoxes.get(w.questionCounter).setBackground(Color.RED);
                    }
                    w.gs.nextQuestionButton.setVisible(true);
                    w.gs.removeActionListeners(this);
                    if (w.questionCounter == w.session.getTotalQsInRound() - 1) {
                        w.gs.nextQuestionButton.setText("Show Results");
                    }
                }
            }
        }
        w.revalidate();
        w.repaint();
    }
}
