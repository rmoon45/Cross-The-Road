package frogger;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.s0.R;

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
    }

    public boolean movePlayer(int keycode) {
        boolean hasMoved = false;
        switch (keycode) {
        //based off of the input string, change the position to be moving in said direction.
        //use subtract for going up/left and plus for down/right bc the origin is at top left.
        case KeyEvent.KEYCODE_W:
            hasMoved = moveUp();
            break;
        case KeyEvent.KEYCODE_S:
            moveDown();
            break;
        case KeyEvent.KEYCODE_A:
            moveLeft();
            break;
        case KeyEvent.KEYCODE_D:
            moveRight();
            break;
        default:
            hasMoved = false;
        }
        return hasMoved;
    }

    private boolean moveUp() {
        if (this.gridY > 0) {
            this.setGridY(this.gridY - 1);
            if (this.furthestReached > this.gridY) {
                this.furthestReached = this.gridY;
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean moveDown() {
        if (this.gridY < numVerticalSquares - 1) {
            this.setGridY(this.gridY + 1);
        }
        return false;
    }

    private boolean moveRight() {
        if (this.gridX < numHorizontalSquares - 2) {
            this.setGridX(this.gridX + 1);
        }
        return false;
    }

    private boolean moveLeft() {
        if (this.gridX > 1) {
            this.setGridX(this.gridX - 1);
        }
        return false;
    }

    public void respawn() {
        this.setGridX(spawnX);
        this.setGridY(spawnY);
    }

    private void setGridX(int gridX) {
        this.gridX = gridX;
        this.setX(this.horizontalOffset + gridX * this.squareSize);
    }

    private void setGridY(int gridY) {
        this.gridY = gridY;
        this.setY(this.squareSize * gridY);
    }

    public int getGridY() {
        return this.gridY;
    }
}
