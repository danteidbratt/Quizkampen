package quizkampen.client;

import quizkampen.client.panels.*;
import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.UIManager;
import quizkampen.server.IPanel;
import quizkampen.server.Subject;

public class Window extends JFrame {

    Color color1;
    Color color2;
    Color color3;
    Color color4;

    Subject[] tempSubjects = new Subject[3];
    public ActionHandler ah;
    protected int questionCounter = 0;
    int playerNumber;
    public SessionQ session;

    SessionHandlerPlayerOne sh1;
    SessionHandlerPlayerTwo sh2;

    protected int portUser = 33334; // Nya porten
    protected int portGame = 33333;
    protected Socket userServerSocket;
    ObjectOutputStream outUserServer;
    ObjectInputStream inUserServer;
    ObjectOutputStream outGameServer;
    ObjectInputStream inGameServer;
    public User user;

    Socket gameServerSocket;

    List<IPanel> panelList;

    WelcomeScreen ws;
    MenuScreen ms;
    GameMenuScreen gms;
    LobbyScreen ls;
    LobbyScreen2 ls2;
    GameScreen gs;
    ResultScreen rs;
    SettingsScreen ses;
    StatsScreen sts;

    public Window() {
        color1 = new Color(20, 0, 160);
        color2 = Color.YELLOW;
        color3 = Color.WHITE;
        color4 = new Color(20, 0, 185);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ah = new ActionHandler(this);
    }

    public void setFrame() {
        ws = new WelcomeScreen();
        ms = new MenuScreen();
        gms = new GameMenuScreen();
        ses = new SettingsScreen();
        sts = new StatsScreen(this);

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
        panelList.forEach(e -> {
            e.setPanel();
            e.setActionListener(ah);
        });
    }

    public User getUser() {
        return user;
    }

    public void setUser(User u) {
        this.user = u;
    }
}
