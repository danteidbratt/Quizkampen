package quizkampen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {

    protected int questionCounter = 0;
    protected int roundCounter = 0;
    protected SessionQ session;
    protected int portUser = 33334;
    protected int portGame = 33333;
    protected Socket userServerSocket;
    ObjectOutputStream outUserServer;
    ObjectInputStream inUserServer;
    ObjectOutputStream outGameServer;
    ObjectInputStream inGameServer;
    protected User user; 
    protected int playerNumber;

    Socket gameServerSocket;

    List<IPanel> panelList;

    WelcomeScreen ws;
    MenuScreen ms;
    GameMenuScreen gms;
    LobbyScreen ls;
    GameScreen gs;
    ResultScreen rs;
    SettingsScreen ses;
    StatsScreen sts;

    public Window() {
        try {
            this.userServerSocket = new Socket("127.0.0.1", portUser);
            outUserServer = new ObjectOutputStream(userServerSocket.getOutputStream());
            System.out.println("output connected");
            inUserServer = new ObjectInputStream(userServerSocket.getInputStream());
            System.out.println("inputconnected");
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void setSessionQ(SessionQ session) {
//        this.session = session;
//    }

    public void setFrame() {
        ws = new WelcomeScreen();
        rs = new ResultScreen();
        ms = new MenuScreen();
        gms = new GameMenuScreen();
        ses = new SettingsScreen();
        sts = new StatsScreen();
        ls = new LobbyScreen();
        gs = new GameScreen();
        
        setTitle("QuizFights");
        add(ws);
        setSize(500, 809);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelList = new ArrayList<>();
        panelList.add(ws);
//        panelList.add(rs);
        panelList.add(ms);
        panelList.add(gms);
        panelList.add(ses);
        panelList.add(sts);
        panelList.add(gs);
        panelList.add(ls);
        panelList.forEach(e -> {
            e.setPanel();
            e.setActionListener(this);
        });
        ls.animation.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ws.okButton || e.getSource() == ws.userNameInput) {
            String userName = ws.userNameInput.getText();
            try {
                if (userName != null) {
                    outUserServer.writeObject(userName);
                }
                if ((user = (User)inUserServer.readObject()) != null) {
                    this.setUser(user);
                    System.out.println(user.getUserName());
                }
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            remove(ws);
            add(ms);
        } else if (e.getSource() == ms.newGameButton) {
            remove(ms);
            add(gms);
        } else if (e.getSource() == gms.randomPlayerButton) {
            try {
                remove(gms);

                this.gameServerSocket = new Socket("127.0.0.1", portGame);
                outGameServer = new ObjectOutputStream(gameServerSocket.getOutputStream());
                inGameServer = new ObjectInputStream(gameServerSocket.getInputStream());
                session = (SessionQ) inGameServer.readObject();
                if (session.getUserNameOne() == null) {
                    session.setUserNameOne(this.user);
                    this.setPlayerNumber(1);
                }
                else {
                    session.setUserNameTwo(this.user);
                    this.setPlayerNumber(2);
                }
                outGameServer.writeObject(session);
                
                System.out.println("User one; " + session.getUserNameOne().getUserName() + 
                        ", nr: " + this.getPlayerNumber());
                
                SessionHandler sessionHandler = new SessionHandler(session);

                outGameServer.writeObject(session);
                
                rs.setResultScreen(session.getTotalQsInRond(), session.getTotalRonds(), "Pronut", "David");
                rs.setPanel();
                rs.setActionListener(this);

                ls.subjectOneButton.setText(session.getProposedSubject().get(0).getName());
                ls.subjectTwoButton.setText(session.getProposedSubject().get(1).getName());
                ls.subjectThreeButton.setText(session.getProposedSubject().get(2).getName());
                
                add(ls);
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getSource() == ls.subjectOneButton) {
            session.setCurrentQuestions(ls.subjectOneButton.getText(), session.getTotalQsInRond());
            ls.buttonPanel.add(ls.startButton);
        } else if (e.getSource() == ls.subjectTwoButton) {
            session.setCurrentQuestions(ls.subjectTwoButton.getText(), session.getTotalQsInRond());
            ls.buttonPanel.add(ls.startButton);
        } else if (e.getSource() == ls.subjectThreeButton) {
            session.setCurrentQuestions(ls.subjectThreeButton.getText(), session.getTotalQsInRond());
            ls.buttonPanel.add(ls.startButton);
        } else if (e.getSource() == ls.startButton) {
            remove(ls);
            gs.setNumberofQuestions(session.getTotalQsInRond());
            gs.questionButton.setText("<html><p>" + session.currentQuestions.get(0).getQuestionQ() + "</p></html>");
            for (int i = 0; i < gs.answerButtons.length; i++) {
                gs.answerButtons[i].setButton(session.getCurrentQuestions().get(0).getAnswerAlternatives().get(i));
            }
            add(gs);
        } else if (e.getSource() == gs.nextQuestionButton){
            if (questionCounter < session.getTotalQsInRond()-1) {
                gs.setNextQuestion(session.getCurrentQuestions().get(questionCounter++));
                gs.setButtonActionListener(this);
            } else {
                gs.setButtonActionListener(this);
                gs.resetColors();
                remove(gs);
                if (roundCounter == session.getTotalRonds()-1){
                    rs.nextButton.setText("YOU WIN");
                }
                add(rs);
            }
        } else if (e.getSource() == rs.nextButton){
            remove(rs);
            roundCounter++;
            questionCounter = 0;
            add(ls);
        } else if (e.getSource() == ms.settingsButton) {
            remove(ms);
            add(ses);
        } else if (e.getSource() == gms.backButton) {
            remove(gms);
            add(ms);
        } else if (e.getSource() == ls.backButton) {
            ls.buttonPanel.remove(ls.startButton);
            remove(ls);
            add(gms);
        } else if (e.getSource() == ses.backButton) {
            remove(ses);
            add(ms);
        } else if (e.getSource() == ms.statsButton) {
            remove(ms);
            add(sts);
        } else if (e.getSource() == ms.logoutButton) {
            remove(ms);
            add(ws);
        } else if (e.getSource() == sts.backButton) {
            remove(sts);
            add(ms);
        } else if (e.getSource() == ws.exitButton || e.getSource() == ms.exitButton || e.getSource() == gms.exitButton) {
            System.exit(0);
        } else if (e.getSource() == ses.blue){
           panelList.forEach(x -> x.setCustomColor(new Color(20, 0, 150), Color.YELLOW, Color.WHITE));
        } else if (e.getSource() == ses.green){
            panelList.forEach(x -> x.setCustomColor(new Color(80, 180, 0), Color.WHITE, Color.WHITE));
        } else if (e.getSource() == ses.red){
            panelList.forEach(x -> x.setCustomColor(new Color(190, 0, 0), Color.WHITE, Color.WHITE));
        }
        
        for (int i = 0; i < gs.answerButtons.length; i++) {
            if(e.getSource() == gs.answerButtons[i]){
            gs.colorChosenButton(gs.answerButtons[i]);
            gs.revealCorrectAnswer();
            if(gs.answerButtons[i].getIsCorrect()) {
                rs.increasePlayerScore();
                rs.boxes[roundCounter][questionCounter].setBackground(Color.GREEN);
                gs.questionBoxes.get(questionCounter).setBackground(Color.GREEN);
            }
            else {
                rs.boxes[roundCounter][questionCounter].setBackground(Color.RED);
                gs.questionBoxes.get(questionCounter).setBackground(Color.RED);
            }
            gs.nextQuestionButton.setVisible(true);
            gs.removeActionListeners(this);
            if(questionCounter == session.getTotalQsInRond()-1)
                gs.nextQuestionButton.setText("Show Results");
            }
        }
        revalidate();
        repaint();
    }
    public User getUser() {
        return user;
    }
    public void setUser(User u) {
        this.user = u;
    }
    public int getPlayerNumber(){
        return this.playerNumber;
    }
    public void setPlayerNumber(int number){
        this.playerNumber = number;
    }
}
