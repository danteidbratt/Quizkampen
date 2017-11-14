package quizkampen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    protected List<Question> science;
    protected List<Question> film;
    protected List<Question> food;
    protected List<Question> celebrities;
    protected List<Question> music;
    protected List<Question> politics;
    protected List<Question> geography;
    protected List<Question> history;
    protected List<Question> it;
    protected List<Question> sport;
    protected List<List> subjectList;
    protected SessionQ sessionQ;

    public void createSubjectList() {
        science = createQuestionList("Database_Science.txt");
        film = createQuestionList("Database_Film.txt");
        food = createQuestionList("Database_Food.txt");
        celebrities = createQuestionList("Database_Celebrities.txt");
        music = createQuestionList("Database_Music.txt");
        politics = createQuestionList("Database_Politics.txt");
        geography = createQuestionList("Database_Geography.txt");
        history = createQuestionList("Database_History.txt");
        it = createQuestionList("Database_IT.txt");
        sport = createQuestionList("Database_Sport.txt");

        subjectList.add(science);
        subjectList.add(film);
        subjectList.add(food);
        subjectList.add(celebrities);
        subjectList.add(music);
        subjectList.add(politics);
        subjectList.add(geography);
        subjectList.add(history);
        subjectList.add(it);
        subjectList.add(sport);
    }

    public SessionQ loadThreeQuestions(SessionQ session) {
        sessionQ = session;

        
       // KOMMENTERA BORT NÄR TEXTFILERNA ÄR FYLLDA MED FRÅGOR // ANNA & CLAUDIA
//        Random rn = new Random();
//
//        int one = rn.nextInt(subjectList.size() - 1);
//        int two;  
//        while ((two = rn.nextInt(subjectList.size() - 1)) == one) {
//            two = rn.nextInt(subjectList.size() - 1);
//        }      
//        int three;
//        while ((three= rn.nextInt(subjectList.size() - 1)) == two 
//                || (three = rn.nextInt(subjectList.size() - 1)) == one) {
//            three = rn.nextInt(subjectList.size() - 1);
//        }
//
//        sessionQ.setProposedSubjectOne(subjectList.get(one));
//        sessionQ.setProposedSubjectOne(subjectList.get(two));
//        sessionQ.setProposedSubjectThree(subjectList.get(three));

        sessionQ.setProposedSubjectOne(subjectList.get(0));
        sessionQ.setProposedSubjectTwo(subjectList.get(1));
        sessionQ.setProposedSubjectThree(subjectList.get(2));
        return sessionQ;

    }

    public List createQuestionList(String filename) {
        List<Question> tempList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String temp;
            while ((temp = in.readLine()) != null) {
                Question tempQuestion = new Question(temp);
                tempQuestion.setAnswerAlternatives(in.readLine(), true);
                for (int i = 0; i < 3; i++) {
                    tempQuestion.setAnswerAlternatives(in.readLine(), false);
                }
                tempList.add(tempQuestion);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempList;
    }

}
