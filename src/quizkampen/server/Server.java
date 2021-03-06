package quizkampen.server;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import quizkampen.client.*;

public class Server implements Runnable {

   
    private Socket clientSocket1;
    private Socket clientSocket2;
    ObjectOutputStream user2Output;
    ObjectInputStream user2Input;
    ObjectOutputStream user1Output;
    ObjectInputStream user1Input;
    private SessionQ session;
    protected Database database = new Database();
    private PropertiesReader p;
    Thread playGame;
    UserManager um;

    public Server(Socket clientSocket1, UserManager um) {
        try {
            this.clientSocket1 = clientSocket1;
            session = new SessionQ();
            session.setSubjectList(database.loadSubjectList());
            session.setSubjectQueue();
            p = new PropertiesReader();
            session.setTotalRounds(p.getRonds());
            session.setTotalQsInRond(p.getQuestionsInRond());
            session.setTimerLength(p.getTimerLength());
            playGame = new Thread(this);
            this.um = um;

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
            playGame.start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            session.setSubjectQueue();
            while (session.getState() != session.SHUTDOWN) {
                user1Output.writeObject(session);
                session = (SessionQ) user1Input.readObject();
                
                if (session.getState() == session.SHUTDOWN) {
                    um.checkWinner(session);
                    break;
                }
                user2Output.writeObject(session);
                session = (SessionQ) user2Input.readObject();
                
                if (session.getState() == session.SHUTDOWN) {
                    um.checkWinner(session);
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
