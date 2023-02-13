package frogger;

import android.app.Application;
import android.content.Context;

import preferences.Preferences;

public class Frogger extends Application {
    private static Context context;

    private Preferences preferences;

    public Preferences getPreferences() {
        return preferences;
    }
    public void setPreferences(Preferences pref) {
        preferences = pref;
    }
    public void onCreate() {
        super.onCreate();
        Frogger.context = getApplicationContext();

        preferences = new Preferences(context);
    }

    public static Context getContext() {
        return Frogger.context;
    }
    
    // source https://stackoverflow.com/questions/2002288/static-way-to-get-context-in-android
}
