package frogger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.KeyEvent;

import com.example.s0.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private ArrayList<Log> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_screen);

        initializeMap();
        Bundle extras = getIntent().getExtras();
        initializeTextViews(extras);

        this.player = new Player(this, extras.getString("character"), this.squareSize,
                this.numHorizontalSquares, this.numVerticalSquares, this.horizontalOffset);

        initializeLogs();

        ((ConstraintLayout) findViewById(R.id.foregroundLayout)).addView(player);

        initializeCars();
        initializeCoins();

    }

    private void initializeLogs() {
        this.logs = new ArrayList<Log>();
        for (int i = 0; i < this.map.size(); i++) {
            if (this.map.get(i) == "river") {
                Log log = new Log(this, screenWidth, i, horizontalOffset, squareSize, numHorizontalSquares);
                this.logs.add(log);
                ((ConstraintLayout) findViewById(R.id.foregroundLayout)).addView(log);
                ViewGroup.LayoutParams logParams = log.getLayoutParams();
                logParams.width = 3 * squareSize;
                logParams.height = 7 * squareSize / 6;
                log.setLayoutParams(logParams);
                log.movement(this.player);
            }
        }
        player.setLogs(this.logs);
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

                    if (GameScreen.this.lives > 0) {
                        GameScreen.this.setScore(0);
                    }

                    GameScreen.this.setScore(0);
                }
                handler.postDelayed(this, delayMillis);
            }
        };
        mStatusChecker.run();
    }
    public void createCoin(ImageView coin){
        Handler mHandler = new Handler();
        Runnable mStatusChecker = new Runnable() {

            public void run(){

                if ( GameScreen.this.player.isCollidingWithCoin(coin.getX() + coin.getWidth() * 0.15f,
                        coin.getY(), coin.getX() + coin.getWidth() * 0.85f,
                        coin.getY() + coin.getHeight())){

                        GameScreen.this.setLives(GameScreen.this.lives++);
                        coin.setVisibility(View.INVISIBLE);

                }
                mHandler.postDelayed(this, 1);
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
    private void initializeCoins(){
        Random r = new Random();
        Handler mHandler = new Handler();
        ImageView coin1 = findViewById(R.id.coin1);
        coin1.setX(r.nextInt(screenWidth-33));
        coin1.setY(squareSize * (numVerticalSquares - 2) - (2 * squareSize));
        ImageView coin2 = findViewById(R.id.coin2);
        coin2.setX(r.nextInt(screenWidth-33));
        coin2.setY(squareSize * (numVerticalSquares - 2) - (4 * squareSize));//18-9
        ImageView coin3 = findViewById(R.id.coin3);
        coin3.setX(r.nextInt(screenWidth-33));
        coin3.setY((numVerticalSquares/2) + 240);
        ImageView coin4 = findViewById(R.id.coin4);
        coin4.setX(r.nextInt(screenWidth-33));
        coin4.setY((numVerticalSquares/2)+400);
        ImageView coin5 = findViewById(R.id.coin5);
        coin5.setX(r.nextInt(screenWidth-33));
        coin5.setY((numVerticalSquares/2) + 560);
        createCoin(coin1);
        createCoin(coin2);
        createCoin(coin3);
        createCoin(coin4);
        createCoin(coin5);
    }

    @SuppressLint("SetTextI18n")
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
        @SuppressLint("InternalInsetResource") int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels
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

        Map<String, Integer> tileImageResources = new HashMap<String, Integer>(Map.of(
                "river",
                R.drawable.river,
                "road",
                R.drawable.road,
                "goal",
                R.drawable.goal,
                "safe",
                R.drawable.safe
        ));

        // Draw the tiles.
        for (int i = 0; i < numVerticalSquares; i++) {
            // Get the corresponding tile image for the row.
            int imageResource = tileImageResources.get(this.map.get(i));

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
        params.width = numHorizontalSquares * squareSize;
        params.height = numVerticalSquares * squareSize;
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

            if (scoreResetTest(this.lives)) {
                this.setScore(0);
            }

            break;

        case 3:
            System.out.println("Case 3");

            Intent intent2 = new Intent(GameScreen.this, WinScreen.class);
            intent2.putExtra("score", this.score);
            startActivity(intent2);

            finish();

            break;
        default:
            return true;
        }
        return true;
    }
    public boolean scoreResetTest(int lives) {
        return (lives > 0);
    }

    @SuppressLint("SetTextI18n")
    public void setScore(int score) {
        this.score = score;
        ((TextView) findViewById(R.id.scoreView)).setText("Score: " + this.score);
    }

    public int getScore() {
        return score;
    }
    @SuppressLint("SetTextI18n")
    public void setLives(int lives) {
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
    public int getScoreAfterMove(int score, String currentSquare, boolean scoreChange) {
        if (scoreChange) {
            if (currentSquare == "road") {
                score += 2;
            } else if (currentSquare == "river") {
                score += 3;
            } else {
                score += 1;
            }
            return score;
        }
        return score;
    }

    @Deprecated
    public String getTileCorrespondingToPosition(int playerPosition, List<String> map) {
        return map.get(map.size() - playerPosition - 1);
    }

    @Deprecated
    public void setCurrPos(int currPos) {
        this.currPos = currPos;
    }

    @Deprecated
    public void setGreatestPos(int greatestPos) {
        this.greatestPos = greatestPos;
    }

    @Deprecated
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