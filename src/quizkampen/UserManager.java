package quizkampen;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserManager implements Serializable {
    private ArrayList<User> userList = new ArrayList<>();

    public UserManager() {
        fileExist();
        readFile();
    }
 
    public void addUser(String userName) {
        if(!userExist(userName)) {
            userList.add(new User(userName));
            writeToFile();
        }
        else
            //TODO Ändra hur felmeddelandet visas
            System.out.println("Användare finns");
    }

    /* Få user när man loggar in */
    public User getUser(String userName) {
        for(User u : userList) {
            if(u.getUserName().equalsIgnoreCase(userName))
                return u;
        }
        return null;
    }

    public void removeUser(String userName) { }

    public boolean userExist(String userName) {
        for(User u : userList) {
            if(u.getUserName().equalsIgnoreCase(userName))
                return true;
        }
        return false;
    }

    private void writeToFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("users.ser");

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
            FileInputStream fis = new FileInputStream("users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userList = (ArrayList<User>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fileExist() {
        Path path = Paths.get("users.ser");
        if ((!Files.exists(path))) {
            writeToFile();
        }
    }

    /* Testar klassen */
    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        userManager.addUser("Test");
    }
}
