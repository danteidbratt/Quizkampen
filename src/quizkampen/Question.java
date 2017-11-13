package quizkampen;

import java.util.ArrayList;
import java.util.List;

public class Question {
	String question;
	List<AnswerAlternative> answers = new ArrayList<>();
	
	public Question(String question) {
		this.question = question;
	}
	
	public void setAnswerAlternatives(String answer, boolean isTrue) {
		answers.add(new AnswerAlternative(answer, isTrue));
	}
}
