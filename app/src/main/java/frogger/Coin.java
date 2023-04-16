package frogger;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.s0.R;

//THIS WHOLE CLASS WAS FOR TESTING
public class Coin {

    private int gridY;
    private int screenWidth;
    private int squareSize;


    private float x;
    private float y;

    private int horizontalOffset;
    private int numHorizontalSquares;

    public Coin(@NonNull int screenWidth, int gridY, int horizontalOffset, int squareSize, int numHorizontalSquares) {

        this.screenWidth = screenWidth;
        this.gridY = gridY;
        this.squareSize = squareSize;
        this.numHorizontalSquares = numHorizontalSquares;


        this.horizontalOffset = horizontalOffset;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public int livesChange(int lives) {
        return lives + 1;
    }


}
