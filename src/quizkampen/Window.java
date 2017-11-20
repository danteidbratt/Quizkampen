package quizkampen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {
    protected SessionQ session;

    List<IPanel> panelList;
    
    WelcomeScreen ws;
    MenuScreen ms;
    GameMenuScreen gms;
    LobbyScreen ls;
    
    //**********
    LobbyScreen2 ls2;
    //***********
    
    GameScreen gs;
    SettingsScreen ses;
    StatsScreen sts;

    public Window() {
        ws = new WelcomeScreen();
        ms = new MenuScreen();
        gms = new GameMenuScreen();
        ses = new SettingsScreen();
        sts = new StatsScreen();
        ls = new LobbyScreen();
        //*************
        ls2 = new LobbyScreen2();
        //*************
        gs = new GameScreen();
    }
    
    public void setSessionQ(SessionQ session){
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
        panelList.add(ms);
        panelList.add(gms);
        panelList.add(ses);
        panelList.add(sts);
        panelList.add(gs);
        panelList.add(ls);
        //***********
        panelList.add(ls2);
        //***********
        panelList.forEach(e -> {
            e.setPanel();
            e.setActionListener(this);
        });
        ls.animation.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ws.okButton || e.getSource() == ws.userNameInput) {
            remove(ws);
            add(ls2);
        } else if (e.getSource() == ms.newGameButton) {
            remove(ms);
            add(gms);
        } else if (e.getSource() == gms.randomPlayerButton) {
            remove(gms);
            ls.subjectOneButton.setText(session.getProposedSubject().get(0).getName());
            ls.subjectTwoButton.setText(session.getProposedSubject().get(1).getName());
            ls.subjectThreeButton.setText(session.getProposedSubject().get(2).getName());
            add(ls);
        } else if (e.getSource() == ls.subjectOneButton) {
            session.setCurrentQuestions(ls.subjectOneButton.getText(), session.getTotalQsInRond());
            ls.buttonPanel.add(ls.startButton);
        } else if (e.getSource() == ls.subjectTwoButton) {
            session.setCurrentQuestions(ls.subjectTwoButton.getText(), session.getTotalQsInRond());
            ls.buttonPanel.add(ls.startButton);
        } else if (e.getSource() == ls.subjectThreeButton) {
            session.setCurrentQuestions(ls.subjectThreeButton.getText(), session.getTotalQsInRond());
            ls.buttonPanel.add(ls.startButton);
        } else if(e.getSource() == ls.startButton){
            remove(ls);
            gs.questionButton.setText(session.currentQuestions.get(0).getQuestionQ());
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
           panelList.forEach(x -> x.setCustomColor(Color.BLUE, Color.YELLOW, Color.WHITE));
        } else if (e.getSource() == ses.green){
            panelList.forEach(x -> x.setCustomColor(Color.GREEN, Color.BLUE, Color.MAGENTA));
        } else if (e.getSource() == ses.red){
            panelList.forEach(x -> x.setCustomColor(Color.RED, Color.WHITE, Color.WHITE));
        }
        revalidate();
        repaint();
    }
}
