package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected String username;

    protected List<String> chosenSubject = new ArrayList<String>();  // NY    
    protected List<ListClass> proposedSubjectList = new ArrayList<ListClass>();  // NY
    public List<Question> currentQuestions;

    private boolean requestingNewSubjects = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        username = u;
    }

    public void setChosenSubject(String subject) {
        this.chosenSubject.add(subject);
    }

    public List<String> getChosenSubject() {
        return this.chosenSubject;
    }

    public void setProposedSubject(ListClass subjectList) {
        this.proposedSubjectList.add(subjectList);
    }

    public List<ListClass> getProposedSubject() {
        return proposedSubjectList;
    }

    public void setCurrentQuestions(String chosenSubject, int howManyQuestions) {
        setChosenSubject(chosenSubject);

//        if (proposedSubjectList.get(0).getName().equals(chosenSubject)) {
//            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectList.get(0));
//        } else if (proposedSubjectList.get(1).getName().equals(chosenSubject)) {
//            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectList.get(1));
//        } else if (proposedSubjectList.get(2).getName().equals(chosenSubject)) {
//            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectList.get(2));
//        }

        for (ListClass l : proposedSubjectList) {                                  // ÄNDRA KODEN OVANFÖR TILL EN SÅN HÄR LISTA
            if (chosenSubject.equalsIgnoreCase(l.getName())) {
                currentQuestions = getRandomQsFromList(howManyQuestions, l);
                break;
            }
        }
    }

    public List<Question> getRandomQsFromList(int howManyQuestions, ListClass list) {
        List<Question> lista = new ArrayList<Question>();
        ListClass<Question> searchList = list;
        Collections.shuffle(searchList);

        for (int i = 0; i < howManyQuestions; i++) {
            lista.add(searchList.get(i));
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
}
