package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected String username;
    
    protected String chosenSubjectOne;  // LÄGG IN I LISTA ISTÄLLET
    protected String chosenSubjectTwo;

    private ListClass<Question> proposedSubjectOne; // GÖR PROTECTED - fixa bugg
    private ListClass<Question> proposedSubjectTwo;
    private ListClass<Question> proposedSubjectThree;

    protected List<ListClass<Question>> proposedSubjectList;      // lägg de tre listorna ovan

    public List<Question> currentQuestions;
    private boolean requestingNewSubjects = false;

    public void resetProposedSubjectList() {
        proposedSubjectList = null;
    }

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

    public void setCurrentQuestions(String chosenSubject, int howManyQuestions) {
        setChosenSubjectOne(chosenSubject);

        System.out.println("1");

        if (proposedSubjectOne.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectOne);
        } else if (proposedSubjectTwo.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectTwo);
        } else if (proposedSubjectThree.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectThree);
        }

        /*
        for (ListClass l : proposedSubjectList) {                                  // ÄNDRA KODEN OVANFÖR TILL EN SÅN HÄR LISTA
            if (l.getName().equals(chosenSubject)) {
                currentQuestions = getRandomQsFromList(howManyQuestions, l);
                System.out.println("2");
                break;
            }
        }*/
    }

    public List<Question> getRandomQsFromList(int howManyQuestions, ListClass list) {
        List<Question> lista = new ArrayList<>();
        Collections.shuffle(list);

        for (int i = 0; i < howManyQuestions; i++) {
            lista.add(((ListClass<Question>) list).get(i));
        }
        return lista;
    }

    public List<Question> getCurrentQuestions() {
        return currentQuestions;
    }

    public void setTotalQsInRond(int totalQuestions) {
        this.totalQuestionsinRond = totalQuestions;
    }

    public void setTotalRonds(int totalRonds) {
        this.totalRonds = totalRonds;
    }

    public int getTotalQsInRond() {
        return this.totalQuestionsinRond;
    }

    public int getTotalRonds() {
        return this.totalRonds;
    }

    public void setRequestingNewSubjects(boolean trueOrFalse) {
        this.requestingNewSubjects = trueOrFalse;
    }

    public boolean getRequestingNewSubjects() {
        return this.requestingNewSubjects;
    }

    public void setProposedSubjectOne(ListClass<Question> proposedSubjectOne) {
        this.proposedSubjectOne = proposedSubjectOne;
    }

    public void setProposedSubjectTwo(ListClass<Question> proposedSubjectTwo) {
        this.proposedSubjectTwo = proposedSubjectTwo;
    }

    public void setProposedSubjectThree(ListClass<Question> proposedSubjectThree) {
        this.proposedSubjectThree = proposedSubjectThree;
    }

    public ListClass<Question> getProposedSubjectOne() {
        return proposedSubjectOne;
    }

    public ListClass<Question> getProposedSubjectTwo() {
        return proposedSubjectTwo;
    }

    public ListClass<Question> getProposedSubjectThree() {
        return proposedSubjectThree;
    }
}
