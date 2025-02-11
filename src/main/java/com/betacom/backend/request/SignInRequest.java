package com.betacom.backend.request;

public class SignInRequest {
    private String username;
    private String pwd;



    public SignInRequest() {
        super();
    }
    public SignInRequest(String username, String pwd) {
        super();
        this.username = username;
        this.pwd = pwd;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @Override
    public String toString() {
        return "SignInRequest [username=" + username + ", pwd=" + pwd + "]";
    }




}

