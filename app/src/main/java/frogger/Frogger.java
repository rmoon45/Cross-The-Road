package frogger;

import android.app.Application;

import preferences.Preferences;

public class Frogger extends Application {
    private Preferences preferences;
    public Preferences getPreferences() {
        return preferences;
    }
    public void setPreferences(Preferences pref) {
        preferences = pref;
    }
    public void onCreate() {
        super.onCreate();
        preferences = new Preferences(getApplicationContext());
    }
}
