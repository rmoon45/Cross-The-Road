package frogger;

public class Game {
    private String difficulty;
    private int score;
    private int lives;

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

    public void setLives(int lives) {
        this.lives = lives;
    }
    public int getLives() {
        return this.lives;
    }

}
