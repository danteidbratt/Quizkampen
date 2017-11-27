package quizkampen;

import java.io.Serializable;

public class User implements Serializable {

    protected final static long LserialVersionUID = 42L;
    boolean isPremuim;
    private String userName;
    int Rating;
    int totalGames;
    int wins;
    int losses;
    int averageCorrect;
    protected int draws;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    
    public void setWins(int wins){
        this.wins = wins;
    }
    public void setLosses(int losses){
        this.losses = losses;
    }
    public void setTotalGames(int totalGames){
        this.totalGames = totalGames;
    }
    public void setDraws(int draws){
        this.draws = draws;
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

    public void addTotalGames() {
        this.totalGames++;
    }
}
