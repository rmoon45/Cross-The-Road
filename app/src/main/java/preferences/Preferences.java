package preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Preferences {
    private static Preferences preferences;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    public Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        Log.d("test", "private Preferences");
    }

    public static Preferences getInstance(Context context) {
        if (preferences == null) {
            preferences = new Preferences(context.getApplicationContext());
        }
        Log.d("test", "public static Preferences getInstance");
        return preferences;
    }

    public static void write(String key, String val) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString(key, val);
        sharedPreferencesEditor.commit();
    }
    public static void write(String key, int val) {
        sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putInt(key, val);
        sharedPreferencesEditor.commit();
    }
    public static String read(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public static int read(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    // Credit to
    // https://stackoverflow.com/questions/70288072/creating-a-common-shared-preference-class-in-android-and-using-that-class-to-fet
    // for this class
}
