package quizkampen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {
    protected SessionQ session;

    List<IPanel> panelList;
    
    WelcomeScreen welcomeScreen;
    MenuScreen menuScreen;
    GameMenuScreen gameMenuScreen;
    LobbyScreen lobbyScreen;
    GameScreen gameScreen;
    SettingsScreen settingsScreen;
    StatsScreen statsScreen;

    public Window() {
        welcomeScreen = new WelcomeScreen();
        menuScreen = new MenuScreen();
        gameMenuScreen = new GameMenuScreen();
        settingsScreen = new SettingsScreen();
        statsScreen = new StatsScreen();
        lobbyScreen = new LobbyScreen();
        gameScreen = new GameScreen();
    }
    
    public void setSessionQ(SessionQ session){
        this.session = session;
    }

    public void setFrame() {
        setTitle("QuizFights");
        add(welcomeScreen);
        setSize(500, 809);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panelList = new ArrayList<>();
        panelList.add(welcomeScreen);
        panelList.add(menuScreen);
        panelList.add(gameMenuScreen);
        panelList.add(settingsScreen);
        panelList.add(statsScreen);
        panelList.add(gameScreen);
        panelList.add(lobbyScreen);
        panelList.forEach(e -> {
            e.setPanel();
            e.setActionListener(this);
        });
        lobbyScreen.animation.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == welcomeScreen.okButton || e.getSource() == welcomeScreen.userNameInput) {
            remove(welcomeScreen);
            welcomeScreen.userNameInput.setText("Enter username to start");
            add(menuScreen);
        } else if (e.getSource() == menuScreen.newGameButton) {
            remove(menuScreen);
            add(gameMenuScreen);
        } else if (e.getSource() == gameMenuScreen.randomPlayerButton) {
            remove(gameMenuScreen);
            lobbyScreen.subjectOneButton.setText(session.getProposedSubjectList().get(0).getName());
            lobbyScreen.subjectTwoButton.setText(session.getProposedSubjectList().get(1).getName());
            lobbyScreen.subjectThreeButton.setText(session.getProposedSubjectList().get(2).getName());
            add(lobbyScreen);
        } else if (e.getSource() == lobbyScreen.subjectOneButton) {
            session.setCurrentQuestions(lobbyScreen.subjectOneButton.getText(), session.getTotalQsInRond());
            lobbyScreen.buttonPanel.add(lobbyScreen.startButton);
        } else if (e.getSource() == lobbyScreen.subjectTwoButton) {
            session.setCurrentQuestions(lobbyScreen.subjectTwoButton.getText(), session.getTotalQsInRond());
            lobbyScreen.buttonPanel.add(lobbyScreen.startButton);
        } else if (e.getSource() == lobbyScreen.subjectThreeButton) {
            session.setCurrentQuestions(lobbyScreen.subjectThreeButton.getText(), session.getTotalQsInRond());
            lobbyScreen.buttonPanel.add(lobbyScreen.startButton);
        } else if(e.getSource() == lobbyScreen.startButton){
            remove(lobbyScreen);
            gameScreen.questionButton.setText(session.getCurrentQuestions().get(0).getQuestionQ());
            gameScreen.answer1Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(0));
            gameScreen.answer2Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(1));
            gameScreen.answer3Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(2));
            gameScreen.answer4Button.setText(session.getCurrentQuestions().get(0).getAnswerAlternative(3));
            add(gameScreen);
        } else if (e.getSource() == menuScreen.settingsButton) {
            remove(menuScreen);
            add(settingsScreen);
        } else if (e.getSource() == gameMenuScreen.backButton) {
            remove(gameMenuScreen);
            add(menuScreen);
        } else if (e.getSource() == lobbyScreen.backButton) {
            lobbyScreen.buttonPanel.remove(lobbyScreen.startButton);
            remove(lobbyScreen);
            add(gameMenuScreen);
        } else if (e.getSource() == settingsScreen.backButton) {
            remove(settingsScreen);
            add(menuScreen);
        } else if (e.getSource() == menuScreen.statsButton) {
            remove(menuScreen);
            add(statsScreen);
        } else if (e.getSource() == menuScreen.logoutButton) {
            remove(menuScreen);
            add(welcomeScreen);
        } else if (e.getSource() == statsScreen.backButton) {
            remove(statsScreen);
            add(menuScreen);
        } else if (e.getSource() == welcomeScreen.exitButton || e.getSource() == menuScreen.exitButton || e.getSource() == gameMenuScreen.exitButton) {
            System.exit(0);
        } else if (e.getSource() == settingsScreen.blue){
           panelList.forEach(x -> x.setCustomColor(Color.BLUE, Color.YELLOW, Color.WHITE));
        } else if (e.getSource() == settingsScreen.green){
            panelList.forEach(x -> x.setCustomColor(Color.GREEN, Color.BLUE, Color.MAGENTA));
        } else if (e.getSource() == settingsScreen.red){
            panelList.forEach(x -> x.setCustomColor(Color.RED, Color.WHITE, Color.WHITE));
        }
        revalidate();
        repaint();
    }
}
