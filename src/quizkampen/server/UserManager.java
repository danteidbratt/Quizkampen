package quizkampen.server;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import quizkampen.client.SessionQ;
import quizkampen.client.User;

public class UserManager implements Serializable {

    private ArrayList<User> userList = new ArrayList<>();
    private String userFile = "users.ser";

    public UserManager() {
        checkIfFileExist();
        readFile();
        for (User r : userList) {
            if (r.getWins() != 0) {
            }
        }
    }

    public void addUser(String userName) {
        if (!userExist(userName)) {
            userList.add(new User(userName));
            writeToFile();
        } else //TODO Ändra hur felmeddelandet visas
        {
        }
    }

    /* Få user när man loggar in */
    public User getUser(String userName) {
        for (User u : userList) {
            if (u.getUserName().equalsIgnoreCase(userName)) {
                return u;
            }
        }
        return null;
    }

    public void removeUser(String userName) {
    }

    public boolean userExist(String userName) {
        for (User u : userList) {
            if (u.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    private void writeToFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(userFile);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            fos.close();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {

        try {
            FileInputStream fis = new FileInputStream(userFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList<User>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void checkIfFileExist() {
        Path path = Paths.get(userFile);
        if ((!Files.exists(path))) {
            writeToFile();
        }
    }

    public void updateDraw(User p1, User p2) {
        for (User u : userList) {
            if (u.getUserName().equals(p1.getUserName())) {
                u.addDraw();
            }
            if (u.getUserName().equals(p2.getUserName())) {
                u.addDraw();
            }
        }
        writeToFile();
    }

    public void updateUsers(User p1, User p2) {

        for (User u : userList) {
            if (u.getUserName().equals(p1.getUserName())) {
                u.addWin();

                System.out.println("update hände för P1");
                System.out.println(u.getUserName() + " total games: "
                        + u.getTotalGames() + " losses: "
                        + u.getLosses() + " wins: " + u.wins + " draws: " + u.draws);
            }

            if (u.getUserName().equals(p2.getUserName())) {
                u.addLoss();

                System.out.println("update hände för P2");
                System.out.println(u.getUserName() + " total games: "
                        + u.getTotalGames() + " losses: "
                        + u.losses + " wins: " + u.wins + " draws: " + u.draws);
            }
        }
        writeToFile();
    }

    public void checkWinner(SessionQ session) {
        if (session.getDraw()) {
            updateDraw(session.getUserOne(), session.getUserTwo());
            System.out.println("blev drawwww");
        } else {
            if (session.getWinner() == session.getUserOne()) {
                
                System.out.println("P1 vann");
                updateUsers(session.getUserOne(), session.getUserTwo());
                
                
            } else {
                System.out.println("P2 vann");
                updateUsers(session.getUserTwo(), session.getUserOne());
            }
        }
    }
}
