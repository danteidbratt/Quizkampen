package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected String username;

    protected List<String> chosenSubject = new ArrayList<String>();  // NY    
    private List<ListClass> proposedSubjectList = new ArrayList<ListClass>();  // NY
    public List<Question> currentQuestions;

    private boolean requestingNewSubjects = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        username = u;
    }

    public void clearProposedSubjectList() {
        proposedSubjectList.clear();
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

        for (ListClass l : proposedSubjectList) {                                  // NY (Davens metod)
            if (chosenSubject.equalsIgnoreCase(l.getName())) {
                currentQuestions = getRandomQsFromList(howManyQuestions, l);
                break;
            }
        }
    }

    private List<Question> getRandomQsFromList(int howManyQuestions, ListClass list) {
        List<Question> lista = new ArrayList<Question>();
        Collections.shuffle(list);

        for (int i = 0; i < howManyQuestions; i++) {
            lista.add(lista.get(i));
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
}
