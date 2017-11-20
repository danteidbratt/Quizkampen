package quizkampen;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerListener extends Thread {

    protected int port = 33333;
    ServerSocket serverSocket;

    public ServerListener() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSock1 = serverSocket.accept();
                Server server = new Server(clientSock1, serverSocket);
            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerListener sl = new ServerListener();
        sl.start();
    }
}
