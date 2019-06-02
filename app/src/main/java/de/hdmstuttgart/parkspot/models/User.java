package de.hdmstuttgart.parkspot.models;

public class User {

    private int userid;
    private String mail;
    private String password;

    public User (int userid, String mail, String password) {
        this.userid = userid;
        this.mail = mail;
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


}
