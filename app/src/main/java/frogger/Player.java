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

    public boolean checkName(String nameInput) {
        if (nameInput == null) {
            return false;
        }
        String userName = nameInput.trim();
        System.out.println(userName);
        if (nameInput.length() == 0 || userName.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    public void setName(String input) {
        name = input;
    }
}
