package frogger;

public class Game {
    private int difficulty;
    private int score;

    public Game() {
        difficulty = -1;
        score = 0;
    }

    public void setDifficulty(int choice) {
        difficulty = choice;
    }

    public int getDifficulty() {
        return difficulty;
    }

}
