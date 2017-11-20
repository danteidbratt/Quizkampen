package quizkampen;

import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Socket bridge;
    SessionQ session;
    Window w;

    public Client() {
//        try {
            Window w = new Window();
//            this.bridge = new Socket("127.0.0.1", 33333);

            w.setFrame();
            w.ws.setPanel();
            w.ws.setActionListener(w);

//            ObjectOutputStream out = new ObjectOutputStream(bridge.getOutputStream());
//            ObjectInputStream in = new ObjectInputStream(bridge.getInputStream());
//            session = (SessionQ) in.readObject();
//            w.setSessionQ(session);
//            SessionHandler sessionHandler = new SessionHandler(session);
//
//            out.writeObject(session);
//        } catch (IOException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    public static void main(String[] args) {
        Client c = new Client();
    }
}
