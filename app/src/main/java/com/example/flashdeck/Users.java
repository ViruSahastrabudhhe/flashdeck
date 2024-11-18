package com.example.flashdeck;

public class Users {

    private int userID;
    private String userEmail;
    private String userPassword;

    public Users(int id, String email, String password) {
        this.userID = id;
        this.userEmail = email;
        this.userPassword = password;
    }

    public String getUserEmail() { return this.userEmail; };
    public String getUserPassword() { return this.userPassword; };
    public int getUserID() { return this.userID; };
}
