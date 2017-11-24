package quizkampen;

import java.io.IOException;

public class SessionHandlerPlayerTwo extends Thread {

    protected Window w;

    public SessionHandlerPlayerTwo(Window w) {
        this.w = w;
    }

    @Override
    public void run() {
        try {
            while (true) {
                w.session = (SessionQ) w.inGameServer.readObject();
                switch (w.session.getState()) {
                    case 1: //SHOWSUBJECT
                        System.out.println("1");
                        if(w.session.roundCounter == 0) {
                            w.ls2.subjectButton.setText(w.session.chosenSubjectName);
                            w.ls2.subjectButton.setVisible(true);
                            w.ls.loopAnimation = false;
                        }
                        else {
                            w.rs.subjects[w.session.roundCounter].setText("- " + w.session.chosenSubjectName + " -");
                        }
                        w.session.setState(w.session.ANSWERQUESTIONS1);
                        w.outGameServer.writeObject(w.session);
                        break;
                    case 2: // ANSWERQUESTIONS1
                        System.out.println("2");
//                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());

                        break;
                    case 3: // ASWERQUESTIONS2
                        if (w.session.roundCounter == 0) {
                            w.rs.setResultScreen(w.session.getTotalQsInRound(), w.session.getTotalRounds(), w.user.getUserName(), w.session.getPlayerNameOne());
                            w.rs.setPanel();
                            w.rs.setActionListener(w.ah);
                        }
                        w.rs.subjects[w.session.roundCounter].setText("- " + w.session.chosenSubjectName + " -");
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.rs.nextRoundButton.setVisible(true);
                        //uiukugjykuytfkutfGTHJKJHGFGHJJHGFG
                        w.gs.roundBoxLabel.setText((w.session.roundCounter + 1) + "/" + w.session.getTotalRounds());
                        System.out.println("3");
                        w.ls2.readyButton.setVisible(true);
                        w.gs.setNextQuestion(w.session.tempQuestions[w.questionCounter]);
                        break;
                    case 4: // SHOWOPPONENTANSWERS
                        System.out.println("4");
                        w.rs.setOpponentBoxes(w.session.opponentsAnswers, w.session.roundCounter, w.session.getTotalQsInRound());
                        w.session.setState(w.session.CHOOSESUBJECT);
                        w.session.roundCounter++;
                        w.rs.nextRoundButton.setVisible(false);
                        w.outGameServer.writeObject(w.session);
                        break;
                    case 0: //CHOOSESUBJECT
                        System.out.println("0");
                        System.out.println("case 0 i p2");
                        w.rs.nextRoundButton.setVisible(true);
                        w.ls.loopAnimation = false;
                        w.ls.resetPanel();
                        for (int i = 0; i < 3; i++) {
                            w.tempSubjects[i] = w.session.getSubject();
                        }
                        w.ls.setSubjectButtons(w.tempSubjects);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }
//        try {
//            switch (session.getState()) {
//
//
//                case WAITING4P2USERNAME: // UserTwo skriver in UserName
//                    System.out.println("waiting4p2username");
//                    session.setUserNameTwo(w.user);
//                    w.setPlayerNumber(2);
//                    w.ls2.opponentLabel.setText(w.session.getUserNameOne().getUserName());
//                    w.add(w.ls2);
//                    w.session.setState(State.WAITINGFORCHOICE);
//                    w.outGameServer.writeObject(session);   // skickar session -> server från P2
//                    w.revalidate();
//                    w.repaint();
//                    break;
//
//
//                case CHOICESENT:
//                    System.out.println("Choice sent");
//                    w.ls2.subjectButton.setText("Valt ämne?");
//                    w.ls2.setActionListener(w.ah);
//                    w.ls2.readyIconPanel.add(w.ls2.readyButton);
//                    w.ls2.subjectIconPanel.add(w.ls2.subjectButton);
//
//                    w.session.setState(State.PLAYGAME);
//                    w.ls2.revalidate();
//                    w.ls2.repaint();
//                    break;
//
//                case PLAYGAME://Chosing Subject, Talar om vems tur de är- väljer ämne.UserTwo får info om ämne.
//                    if (session.getUserChosing() == w.user) {
//                        session.ChangeUserChosing();
//                    }
//                    // Set resultat
//                    // skriver ut resultat till andra spelaren
//                    w.outGameServer.writeObject(w.session);
//
//                    break;
//
//                case GAMEOVER:
//                    break;
//
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try {
//            switch (session.getState()) {
//
//
//                case WAITING4P2USERNAME: // UserTwo skriver in UserName
//                    System.out.println("waiting4p2username");
//                    session.setUserNameTwo(w.user);
//                    w.setPlayerNumber(2);
//                    w.ls2.opponentLabel.setText(w.session.getUserNameOne().getUserName());
//                    w.add(w.ls2);
//                    w.session.setState(State.WAITINGFORCHOICE);
//                    w.outGameServer.writeObject(session);   // skickar session -> server från P2
//                    w.revalidate();
//                    w.repaint();
//                    break;
//
//
//                case CHOICESENT:
//                    System.out.println("Choice sent");
//                    w.ls2.subjectButton.setText("Valt ämne?");
//                    w.ls2.setActionListener(w.ah);
//                    w.ls2.readyIconPanel.add(w.ls2.readyButton);
//                    w.ls2.subjectIconPanel.add(w.ls2.subjectButton);
//
//                    w.session.setState(State.PLAYGAME);
//                    w.ls2.revalidate();
//                    w.ls2.repaint();
//                    break;
//
//                case PLAYGAME://Chosing Subject, Talar om vems tur de är- väljer ämne.UserTwo får info om ämne.
//                    if (session.getUserChosing() == w.user) {
//                        session.ChangeUserChosing();
//                    }
//                    // Set resultat
//                    // skriver ut resultat till andra spelaren
//                    w.outGameServer.writeObject(w.session);
//
//                    break;
//
//                case GAMEOVER:
//                    break;
//
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
