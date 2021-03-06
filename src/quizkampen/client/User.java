package quizkampen.client;

import java.io.Serializable;

public class User implements Serializable {

    boolean isPremuim;
    private String userName;
    int Rating;
    int totalGames;
    public int wins;
    public int losses;
    public int averageCorrect;
    public int draws;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void addWin() {
        this.wins++;
        this.totalGames++;
    }

    public int getWins() {
        return this.wins;
    }

    public void addDraw() {
        this.draws++;
        this.totalGames++;
    }

    public int getDraws() {
        return this.draws;
    }

    public void addLoss() {
        this.losses++;
        this.totalGames++;
    }

    public int getLosses() {
        return this.losses;
    }

    public int getTotalGames() {
        return this.totalGames;
    }

}
