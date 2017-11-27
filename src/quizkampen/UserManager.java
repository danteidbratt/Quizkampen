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

    /* Testar klassen */
//    public static void main(String[] args) {
//
//        UserManager userManager = new UserManager();
//
//        userManager.addUser("Claudia");
//        userManager.addUser("Anna");
//        userManager.addUser("David");
//        userManager.addUser("Daven");
//        userManager.addUser("hej");
//        userManager.addUser("på");
//        userManager.addUser("dig");
//                userManager.addUser("testttt");
//        userManager.addUser("ja");
//        userManager.addUser("nej");
//        userManager.addUser("fffå");
//        userManager.addUser("ggggg");
//
//        userManager.writeToFile();
//        
//        userManager.printUserList();
//    }
    public void updateUser(User user) {

        for (User u : userList) {

            if (u.getUserName().equals(user.getUserName())) {

                u.totalGames = user.getTotalGames();
                u.wins = user.wins;
                u.losses = user.losses;
                u.draws = user.draws;

                System.out.println("update hände");
                break;
            }

        }

        writeToFile();

    }

}
