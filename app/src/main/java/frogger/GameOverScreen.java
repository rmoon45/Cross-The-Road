package frogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.s0.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameOverScreen extends AppCompatActivity {
    private int displayScore;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);

        Bundle extra = getIntent().getExtras();
        displayScore = extra.getInt("score");

        this.database = FirebaseDatabase.getInstance().getReference();

        ((TextView) findViewById(R.id.scoreText)).setText("Score: " + extra.getInt("score"));

        database.child("scores").child(extra.getString("name")).setValue(displayScore);
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

    public int getDisplayScore() {
        return displayScore;
    }

    public void setDisplayScore(int i) {
        displayScore = i;
    }

}