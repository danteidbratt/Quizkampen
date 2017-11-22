package quizkampen;

import java.util.Queue;

public class Subject {
	String name;
	Queue questions;

	public Subject(String name, Queue questions) {
		this.name = name;
		this.questions = questions;
	}
	
	public String getName() {
		return name;
	}

	public Queue getQuestion() {
		return questions;
	}
}
