package quizkampen;

import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    Socket bridge;
    SessionQ session;

    public Client() {
        try {
            this.bridge = new Socket("127.0.0.1", 33333);
//            Gui gui = new Gui();
            Window w = new Window();
            ObjectOutputStream out = new ObjectOutputStream(bridge.getOutputStream());                   
            ObjectInputStream in = new ObjectInputStream(bridge.getInputStream());

           
            session = (SessionQ)in.readObject();
            System.out.println(session.proposedSubjectOne.get(0).question);
            
            out.writeObject(session);
            
    }   catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public static void main(String[] args) {
        Client c = new Client();
    }
}