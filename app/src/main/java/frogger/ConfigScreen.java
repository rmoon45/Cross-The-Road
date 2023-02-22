package frogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.s0.R;
import preferences.Preferences;
import android.widget.EditText;
import android.widget.ImageView;
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
                String userName = nameInput.getText().toString().trim();
                if (nameInput.length() == 0 || userName.length() == 0) {
                    invalidName.setVisibility(View.VISIBLE);
                    nameInput.setError("Invalid Name! Please enter valid name.");
                    setName.setBackgroundColor(Color.RED);
                } else {
                    invalidName.setVisibility(View.INVISIBLE);
                    user.setName(userName);
                    Preferences.write("name", userName);
                    setName.setBackgroundColor(Color.GREEN);
                }
            }
        });
        easy = (Button) findViewById(R.id.easyButton);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty("easy");
                game.setLives(7);
                Preferences.write("difficulty", "easy");
                easy.setBackgroundColor(Color.BLUE);
                medium.setBackgroundColor( -7829368);
                hard.setBackgroundColor( -7829368);
            }
        });
        medium = (Button) findViewById(R.id.mediumButton);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty("medium");
                game.setLives(3);
                Preferences.write("difficulty", "medium");
                easy.setBackgroundColor( -7829368);
                medium.setBackgroundColor(Color.BLUE);
                hard.setBackgroundColor( -7829368);
            }
        });
        hard = (Button) findViewById(R.id.hardButton);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty("hard");
                game.setLives(1);
                Preferences.write("difficulty", "hard");
                easy.setBackgroundColor( -7829368);
                medium.setBackgroundColor( -7829368);
                hard.setBackgroundColor(Color.BLUE);
            }
        });

    }

    public void onBunnySelected(View v) {
        Log.d("test", "bunny selected");
        Preferences.write("character", "bunny");

        ImageView bunny = (ImageView) findViewById(R.id.bunny);
        ImageView duck = (ImageView) findViewById(R.id.duck);
        ImageView frog = (ImageView) findViewById(R.id.frog);
        bunny.setBackgroundColor(Color.BLUE);
        duck.setBackgroundColor(Color.GRAY);
        frog.setBackgroundColor(Color.GRAY);
    }
    public TextView getInvalidName() {
        return this.invalidName;
    }

    public void onFrogSelected(View v) {
        Log.d("test", "frog selected");
        Preferences.write("character", "frog");

        ImageView bunny = (ImageView) findViewById(R.id.bunny);
        ImageView duck = (ImageView) findViewById(R.id.duck);
        ImageView frog = (ImageView) findViewById(R.id.frog);
        bunny.setBackgroundColor(Color.GRAY);
        duck.setBackgroundColor(Color.GRAY);
        frog.setBackgroundColor(Color.BLUE);
    }

    public void onDuckSelected(View v) {
        Log.d("test", "duck selected");
        Preferences.write("character", "duck");

        ImageView bunny = (ImageView) findViewById(R.id.bunny);
        ImageView duck = (ImageView) findViewById(R.id.duck);
        ImageView frog = (ImageView) findViewById(R.id.frog);
        bunny.setBackgroundColor(Color.GRAY);
        duck.setBackgroundColor(Color.BLUE);
        frog.setBackgroundColor(Color.GRAY);
    }
    public void onStartGame(View v) {

        Preferences.read("name", "Prichard");

        Intent intent = new Intent(ConfigScreen.this, frogger.GameScreen.class);
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