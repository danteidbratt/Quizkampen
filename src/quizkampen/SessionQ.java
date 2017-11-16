package quizkampen;

import java.io.Serializable;
import java.util.List;

public class SessionQ implements Serializable {
    //portnummer = 33333

    protected String username;
    protected String chosenSubjectOne;
    protected String chosenSubjectTwo;
    public List<Question> proposedSubjectOne;
    protected List<Question> proposedSubjectTwo;
    protected List<Question> proposedSubjectThree;
    protected List<Question> currentQuestions; 
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        username = u;
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
    public List getcurrentQuestions() {
        return currentQuestions; 
    }
}
