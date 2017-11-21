package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected User userOne;
    protected User userTwo;
    private boolean listFull = false;
    protected List<String> chosenSubject = new ArrayList<String>();
    protected List<ListClass> proposedSubjectList = new ArrayList<ListClass>();
    public List<Question> currentQuestions;
    protected List<ListClass<Question>> subjectList = new ArrayList<ListClass<Question>>();

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

    public void setChosenSubject(String subject) {
        this.chosenSubject.add(subject);
    }

    public List<String> getChosenSubject() {
        return this.chosenSubject;
    }

    public void clearProposedSubjectList() {
        this.proposedSubjectList.clear();
    }

    public void setProposedSubject(ListClass subjectList) {
        this.proposedSubjectList.add(subjectList);
    }

    public List<ListClass> getProposedSubject() {
        return proposedSubjectList;
    }

    public void setCurrentQuestions(String chosenSubject, int howManyQuestions) {
        setChosenSubject(chosenSubject);

        for (ListClass l : proposedSubjectList) {                               
            if (chosenSubject.equalsIgnoreCase(l.getName())) {
                currentQuestions = getRandomQsFromList(howManyQuestions, l);
                break;
            }
        }
    }

    public List<Question> getRandomQsFromList(int howManyQuestions, ListClass list) {
        List<Question> lista = new ArrayList<Question>();
        ListClass<Question> searchList = list;

        Random rn = new Random();
        Question temp;
        for (int i = 0; i < searchList.size(); i++) {
            int index = rn.nextInt(searchList.size() - 1);

            temp = searchList.get(index);
            searchList.set(index, searchList.get(i));
            searchList.set(i, temp);
        }

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

    public void loadThreeSubjects() {
        this.getProposedSubject().clear();

        Random rn = new Random();
        ListClass<Question> temp;
        for (int i = 0; i < subjectList.size(); i++) {
            int index = rn.nextInt(subjectList.size() - 1);
            temp = subjectList.get(index);
            subjectList.set(index, subjectList.get(i));
            subjectList.set(i, temp);
        }

        int counter = 0;
        while (!listFull) {
            if (!(getChosenSubject().contains(subjectList.get(counter).getName()))) { // OM ÄMNE EJ FINNS I CHOSEN SUBJECT-LISTAN
                setProposedSubject(subjectList.get(counter));             // ADD ÄMNE TILL PROPOSED SUBJECT-LISTAN
            }
            counter++;
            if (getProposedSubject().size() == 3) { // OM LISTAN ÄR FULL - BREAK
                listFull = true;
            }
        }
    }
}
