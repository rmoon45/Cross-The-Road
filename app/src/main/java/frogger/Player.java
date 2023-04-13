package frogger;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.s0.R;

import java.util.ArrayList;

public class Player extends AppCompatImageView {

    private int squareSize;
    private int numHorizontalSquares;
    private int numVerticalSquares;
    private int horizontalOffset;

    private int gridX;
    private int gridY;

    private int spawnX;
    private int spawnY;

    private int furthestReached;

    private boolean movingEnabled;

    @Deprecated
    private boolean isMoveUp;
    @Deprecated
    private boolean isMoveDown;
    @Deprecated
    private boolean isMoveRight;
    @Deprecated
    private boolean isMoveLeft;

    // temporary
    private ArrayList<Log> logs;

    // hmmm don't use this
    public Player(@NonNull Context context) {
        super(context);
    }

    public Player(@NonNull Context context, String character, int squareSize,
                  int numHorizontalSquares, int numVerticalSquares, int horizontalOffset) {
        super(context);
        this.squareSize = squareSize;
        this.numHorizontalSquares = numHorizontalSquares;
        this.numVerticalSquares = numVerticalSquares;
        this.horizontalOffset = horizontalOffset;

        switch (character) {
        case "bunny":
            setImageResource(R.drawable.bunny);
            break;
        case "duck":
            setImageResource(R.drawable.duck);
            break;
        default:
            setImageResource(R.drawable.frog);
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                this.squareSize, this.squareSize);
        this.setLayoutParams(layoutParams);

        // Put the character in the vertical second-to-bottommost square.
        this.setX(this.horizontalOffset + (this.numHorizontalSquares / 2) * this.squareSize);
        this.setY(this.squareSize * (this.numVerticalSquares - 2));

        this.gridX = this.numHorizontalSquares / 2;
        this.gridY = this.numVerticalSquares - 2;
        this.spawnX = this.gridX;
        this.spawnY = this.gridY;

        this.furthestReached = this.spawnY;

        this.movingEnabled = true;
        System.out.println(this.gridY);
    }

    public int move(ArrayList<String> map, int keycode) {
        if (!this.movingEnabled) {
            return 0;
        }
        int movementResult = 0;
        switch (keycode) {
        //based off of the input string, change the position to be moving in said direction.
        //use subtract for going up/left and plus for down/right bc the origin is at top left.
        case KeyEvent.KEYCODE_W:
            movementResult = moveUp(map);
            break;
        case KeyEvent.KEYCODE_S:
            movementResult = moveDown(map);
            break;
        case KeyEvent.KEYCODE_A:
            movementResult = moveLeft(map);
            break;
        case KeyEvent.KEYCODE_D:
            movementResult = moveRight(map);
            break;
        default:
            movementResult = 0;
        }
        return movementResult;
    }

    /*
    0 - nothing happens
    1 - increase score
    2 - decrease lives & scoreww
     */
    private int moveUp(ArrayList<String> map) {
        if (this.gridY > 0) {
            System.out.println(this.gridY);
            int newGridY = this.gridY - 1;
            if (map.get(newGridY) == "river" && !isCollidingWithLog((int) getX(), newGridY)) {
                this.respawn();
                return 2;
            } else {
                this.setGridY(newGridY);
                if (this.furthestReached > this.gridY) {
                    this.furthestReached = this.gridY;
                    return 1;
                }
            }
        }
        return 0;
    }

    private int moveDown(ArrayList<String> map) {

        if (this.gridY < numVerticalSquares - 1) {
            int newGridY = this.gridY + 1;
            if (map.get(newGridY) == "river" && !isCollidingWithLog((int) getX(), newGridY)) {
                this.respawn();
                return 2;
            } else {
                this.setGridY(newGridY);
            }
        }
        return 0;
    }

    private int moveRight(ArrayList<String> map) {
        if (map.get(gridY) == "river") {
            if (isCollidingWithLog((int) this.getX() + this.squareSize, this.gridY)) {
                this.gridX++;
                this.setX(this.getX() + this.squareSize);
            } else {
                this.respawn();
                return 2;
            }
            this.respawn();
            return 2;
        } else if (this.gridX < numHorizontalSquares - 2) {
            this.setGridX(this.gridX + 1);
        }
        this.setScaleX(1);
        return 0;
    }

    private int moveLeft(ArrayList<String> map) {
        if (map.get(gridY) == "river") {
            if (isCollidingWithLog((int) this.getX() - this.squareSize, this.gridY)) {
                this.gridX--;
                this.setX(this.getX() - this.squareSize);
            } else {
                this.respawn();
                return 2;
            }
        } else if (this.gridX > 1) {
            this.setGridX(this.gridX - 1);
        }
        this.setScaleX(-1);
        return 0;
    }

    // The movingEnabled stuff here seems to decrease occurrences when two lives are subtracted
    // instead of one due to lag, but that might be a placebo.
    public void respawn() {
        this.movingEnabled = false;
        this.setGridX(spawnX);
        this.setGridY(spawnY);
        this.furthestReached = spawnY;
        this.movingEnabled = true;
    }

    private void setGridX(int gridX) {
        this.gridX = gridX;
        this.setX(this.horizontalOffset + gridX * this.squareSize);
    }

    public void setGridXWithoutUpdatingPosition(int gridX) {
        if (gridX < 0 || gridX > numHorizontalSquares) {
            respawn();
        } else {
            this.gridX = gridX;
        }
    }

    private void setGridY(int gridY) {
        this.gridY = gridY;
        this.setY(this.squareSize * gridY);
    }

    public int getGridY() {
        return this.gridY;
    }

    public int getGridX() {
        return this.gridX;
    }

    public boolean isColliding(float xTopLeft, float yTopLeft, float xBottomRight,
                               float yBottomRight) {
        if (xTopLeft > this.getX() + this.squareSize
                || xBottomRight < this.getX()
                || yTopLeft < this.getY()
                || yBottomRight > this.getY() + this.squareSize
        ) {
            return false;
        }
        respawn();
        return true;
    }
    public boolean isCollidingWithCoin(float xTopLeft, float yTopLeft, float xBottomRight,
                               float yBottomRight) {
        if (xTopLeft > this.getX() + this.squareSize
                || xBottomRight < this.getX()
                || yTopLeft < this.getY()
                || yBottomRight > this.getY() + this.squareSize
        ) {
            return false;
        }
        //respawn();
        return true;
    }

    @Deprecated
    public void movePlayerTest(String movement, int squareSize, int screenWidth, int screenHeight) {
        switch (movement) {
        //based off of the input string, change the position to be moving in said direction.
        //use subtract for going up/left and plus for down/right bc the origin is at top left.
        case "moveUp":
            if (this.getY() >= 0) {
                isMoveUp = true;
            } else {
                isMoveUp = false;
            }
            break;
        case "moveLeft":
            if (this.getX() >= 0 - (squareSize / 2)) {
                isMoveLeft = true;
            } else {
                isMoveLeft = false;
            }
            break;
        case "moveRight":
            if ((this.getX() + squareSize)
                    < screenWidth - (squareSize / 2)) {
                isMoveRight = true;
            } else {
                isMoveRight = false;
            }
            break;
        default:
            if ((this.getY() + (2 * squareSize)) < screenHeight) {
                isMoveDown = true;
            } else {
                isMoveDown = false;
            }
        }
    }

    private boolean isCollidingWithLog(int x, int y) {
        for (Log log : this.logs) {
            if (log.isColliding(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean checkName(String nameInput) {
        if (nameInput == null) {
            return false;
        }
        String userName = nameInput.trim();
        return (!(nameInput.length() == 0 || userName.length() == 0));
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }
}
