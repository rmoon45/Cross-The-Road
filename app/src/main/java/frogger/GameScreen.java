package frogger;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.KeyEvent;

import com.example.s0.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameScreen extends AppCompatActivity {

    // The map and the score for each type of tile.
    private ArrayList<String> map;
    private HashMap<String, Integer> tileValues;

    // "constants" for drawing
    private int squareSize;
    private int numHorizontalSquares;
    private int numVerticalSquares;
    private int horizontalOffset; // This is the number of pixels needed to add to the first tile
    //                               to make the center tile align correctly.

    // character-related stuff for moving
    private Player player;

    private int score;
    private int lives;

    // I don't want to have this as a field but here we are
    private int screenWidth;
    
    // ttttest
    private int currPos;
    private int greatestPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_screen);

        initializeMap();
        Bundle extras = getIntent().getExtras();
        initializeTextViews(extras);

        this.player = new Player(this, extras.getString("character"), this.squareSize,
                this.numHorizontalSquares, this.numVerticalSquares, this.horizontalOffset);
        ((ConstraintLayout) findViewById(R.id.foregroundLayout)).addView(player);

        initializeCars();
    }

    private void createCar(int carId, boolean isGoingRight, int width, int x, int y,
                           int delayMillis, Handler handler) {

        ImageView car = findViewById(carId);
        car.getLayoutParams().height = squareSize;
        car.getLayoutParams().width = width;
        car.setX(x);
        car.setY(y);

        Runnable mStatusChecker = new Runnable() {

            //car movement
            @Override
            public void run() {
                if (isGoingRight) {
                    if (car.getX() > -car.getWidth()) {
                        car.setX(car.getX() - 20);
                    } else {
                        car.setX(screenWidth);
                    }
                } else {
                    if (car.getX() < screenWidth) {
                        car.setX(car.getX() + 20);
                    } else {
                        car.setX(-car.getWidth());
                    }
                }
                // The 0.15% offset doesn't make sense but makes the collision look accurate for
                // some reason.
                if (GameScreen.this.player.isColliding(car.getX() + car.getWidth() * 0.15f,
                        car.getY(), car.getX() + car.getWidth() * 0.85f,
                        car.getY() + car.getHeight())) {

                    //if player collides with car, decrease life by 1
                    GameScreen.this.setLives(GameScreen.this.lives - 1);
                }
                handler.postDelayed(this, delayMillis);
            }
        };
        mStatusChecker.run();
    }

    private void initializeCars() {
        Handler mHandler = new Handler();

        createCar(R.id.car1, true, squareSize, horizontalOffset
                + (numHorizontalSquares / 2) * squareSize, squareSize * (numVerticalSquares - 2)
                - squareSize, 1, mHandler);

        createCar(R.id.car2, false, squareSize * 2, this.screenWidth,
                squareSize * (numVerticalSquares - 2) - (2 * squareSize), 10, mHandler);

        createCar(R.id.car3, true, squareSize * 3, 0,
                squareSize * (numVerticalSquares - 2) - (3 * squareSize), 3, mHandler);

        createCar(R.id.car4, false, squareSize * 4, horizontalOffset
                + (numHorizontalSquares / 2) * squareSize,
                squareSize * (numVerticalSquares - 2) - (4 * squareSize), 20, mHandler);
    }

    private void initializeTextViews(Bundle extras) {
        ((TextView) findViewById(R.id.nameView)).setText(extras.getString("name"));
        ((TextView) findViewById(R.id.difficultyView)).setText("Difficulty: "
                + extras.getString("difficulty"));
        setLives(extras.getInt("lives"));
        setScore(0);
    }

    private void initializeMap() {
        this.map = new ArrayList<String>(Arrays.asList(
                "safe",
                "river",
                "river",
                "river",
                "river",
                "river",
                "river",
                "river",
                "river",
                "river",
                "safe",
                "road",
                "road",
                "road",
                "road"
        ));
        this.map.add(0, "goal"); // Don't put these into the List definition plz thx
        this.map.add("safe");
        this.map.add("safe");

        this.tileValues = new HashMap<String, Integer>();
        tileValues.put("safe", 1);
        tileValues.put("river", 3);
        tileValues.put("road", 2);

        drawMap();
    }

    private void drawMap() {
        RelativeLayout backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);

        this.screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        // to-do: if someone could fix this to get the actual usable height, that would be great.
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels
                - getResources().getDimensionPixelSize(
                getResources().getIdentifier("navigation_bar_height", "dimen", "android")
        );
        numVerticalSquares = this.map.size();
        squareSize = screenHeight / numVerticalSquares;
        numHorizontalSquares = (screenWidth / squareSize);

        // Make sure all of the screen is covered horizontally if the numbers don't divide
        // perfectly.
        if (numHorizontalSquares * squareSize < screenWidth) {
            numHorizontalSquares++;
        }
        // Make sure there is an odd number of horizontal squares so the character can be centered
        // in the middle.
        if (numHorizontalSquares % 2 == 0) {
            numHorizontalSquares++;
        }

        // Calculate the horizontal offset needed so that the middle column of tiles is centered.
        // This should be a negative number or zero.
        horizontalOffset = (screenWidth / 2)
                - (squareSize / 2)
                - (numHorizontalSquares / 2) * squareSize;

        // Draw the tiles.
        for (int i = 0; i < numVerticalSquares; i++) {
            // Get the corresponding tile image for the row.
            int imageResource = R.drawable.safe;
            switch (this.map.get(i)) {
            case "river":
                imageResource = R.drawable.river;
                break;
            case "road":
                imageResource = R.drawable.road;
                break;
            case "goal":
                imageResource = R.drawable.goal;
                break;
            default:
                if (this.map.get(i) != "safe") {
                    throw new RuntimeException("you dnun goofed");
                }
            }

            // Populate the tile image onto each square in the row.
            for (int j = 0; j < numHorizontalSquares; j++) {
                ImageView tile = new ImageView(this);
                tile.setImageResource(imageResource);
                RelativeLayout.LayoutParams params
                        = new RelativeLayout.LayoutParams(squareSize, squareSize);
                params.topMargin = i * squareSize;
                params.leftMargin = j * squareSize + horizontalOffset;
                backgroundLayout.addView(tile, params);
            }
        }

        // Set the height and width of the backgroundLayout to be exactly the amount of room the
        // tiles take up. This stops the rightmost and bottommost tiles from being resized when the
        // tiles don't fit exactly onto the screen.
        ViewGroup.LayoutParams params = backgroundLayout.getLayoutParams();
        params.height = numVerticalSquares * squareSize;
        params.width = numHorizontalSquares * squareSize;
        backgroundLayout.setLayoutParams(params);
    }

    //score and lives being set depending on situation
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (this.player.move(this.map, keyCode)) {
        case 1:
            this.setScore(this.score
                    + this.tileValues.get(this.map.get(this.player.getGridY() + 1)));
            break;
        case 2:
            this.setLives(this.lives - 1);




            break;
        default:
            return true;
        }
        return true;
    }

    private void setScore(int score) {
        this.score = score;
        ((TextView) findViewById(R.id.scoreView)).setText("Score: " + this.score);
    }

    public int getScore() {
        return score;
    }
    private void setLives(int lives) {
        this.lives = lives;

        ((TextView) findViewById(R.id.livesView)).setText("Lives: " + lives);

        //start gameOveractivity when you lose all lives
        if (this.lives <= 0) {

            System.out.println("Game Over");
            System.out.println("score is " + this.score);

            //activity is subclass of GameScreen context
            Intent intent2 = new Intent(GameScreen.this, GameOverScreen.class);
            intent2.putExtra("score", this.score);
            startActivity(intent2);

            finish();
        }
    }

    @Deprecated
    public void setCurrPos(int currPos) {
        this.currPos = currPos;
    }

    @Deprecated
    public void setGreatestPos(int greatestPos) {
        this.greatestPos = greatestPos;
    }

    public boolean getScoreChange(String movement) {
        return movement.equals("moveUp");
    }

    @Deprecated
    public int getNumVerticalSquares() {
        return this.map.size();
    }

    @Deprecated
    public int getSquareSize() {
        return squareSize;
    }

    @Deprecated
    public float getCar1Y() {
        return findViewById(R.id.car1).getY();
    }

    @Deprecated
    public float getCar2Y() {
        return findViewById(R.id.car4).getY();
    }
}