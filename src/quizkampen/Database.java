package quizkampen;

import java.io.*;
import java.util.*;
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
    protected List<ListClass<Question>> subjectList = new ArrayList<>();

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

    public List<ListClass<Question>> loadSubjectList() {
        return this.subjectList;
    }

    public ListClass createQuestionList(String filename, String name) {
        ListClass<Question> tempList = new ListClass<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String tempString;
            while ((tempString = in.readLine()) != null) {
                Question tempQuestion = new Question(tempString);
                tempQuestion.setAnswerAlternatives(in.readLine(), true);
                for (int i = 0; i < 3; i++) {
                    tempQuestion.setAnswerAlternatives(in.readLine(), false);
                }

                Random rn = new Random();
                AnswerAlternative temp;
                for (int i = 0; i < tempQuestion.getAnswerAlternatives().size(); i++) {
                    int index = rn.nextInt(tempQuestion.getAnswerAlternatives().size() - 1);
                    temp = tempQuestion.getAnswerAlternatives().get(index);
                    tempQuestion.getAnswerAlternatives().set(index, tempQuestion.getAnswerAlternatives().get(i));
                    tempQuestion.getAnswerAlternatives().set(i, temp);
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
