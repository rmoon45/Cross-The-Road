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

public class ConfigScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
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

        Preferences.write("name", "TODO: insert name here");

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