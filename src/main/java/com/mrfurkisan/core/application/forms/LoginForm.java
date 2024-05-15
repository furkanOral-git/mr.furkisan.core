package com.mrfurkisan.core.application.forms;

public class LoginForm {

    private String emailOrUsername;
    private String password;
    private String macAddress;

    public String getEmailOrUsername() {
        return this.emailOrUsername;
    }

    public String getPassword() {
        return this.password;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setEmailOrUsername(String value) {
        this.emailOrUsername = value;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public void setMacAddress(String value) {
        this.macAddress = value;
    }

}
