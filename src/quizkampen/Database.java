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
    private boolean listFull = false;

    Database() {

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

    public void loadThreeSubjects(SessionQ session) {                   // NY - istället för metoden nedanför
        sessionQ = session;
        sessionQ.proposedSubjectList.clear();
        Collections.shuffle(subjectList);
        int counter = 0;
        while (listFull == false) {
            if ((sessionQ.getChosenSubject().contains(subjectList.get(counter).getName())) == false) { // OM ÄMNE EJ FINNS I CHOSEN SUBJECT-LISTAN
                sessionQ.setProposedSubject(subjectList.get(counter));                 // ADD ÄMNE TILL PROPOSED SUBJECT-LISTAN
            }
            counter++;
            if (sessionQ.getProposedSubject().size() == 3) { // OM LISTAN ÄR FULL - BREAK
                listFull = true;
            }

        }
    }

//        Random rn = new Random();
//
//        int one = rn.nextInt(subjectList.size() - 1);
//        int two;
//        while ((two = rn.nextInt(subjectList.size() - 1)) == one) {
//            two = rn.nextInt(subjectList.size() - 1);
//        }
//        int three;
//        while ((three = rn.nextInt(subjectList.size() - 1)) == two
//                || (three = rn.nextInt(subjectList.size() - 1)) == one) {
//            three = rn.nextInt(subjectList.size() - 1);
//        }
//
//        sessionQ.setProposedSubject(subjectList.get(one));
//        sessionQ.setProposedSubject(subjectList.get(two));
//        sessionQ.setProposedSubject(subjectList.get(three));
    public ListClass createQuestionList(String filename, String name) {
        ListClass<Question> tempList = new ListClass<Question>();
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
        tempList.setName(name);
        return tempList;
    }

}
