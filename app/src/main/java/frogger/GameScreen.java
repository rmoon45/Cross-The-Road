package frogger;

import android.content.res.Resources;
import android.os.Bundle;
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

        // Instantiate Game object and Player object
        Game game = new Game();
        Player user = new Player();

        backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        game.setScreenWidth(screenWidth);
        // to-do: if someone could fix this to get the actual usable height, that would be great.
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels
            - getResources().getDimensionPixelSize(
                getResources().getIdentifier("navigation_bar_height", "dimen", "android")
            );
        game.setScreenHeight(screenHeight);
        int numVerticalSquares = rows.size();
        squareSize = screenHeight / numVerticalSquares;
        game.setSquareSize(squareSize);
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

        // Set the size and location of your fighter.
        characterView.getLayoutParams().height = squareSize;
        characterView.getLayoutParams().width = squareSize;
        // Put the character in the horizontal middle square of the map.
        characterView.setX(horizontalOffset + (numHorizontalSquares / 2) * squareSize);
        user.setPosX(characterView.getX());
        // Put the character in the vertical second-to-bottommost square.
        characterView.setY(squareSize * (numVerticalSquares - 2));
        user.setPosY(characterView.getY());

        // Display the name. Or the best name, Prichard.
        nameView = findViewById(R.id.nameView);
        nameView.setText(Preferences.read("name", "Prichard"));
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

    // KeyEvent method; opens up its own thread so no need to put in onCreate.
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Game game = new Game();
        game.setScreenHeight(screenHeight);
        game.setScreenWidth(screenWidth);
        game.setSquareSize(squareSize);
        Player user = new Player();
        user.setCharacterView(characterView);
        switch (keyCode) {
        //Uses WASD system.
        case KeyEvent.KEYCODE_W:
            user.movePlayer("moveUp", game);
            return true;
        case KeyEvent.KEYCODE_A:
            user.movePlayer("moveLeft", game);
            return true;
        case KeyEvent.KEYCODE_D:
            user.movePlayer("moveRight", game);
            return true;
        case KeyEvent.KEYCODE_S:
            user.movePlayer("moveDown", game);
            return true;
        default:
            return super.onKeyUp(keyCode, event);
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