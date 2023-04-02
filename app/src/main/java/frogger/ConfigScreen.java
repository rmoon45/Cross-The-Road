package frogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.example.s0.R;
import android.widget.EditText;

public class ConfigScreen extends AppCompatActivity {

    // Difficulty, lives, character, and name to pass to the game screen.
    private String difficulty;
    private int lives;
    private String character;
    private String name;

    // Make sure a valid name, character, and difficulty have been selected.
    private boolean isNameValid;
    private boolean isDifficultySelected;
    private boolean isCharacterSelected;

    // UI resource ids for convenience
    private int[] difficultyButtonIds;
    private int[] characterButtonIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        this.isNameValid = false;
        this.isDifficultySelected = false;
        this.isCharacterSelected = false;

        this.difficultyButtonIds = new int[]{R.id.easyButton, R.id.mediumButton, R.id.hardButton};
        this.characterButtonIds = new int[]{R.id.bunny, R.id.frog, R.id.duck};

        findViewById(R.id.startButton).setEnabled(false);

        initializeNameValidation();
    }

    public void onStartGame(View v) {
        Intent intent = new Intent(ConfigScreen.this, frogger.GameScreen.class);
        intent.putExtra("name", this.name);
        intent.putExtra("difficulty", this.difficulty);
        intent.putExtra("lives", this.lives);
        intent.putExtra("character", this.character);
        startActivity(intent);
        finish();
    }

    public void initializeNameValidation() {
        this.name = "Name";
        this.isNameValid = true;

        ((EditText) findViewById(R.id.nameInput)).addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString();
                boolean isNameValid = (name.trim().length() != 0);

                ConfigScreen.this.name = name;
                ConfigScreen.this.isNameValid = isNameValid;

                if (isNameValid) {
                    findViewById(R.id.nameInvalid).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.nameInvalid).setVisibility(View.VISIBLE);
                }

                updateStartButton();
            }
        });
    }

    public void onBunnySelected(View v) {
        displaySelectedCharacter(R.id.bunny);
        this.character = "bunny";
    }

    public void onDuckSelected(View v) {
        displaySelectedCharacter(R.id.duck);
        this.character = "duck";
    }

    public void onFrogSelected(View v) {
        displaySelectedCharacter(R.id.frog);
        this.character = "frog";
    }

    public void onEasySelected(View v) {
        this.difficulty = "easy";
        this.lives = 7;
        displaySelectedDifficulty(R.id.easyButton);
    }

    public void onMediumSelected(View v) {
        this.difficulty = "medium";
        this.lives = 3;
        displaySelectedDifficulty(R.id.mediumButton);
    }

    public void onHardSelected(View v) {
        this.difficulty = "hard";
        this.lives = 1;
        displaySelectedDifficulty(R.id.hardButton);
    }

    private void displaySelectedCharacter(int selectedCharacterId) {
        this.isCharacterSelected = true;
        displaySelectedButton(selectedCharacterId, characterButtonIds);
    }

    private void displaySelectedDifficulty(int selectedDifficultyId) {
        this.isDifficultySelected = true;
        displaySelectedButton(selectedDifficultyId, difficultyButtonIds);
    }

    private void displaySelectedButton(int selectedResourceId, int[] resourceIds) {
        for (int currentResourceId: resourceIds) {
            (findViewById(currentResourceId)).setBackgroundColor(Color.GRAY);
        }
        (findViewById(selectedResourceId)).setBackgroundColor(Color.BLUE);
        updateStartButton();
    }

    private void updateStartButton() {
        if (isNameValid && isCharacterSelected && isDifficultySelected) {
            findViewById(R.id.startButton).setEnabled(true);
        } else {
            findViewById(R.id.startButton).setEnabled(false);
        }
    }
}