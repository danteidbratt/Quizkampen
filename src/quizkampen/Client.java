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
        try {
            this.bridge = new Socket("127.0.0.1", 33333);
            Window w = new Window();
            w.setFrame();
            w.welcomeScreen.setPanel();
            w.welcomeScreen.setActionListener(w);
            
            ObjectOutputStream out = new ObjectOutputStream(bridge.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(bridge.getInputStream());

            session = (SessionQ) in.readObject();
            w.setSessionQ(session);
            new SessionHandler(session);

            out.writeObject(session);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
    }
}
