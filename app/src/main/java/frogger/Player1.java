package frogger;

import android.widget.ImageView;

@Deprecated
public class Player1 {
    private float posX;
    private float posY;
    private ImageView characterView;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveRight;
    private boolean moveLeft;
    private int score;
    private int lives;
    private boolean respawned=false;
    private String name;
    private boolean liveReset=false;
    public Player1() {
        this.posX = 0;
        this.posY = 0;
        this.lives = 3;
        this.moveUp = false;
        this.moveDown = false;
        this.moveLeft = false;
        this.moveRight = false;
    }

    public boolean checkName(String nameInput) {
        if (nameInput == null) {
            return false;
        }
        String userName = nameInput.trim();
        System.out.println(userName);
        return (!(nameInput.length() == 0 || userName.length() == 0));
    }

    public void movePlayerTest(String movement, int squareSize, int screenWidth, int screenHeight) {
        switch (movement) {
        //based off of the input string, change the position to be moving in said direction.
        //use subtract for going up/left and plus for down/right bc the origin is at top left.
        case "moveUp":
            if (this.getPosY() >= 0) {
                moveUp = true;
            } else {
                moveUp = false;
            }
            break;
        case "moveLeft":
            if (this.getPosX() >= 0 - (squareSize / 2)) {
                moveLeft = true;
            } else {
                moveLeft = false;
            }
            break;
        case "moveRight":
            if ((this.getPosX() + squareSize)
                < screenWidth - (squareSize / 2)) {
                moveRight = true;
            } else {
                moveRight = false;
            }
            break;
        default:
            if ((this.getPosY() + (2 * squareSize)) < screenHeight) {
                moveDown = true;
            } else {
                moveDown = false;
            }
        }
    }

    public boolean movePlayer(String movement, int squareSize, int screenWidth, int screenHeight) {
        this.movePlayerTest(movement, squareSize, screenWidth, screenHeight);
        switch (movement) {
        //based off of the input string, change the position to be moving in said direction.
        //use subtract for going up/left and plus for down/right bc the origin is at top left.
        case "moveUp":
            if (characterView.getY() > 0 && moveUp) {
                characterView.setY(characterView.getY() - squareSize);
                this.posY = characterView.getY();
                return true;
            }
            break;
        case "moveLeft":
            if (characterView.getX() > 0 + (squareSize / 2) && moveLeft) {
                characterView.setX(characterView.getX() - squareSize);
                this.posX = characterView.getX();
            }
            break;
        case "moveRight":
            if ((characterView.getX() + squareSize) < screenWidth - (squareSize / 2) && moveRight) {
                characterView.setX(characterView.getX() + squareSize);
                this.posX = characterView.getX();
            }
            break;
        default:
            if ((characterView.getY() + (2 * squareSize)) < screenHeight && moveDown) {
                characterView.setY(characterView.getY() + squareSize);
                this.posY = characterView.getY();
                return true;
            }
        }
        return false;
    }

    public boolean isColliding(float xTopLeft, float yTopLeft, float xBottomRight,
                               float yBottomRight) {
        if (xTopLeft > this.getPosX() + 112
                || xBottomRight < this.getPosX()
                || yTopLeft < this.getPosY()
                || yBottomRight > this.getPosY() + 112
        ) {
            return false;
        }
        this.lives--;
        //respawn();
        respawned=true;
        liveReset=true;
        return true;
    }
    public boolean getRespawned(){
        return this.respawned;
    }
    public boolean getLivesReset(){
        return this.liveReset;
    }

    public void setName(String x){
        this.name=x;
    }
    public void setPosX(float x) {
        posX = x;
    }
    public void setPosY(float y) {
        posY = y;
    }
    public float getPosX() {
        return this.posX;
    }
    public float getPosY() {
        return this.posY;
    }

    public void setCharacterView(ImageView characterView) {
        this.characterView = characterView;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

}
