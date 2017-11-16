package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    protected String username;
    protected String chosenSubjectOne;
    protected String chosenSubjectTwo;
    public ListClass<Question> proposedSubjectOne;
    protected ListClass<Question> proposedSubjectTwo;
    protected ListClass<Question> proposedSubjectThree;
//    protected List<ListClass> propsedSubjectList
//            = Arrays.asList(proposedSubjectOne,
//            proposedSubjectTwo, proposedSubjectThree);
    public List<Question> currentQuestions;
    private int totalRonds;
    private int totalQuestionsinRond;

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
        
        if (proposedSubjectOne.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectOne); 
        }
        else if (proposedSubjectTwo.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectTwo); 
        }
        else if (proposedSubjectThree.getName().equals(chosenSubject)) {
            currentQuestions = getRandomQsFromList(howManyQuestions, proposedSubjectThree); 
        }

//        for (ListClass l : propsedSubjectList) {
//            if (chosenSubject.equalsIgnoreCase(l.getName())) {
//                currentQuestions = getRandomQsFromList(howManyQuestions, l);
//                break;
//            }
//        }
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
    
    public void setTotalQsInRond(int totalQuestions){
        this.totalQuestionsinRond = totalQuestions;
    }
    public void setTotalRonds(int totalRonds){
        this.totalRonds = totalRonds;
    }
    public int getTotalQsInRond(){
        return this.totalQuestionsinRond;
    }
    public int getTotalRonds(){
        return this.totalRonds;
    }
}
