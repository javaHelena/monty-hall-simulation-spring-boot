package se.javajive.monty.domain;


public class GameResult {

    private int numberOfIterations;
    private int numberOfWins;
    private float percentage;
    private String gameStrategy;

    public GameResult() {
    }

    public GameResult(int numberOfIterations, String  gameStrategy) {
        this.numberOfIterations = numberOfIterations;
        this.gameStrategy = gameStrategy;
    }

    public void incrementNumberOfWins(){
        numberOfWins++;
        updatePercentage();
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public String getGameStrategy() {
        return gameStrategy;
    }

    public float getPercentage() {
        return this.percentage;
    }

    private void updatePercentage() {
        this.percentage = (numberOfWins * 100.0f) / numberOfIterations;
    }

}
