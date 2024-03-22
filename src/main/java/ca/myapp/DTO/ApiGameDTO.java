package ca.myapp.DTO;

/**
 * DTO class for the REST API to define object structures required by the front-end.
 * HINT: Create static factory methods (or constructors) which help create this object
 *       from the data stored in the model, or required by the model.
 */
public class ApiGameDTO {
    public int gameNumber;
    public boolean isGameWon;
    public boolean isGameLost;
    public int opponentPoints;
    public long numActiveOpponentForts;

    // Amount of points that the opponents scored on the last time they fired.
    // If opponents have not yet fired, then it should be an empty array (0 size).
    public int[] lastOpponentPoints;

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }

    public boolean isGameLost() {
        return isGameLost;
    }

    public void setGameLost(boolean gameLost) {
        isGameLost = gameLost;
    }

    public ApiGameDTO(int gameNumber, long numActiveOpponentForts) {
        this.gameNumber = gameNumber;
        this.numActiveOpponentForts = numActiveOpponentForts;

        this.isGameWon = false;
        this.isGameLost = false;
        this.opponentPoints = 0;
        this.lastOpponentPoints = new int[0];
    }

    public ApiGameDTO(int gameNumber, boolean isGameWon, boolean isGameLost, int opponentPoints, long numActiveOpponentForts, int[] lastOpponentPoints) {
        this.gameNumber = gameNumber;
        this.isGameWon = isGameWon;
        this.isGameLost = isGameLost;
        this.opponentPoints = opponentPoints;
        this.numActiveOpponentForts = numActiveOpponentForts;
        this.lastOpponentPoints = lastOpponentPoints;
    }

    public int getOpponentPoints() {
        return opponentPoints;
    }

    public void setOpponentPoints(int opponentPoints) {
        this.opponentPoints = opponentPoints;
    }

    public long getNumActiveOpponentForts() {
        return numActiveOpponentForts;
    }

    public void setNumActiveOpponentForts(long numActiveOpponentForts) {
        this.numActiveOpponentForts = numActiveOpponentForts;
    }

    public int[] getLastOpponentPoints() {
        return lastOpponentPoints;
    }

    public void setLastOpponentPoints(int[] lastOpponentPoints) {
        this.lastOpponentPoints = lastOpponentPoints;
    }
}
