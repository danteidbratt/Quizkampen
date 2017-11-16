package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected String username;
    
    protected String chosenSubjectOne;  // LÄGG IN I LISTA ISTÄLLET
    protected String chosenSubjectTwo;

    private List<ListClass> proposedSubjectList;      // lägg de tre listorna ovan

    public List<Question> currentQuestions;
    private boolean requestingNewSubjects = false;

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

        for (ListClass l : proposedSubjectList) {
            if (l.getName().equals(chosenSubject)) {
                currentQuestions = getRandomQsFromList(howManyQuestions, l);
                break;
            }
        }
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

    public List<ListClass> getProposedSubjectList() {
        return proposedSubjectList;
    }

    public void setProposedSubjectList(List<ListClass> proposedSubjectList) {
        this.proposedSubjectList = proposedSubjectList;
    }
}
