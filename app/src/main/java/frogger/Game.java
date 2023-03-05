package frogger;

import android.widget.TextView;

public class Game {
    private String difficulty;
    private int score;
    private int lives;
    private String screen;
    private int squareSize;
    private int screenWidth;
    private int screenHeight;
    private TextView scoreNumber;



    public Game() {
        difficulty = "easy";
        score = 0;
        screen = "start";
        squareSize = 92;
        screenHeight = 1668;
        screenWidth = 1080;


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

    public int getScore(){
        return this.score;
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

    public void setScreen(String screen) {
        this.screen = screen;
    }
    public String getScreen() {
        return this.screen;
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

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }


}
