package quizkampen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private Socket clienSocket1;
    private Socket clienSocket2;
    private List<Question> questions = new ArrayList<>();
    private Question tempQuestion;
    private SessionQ session;

<<<<<<< HEAD
    public Server(Socket clientSocket1, Socket clientSocket2) throws IOException {
        this.clienSocket1 = clientSocket1;
        this.clienSocket2 = clientSocket2;
        readQuestionsFromFile();
        session = new SessionQ();
        session.setQuestion(questions.get(0));

        ObjectOutputStream user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
        ObjectInputStream user1Input = new ObjectInputStream(clientSocket1.getInputStream());

        ObjectOutputStream user2Output = new ObjectOutputStream(clientSocket2.getOutputStream());
        ObjectInputStream user2Input = new ObjectInputStream(clientSocket2.getInputStream());

        user1Output.writeObject(session);
        user2Output.writeObject(session);
    }

    public void readQuestionsFromFile() throws FileNotFoundException {
        BufferedReader in = new BufferedReader(
                new FileReader("Questions_database.txt"));
        for (int i = 0; i < 5; i++) {
            try {
                tempQuestion = new Question(in.readLine());
                tempQuestion.setAnswerAlternatives(in.readLine(), true);
                tempQuestion.setAnswerAlternatives(in.readLine(), false);
                tempQuestion.setAnswerAlternatives(in.readLine(), false);
                tempQuestion.setAnswerAlternatives(in.readLine(), false);
                questions.add(tempQuestion);
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }
}
=======
    public Server(Socket clientSocket1, Socket clientSocket2) {
        this.clienSocket1 = clientSocket1;
        this.clienSocket2 = clientSocket2;

        try(ObjectOutputStream user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
            ObjectInputStream user1Input = new ObjectInputStream(clientSocket1.getInputStream());
            ObjectOutputStream user2Output = new ObjectOutputStream(clientSocket2.getOutputStream());
            ObjectInputStream user2Input = new ObjectInputStream(clientSocket2.getInputStream());) {

            readQuestionsFromFile();
            session = new SessionQ();
            session.setQuestion(questions.get(1));
            
            user1Output.writeObject(session);
            session = (SessionQ) user1Input.readObject();
            user2Output.writeObject(session);
            session = (SessionQ) user2Input.readObject();

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readQuestionsFromFile() throws FileNotFoundException {
        File file = new File("Questions_database.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            tempQuestion = new Question(sc.nextLine());
            tempQuestion.setAnswerAlternatives(sc.nextLine(), true);
            tempQuestion.setAnswerAlternatives(sc.nextLine(), false);
            tempQuestion.setAnswerAlternatives(sc.nextLine(), false);
            tempQuestion.setAnswerAlternatives(sc.nextLine(), false);
            questions.add(tempQuestion);
        }
        sc.close();
    }
}
>>>>>>> master
