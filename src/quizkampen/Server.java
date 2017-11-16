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

public class Server {

    private Socket clienSocket1;
    private Socket clienSocket2;
    private List<Question> questions = new ArrayList<>();
    private Question tempQuestion;
    private SessionQ session;
    protected Database database = new Database();
    protected boolean waitingForClient2 = false;
    ServerSocket serverSocket;

    public Server(Socket clientSocket1, ServerSocket serverSocket) {                // NY
        try {
            this.serverSocket = serverSocket;
            session = new SessionQ();

            database.loadThreeSubjects(session);

            ObjectOutputStream user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
            ObjectInputStream user1Input = new ObjectInputStream(clientSocket1.getInputStream());

            user1Output.writeObject(session);

            while (!waitingForClient2) {                                     // Ny metod som connectar till klient nr 2
                Socket clientSocket2;
                if ((clientSocket2 = serverSocket.accept()) != null) {
                    ObjectOutputStream user2Output = new ObjectOutputStream(clientSocket2.getOutputStream());
                    ObjectInputStream user2Input = new ObjectInputStream(clientSocket2.getInputStream());
                    user2Output.writeObject(session);
                    waitingForClient2 = true;
                }
            }

            while (true) {
                if (session.getRequestingNewSubjects()) {
                    // fyll på frågor
                    session.setRequestingNewSubjects(false);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
