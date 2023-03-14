package frogger;

import android.widget.ImageView;

public class Player {
    private float posX;
    private float posY;
    private ImageView characterView;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveRight;
    private boolean moveLeft;
    private String name;

    public Player() {
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
    /*public void moveCar1Left(ImageView car, Game game, int startPosition) {
        //this.movePlayerTest(movement, game);
        int squareSize = game.getSquareSize();
        int screenWidth = game.getScreenWidth();
        int screenHeight = game.getScreenHeight();
        while (car.getX() > 0 + (squareSize / 2)) {
            car.setX(car.getX() - squareSize);
        }
        car.setX(startPosition);
        //moveCar1Left(car, game, startPosition);
    }*/

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

    public void setName(String input) {
        this.name = input;
    }

    public boolean scoreChange(String movement) {
        if (movement == "moveup") {
            return true;
        } else {
            return false;
        }
    }
}
