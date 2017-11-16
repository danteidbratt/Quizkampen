package quizkampen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener {

    private final Font buttonFont = new Font("SansSarif", Font.BOLD, 20);
    private final Color backgroundColor = new Color(0, 0, 255);

    WelcomeScreen ws;
    MenuScreen ms;
    GameMenuScreen gms;
    LobbyScreen ls;
    GameScreen gs;
    SettingsScreen ses;
    StatsScreen sts;

    public Window() {
        ws = new WelcomeScreen(buttonFont, backgroundColor);
        ms = new MenuScreen(buttonFont, backgroundColor);
        gms = new GameMenuScreen(buttonFont, backgroundColor);
        ses = new SettingsScreen(buttonFont, backgroundColor);
        sts = new StatsScreen(buttonFont, backgroundColor);
        ls = new LobbyScreen(buttonFont, backgroundColor);
        gs = new GameScreen(buttonFont, backgroundColor);
    }

    public void setFrame() {
        setTitle("QuizFights");
        add(ws);
        setSize(500, 809);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        List<IPanel> panelList = new ArrayList<>();
        panelList.add(ws);
        panelList.add(ms);
        panelList.add(gms);
        panelList.add(ses);
        panelList.add(sts);
        panelList.add(ls);
        panelList.add(gs);
        panelList.forEach(e -> e.setPanel());
        panelList.forEach(e -> e.setActionListener(this));
        
//        ws.setPanel();
//        ws.setActionListener(this);
//        ms.setPanel();
//        ms.setActionListener(this);
//        gms.setPanel();
//        gms.setActionListener(this);
//        ses.setPanel();
//        ses.setActionListener(this);
//        sts.setPanel();
//        sts.setActionListener(this);
//        ls.setPanel();
//        ls.setActionListener(this);
//        gs.setPanel();
//        gs.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ws.okButton) {
            remove(ws);
            add(ms);
        } else if (e.getSource() == ms.newGameButton) {
            remove(ms);
            add(gms);
        } else if (e.getSource() == gms.randomPlayerButton) {
            remove(gms);
            add(ls);
        } else if (e.getSource() == ls.subjectOneButton
                || e.getSource() == ls.subjectTwoButton
                || e.getSource() == ls.subjectThreeButton) {
            ls.startButton.addActionListener(this);
        } else if (e.getSource() == ls.startButton) {
            remove(ls);
            add(gs);
        } else if (e.getSource() == ms.settingsButton) {
            remove(ms);
            add(ses);
        } else if (e.getSource() == gms.backButton) {
            remove(gms);
            add(ms);
        } else if (e.getSource() == ls.backButton) {
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
        }
        revalidate();
        repaint();
    }
}
