package frogger;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s0.R;

import preferences.Preferences;

public class game_screen extends AppCompatActivity {

    ImageView characterView;
    TextView livesView;
    TextView difficultyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        characterView = findViewById(R.id.characterView);
        livesView = findViewById(R.id.livesView);
        difficultyView = findViewById(R.id.difficultyView);

        String difficulty = Preferences.read("difficulty", "easy");
        difficultyView.setText("Difficulty: " + difficulty);
        switch (difficulty) {
            case "medium":
                livesView.setText("Lives: " + 3);
                break;
            case "hard":
                livesView.setText("Lives: " + 1);
                break;
            default:
                livesView.setText("Lives: " + 7);
        }

        String character = Preferences.read("character", "duck");
        switch(character) {
            case "bunny":
                characterView.setImageResource(R.drawable.bunny);
                break;
            case "duck":
                characterView.setImageResource(R.drawable.duck);
                break;
            default:
                characterView.setImageResource(R.drawable.frog);
        }
    }
}