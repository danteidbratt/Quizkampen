package quizkampen;

import java.util.ArrayList;
import java.util.List;

public class Question {
	public String question;
	private List<AnswerAlternative> answers = new ArrayList<>();
	
	public Question(String question) {
		this.question = question;
	}
	
	public void setAnswerAlternatives(String answer, boolean isTrue) {
		answers.add(new AnswerAlternative(answer, isTrue));
	}
        public void setQuestion(String question){
            this.question = question;
        }
}