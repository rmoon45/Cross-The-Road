package frogger;

public class Game {
    private String difficulty;
    private int lives;
    private int squareSize;
    private int screenWidth;
    private int screenHeight;

    // Use this for testing only
    public Game() {
        this(1080, 1668, 92);
    }

    public Game(int screenWidth, int screenHeight, int squareSize) {
        this.difficulty = "easy";
        this.squareSize = squareSize;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void setDifficulty(String choice) {
        difficulty = choice;
        if (difficulty.equals("hard")) {
            this.setLives(1);
        } else if (difficulty.equals("medium")) {
            this.setLives(3);
        } else {
            this.setLives(7);
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    public int getLives() {
        return this.lives;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
