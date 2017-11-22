package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected User userOne;
    protected User userTwo;
    public Queue<Subject> subjects = new LinkedList();
    protected List<ListClass<Question>> subjectList = new ArrayList<>();

    public void setQuestionsQueue() {

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
        Subject temp1 = subjects.remove();
        Subject temp2 = subjects.remove();
    }
    
    public Subject getSubject(){
        return subjects.remove();
    }

    public void setSubjectList(List<ListClass<Question>> subjectList) {
        this.subjectList = subjectList;
    }

    public User getUserNameOne() {
        return userOne;
    }

    public void setUserNameOne(User u) {
        userOne = u;
    }

    public User getUserNameTwo() {
        return userTwo;
    }

    public void setUserNameTwo(User u) {
        userTwo = u;
    }

    public void setTotalQsInRond(int totalQuestions) {
        this.totalQuestionsinRond = totalQuestions;
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
}