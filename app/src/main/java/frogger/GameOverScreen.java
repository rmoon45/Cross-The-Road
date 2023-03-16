package frogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.s0.R;

public class GameOverScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);

        Bundle extra = getIntent().getExtras();
        initializeScoreView(extra);
    }

    private void initializeScoreView(Bundle extra) {
        ((TextView) findViewById(R.id.scoreView)).setText(extra.getString("Score: \n" + extra.getString("score")));
    }

    //2 buttons: restart and exit game
    //restart -> go back to config screen
    public void onRestartGame(View view) {
        Intent intent = new Intent(GameOverScreen.this, ConfigScreen.class);
        startActivity(intent);
    }

    //exit: not the 'x' button
    public void onExitGame(View view) {
        finish();
    }

    //final score should be displayed on gameoverscreen
}