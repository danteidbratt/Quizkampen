package quizkampen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private Socket clienSocket1;
    private Socket clienSocket2;
    private List<Question> questions = new ArrayList<>();
    private Question tempQuestion;
    private SessionQ session;
    protected Database database = new Database();

    public Server(Socket clientSocket1, Socket clientSocket2) throws IOException {
        this.clienSocket1 = clientSocket1;
        this.clienSocket2 = clientSocket2;
        session = new SessionQ();

        database.loadThreeSubjects(session);

        ObjectOutputStream user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
        ObjectInputStream user1Input = new ObjectInputStream(clientSocket1.getInputStream());

        ObjectOutputStream user2Output = new ObjectOutputStream(clientSocket2.getOutputStream());
        ObjectInputStream user2Input = new ObjectInputStream(clientSocket2.getInputStream());

        user1Output.writeObject(session);
        user2Output.writeObject(session);
        
        while(true){
            if(session.getRequestingNewSubjects() == true){
                // fyll på frågor
                
                session.setRequestingNewSubjects(false);
            }
        }
    }

}
