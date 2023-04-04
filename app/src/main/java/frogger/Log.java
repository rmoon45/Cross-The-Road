package frogger;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.s0.R;

public class Log extends AppCompatImageView {

    private int gridY;
    private int screenWidth;
    private int squareSize;

    private int leftGridX;
    private int rightGridX;

    private boolean isFast;
    private final int FASTSPEED = 20;
    private final int SLOWSPEED = 10;

    private int horizontalOffset;

    public Log(@NonNull Context context) {
        super(context);
    }

    public Log(@NonNull Context context, int screenWidth, int gridY, int horizontalOffset, int squareSize, boolean isFast) {
        this(context);

        this.screenWidth = screenWidth;
        this.gridY = gridY;
        this.squareSize = squareSize;
        this.isFast = isFast;

        this.leftGridX = -3;
        this.rightGridX = -1;

        this.horizontalOffset = horizontalOffset;

        this.setImageResource(R.drawable.log);
        this.setX(-3 * this.squareSize);
        this.setY(this.gridY * this.squareSize);
    }

    public void move(Player player) {
        // Check if the player is on the log (must be checked before moving the log)
        boolean isCarryingPlayer = isColliding(player.getGridX(), player.getGridY());

        // Move the log
        float xPos = this.getX();
        int movementSpeed = isFast ? FASTSPEED : SLOWSPEED;
        if (xPos < this.screenWidth) {
            this.setX(xPos + movementSpeed);
        } else {
            this.setX(-3 * this.squareSize);
            this.leftGridX = -3;
            this.rightGridX = -1;
        }

        // Check if log has moved enough that gridX needs to be updated
        boolean hasMovedRightOneGridSquare
                = this.getX() > this.leftGridX * this.squareSize + this.horizontalOffset;

        // Move the player if they are on the log
        if (isCarryingPlayer) {
            player.setX(player.getX() + movementSpeed);
            if (hasMovedRightOneGridSquare) {
                player.setGridXWithoutUpdatingPosition(player.getGridX() + 1);
                player.checkBordersAndRespawnIfNecessary();
            }
        }

        if (hasMovedRightOneGridSquare) {
            this.leftGridX++;
            this.rightGridX++;
        }
    }

    public boolean isColliding(int gridX, int gridY) {
        /*System.out.println("isColliding: " + (gridY == this.gridY
                && gridX >= this.leftGridX
                && gridX <= this.rightGridX));
        System.out.println("log grid coords: " + this.gridY + ", " + this.leftGridX);
        System.out.println("player tested grid Y: " + gridY + ", " + gridX);*/
        return gridY == this.gridY
            && gridX >= this.leftGridX
            && gridX <= this.rightGridX;
    }
}
