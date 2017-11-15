package quizkampen;

import java.io.Serializable;

public class User implements Serializable {
    
    boolean isPremuim;
    private String userName;
    int Rating;
    int totalGames;
    int wins;
    int losses;
    int averageCorrect;

    public User(String userName) {
        this.userName = userName;
    }

    public void newUser(String userName) {
        if(userExist()) {
            new User(userName);

        } else {

        }
    }

    public void writeToFile() {

    }

    public boolean userExist() {
        return true;
    }

    public void findUser() {

    }

    public String getUserName() {
        return userName;
    }
}