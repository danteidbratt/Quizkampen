package quizkampen;

import java.io.Serializable;
import java.util.*;

public class SessionQ implements Serializable {

    private int totalRonds;
    private int totalQuestionsinRond;
    protected User userOne;
    protected User userTwo;
    protected State state;
    private boolean listFull = false;
    protected List<String> chosenSubject = new ArrayList<>();
    protected List<ListClass> proposedSubjectList = new ArrayList<>();
    public List<Question> currentQuestions = new ArrayList<>();
    protected List<Question> usedQuestions = new ArrayList<>();
    protected List<ListClass<Question>> subjectList = new ArrayList<>();
    
   
    private PropertiesReader p;
    
    protected User userChosing; 
    
        SessionQ() {
        p = new PropertiesReader();
        this.totalRonds = p.getRonds();
        this.totalQuestionsinRond = p.getQuestionsInRond();
        this.setTotalRonds(totalRonds);
        this.setTotalQsInRond(totalQuestionsinRond);
        userChosing = userOne;
        this.state = State.WAITING4P1USERNAME;
    }

    public void ChangeUserChosing() {
        if (userChosing == userOne) {
            setUserChosing(userTwo);
        } else if (userChosing == userTwo) {
            setUserChosing(userOne);
        }
    }

    public void setUserChosing(User userChosing) {
        this.userChosing = userChosing; 
    }
    public User getUserChosing (){
        return userChosing;
    }

    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return state; 
    }
    
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
        this.setChosenSubject(chosenSubject);
        currentQuestions.clear();

        for (ListClass l : proposedSubjectList) {
            if (chosenSubject.equalsIgnoreCase(l.getName())) {
                currentQuestions = getRandomQsFromList(howManyQuestions, l);

                // lägger till currentQuestions i usedQuestions-listan
                this.usedQuestions.addAll(currentQuestions);
                break;
            }

        }
    }

    public List<Question> getRandomQsFromList(int howManyQuestions, ListClass list) {
        List<Question> randomQuestionsList = new ArrayList<>();
        ListClass<Question> chosenSubjectList = list;

        Random rn = new Random();
        Question temp;
        for (int i = 0; i < 300; i++) {
            int randNum1 = rn.nextInt(chosenSubjectList.size() - 1);
            int randNum2 = rn.nextInt(chosenSubjectList.size() - 1);
            temp = chosenSubjectList.get(randNum1);
            chosenSubjectList.set(randNum1, chosenSubjectList.get(randNum2));
            chosenSubjectList.set(randNum2, temp);
        }
        // 2
        // Daven: ändrade den lite då det inte såg ut som att den skulle fungera
        for (int i = 0; i < howManyQuestions; i++) {                    // Om Question inte finns i usedQuestions-lista
            if (!(usedQuestions.contains(chosenSubjectList.get(i)))) {      // läggs den till i randomQs-lista
                randomQuestionsList.add(chosenSubjectList.get(i));
            }
        }
        return randomQuestionsList;
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

    public int getTotalRounds() {
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
