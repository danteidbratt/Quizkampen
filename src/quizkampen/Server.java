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
    ObjectOutputStream user1Output;
    ObjectInputStream user1Input;
    private SessionQ session;
    protected Database database = new Database();

    public Server(Socket clientSocket1) {
        try {
            session = new SessionQ();
            database.loadThreeSubjects(session);

            user1Output = new ObjectOutputStream(clientSocket1.getOutputStream());
            user1Input = new ObjectInputStream(clientSocket1.getInputStream());

            user1Output.writeObject(session);
            session = (SessionQ) user1Input.readObject();

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
            this.playGame();                     // SPELET BÖRJAR HÄR

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void playGame() {

        try {
            while (true) {
                session = (SessionQ) user1Input.readObject();    // tar in valt ämne från P1
                user2Output.writeObject(session);       // skickar valt ämne till P2
                session = (SessionQ) user1Input.readObject();    // tar in svar från P1
                user2Output.writeObject(session);           //skickar P1 resultat till P2
                session = (SessionQ) user2Input.readObject(); // får veta när P2 svarat
                user1Output.writeObject(session);       // skickar P2 resultat till P1

                // kolla om antalSpeladeRonder == totalaRonder. BREAK
                
                database.loadThreeSubjects(session);    // laddar om 3 ämnen i session
                user2Output.writeObject(session);       //P2 får de nya ämnena
                session = (SessionQ) user2Input.readObject();    // läser in valt ämne från P2 
                // ska P1 få reda på nästa valda ämne?
                session = (SessionQ) user2Input.readObject();    // läser in svar från P2
                user1Output.writeObject(session);       // skickar ämne + P2 svar till P1
                session = (SessionQ) user1Input.readObject();    // får veta när P1 svarat
                user2Output.writeObject(session);        // skickar P1 resultat till P2
                
                // kolla om antalSpeladeRonder == totalaRonder. BREAK
                
                database.loadThreeSubjects(session);    // laddar om 3 ämnen i session
                user1Output.writeObject(session);       // skickar ämnen till P1

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
