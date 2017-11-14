package quizkampen;

import java.io.Serializable;
import java.util.List;

public class SessionQ implements Serializable {
    //portnummer = 33333

    protected String username;
    protected Question question;
    protected String alternative;
    protected String answer;
    protected String chosenSubjectOne;
    protected String chosenSubjectTwo;
    public List<Question> proposedSubjectOne;
    protected List<Question> proposedSubjectTwo;
    protected List<Question> proposedSubjectThree;

    SessionQ() {

    }

    public String getUsername() {
        return username;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAlternative() {
        return alternative;
    }

    public String getAnswer() {
        return answer;
    }

    public void setUsername(String u) {
        username = u;
    }

    public void setQuestion(Question q) {
        question = q;
    }

    public void setAlternative(String a) {
        alternative = a;
    }

    public void setAnswer(String s) {
        answer = s;
    }

    public void setChosenSubjectOne(String chosenSubject) {
        this.chosenSubjectOne = chosenSubject;
    }

    public void setChosenSubjectTwo(String chosenSubject) {
        this.chosenSubjectTwo = chosenSubject;
    }

    public String getChosenSubjectOne() {
        return chosenSubjectOne;
    }

    public String getChosenSubjectTwo() {
        return chosenSubjectTwo;
    }

    public void setProposedSubjectOne(List lista) {
        this.proposedSubjectOne = lista;
    }

    public void setProposedSubjectTwo(List lista) {
        this.proposedSubjectTwo = lista;
    }

    public void setProposedSubjectThree(List lista) {
        this.proposedSubjectThree = lista;
    }

    public List getProposedSubjectOne() {
        return proposedSubjectOne;
    }

    public List getProposedSubjectTwo() {
        return proposedSubjectTwo;
    }

    public List getProposedSubjectThree() {
        return proposedSubjectThree;
    }
    
}
