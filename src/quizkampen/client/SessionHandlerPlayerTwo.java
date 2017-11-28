package quizkampen.client;

import java.io.IOException;

public class SessionHandlerPlayerTwo extends Thread {

    protected Window w;
    protected SessionQ session; 
    
    public SessionHandlerPlayerTwo(Window w) {
        this.w = w;
    }

    @Override
    public void run() {
        w.ls.removeActionListener(w.ah);
        try {
            while (w.session.getState() != w.session.GAMEOVER) {
                w.session = (SessionQ) w.inGameServer.readObject();
                switch (w.session.getState()) {
                    case 0: //CHOOSESUBJECT
                        w.rs.nextRoundButton.setVisible(true);
                        w.ls.resetPanel();
                        for (int i = 0; i < 3; i++) {
                            w.tempSubjects[i] = w.session.getSubject();
                        }
                        w.ls.setSubjectButtons(w.tempSubjects);
                        break;
                    case 1: //SHOWSUBJECT
                        if (w.session.roundCounter == 0) {
                            w.ls2.subjectButton.setText(w.session.chosenSubjectName);
                            w.ls2.subjectButton.setVisible(true);
                            w.ls.loopAnimation = false;
                        } else {
                            w.rs.setSubject(w.session.chosenSubjectName, w.session.roundCounter);
                        }
                        w.session.setState(w.session.ANSWERQUESTIONS1);
                        w.outGameServer.writeObject(w.session);
                        break;
                    case 2: // ANSWERQUESTIONS1
                        w.ls.startButton.setVisible(true);
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        break;
                    case 3: // ASWERQUESTIONS2
                        w.ls2.readyButton.setVisible(true);
                        w.gs.roundBoxLabel.setText((w.session.roundCounter + 1) + "/" + w.session.getTotalRounds());
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        if (w.session.roundCounter == 0) {
                            w.rs.setCustomColor(w.color1, w.color2, w.color3, w.color4);
                            w.rs.setResultScreen(w.session.getTotalQsInRound(), w.session.getTotalRounds(),
                                                 w.user.getUserName(), w.session.getPlayerNameOne());
                            w.rs.setPanel();
                            w.rs.setActionListener(w.ah);
                        }
                        w.rs.setSubject(w.session.chosenSubjectName, w.session.roundCounter);
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.rs.nextRoundButton.setVisible(true);
                        break;
                    case 4: // SHOWOPPONENTANSWERS
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.session.roundCounter++;
                        if (w.session.roundCounter >= (w.session.getTotalRounds())) {
                            if (Integer.parseInt(w.rs.leftNumber.getText()) == Integer.parseInt(w.rs.rightNumber.getText())) {
                                
                                // HAR LAGT TILL 
                                w.rs.nextRoundButton.setText("Draw");
                                w.session.userTwo.addDraw();
                                w.user.addDraw();
                                w.session.setDraw(true);
                            } else if (Integer.parseInt(w.rs.leftNumber.getText()) > Integer.parseInt(w.rs.rightNumber.getText())) {
                                w.rs.nextRoundButton.setText("You Win");
                                w.session.userTwo.addWin();
                                w.user.addWin();
                                w.session.winner = w.session.userTwo;
                            } else {
                                w.rs.nextRoundButton.setText("You Lose");
                                w.session.userTwo.addLoss();
                                w.user.addLoss();
                                w.session.loser = w.session.userTwo;
                            }
                            w.rs.nextRoundButton.setVisible(true);
                            w.session.setState(w.session.GAMEOVER);
                        } else {
                            w.session.setState(w.session.CHOOSESUBJECT);
                        }
                        w.outGameServer.writeObject(w.session);
                        break;
                    case 5: // GAMEOVER
                            if (Integer.parseInt(w.rs.leftNumber.getText()) == Integer.parseInt(w.rs.rightNumber.getText())) {
                                
                                // HAR LAGT TILL 
                                w.rs.nextRoundButton.setText("Draw");
                                w.session.userTwo.addDraw();
                                w.user.addDraw();
                                w.session.setDraw(true);
                            } else if (Integer.parseInt(w.rs.leftNumber.getText()) > Integer.parseInt(w.rs.rightNumber.getText())) {
                                w.rs.nextRoundButton.setText("You Win");
                                w.session.userTwo.addWin();
                                w.user.addWin();
                                w.session.winner = w.session.userTwo;
                            } else {
                                w.rs.nextRoundButton.setText("You Lose");
                                w.session.userTwo.addLoss();
                                w.user.addLoss();
                                w.session.loser = w.session.userTwo;
                            }

                        w.session.setState(w.session.SHUTDOWN);
                        w.outGameServer.writeObject(w.session);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
