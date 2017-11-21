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

    private Socket clientSocket1;
    private Socket clientSocket2;
    private SessionQ session;
    protected Database database = new Database();

    public Server(Socket clientSocket1) {
        try {
            session = new SessionQ();
            database.loadThreeSubjects(session);

            ObjectOutputStream user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
            ObjectInputStream user1Input = new ObjectInputStream(clientSocket1.getInputStream());

            user1Output.writeObject(session);
//            while ((session = (SessionQ) user1Input.readObject()) != null) {
//                if (session.getRequestingNewSubjects()) {    //  - metod som fyller på frågor i session objektet
//                    database.loadThreeSubjects(session);
//                    session.setRequestingNewSubjects(false);
//                }
//                user1Output.writeObject(session);
//            }
            session = (SessionQ) user1Input.readObject();

        } catch (IOException | ClassNotFoundException ex) {
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
            session = (SessionQ) user2Input.readObject();

//            while ((session = (SessionQ) user2Input.readObject()) != null) {
//                if (session.getRequestingNewSubjects()) {    //  - metod som fyller på frågor i session objektet
//                    database.loadThreeSubjects(session);
//                    session.setRequestingNewSubjects(false);
//                }
//                user2Output.writeObject(session);
//            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

