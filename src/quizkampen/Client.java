package quizkampen;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private Window w;

    private void startGui(Client client) {
        w = new Window(client);
        w.setFrame();
        w.welcomeScreen.setPanel();
        w.welcomeScreen.setActionListener(w);
    }

    public void connect() {
        try {
            Socket bridge = new Socket("127.0.0.1", 33333);
            ObjectOutputStream out = new ObjectOutputStream(bridge.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(bridge.getInputStream());

            SessionQ session = (SessionQ) in.readObject();
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
        Client client = new Client();
        client.startGui(client);
    }
}
