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
                        System.out.println("0");
                        w.rs.nextRoundButton.setVisible(true);
                        w.ls.resetPanel();
                        for (int i = 0; i < 3; i++) {
                            w.tempSubjects[i] = w.session.getSubject();
                        }
                        w.ls.setSubjectButtons(w.tempSubjects);
                        break;
                    case 1: //SHOWSUBJECT
                        System.out.println("1");
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
                        System.out.println("2");
                        w.ls.startButton.setVisible(true);
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        break;
                    case 3: // ASWERQUESTIONS2
                        System.out.println("3");
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
                        System.out.println("4");
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.session.roundCounter++;
                        if (w.session.roundCounter >= (w.session.getTotalRounds())) {
                            if (Integer.parseInt(w.rs.leftNumber.getText()) == Integer.parseInt(w.rs.rightNumber.getText())) {
                                w.rs.nextRoundButton.setText("Draw");
                            } else if (Integer.parseInt(w.rs.leftNumber.getText()) > Integer.parseInt(w.rs.rightNumber.getText())) {
                                w.rs.nextRoundButton.setText("You Win");
                            } else {
                                w.rs.nextRoundButton.setText("You Lose");
                            }
                            w.rs.nextRoundButton.setVisible(true);
                            w.session.setState(w.session.GAMEOVER);
                        } else {
                            w.session.setState(w.session.CHOOSESUBJECT);
                        }
                        w.outGameServer.writeObject(w.session);
                        break;
                    case 5: // GAMEOVER
                        w.session.setState(w.session.SHUTDOWN);
                        w.outGameServer.writeObject(w.session);
                        break;
                }
            }
            System.out.println("loppen är slut");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
