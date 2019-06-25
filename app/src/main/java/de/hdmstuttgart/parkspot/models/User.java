package de.hdmstuttgart.parkspot.models;

import de.hdmstuttgart.parkspot.SharedPrefs;

public class User {

    private int userid;
    private String mail;
    private Boolean LOGGED_IN;

    public static Boolean getLOGGED_IN() {
        return SharedPrefs.read(String.valueOf(SharedPrefs.LOGGED_IN), false);
    }

    public static void setLOGGED_IN(Boolean status) {
        SharedPrefs.write(String.valueOf(SharedPrefs.LOGGED_IN), status);
    }

    public static int getUserid() {
        return SharedPrefs.read(SharedPrefs.USERID, 0);
    }

    public static void setUserid(int userid) {
        SharedPrefs.write(SharedPrefs.USERID, userid);
    }

    public static String getMail() {
        return SharedPrefs.read(SharedPrefs.MAIL, null);
    }

    public static void setMail(String mail) {
        SharedPrefs.write(SharedPrefs.MAIL, mail);
    }


}
