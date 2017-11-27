package quizkampen.client;

import java.io.Serializable;
import java.util.*;
import quizkampen.server.ListClass;
import quizkampen.server.Question;
import quizkampen.server.Subject;

public class SessionQ implements Serializable {

    
    int state;
    final int FIRST = -2;
    final int SECOND = -1;
    final int CHOOSESUBJECT = 0;
    final int SHOWSUBJECT = 1; 
    final int ANSWERQUESTIONS1 = 2; 
    final int ANSWERQUESTIONS2 = 3; 
    final int SHOWOPPONENTANSWERS = 4;
    final int GAMEOVER = 5;
    public int SHUTDOWN = 6;

    public Queue<Subject> subjects = new LinkedList();
    protected List<ListClass<Question>> subjectList = new ArrayList<>();
    protected String chosenSubjectName;
    Question[] tempQuestions;
    boolean[] opponentsAnswers;
    int roundCounter;
    private int totalRonds;
    private int totalQuestionsinRond;
    private int timerLength;
    protected String playerNameOne;
    protected String playerNameTwo;

    public SessionQ() {
        roundCounter = 0;
        state = FIRST;
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
        tempQuestions = new Question[totalQuestionsinRond];
        opponentsAnswers = new boolean[totalQuestionsinRond];
        for (int i = 0; i < totalQuestionsinRond; i++) {
            tempQuestions[i] = new Question();
            opponentsAnswers[i] = false;
        }
    }

    public void setTotalRounds(int totalRonds) {
        this.totalRonds = totalRonds;
    }
    
    public void setTimerLength(int timerLength){
        this.timerLength = timerLength;
    }

    public int getTotalQsInRound() {
        return this.totalQuestionsinRond;
    }

    public int getTotalRounds() {
        return this.totalRonds;
    }
    
    public int getTimerLength(){
        return timerLength;
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

    public void clearOpponentAnswers() {
        for (int i = 0; i < opponentsAnswers.length; i++) {
            opponentsAnswers[i] = false;
        }
    }
}
