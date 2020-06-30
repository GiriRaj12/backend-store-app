package com.backend.Models;

public class UserModel {
    private String userName;
    private String passWord;
    private boolean adminLogin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(boolean adminLogin) {
        this.adminLogin = adminLogin;
    }
}
