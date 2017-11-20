package quizkampen;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerListener extends Thread {

    protected int port1 = 33333;
    ServerSocket serverSocket1;

    public ServerListener() throws IOException {
        serverSocket1 = new ServerSocket(port1);
        UserServerListener s2 = new UserServerListener();
        s2.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSock1 = serverSocket1.accept();
                Server server = new Server(clientSock1);
                Socket clientSock2 = serverSocket1.accept();
                server.setPlayer2(clientSock2);

            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public class UserServerListener extends Thread {

        protected int port2 = 33334;
        ServerSocket loginSocket;
        UserManager um = new UserManager();

        public UserServerListener() {
            try {
                loginSocket = new ServerSocket(port2);
            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket clientSocket2 = loginSocket.accept();
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket2.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(clientSocket2.getInputStream());
                    String userName = (String) in.readObject();
                    System.out.println(userName);
                    if (um.userExist(userName)) {
                        out.writeObject(um.getUser(userName));
                    } else {
                        um.addUser(userName);
                        out.writeObject(um.getUser(userName));
                    }

                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerListener sl = new ServerListener();
        sl.start();
    }
}
