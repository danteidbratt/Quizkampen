package quizkampen;

import java.io.Serializable;
import java.util.List;

public class SessionQ implements Serializable {
    //portnummer = 33333

    protected String username;
    protected String chosenSubjectOne;
    protected String chosenSubjectTwo;
<<<<<<< HEAD
    public ListClass<Question> proposedSubjectOne;
    protected ListClass<Question> proposedSubjectTwo;
    protected ListClass<Question> proposedSubjectThree;

=======
    public List<Question> proposedSubjectOne;
    protected List<Question> proposedSubjectTwo;
    protected List<Question> proposedSubjectThree;
    protected List<Question> currentQuestions; 
>>>>>>> ClaudiaFeture
    
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

    public void setProposedSubjectOne(ListClass lista) {
        this.proposedSubjectOne = lista;
    }

    public void setProposedSubjectTwo(ListClass lista) {
        this.proposedSubjectTwo = lista;
    }

    public void setProposedSubjectThree(ListClass lista) {
        this.proposedSubjectThree = lista;
    }
    
    //public void setCurrentQuestions(String chosenSubject){
       // setChosenSubjectOne(chosenSubject);
        //if (chosenSubject.equals(proposedSubjectOne))
    //}

    public ListClass getProposedSubjectOne() {
        return proposedSubjectOne;
    }

    public ListClass getProposedSubjectTwo() {
        return proposedSubjectTwo;
    }

    public ListClass getProposedSubjectThree() {
        return proposedSubjectThree;
    }
    public List getCurrentQuestions() {
        return currentQuestions; 
    }
}
