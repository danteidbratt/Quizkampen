package quizkampen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SessionQ implements Serializable {
    //portnummer = 33333

    protected String username;
    protected String chosenSubjectOne;
    protected String chosenSubjectTwo;

    public ListClass<Question> proposedSubjectOne;
    protected ListClass<Question> proposedSubjectTwo;
    protected ListClass<Question> proposedSubjectThree;
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

    public void setProposedSubjectOne(ListClass lista) {
        this.proposedSubjectOne = lista;
    }

    public void setProposedSubjectTwo(ListClass lista) {
        this.proposedSubjectTwo = lista;
    }

    public void setProposedSubjectThree(ListClass lista) {
        this.proposedSubjectThree = lista;
    }
    
    public void setCurrentQuestions(String chosenSubject){
       setChosenSubjectOne(chosenSubject);
        if (chosenSubject.equals(proposedSubjectOne.getName())) {
            currentQuestions = getRandomQsFromList(2, proposedSubjectOne);
        }
        else if(chosenSubject.equals(proposedSubjectTwo.getName())){
            currentQuestions = getRandomQsFromList(2, proposedSubjectTwo);
        }
        else if(chosenSubject.equals(proposedSubjectThree.getName())){
            currentQuestions = getRandomQsFromList(2, proposedSubjectThree);
        }
    }
    
    public List<Question> getRandomQsFromList(int howManyQuestions, ListClass list){
        List<Question> lista = new ArrayList<Question>();
        ListClass<Question> searchList = list;      
        Collections.shuffle(searchList);    
        
        for (int i = 0; i < howManyQuestions; i++) {
            lista.add(searchList.get(i));
        }      
        return lista;     
    }

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
