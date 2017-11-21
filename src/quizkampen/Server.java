package quizkampen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {

    private Socket clientSocket1;
    private Socket clientSocket2;
//    private List<Question> questions = new ArrayList<>();
//    private Question tempQuestion;
    private SessionQ session;
    protected Database database = new Database();
//    protected boolean waitingForClient2 = false;
    protected Thread updateSubjects;

    public Server(Socket clientSocket1) {
        try {
            session = new SessionQ();
            database.loadThreeSubjects(session);
            

            ObjectOutputStream user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
            ObjectInputStream user1Input = new ObjectInputStream(clientSocket1.getInputStream());

            user1Output.writeObject(session);

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPlayer2(Socket clientsocket2) {
        this.clientSocket2 = clientsocket2;
        ObjectOutputStream user2Output;
        ObjectInputStream user2Input;
        try {
            user2Output = new ObjectOutputStream(clientSocket2.getOutputStream());
            user2Input = new ObjectInputStream(clientSocket2.getInputStream());

            user2Output.writeObject(session);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            if (session.getRequestingNewSubjects()) {    // FIXAR SEN / ANNA&CLAUDIA
                database.loadThreeSubjects(session);
                session.setRequestingNewSubjects(false);
            }
        }
    }

}
