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

    public Server(Socket clientSocket1) {
        try {
            this.clientSocket1 = clientSocket1;
            session = new SessionQ();
            session.setSubjectList(database.loadSubjectList());
            session.setSubjectQueue();

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
            while (true) {

                // kolla om antalSpeladeRonder == totalaRonder. BREAK
                session.setSubjectQueue();

                while(true){
                user1Output.writeObject(session); // skickar P2 namn till P1     (set result screen P1)
                session = (SessionQ) user1Input.readObject(); // läser in valt ämne + svar från P1
                // IF - gameOver - break
                // IF - Rond is over - LoadQuestions?
                user2Output.writeObject(session); // skickar valt ämne + P1 resultat till P2    (set result screen P2)
                
                session = (SessionQ) user2Input.readObject(); // läser in från P2
                user1Output.writeObject(session); // skickar till P1
                // IF - GameOver - break
    
                
                
                }
                
                
//                
//                user2Output.writeObject(session);       //P2 får de nya ämnena
//                session = (SessionQ) user2Input.readObject();    // läser in valt ämne från P2 
//                session = (SessionQ) user2Input.readObject();    // läser in svar från P2
//                user1Output.writeObject(session);       // skickar ämne + P2 svar till P1
//                session = (SessionQ) user1Input.readObject();    // får veta när P1 svarat
//                user2Output.writeObject(session);        // skickar P1 resultat till P2
//
//                user1Output.writeObject(session);       // skickar ämnen till P1
//
//                
                

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
