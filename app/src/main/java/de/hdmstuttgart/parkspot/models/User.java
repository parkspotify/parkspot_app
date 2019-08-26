package de.hdmstuttgart.parkspot.models;

import de.hdmstuttgart.parkspot.SharedPrefs;

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
