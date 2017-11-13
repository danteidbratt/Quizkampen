package quizkampen;

import java.io.IOException;
import java.io.*;
import java.net.Socket;

public class Client {
    
    Socket bridge;
    SessionQ session;

    public Client() {
        try {
            bridge = new Socket("127.0.0.1", 33333);
            System.out.println("hej");
            ObjectOutputStream out = new ObjectOutputStream(bridge.getOutputStream());
                        
            ObjectInputStream in = new ObjectInputStream(bridge.getInputStream());
            session = (SessionQ)in.readObject();
            System.out.println(session.getQuestion().question);
            out.writeObject(session);
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Client c = new Client();
    }
}