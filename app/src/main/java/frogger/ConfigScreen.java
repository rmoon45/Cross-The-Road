package frogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
                }
            }
        });

        easy = (Button) findViewById(R.id.easyButton);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty(1);
                System.out.println(game.getDifficulty());
            }
        });
        medium = (Button) findViewById(R.id.mediumButton);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty(2);
                System.out.println(game.getDifficulty());
            }
        });
        hard = (Button) findViewById(R.id.hardButton);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setDifficulty(3);
                System.out.println(game.getDifficulty());
            }
        });

    }
}