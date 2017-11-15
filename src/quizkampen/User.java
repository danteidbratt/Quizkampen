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
}