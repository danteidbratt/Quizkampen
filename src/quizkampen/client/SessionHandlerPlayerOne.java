package quizkampen.client;

import java.io.IOException;

public class SessionHandlerPlayerOne extends Thread {

    protected Window w;

    public SessionHandlerPlayerOne(Window w) {
        this.w = w;
    }

    @Override
    public void run() {
        try {
            while (w.session.getState() != w.session.GAMEOVER) {
                w.session = (SessionQ) w.inGameServer.readObject();
                switch (w.session.getState()) {
                    case 0: // CHOOSESUBJECT
                        w.ls.loopAnimation = false;
                        w.ls.resetPanel();
                        for (int i = 0; i < 3; i++) {
                            w.tempSubjects[i] = w.session.getSubject();
                        }
                        w.ls.setSubjectButtons(w.tempSubjects);
                        break;
                    case 1: // SHOWSUBJECT
                        w.rs.setSubject(w.session.chosenSubjectName, w.session.roundCounter);
                        w.session.setState(w.session.ANSWERQUESTIONS1);
                        w.outGameServer.writeObject(w.session);
                        break;
                    case 2: // ANSWERQUESTIONS1
                        if (w.session.roundCounter == 0) {
                            w.rs.setCustomColor(w.color1, w.color2, w.color3, w.color4);
                            w.rs.setResultScreen(w.session.getTotalQsInRound(), w.session.getTotalRounds(), w.user.getUserName(), w.session.getPlayerNameTwo());
                            w.rs.setPanel();
                            w.rs.setActionListener(w.ah);
                        }
                        w.rs.nextRoundButton.setVisible(true);
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        break;
                    case 3: // ANSWERQUESTIONS2
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        w.gs.roundBoxLabel.setText((w.session.roundCounter + 1) + "/" + w.session.getTotalRounds());
                        w.rs.nextRoundButton.setVisible(true);
                        break;
                    case 4: // SHOWOPPONENTANSWERS
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.session.roundCounter++;
                        if (w.session.roundCounter == (w.session.getTotalRounds())) {
                            if (Integer.parseInt(w.rs.leftNumber.getText()) == Integer.parseInt(w.rs.rightNumber.getText())) {

                                // LAGT TILL
                                w.rs.nextRoundButton.setText("Draw");
                                w.session.userOne.addDraw();
                                w.user.addDraw();
                                w.session.setDraw(true);
    
                            } else if (Integer.parseInt(w.rs.leftNumber.getText()) > Integer.parseInt(w.rs.rightNumber.getText())) {
                                w.rs.nextRoundButton.setText("You Win");
                                w.session.userOne.addWin();
                                w.user.addWin();
                                w.session.winner = w.session.userOne;
                            } else {
                                w.rs.nextRoundButton.setText("You Lose");
                                w.session.userOne.addLoss();
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

                                // LAGT TILL
                                w.rs.nextRoundButton.setText("Draw");
                                w.session.userOne.addDraw();
                                w.user.addDraw();
                                w.session.setDraw(true);
    
                            } else if (Integer.parseInt(w.rs.leftNumber.getText()) > Integer.parseInt(w.rs.rightNumber.getText())) {
                                w.rs.nextRoundButton.setText("You Win");
                                w.session.userOne.addWin();
                                w.user.addWin();
                                w.session.winner = w.session.userOne;
                            } else {
                                w.rs.nextRoundButton.setText("You Lose");
                                w.session.userOne.addLoss();
                                w.user.addLoss();
                                w.session.loser = w.session.userTwo;
                            }
                        w.session.setState(w.session.SHUTDOWN);
                        w.outGameServer.writeObject(w.session);
                        break;
                }

            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
