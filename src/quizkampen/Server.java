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
    ObjectOutputStream user2Output;
    ObjectInputStream user2Input;
    private SessionQ session;
    protected boolean p2Connected = false;
    protected Database database = new Database();

    public Server(Socket clientSocket1) {
        try {
            session = new SessionQ();
            database.loadThreeSubjects(session);

            ObjectOutputStream user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
            ObjectInputStream user1Input = new ObjectInputStream(clientSocket1.getInputStream());

            user1Output.writeObject(session);
            session = (SessionQ) user1Input.readObject();

            while (!p2Connected) {
                if (p2Connected) {
                    user2Output.writeObject(session);
                    session = (SessionQ) user2Input.readObject(); 
                }
                break;
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPlayer2(Socket clientsocket2) {
        this.clientSocket2 = clientsocket2;
        try {
            user2Output = new ObjectOutputStream(clientSocket2.getOutputStream());
            user2Input = new ObjectInputStream(clientSocket2.getInputStream());
            p2Connected = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
