package quizkampen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    protected ListClass<Question> science;
    protected ListClass<Question> film;
    protected ListClass<Question> food;
    protected ListClass<Question> celebrities;
    protected ListClass<Question> music;
    protected ListClass<Question> politics;
    protected ListClass<Question> geography;
    protected ListClass<Question> history;
    protected ListClass<Question> it;
    protected ListClass<Question> sport;
    protected List<ListClass<Question>> subjectList = new ArrayList<ListClass<Question>>();
    protected SessionQ sessionQ;

    Database () {
    
        science = createQuestionList("Database_Science.txt", "Science");
        film = createQuestionList("Database_Film.txt", "Film");
        food = createQuestionList("Database_Food.txt", "Food");
        celebrities = createQuestionList("Database_Celebrities.txt", "Celebrities");
        music = createQuestionList("Database_Music.txt", "Music");
        politics = createQuestionList("Database_Politics.txt", "Politics");
        geography = createQuestionList("Database_Geography.txt", "Geography");
        history = createQuestionList("Database_History.txt", "History");
        it = createQuestionList("Database_IT.txt", "IT");
        sport = createQuestionList("Database_Sport.txt", "Sport");

        subjectList.add(science); // index 0
        subjectList.add(film);
        subjectList.add(food);
        subjectList.add(celebrities);
        subjectList.add(music);
        subjectList.add(politics);
        subjectList.add(geography);
        subjectList.add(history);
        subjectList.add(it);
        subjectList.add(sport); // index 9
    
}
    public void loadThreeSubjects(SessionQ session) {
        sessionQ = session;
        Collections.shuffle(subjectList);

        sessionQ.setProposedSubjectOne(subjectList.get(0));
        sessionQ.setProposedSubjectTwo(subjectList.get(1));
        sessionQ.setProposedSubjectThree(subjectList.get(2));
    }

    public ListClass createQuestionList(String filename, String name) {
        ListClass<Question> tempList = new ListClass<Question>();
//        List<Question> tempList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String tempString;
            while ((tempString = in.readLine()) != null) {
                Question tempQuestion = new Question(tempString);
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

//        temp.setName(name);
        tempList.setName(name);
        return tempList;
    }

}
