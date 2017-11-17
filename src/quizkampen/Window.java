package quizkampen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {
    protected SessionQ session;

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
        
        List<IPanel> panelList = new ArrayList<>();
        panelList.add(ws);
        panelList.add(ms);
        panelList.add(gms);
        panelList.add(ses);
        panelList.add(sts);
        panelList.add(ls);
        panelList.add(gs);
        panelList.forEach(e -> {
            e.setPanel();
            e.setActionListener(this);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ws.okButton || e.getSource() == ws.userNameInput) {
            remove(ws);
            ws.userNameInput.setText("Enter username to start");
            add(ms);
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
            ls.startButton.addActionListener(this);
        } else if (e.getSource() == ls.subjectTwoButton) {
            session.setCurrentQuestions(ls.subjectTwoButton.getText(), session.getTotalQsInRond());
            ls.startButton.addActionListener(this);
        } else if (e.getSource() == ls.subjectThreeButton) {
            session.setCurrentQuestions(ls.subjectThreeButton.getText(), session.getTotalQsInRond());
            ls.startButton.addActionListener(this);
            System.out.println("hej3");
        } else if(e.getSource() == ls.subjectTwoButton){
            session.setCurrentQuestions(ls.subjectTwoButton.getText(), session.getTotalQsInRond());
            ls.startButton.addActionListener(this);
        } else if(e.getSource() == ls.subjectThreeButton){
            session.setCurrentQuestions(ls.subjectThreeButton.getText(), session.getTotalQsInRond());
            ls.startButton.addActionListener(this);
        } else if(e.getSource() == ls.startButton){
            remove(ls);
            gs.questionButton.setText(session.getCurrentQuestions().get(0).getQuestionQ());
            gs.answer1Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(0).getAnswer());
            gs.answer2Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(1).getAnswer());
            gs.answer3Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(2).getAnswer());
            gs.answer4Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(3).getAnswer());
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
        } else if (e.getSource() == ses.blue){
            
        } else if (e.getSource() == ses.green){
            
        } else if (e.getSource() == ses.red){
            
        }
        revalidate();
        repaint();
    }
}
