package frogger;

public class Game {
    private String difficulty;
    private int score;
    private String screen;

    public Game() {
        difficulty = "easy";
        score = 0;
        screen = "start";
    }

    public void setDifficulty(String choice) {
        difficulty = choice;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }
    public String getScreen() {
        return this.screen;
    }

}
