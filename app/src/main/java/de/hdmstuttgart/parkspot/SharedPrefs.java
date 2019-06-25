package de.hdmstuttgart.parkspot;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Context;

public class SharedPrefs {

    private static SharedPreferences parkspotSharedPrefs;
    public static final String USERID = "USERID";
    public static final String MAIL = "MAIL";
    public static final Boolean LOGGED_IN = false;

    public static void init(Context context) {
        if(parkspotSharedPrefs == null) {
            parkspotSharedPrefs = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        }
    }

    //String read and write
    public static String read(String key, String defValue) {
        return parkspotSharedPrefs.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = parkspotSharedPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    //Boolean read and write
    public static boolean read(String key, boolean defValue) {
        return parkspotSharedPrefs.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = parkspotSharedPrefs.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    //Boolean read and write
    public static Integer read(String key, int defValue) {
        return parkspotSharedPrefs.getInt(key, defValue);
    }

    public static void write(String key, int value) {
        SharedPreferences.Editor prefsEditor = parkspotSharedPrefs.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }
}
