package quizkampen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {

    protected String question;
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

    public List<AnswerAlternative> getAnswerAlternatives() {
        return answers;
    }
    public String getAnswerAlternative(int questionIndex){
        return this.answers.get(questionIndex).getAnswer();
    }
}
