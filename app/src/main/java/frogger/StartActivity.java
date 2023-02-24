package frogger;

import android.graphics.Canvas;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.example.s0.R;
import android.content.Intent;

public class StartActivity extends AppCompatActivity {

    //GameScreen gameScreen = new GameScreen(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        //gameScreen.onDraw(new Canvas());

    }


    public void onEnd(View view) {
        finish();
    }
    public void onStart(View view) {
        Intent intent = new Intent(StartActivity.this, ConfigScreen.class);
        startActivity(intent);
    }
}
