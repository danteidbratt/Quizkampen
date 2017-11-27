package quizkampen.server;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ServerListener extends Thread {

    protected int port1 = 33333;
    ServerSocket serverSocket1;

    public ServerListener() throws IOException {
        serverSocket1 = new ServerSocket(port1);
        UserServerListener s2 = new UserServerListener();
        s2.start();
    }

    @Override
    public void run() {         // Väntar på att en user ska trycka "Random Player" - då startas servern
        while (true) {          // När user2 klickar "Random Player" kopplas den upp till samma server
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

    public class UserServerListener extends Thread {    // Väntar på att en användare ska logga in

        protected int port2 = 33334;
        ServerSocket loginSocket;

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
                    Socket clientSocket1 = loginSocket.accept();
                    UserServerConnection uc = new UserServerConnection(clientSocket1);
                    uc.start();
                } catch (IOException ex) {
                    Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public class UserServerConnection extends Thread {  // Skickar user object när användare loggat in

        Socket clientSock;

        public UserServerConnection(Socket clientSocket) {
            this.clientSock = clientSocket;
        }

        @Override
        public void run() {
            ObjectOutputStream out1 = null;
            ObjectInputStream in1 = null;
            UserManager um = new UserManager();
            try {
                out1 = new ObjectOutputStream(clientSock.getOutputStream());
                in1 = new ObjectInputStream(clientSock.getInputStream());
                String userName1 = (String) in1.readObject();
                if (um.userExist(userName1)) {
                    out1.writeObject(um.getUser(userName1));
                } else {
                    um.addUser(userName1);
                    out1.writeObject(um.getUser(userName1));
                }               
                              
            } catch (IOException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    out1.close();
                } catch (IOException ex) {
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
