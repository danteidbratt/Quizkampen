package quizkampen.client;

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
import quizkampen.client.panels.*;
import quizkampen.client.panels.LobbyScreen;
import quizkampen.client.panels.LobbyScreen2;
import quizkampen.client.panels.MasterPanel;
import quizkampen.server.Question;
import quizkampen.client.panels.ResultScreen;

public class ActionHandler implements ActionListener {

    Window w;
    Timer timer;

    public ActionHandler(Window window) {
        this.w = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == w.ws.okButton || e.getSource() == w.ws.userNameInput) {
            String userName = w.ws.userNameInput.getText();
            try {
                w.userServerSocket = new Socket("172.20.201.98", w.portUser);
                w.outUserServer = new ObjectOutputStream(w.userServerSocket.getOutputStream());
                System.out.println("output connected");
                w.inUserServer = new ObjectInputStream(w.userServerSocket.getInputStream());
                System.out.println("inputconnected");
                if (userName != null) {
                    w.outUserServer.writeObject(userName);
                }
                if ((w.user = (User) w.inUserServer.readObject()) != null) {
                    w.setUser(w.user);
                    System.out.println(w.user.getUserName());
                    w.remove(w.ws);
                    w.add(w.ms);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not connect to server. "
                        + "\nPlease try again later.",
                        "QuizFights - Server problem",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (ClassNotFoundException | NullPointerException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == w.ms.newGameButton) {
            w.remove(w.ms);
            w.add(w.gms);
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
            w.sts.setUserData();
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
            w.color1 = new Color(20, 0, 160);
            w.color2 = Color.YELLOW;
            w.color3 = Color.WHITE;
            w.color4 = new Color(20, 0, 185);
            w.panelList.forEach(x -> x.setCustomColor(w.color1, w.color2, w.color3, w.color4));
        } else if (e.getSource() == w.ses.green) {
            w.color1 = new Color(80, 180, 0);
            w.color2 = Color.WHITE;
            w.color3 = Color.WHITE;
            w.color4 = new Color(70, 160, 0);
            w.panelList.forEach(x -> x.setCustomColor(w.color1, w.color2, w.color3, w.color4));
        } else if (e.getSource() == w.ses.red) {
            w.color1 = new Color(190, 0, 0);
            w.color2 = Color.WHITE;
            w.color3 = Color.WHITE;
            w.color4 = new Color(170, 0, 0);
            w.panelList.forEach(x -> x.setCustomColor(w.color1, w.color2, w.color3, w.color4));
        } else if (e.getSource() == w.gms.randomPlayerButton) {
            try {
                w.rs = new ResultScreen();
                w.ls = new LobbyScreen(w);
                w.ls2 = new LobbyScreen2();
                w.gs = new GameScreen(w);
                List<MasterPanel> gamePanels = new ArrayList<>();
                gamePanels = Arrays.asList(w.ls, w.ls2, w.gs);
                gamePanels.forEach(a -> {
                    a.setCustomColor(w.color1, w.color2, w.color3, w.color4);
                    a.setActionListener(this);
                });
                w.gameServerSocket = new Socket("172.20.201.98", w.portGame);
                w.outGameServer = new ObjectOutputStream(w.gameServerSocket.getOutputStream());
                w.inGameServer = new ObjectInputStream(w.gameServerSocket.getInputStream());
                w.session = (SessionQ) w.inGameServer.readObject();
                if (w.session.getState() == w.session.FIRST) {
                    w.session.setPlayerNameOne(w.getUser().getUserName());
                    w.session.setUserOne(w.getUser());
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
                    w.session.setUserTwo(w.getUser());
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
            w.rs.setSubject(w.session.chosenSubjectName, w.session.roundCounter);
            w.remove(w.ls);
            w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
            w.gs.setNumberofQuestions(w.session.getTotalQsInRound());
            w.gs.roundBoxLabel.setText(String.valueOf(w.session.roundCounter + 1) + "/" + String.valueOf(w.session.getTotalRounds()));
            w.add(w.gs);
            timer = new Timer();
            timer.start();
        } else if (e.getSource() == w.ls2.readyButton) {
            w.session.clearOpponentAnswers();
            w.remove(w.ls2);
            w.gs.setNumberofQuestions(w.session.getTotalQsInRound());
            w.gs.roundBoxLabel.setText(String.valueOf(w.session.roundCounter + 1) + "/" + String.valueOf(w.session.getTotalRounds()));
            w.add(w.gs);
            timer = new Timer();
            timer.start();
        } else if (e.getSource() == w.gs.nextQuestionButton) {
            w.gs.setButtonActionListener(this);
            if (w.questionCounter < (w.session.getTotalQsInRound() - 1)) {
                w.gs.setNextQuestion(w.session.tempQuestions[++w.questionCounter]);
                timer = new Timer();
                timer.start();
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
                timer = new Timer();
                timer.start();
            } else if (w.session.getState() == w.session.CHOOSESUBJECT) {
                w.remove(w.rs);
                w.ls.resetPanel();
                w.ls.setActionListener(this);
                w.add(w.ls);
            }
        } else {
            for (int i = 0; i < w.ls.subjectButtons.length; i++) {
                if (e.getSource() == w.ls.subjectButtons[i]) {
                    for (int j = 0; j < w.session.tempQuestions.length; j++) {
                        w.session.tempQuestions[j] = w.tempSubjects[i].getQuestion();
                    }
                    w.ls.subjectButtons[i].setBackground(Color.YELLOW);
                    w.ls.subjectButtons[i].setOpaque(true);
                    w.session.chosenSubjectName = w.tempSubjects[i].getName();
                    try {
                        w.session.setState(w.session.SHOWSUBJECT);
                        w.outGameServer.writeObject(w.session);
                    } catch (IOException ex) {
                        Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    w.ls.removeActionListener(this);
                    w.ls.startButton.setVisible(true);
                }
            }
            for (int i = 0; i < w.gs.answerButtons.length; i++) {
                if (e.getSource() == w.gs.answerButtons[i]) {
                    timer.stopTimer();
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
                    w.gs.removeActionListeners(this);
                    if (w.questionCounter == w.session.getTotalQsInRound() - 1) {
                        w.gs.nextQuestionButton.setText("Show Results");
                    }
                    if (w.getUser().getUserName() == w.session.getPlayerNameOne()) {  // Set score i session
                        w.session.setScoreUserOne(w.rs.getPlayerScore());
                    } else if (w.getUser().getUserName() == w.session.getPlayerNameTwo()) {
                        w.session.setScorePlayerTwo(w.rs.getPlayerScore());
                    }
                }
            }
        }
        w.revalidate();
        w.repaint();
    }

    public class Timer extends Thread {

        int timerLength;
        private boolean keepCounting;

        public Timer() {
            this.timerLength = w.session.getTimerLength();
        }

        public void stopTimer() {
            keepCounting = false;
        }

        @Override
        public void run() {
            System.out.println("Timer fråga: " + w.questionCounter);
            try {
                w.gs.buttonPanel.remove(w.gs.nextQuestionButton);
                w.gs.buttonPanel.add(w.gs.timerPanel);
                keepCounting = true;
                int milliseconds = w.session.getTimerLength() * 10;
                int i;
                for (i = w.gs.timerBar.length - 1; i >= 0 && keepCounting == true; i--) {
                    Thread.sleep(milliseconds);
                    w.gs.timerBar[i].setBackground(w.gs.backgroundColor);
                    w.gs.revalidate();
                    w.gs.repaint();
                }
                if (i < 0) {
                    w.gs.removeActionListeners(w.ah);
                    w.gs.questionBoxes.get(w.questionCounter).setBackground(Color.RED);
                    w.rs.boxes[w.session.roundCounter][w.questionCounter].setBackground(Color.RED);
                    w.gs.revealCorrectAnswer();
                    if (w.questionCounter == w.session.getTotalQsInRound() - 1) {
                        w.gs.nextQuestionButton.setText("Show Results");
                    }
                }
                w.gs.buttonPanel.remove(w.gs.timerPanel);
                w.gs.buttonPanel.add(w.gs.nextQuestionButton);
                w.gs.revalidate();
                w.gs.repaint();
                for (int j = 0; j < w.gs.timerBar.length; j++) {
                    w.gs.timerBar[j].setBackground(Color.YELLOW);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
