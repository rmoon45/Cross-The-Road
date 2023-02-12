package frogger;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.s0.R;

import preferences.Preferences;

import com.example.s0.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ConfigScreen extends AppCompatActivity {

    private EditText nameInput;
    private TextView invalidName;
    private Button setName;
    private Button easy;
    private Button medium;
    private Button hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        Game game = new Game();
        Player user = new Player();
        nameInput = (EditText) findViewById(R.id.nameInput);
        invalidName = (TextView) findViewById(R.id.nameInvalid);
        setName = (Button) findViewById(R.id.setName);
        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameInput.length() == 0 || nameInput.getText().toString().trim().length() == 0) {
                    invalidName.setVisibility(View.VISIBLE);
                    nameInput.setError("Invalid Name! Please enter valid name.");
                } else {
                    invalidName.setVisibility(View.INVISIBLE);
                    user.setName(nameInput.getText().toString().trim());
                    Preferences.write("name", nameInput.getText().toString().trim());
                }
            }
        });

        easy = (Button) findViewById(R.id.easyButton);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty("easy");
                Preferences.write("difficulty", "easy");
            }
        });
        medium = (Button) findViewById(R.id.mediumButton);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty("medium");
                Preferences.write("difficulty", "medium");
            }
        });
        hard = (Button) findViewById(R.id.hardButton);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty("hard");
                Preferences.write("difficulty", "hard");
            }
        });

    }

    public void onBunnySelected(View v) {
        Log.d("test", "bunny selected");
        Preferences.write("character", "bunny");
    }

    public void onFrogSelected(View v) {
        Log.d("test", "frog selected");
        Preferences.write("character", "frog");
    }

    public void onDuckSelected(View v) {
        Log.d("test", "duck selected");
        Preferences.write("character", "duck");
    }
    public void onStartGame(View v) {

        Preferences.read("name", "Prichard");

        Intent intent = new Intent(ConfigScreen.this, frogger.game_screen.class);
        startActivity(intent);
        finish();
    }

    public void onEasySelected(View v) {
        Log.d("test", "easy selected");
        Preferences.write("difficulty", "easy");
    }

    public void onMediumSelected(View v) {
        Log.d("test", "medium selected");
        Preferences.write("difficulty", "medium");
    }

    public void onHardSelected(View v) {
        Log.d("test", "hard selected");
        Preferences.write("difficulty", "hard");
    }
}