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
    private boolean startGame;
    private Button setName;
    private ImageView bunny;
    private ImageView duck;
    private ImageView frog;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        bunny = (ImageView) findViewById(R.id.bunny);
        duck = (ImageView) findViewById(R.id.duck);
        frog = (ImageView) findViewById(R.id.frog);

        Player user = new Player();
        Game game = new Game();

        nameInput = (EditText) findViewById(R.id.nameInput);
        invalidName = (TextView) findViewById(R.id.nameInvalid);
        setName = (Button) findViewById(R.id.setName);
        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame = false;
                String userName = nameInput.getText().toString();
                if (!user.checkName(userName)) {
                    invalidName.setVisibility(View.VISIBLE);
                    nameInput.setError("Invalid Name! Please enter valid name.");
                    setName.setBackgroundColor(Color.RED);
                } else {
                    invalidName.setVisibility(View.INVISIBLE);
                    Preferences.write("name", userName);
                    setName.setBackgroundColor(Color.GREEN);
                    startGame = true;
                }
            }
        });
    }

    public void onBunnySelected(View v) {
        Log.d("test", "bunny selected");
        Preferences.write("character", "bunny");
        bunny.setBackgroundColor(Color.BLUE);
        duck.setBackgroundColor(Color.GRAY);
        frog.setBackgroundColor(Color.GRAY);
    }

    public void onFrogSelected(View v) {
        Log.d("test", "frog selected");
        Preferences.write("character", "frog");
        bunny.setBackgroundColor(Color.GRAY);
        duck.setBackgroundColor(Color.GRAY);
        frog.setBackgroundColor(Color.BLUE);
    }

    public void onDuckSelected(View v) {
        Log.d("test", "duck selected");
        Preferences.write("character", "duck");
        bunny.setBackgroundColor(Color.GRAY);
        duck.setBackgroundColor(Color.BLUE);
        frog.setBackgroundColor(Color.GRAY);
    }
    public void onStartGame(View v) {
        if (startGame) {
            Preferences.read("name", "Prichard");
            Intent intent = new Intent(ConfigScreen.this, frogger.GameScreen.class);
            startActivity(intent);
            finish();
        }
    }

    public void onEasySelected(View v) {
        ((Button) findViewById(R.id.easyButton)).setBackgroundColor(Color.BLUE);
        ((Button) findViewById(R.id.mediumButton)).setBackgroundColor(-7829368);
        ((Button) findViewById(R.id.hardButton)).setBackgroundColor(-7829368);
        Log.d("test", "easy selected");
        Preferences.write("difficulty", "easy");
    }

    public void onMediumSelected(View v) {
        findViewById(R.id.easyButton).setBackgroundColor(-7829368);
        findViewById(R.id.mediumButton).setBackgroundColor(Color.BLUE);
        findViewById(R.id.hardButton).setBackgroundColor(-7829368);
        Log.d("test", "medium selected");
        Preferences.write("difficulty", "medium");
    }

    public void onHardSelected(View v) {
        findViewById(R.id.easyButton).setBackgroundColor(-7829368);
        findViewById(R.id.mediumButton).setBackgroundColor(-7829368);
        findViewById(R.id.hardButton).setBackgroundColor(Color.BLUE);
        Log.d("test", "hard selected");
        Preferences.write("difficulty", "hard");
    }
}