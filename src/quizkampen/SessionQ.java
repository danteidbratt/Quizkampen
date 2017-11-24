package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    final int CHOOSESUBJECT = 0;
    final int SHOWSUBJECT = 1; 
    final int ANSWERQUESTIONS = 2; 
    final int SHOWOPPONENTANSWERS = 3;

    int playerWhoshouldChoose = 1;
    boolean[] opponentsAnswers;
    private int totalRonds;
    private int totalQuestionsinRond;
    protected String playerNameOne;
    protected String playerNameTwo;
    public Queue<Subject> subjects = new LinkedList();
    protected List<ListClass<Question>> subjectList = new ArrayList<>();
//    protected State state;
    protected String chosenSubjectName;
    Question[] tempQuestions;
    int playerNumber;
    int state;

    SessionQ() {
        playerNumber = 1;
        state = CHOOSESUBJECT;
    }

    public int getPlayerNumber() {
        return playerNumber++;
    }

    public String getPlayerNameTwo() {
        return playerNameTwo;
    }

    public String getPlayerNameOne() {
        return playerNameOne;
    }

    public void setPlayerNameTwo(String playerNameTwo) {
        this.playerNameTwo = playerNameTwo;
    }

    public void setPlayerNameOne(String playerNameOne) {
        this.playerNameOne = playerNameOne;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setSubjectList(List<ListClass<Question>> subjectList) {
        this.subjectList = subjectList;
    }

    public void setTotalQsInRond(int totalQuestions) {
        this.totalQuestionsinRond = totalQuestions;
        opponentsAnswers = new boolean[totalQuestions];
        for (int i = 0; i < opponentsAnswers.length; i++) {
            opponentsAnswers[i] = false;
        }
    }

    public void setTotalRounds(int totalRonds) {
        this.totalRonds = totalRonds;
    }

    public int getTotalQsInRound() {
        return this.totalQuestionsinRond;
    }

    public int getTotalRounds() {
        return this.totalRonds;
    }

    public void setSubjectQueue() {
        for (int p = 0; p < 2; p++) {
            Collections.shuffle(subjectList);
            for (int i = 0; i < subjectList.size(); i++) {
                Queue questions = new LinkedList();
                Collections.shuffle(subjectList.get(i));
                for (int j = 0; j < subjectList.get(i).size(); j++) {
                    questions.add(subjectList.get(i).get(j));
                }
                subjects.add(new Subject(subjectList.get(i).getName(), questions));
            }
        }
    }

    public Subject getSubject() {
        return subjects.remove();
    }

    public void switchPlayerWhoShoulgChoose() {
        if (playerWhoshouldChoose == 1) {
            playerWhoshouldChoose = 2;
        } else {
            playerWhoshouldChoose = 1;
        }
    }

    public void clearOpponentAnswers() {
        for (int i = 0; i < opponentsAnswers.length; i++) {
            opponentsAnswers[i] = false;
        }
    }
}
