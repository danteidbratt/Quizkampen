package quizkampen.server;

import java.io.Serializable;
import java.util.Queue;

public class Subject implements Serializable {

    String name;
    Queue<Question> questions;

    public Subject(String name, Queue questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public Question getQuestion() {
        return questions.remove();
    }
}
