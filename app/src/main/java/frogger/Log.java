package frogger;

import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.s0.R;

import java.util.Random;

public class Log extends AppCompatImageView {

    private int gridY;
    private int screenWidth;
    private int squareSize;

    private int leftGridX;

    private int horizontalOffset;
    private int numHorizontalSquares;

    private final int MINSPEED = 1;
    private final int MAXSPEED = 10;
    private int speed;
    private boolean isGoingRight;

    public Log(@NonNull Context context) {
        super(context);
    }

    public Log(@NonNull Context context, int screenWidth, int gridY, int horizontalOffset, int squareSize, int numHorizontalSquares) {
        this(context);

        this.screenWidth = screenWidth;
        this.gridY = gridY;
        this.squareSize = squareSize;
        this.numHorizontalSquares = numHorizontalSquares;

        this.leftGridX = -3;

        this.horizontalOffset = horizontalOffset;

        this.setImageResource(R.drawable.log);
        this.setX(-3 * this.squareSize);
        this.setY(this.gridY * this.squareSize);

        this.isGoingRight = (gridY % 2 == 0);

        generateSpeed();
    }

    public void movement(Player player) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                Log.this.updateLogPositionAndMovePlayerIfNeeded(player);
                handler.postDelayed(this, 1);
            }
        };
        runnable.run();
    }

    // This should be called every x milliseconds
    public void updateLogPositionAndMovePlayerIfNeeded(Player player) {
        int playerLocationOnLog = collisionLocationAbsoluteCoords((int) player.getX(), player.getGridY());

        // Move the log
        float xPos = this.getX();
        if (isGoingRight) {
            if (xPos < this.screenWidth) {
                this.setX(xPos + this.speed);
            } else {
                this.setX(-3 * this.squareSize);
                this.leftGridX = -3;
            }
        } else {
            if (xPos > -3 * this.squareSize) {
                this.setX(xPos - this.speed);
            } else {
                this.setX(this.screenWidth);
                this.leftGridX = numHorizontalSquares + 1;
            }
        }

        // Check if log has moved enough that gridX needs to be updated
        int closestGridX = calculateClosestGridX();

        // Move the player if they are on the log
        if (playerLocationOnLog != -1) {
            player.setX(getX() + squareSize * playerLocationOnLog);
            if (this.leftGridX != closestGridX) {
                player.setGridXWithoutUpdatingPosition(closestGridX + playerLocationOnLog);
            }
        }

        // Update gridX as necessary
        if (this.leftGridX != closestGridX) {
            this.leftGridX = closestGridX;
        }
    }

    private int calculateClosestGridX() {
        return (int) (getX() - this.horizontalOffset) / squareSize;
    }

    public int collisionLocationAbsoluteCoords(int x, int gridY) {
        if (gridY != this.gridY) {
            return -1;
        } else {
            float leftX = getX();
            float slack = 0.5f; // This is the fraction of a square that the player can be
                    // outside of the log at but still count as hitting the log
            if (x >= leftX - squareSize * slack) {
                if (x < leftX + (squareSize / 2)) {
                    return 0;
                } else if (x < leftX + (squareSize) + (squareSize / 2)) {
                    return 1;
                } else if (x < leftX + (3 * squareSize) + squareSize * slack) {
                    return 2;
                }
            }
        }
        return -1;
    }

    public boolean isColliding(int x, int gridY) {
        return collisionLocationAbsoluteCoords(x, gridY) != -1;
    }

    private void generateSpeed() {
        this.speed = new Random().nextInt((this.MAXSPEED - this.MINSPEED) + 1) + this.MINSPEED;
    }
}
