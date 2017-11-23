package quizkampen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Window extends JFrame {

    Subject[] tempSubjects = new Subject[3];
    Question[] tempQuestions;
    int tempIndex;
    ActionHandler ah;
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
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        ah = new ActionHandler(this);
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
        panelList.add(ms);
        panelList.add(gms);
        panelList.add(ses);
        panelList.add(sts);
        panelList.add(gs);
        panelList.add(ls);
        panelList.forEach(e -> {
            e.setPanel();
            e.setActionListener(ah);
        });
        ls.animation.start();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void setPlayerNumber(int number) {
        this.playerNumber = number;
    }
}
