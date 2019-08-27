package de.hdmstuttgart.parkspot;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Context;

import de.hdmstuttgart.parkspot.models.User;

/**
 * This file is part of Parkspot.      
 *
 * Parkspot is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation version 3 of the License.
 * Parkspot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Parkspot. 
 * If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2019, Hochschule der Medien
 * Author: Nils Mursinsky
 */

public class SharedPrefs {


    private static SharedPreferences parkspotSharedPrefs;
    public static final String USERID = "USERID";
    public static final String MAIL = "MAIL";
    public static final String ACCESSTOKEN = "ACCESSTOKEN";
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

    //Int read and write
    public static Integer read(String key, int defValue) {
        return parkspotSharedPrefs.getInt(key, defValue);
    }

    public static void write(String key, int value) {
        SharedPreferences.Editor prefsEditor = parkspotSharedPrefs.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }
}
