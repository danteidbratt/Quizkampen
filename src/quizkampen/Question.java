package quizkampen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {

    public String question;
    protected List<AnswerAlternative> answers = new ArrayList<>();

    public Question(String question) {
        this.question = question;
    }

    public void setAnswerAlternatives(String answer, boolean isTrue) {
        answers.add(new AnswerAlternative(answer, isTrue));
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionQ() {
        return question;
    }

    public List getAnsweAlternative() {
        return answers;
    }
}
