package frogger;

public class Player {

    private String name;
    private int character;
    private int lives;



    public Player() {
        this.name = "";
        this.character = 0;
        this.lives = -1;
    }
    public void setName(String input) {
        name = input;
    }
}
