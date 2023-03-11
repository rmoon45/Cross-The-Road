package frogger;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.view.KeyEvent;

import com.example.s0.R;

import java.util.ArrayList;
import java.util.Arrays;

import preferences.Preferences;
import java.util.Random;

public class GameScreen extends AppCompatActivity {

    private ImageView characterView;
    private TextView livesView;
    private TextView difficultyView;
    private TextView nameView;
    private TextView scoreNumber;
    private int currPos;
    private int greatestPos;
    private int score;
    private ImageView car1;
    private ImageView car2;
    private ImageView car3;
    private ImageView car4;
    private ArrayList<Object> spawnList;
    private int startPositionCar1;
    private Handler mHandler;
    private int mInterval = 200; // 1 seconds


    private RelativeLayout backgroundLayout;

    private Bitmap bitmap;

    private int squareSize;

    private int screenWidth;

    private int screenHeight;

    //private int horizontalOffset;
    //private

    private ArrayList<String> map;

    public GameScreen() {
        // Change the contents of the level by modifying this list.
        // The top row will always be the goal, and the bottom two rows will always be safe tiles
        // for now. (the goal is to have only the bottom row need to be a safe tile, but
        // there is an issue with getting the accurate screen height in the current code that
        // makes it necessary to have the bottom two rows as safe tiles. )
        /* The three tile options here are river, safe, and road.
         * Per the specs, rivers should always have a different width than roads, and safe tiles
         * should always be narrower than rivers and roads.
         */
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
        //characterView.setImageResource(R.drawable.frog);

        //this.spawnList = new ArrayList<>(Arrays.asList(car1,car2,car3));
    }
    private void spawnObject() {
        Random random = new Random();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_screen);

        backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        // to-do: if someone could fix this to get the actual usable height, that would be great.
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels
            - getResources().getDimensionPixelSize(
                getResources().getIdentifier("navigation_bar_height", "dimen", "android")
            );
        int numVerticalSquares = this.map.size();
        squareSize = screenHeight / numVerticalSquares;
        int numHorizontalSquares = (screenWidth / squareSize);
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

        // Instantiate game and player objects.
        Game game = new Game(screenWidth, screenHeight, squareSize);
        Player user = new Player();

        // Calculate the horizontal offset needed so that the middle column of tiles is centered.
        // This should be a negative number or zero.
        int horizontalOffset = (screenWidth / 2)
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

        // Get the TextViews to set the text of.
        characterView = findViewById(R.id.characterView);
        car1 = findViewById(R.id.car1);
        car2 = findViewById(R.id.car2);
        car3 = findViewById(R.id.car3);
        car4 = findViewById(R.id.car4);

        user.setCharacterView(characterView);

        // Set and display lives and difficulty.
        setDifficultyText(findViewById(R.id.difficultyView), findViewById(R.id.livesView));

        // Choooooose your fighter~!
        String character = Preferences.read("character", "duck");
        switch (character) {
        case "bunny":
            characterView.setImageResource(R.drawable.bunny);
            break;
        case "duck":
            characterView.setImageResource(R.drawable.duck);
            break;
        default:
            characterView.setImageResource(R.drawable.frog);
        }
        car1.setImageResource(R.drawable.car1);
        //car2.setImageResource(R.drawable.car3);
        //car3.setImageResource(R.drawable.car1);

        // Set the size and location of your fighter.
        characterView.getLayoutParams().height = squareSize;
        characterView.getLayoutParams().width = squareSize;
        car1.getLayoutParams().height = squareSize;
        car1.getLayoutParams().width = squareSize;
        car2.getLayoutParams().height = squareSize;
        car2.getLayoutParams().width = squareSize * 2;
        car3.getLayoutParams().height = squareSize;
        car3.getLayoutParams().width = squareSize * 3;
        car4.getLayoutParams().height = squareSize;
        car4.getLayoutParams().width = squareSize * 4;
        //car1.setX(numHorizontalSquares);
        car1.setX(horizontalOffset + (numHorizontalSquares / 2) * squareSize);
        this.startPositionCar1 = horizontalOffset + (numHorizontalSquares / 2) * squareSize;

        car1.setY(squareSize * (numVerticalSquares - 2) - squareSize);
        car2.setX(screenWidth);
        car2.setY(squareSize * (numVerticalSquares - 2) - (2 * squareSize));
        car3.setX(0);
        car3.setY(squareSize * (numVerticalSquares - 2) - (3 * squareSize));
        car4.setX(horizontalOffset + (numHorizontalSquares / 2) * squareSize);
        car4.setY(squareSize * (numVerticalSquares - 2) - (4 * squareSize));

        // Put the character in the horizontal middle square of the map.
        characterView.setX(horizontalOffset + (numHorizontalSquares / 2) * squareSize);
        user.setPosX(characterView.getX());
        // Put the character in the vertical second-to-bottommost square.
        characterView.setY(squareSize * (numVerticalSquares - 2));
        user.setPosY(characterView.getY());

        // Display the name. Or the best name, Prichard.
        nameView = findViewById(R.id.nameView);
        nameView.setText(Preferences.read("name", "Prichard"));
        mHandler = new Handler();
        movementOfCars();
        //movementOfCars();
        //randomMovementCar1();
    }
    private void movementOfCars() {
        mStatusChecker1.run();
        mStatusChecker2.run();
        mStatusChecker3.run();
        mStatusChecker4.run();
        //while(true){
        //    randomMovementCar1();
        //    randomMovementCar2();
        //      randomMovementCar3();
        //}
    }
    Runnable mStatusChecker1 = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus();
            } finally {
                randomMovementCar1();
                mHandler.postDelayed(mStatusChecker1, 400);
            }
        }
    };
    Runnable mStatusChecker2 = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus();
            } finally {
                randomMovementCar2();
                mHandler.postDelayed(mStatusChecker2, 500);
            }
        }
    };
    Runnable mStatusChecker4 = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus();
            } finally {
                randomMovementCar4();
                mHandler.postDelayed(mStatusChecker4, 350);
            }
        }
    };
    Runnable mStatusChecker3 = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus();
            } finally {
                randomMovementCar3();
                mHandler.postDelayed(mStatusChecker3, 200);
            }
        }
    };

    private void randomMovementCar1() {
        if (car1.getX() > -car1.getWidth()) {
            car1.setX(car1.getX() - squareSize);
        } else {
            car1.setX(screenWidth);
        }
    }
    private void randomMovementCar2() {
        if (car2.getX() < screenWidth) {
            car2.setX(car2.getX() + squareSize);
        } else {
            car2.setX(-car2.getWidth());
        }
    }
    private void randomMovementCar4() {
        if (car4.getX() < screenWidth) {
            car4.setX(car4.getX() + squareSize);
        } else {
            car4.setX(-car4.getWidth());
        }
    }
    private void randomMovementCar3() {
        if (car3.getX() > -car3.getWidth()) {
            car3.setX(car3.getX() - (2 * squareSize));
        } else {
            car3.setX(screenWidth);
        }
    }

    private void setDifficultyText(TextView difficultyView, TextView livesView) {
        String difficulty = Preferences.read("difficulty", "easy");
        difficultyView.setText("Difficulty: " + difficulty);
        switch (difficulty) {
        case "hard":
            livesView.setText("Lives: " + 1);
            break;
        case "medium":
            livesView.setText("Lives: " + 3);
            break;
        default:
            livesView.setText("Lives: " + 7);
        }
    }
    //movement for the cars
    //public static void randomMovementCar1(){
    //    Game game = new Game(this.screenWidth, this.screenHeight, this.squareSize);
    //    Player user = new Player();
    //    user.setCharacterView(characterView);
    //    user.moveCar1Left(car1, game, startPositionCar1 );
    //}

    // KeyEvent method; opens up its own thread so no need to put in onCreate.
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Game game = new Game(this.screenWidth, this.screenHeight, this.squareSize);
        Player user = new Player();
        user.setCharacterView(characterView);
        scoreNumber = (TextView) findViewById(R.id.scoreNumber);
        switch (keyCode) {
        // Uses WASD system.
        case KeyEvent.KEYCODE_W:
            if (user.movePlayer("moveUp", game)) {
                this.currPos++;
                boolean atGreatestSpot = false;
                if (this.currPos > this.greatestPos) {
                    this.greatestPos = this.currPos;
                    atGreatestSpot = true;
                }
                System.out.println(ScoreManager.getTileCorrespondingToPosition(currPos, this.map));
                if (atGreatestSpot) {
                    this.score = ScoreManager.getScoreAfterMove(this.score,
                            ScoreManager.getTileCorrespondingToPosition(currPos, this.map));
                } else {
                    System.out.print("not at the greatest spot");
                }
                System.out.println("Score is " + this.score);
                System.out.println("current position is " + this.currPos);
                System.out.println("max Position is  " + this.greatestPos);
                scoreNumber.setText("" + score);
            }
            //return true;
            break;
        case KeyEvent.KEYCODE_A:
            user.movePlayer("moveLeft", game);
            //return true;
            break;
        case KeyEvent.KEYCODE_D:
            user.movePlayer("moveRight", game);
            //return true;
            break;
        case KeyEvent.KEYCODE_S:
            if (user.movePlayer("moveDown", game)) {
                this.currPos--;
                //System.out.println("Score is " + this.score);
                //System.out.println("current position is " + this.currPos);
                //System.out.println("max Position is  " + this.greatestPos);
                //return true;
            }
            break;
        default:
            return super.onKeyUp(keyCode, event);
        }
        return true;
    }

    public TextView getNameView() {
        return this.nameView;
    }

    public TextView getDifficultyView() {
        return this.difficultyView;
    }

    public TextView getLivesView() {
        return this.livesView;
    }

    public void setMap(ArrayList<String> map) {
        this.map = map;
    }
}