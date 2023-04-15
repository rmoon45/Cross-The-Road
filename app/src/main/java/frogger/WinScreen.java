package frogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.s0.R;

public class WinScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        Bundle extra = getIntent().getExtras();

        ((TextView) findViewById(R.id.scoreDisplay)).setText(extra.getInt("score"));
    }


    public void onRestartGame(View view) {
        //restart -> go back to config screen
        Intent intent = new Intent(WinScreen.this, ConfigScreen.class);
        startActivity(intent);
        finish();
    }

    //exit: not the 'x' button
    public void onExitGame(View view) {
        Intent intent = new Intent(WinScreen.this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}