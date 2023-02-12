package frogger;

public class Game {
    private String difficulty;
    private int score;

    public Game() {
        difficulty = "easy";
        score = 0;
    }

    public void setDifficulty(String choice) {
        difficulty = choice;
    }

    public String getDifficulty() {
        return difficulty;
    }

}
