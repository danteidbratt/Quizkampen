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

    protected SessionQ session;
    protected int portUser = 33334;
    protected int portGame = 33333;
    protected Socket userServerSocket;
    ObjectOutputStream outUserServer;
    ObjectInputStream inUserServer;
    ObjectOutputStream outGameServer;
    ObjectInputStream inGameServer;
    protected User user; 

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
        ws = new WelcomeScreen();
        rs = new ResultScreen(4, "Dante", "David");
        ms = new MenuScreen();
        gms = new GameMenuScreen();
        ses = new SettingsScreen();
        sts = new StatsScreen();
        ls = new LobbyScreen();
        gs = new GameScreen();

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

    public void setSessionQ(SessionQ session) {
        this.session = session;
    }

    public void setFrame() {
        setTitle("QuizFights");
        add(ws);
        setSize(500, 809);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelList = new ArrayList<>();
        panelList.add(ws);
        panelList.add(rs);
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
                }
                else {
                    session.setUserNameTwo(this.user);
                }
                
                System.out.println("User one; " + session.getUserNameOne());
                System.out.println("User two; " + session.getUserNameTwo());
                
                SessionHandler sessionHandler = new SessionHandler(session);

                outGameServer.writeObject(session);

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
            gs.questionButton.setText("<html><p>" + session.currentQuestions.get(0).getQuestionQ() + "</p></html>");
            gs.answer1Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(0));
            gs.answer2Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(1));
            gs.answer3Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(2));
            gs.answer4Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(3));
            add(gs);

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
        revalidate();
        repaint();
    }
    public User getUser() {
        return user;
    }
    public void setUser(User u) {
        this.user = u;
    }
}
