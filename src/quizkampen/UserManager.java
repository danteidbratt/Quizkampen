package quizkampen;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserManager implements Serializable {

    private ArrayList<User> userList = new ArrayList<>();
    private String userFile = "users.ser";

    public UserManager() {
        checkIfFileExist();
        readFile();
        for (User r : userList) {
            if (r.getWins() != 0) {
                System.out.println(r.getUserName());
            }
        }
    }

    public void addUser(String userName) {
        if (!userExist(userName)) {
            userList.add(new User(userName));
            writeToFile();
        } else //TODO Ändra hur felmeddelandet visas
        {
            System.out.println("Användare finns");
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

    public void updateUsers(User p1, User p2) {
        for (User u : userList) {

            if (u.getUserName().equals(p1.getUserName())) {
                u.totalGames = p1.getTotalGames();
                u.wins = p1.wins;
                u.losses = p1.losses;
                u.draws = p1.draws;

                System.out.println("update hände för P1");
            }
            else{
                System.out.println("P1 ingen update");
            }
            if (u.getUserName().equals(p2.getUserName())) {
                u.totalGames = p2.getTotalGames();
                u.wins = p2.wins;
                u.losses = p2.losses;
                u.draws = p2.draws;

                System.out.println("update hände för P2");
            }
            else{
                System.out.println("P2 update nej");
            }
        }
        writeToFile();
    }

}
