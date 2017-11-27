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
//        readUserFile();
    }

    private void writeToUserFile() {
        try (FileOutputStream fou = new FileOutputStream("userObject.ser");
                ObjectOutputStream ous = new ObjectOutputStream(fou)) {

            for (User u : userList) {
                ous.writeObject(u);
            }
            fou.close();
            ous.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readUserFile() {

        try (FileInputStream fiu = new FileInputStream("userObject.ser");
                ObjectInputStream oiss = new ObjectInputStream(fiu);) {

            User temp;
            while ((temp = (User) oiss.readObject()) != null) {
                userList.add(temp);
                System.out.println(temp.getUserName());
            }
            
            fiu.close(); oiss.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String userName) {
        if (!userExist(userName)) {
            userList.add(new User(userName));
            writeToUserFile();
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
            fos = new FileOutputStream("users.ser");

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            fos.close();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void readFile() {
//
//        try {
//            FileInputStream fis = new FileInputStream("users.ser");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            userList = (ArrayList<User>) ois.readObject();
//            System.out.println(userList.get(1).getUserName());
//            ois.close();
//            fis.close();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    private void fileExist() {
        Path path = Paths.get("users.ser");
        if ((!Files.exists(path))) {
            writeToUserFile();
        }
    }

    /* Testar klassen */
    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        userManager.addUser("Claudia");
        userManager.addUser("Anna");
        userManager.addUser("David");
        userManager.addUser("Daven");
                userManager.addUser("hej");
        userManager.addUser("på");
        userManager.addUser("dig");

        userManager.readUserFile();
    }

    public void updateUser(User user) {
//        readFile();
//        
//
//        int index = userList.indexOf(0);
//        System.out.println(userList.get(index).getUserName());
//        
////        userList.set(index, user);
//        writeToFile();

    }
}
