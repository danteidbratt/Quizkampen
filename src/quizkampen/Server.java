package quizkampen;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Server {

    private Socket clientSocket1;
    private Socket clientSocket2;
    ObjectOutputStream user2Output;
    ObjectInputStream user2Input;
    ObjectOutputStream user1Output;
    ObjectInputStream user1Input;
    private SessionQ session;
    protected Database database = new Database();
    private PropertiesReader p;

    public Server(Socket clientSocket1) {
        try {
            this.clientSocket1 = clientSocket1;
            session = new SessionQ();
            session.setSubjectList(database.loadSubjectList());
            session.setSubjectQueue();
            p = new PropertiesReader();
            session.setTotalRounds(p.getRonds());
            session.setTotalQsInRond(p.getQuestionsInRond());

            user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
            user1Input = new ObjectInputStream(clientSocket1.getInputStream());

            user1Output.writeObject(session);   // skickar session till P1 för första gången
            session = (SessionQ) user1Input.readObject();   // tar emot session med user1 uppdaterad

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPlayer2(Socket clientsocket2) {
        this.clientSocket2 = clientsocket2;
        try {
            user2Output = new ObjectOutputStream(clientSocket2.getOutputStream());
            user2Input = new ObjectInputStream(clientSocket2.getInputStream());
            user2Output.writeObject(session);
            session = (SessionQ) user2Input.readObject();
            this.playGame();                     // spelet börjar här

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void playGame() {
        try {
            session.setSubjectQueue();
            while (session.getState() != session.SHUTDOWN) {
                user1Output.writeObject(session);
                session = (SessionQ) user1Input.readObject();
                if (session.getState() == session.SHUTDOWN)
                    break;
                user2Output.writeObject(session);
                session = (SessionQ) user2Input.readObject();
            }
            System.out.println("Server loop ends");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
