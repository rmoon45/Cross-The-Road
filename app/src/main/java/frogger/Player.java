package frogger;

import android.widget.ImageView;

public class Player {

    private String name;
    private int character;
    private int lives;
    private float posX;
    private float posY;
    private ImageView characterView;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveRight;
    private boolean moveLeft;

    public Player() {
        this.name = "";
        this.character = 0;
        this.lives = -1;
        this.posX = 0;
        this.posY = 0;
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
        if (nameInput.length() == 0 || userName.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void movePlayerTest(String movement, Game game) {
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
                if (this.getPosX() >= 0 - (game.getSquareSize() / 2)) {
                    moveLeft = true;
                } else {
                    moveLeft = false;
                }
                break;
            case "moveRight":
                if ((this.getPosX() + game.getSquareSize()) < game.getScreenWidth() - (game.getSquareSize() / 2)) {
                    moveRight = true;
                } else {
                    moveRight = false;
                }
                break;
            default:
                if ((this.getPosY() + (2 * game.getSquareSize())) < game.getScreenHeight()) {
                    moveDown = true;
                } else {
                    moveDown = false;
                }

        }
    }

    public void movePlayer(String movement, Game game) {
        this.movePlayerTest(movement, game);
        int squareSize = game.getSquareSize();
        int screenWidth = game.getScreenWidth();
        int screenHeight = game.getScreenHeight();
        switch (movement) {
            //based off of the input string, change the position to be moving in said direction.
            //use subtract for going up/left and plus for down/right bc the origin is at top left.
            case "moveUp":
                if (characterView.getY() > 0 && moveUp) {
                    characterView.setY(characterView.getY() - squareSize);
                    this.posY = characterView.getY();
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
                }

        }
    }

    public void setName(String input) {
        name = input;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    public int getLives() {
        return this.lives;
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

    public ImageView getCharacterView() {
        return characterView;
    }

    public void setCharacterView(ImageView characterView) {
        this.characterView = characterView;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

}
