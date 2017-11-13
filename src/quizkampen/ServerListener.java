package quizkampen;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerListener extends Thread {
    protected int port = 33333;
    ServerSocket serverSocket;
    Session session = new Session();

    public ServerListener() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSock1 = serverSocket.accept();
                
                ObjectOutputStream user1Output = new ObjectOutputStream(clientSock1.getOutputStream());
                ObjectInputStream user1Input = new ObjectInputStream(clientSock1.getInputStream());
                user1Output.writeObject(session);
                
                Socket clientSock2 = serverSocket.accept();
                ObjectOutputStream user2Output = new ObjectOutputStream(clientSock2.getOutputStream());
                ObjectInputStream user2Input = new ObjectInputStream(clientSock2.getInputStream());
                user2Output.writeObject(session);
                
                Server server = new Server(clientSock1, clientSock2);

            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {

    }
}
