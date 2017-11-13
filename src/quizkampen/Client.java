
package quizkampen;

import java.io.IOException;
import java.io.*;
import java.net.Socket;

public class Client {
    
    Socket bridge;

    public Client() {
        try {
            bridge = new Socket("127.0.0.1", 33333);
            ObjectOutputStream out = new ObjectOutputStream(bridge.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(bridge.getInputStream());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Client c = new Client();
    }
}