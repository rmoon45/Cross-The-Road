package frogger;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.TranslateAnimation;

import com.example.s0.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import preferences.Preferences;

public class GameScreen extends AppCompatActivity {

    private ImageView characterView;
    private TextView livesView;
    private TextView difficultyView;
    private TextView nameView;

    private RelativeLayout backgroundLayout;

    private Bitmap bitmap;

    private int squareSize;

    private int screenWidth;

    private int screenHeight;

    public GameScreen() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        // Change the contents of the level by modifying this list.
        // The top row will always be the goal, and the bottom two rows will always be safe tiles
        // for now. (the goal is to have only the bottom row need to be a safe tile, but
        // there is an issue with getting the accurate screen height in the current code that
        // makes it necessary to have the bottom two rows as safe tiles. )
        /* The three tile options here are river, safe, and road.
         * Per the specs, rivers should always have a different width than roads, and safe tiles
         * should always be narrower than rivers and roads.
         */
        List<String> rows = new ArrayList<String>(Arrays.asList(
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
        rows.add(0, "goal"); // Don't put these into the List definition plz thx
        rows.add("safe");
        rows.add("safe");

        backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        // TODO: if someone could fix this to get the actual usable height, that would be great.
        // When the height is correct, the character should be at the very bottom of the screen.
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels
            - getResources().getDimensionPixelSize(
                getResources().getIdentifier("navigation_bar_height", "dimen", "android")
            );
        int numVerticalSquares = rows.size();
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

        // Calculate the horizontal offset needed so that the middle column of tiles is centered.
        // This should be a negative number or zero.
        int horizontalOffset = (screenWidth / 2)
                - (squareSize / 2)
                - (numHorizontalSquares / 2) * squareSize;

        // Draw the tiles.
        for (int i = 0; i < numVerticalSquares; i++) {
            // Get the corresponding tile image for the row.
            int imageResource = R.drawable.safe;
            switch (rows.get(i)) {
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
                if (rows.get(i) != "safe") {
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
        livesView = findViewById(R.id.livesView);
        difficultyView = findViewById(R.id.difficultyView);

        // Set and display lives and difficulty.
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

        // Set the size and location of your fighter.
        characterView.getLayoutParams().height = squareSize;
        characterView.getLayoutParams().width = squareSize;
        // Put the character in the horizontal middle square of the map.
        characterView.setX(horizontalOffset + (numHorizontalSquares / 2) * squareSize);
        // Put the character in the vertical second-to-bottommost square.
        characterView.setY(squareSize * (numVerticalSquares - 2));

        // Display the name.
        nameView = findViewById(R.id.nameView);
        nameView.setText(Preferences.read("name", "Prichard"));

        Log.d("squaresize", String.valueOf(squareSize));    //112
        Log.d("coord", String.valueOf(characterView.getX()));   //484.0
        Log.d("coord", String.valueOf(characterView.getY()));   //1792.0


    }

    //KeyEvent method; opens up its own thread so no need to put in onCreate.
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode) {

            //Uses WASD system.
            case KeyEvent.KEYCODE_W:
                movePlayer("moveUp");
                return true;
            case KeyEvent.KEYCODE_A:
                movePlayer("moveLeft");
                return true;
            case KeyEvent.KEYCODE_D:
                movePlayer("moveRight");
                return true;
            case KeyEvent.KEYCODE_S:
                movePlayer("moveDown");
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }

    }

    //Determines the movement of the player on grid system.
    protected void movePlayer(String movement) {
        switch(movement) {

            //based off of the input string, change the position to be moving in said direction.
            //use subtract for going up/left and plus for down/right bc the origin is at top left.
            case "moveUp":
                if (characterView.getY() > 0) {
                    characterView.setY(characterView.getY() - squareSize);
                }
                break;
            case "moveLeft":
                if (characterView.getX() > 0 + (squareSize / 2)) {
                    characterView.setX(characterView.getX() - squareSize);
                }
                break;
            case "moveRight":
                if ((characterView.getX() + squareSize) < screenWidth - (squareSize /2)) {
                    characterView.setX(characterView.getX() + squareSize);
                }
                break;
            case "moveDown":
                if ((characterView.getY() + (2 * squareSize)) < screenHeight) {
                    characterView.setY(characterView.getY() + squareSize);
                }
        }
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
}