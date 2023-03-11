package frogger;

import android.app.Application;

import preferences.Preferences;

public class Frogger extends Application {
    public void onCreate() {
        super.onCreate();
        // This object has to be instantiated at least once for everything to work ¯\_(ツ)_/¯
        new Preferences(getApplicationContext());
    }
}
