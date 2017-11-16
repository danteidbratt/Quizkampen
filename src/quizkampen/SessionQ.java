package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected String username;
    
    protected String chosenSubjectOne;  // LÄGG IN I LISTA ISTÄLLET
    protected String chosenSubjectTwo;

    protected ListClass<Question> proposedSubjectOne; // GÖR PROTECTED - fixa bugg
    protected ListClass<Question> proposedSubjectTwo;
    protected ListClass<Question> proposedSubjectThree;
    protected List<ListClass> propsedSubjectList      // lägg de tre listorna ovan
            = Arrays.asList(proposedSubjectOne,       // i en sån här lista
            proposedSubjectTwo, proposedSubjectThree);
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

    public void setProposedSubjectOne(ListClass lista) {
        this.proposedSubjectOne = lista;
    }

    public void setProposedSubjectTwo(ListClass lista) {
        this.proposedSubjectTwo = lista;
    }

    public void setProposedSubjectThree(ListClass lista) {
        this.proposedSubjectThree = lista;
    }

    public void setCurrentQuestions(String chosenSubject, int howManyQuestions) {
        setChosenSubjectOne(chosenSubject);

        /*
        if (proposedSubjectOne.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectOne);
        } else if (proposedSubjectTwo.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectTwo);
        } else if (proposedSubjectThree.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectThree);
        }
*/
        for (ListClass l : propsedSubjectList) {                                  // ÄNDRA KODEN OVANFÖR TILL EN SÅN HÄR LISTA
            if (chosenSubject.equalsIgnoreCase(l.getName())) {
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

    public ListClass getProposedSubjectOne() {
        return proposedSubjectOne;
    }

    public ListClass getProposedSubjectTwo() {
        return proposedSubjectTwo;
    }

    public ListClass getProposedSubjectThree() {
        return proposedSubjectThree;
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
}
