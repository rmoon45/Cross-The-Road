package frogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.s0.R;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ConfigScreen extends AppCompatActivity {

    private EditText nameInput;
    private TextView invalidName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        nameInput = (EditText) findViewById(R.id.nameInput);
        invalidName = (TextView) findViewById(R.id.nameInvalid);

    }

    // Check if invalid.
    public void onSetName(View view) {
        String name = nameInput.getText().toString();
        if (name == null || name == "" || name.trim() == "") {
            invalidName.setVisibility(View.VISIBLE);
        } else {
            Player user = new Player();
            user.setName(name);
        }
    }
}