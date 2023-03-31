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

        ((TextView) findViewById(R.id.scoreText)).setText("Score: " + extra.getInt("score"));
        //((TextView) findViewById(R.id.scoreText)).setText("Score: " + extra.getInt("displayScore"));
    }


    //2 buttons: restart and exit game
    public void onRestartGame(View view) {
        //restart -> go back to config screen
        Intent intent = new Intent(GameOverScreen.this, ConfigScreen.class);
        startActivity(intent);
        finish();
    }

    //exit: not the 'x' button
    public void onExitGame(View view) {
        Intent intent = new Intent(GameOverScreen.this, StartActivity.class);
        startActivity(intent);
        finish();
    }

}