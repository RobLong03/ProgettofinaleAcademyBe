package com.betacom.backend.dto;

import com.betacom.backend.utils.Roles;

public class SignInDTO {
    private Boolean logged;
    private String role;

    public SignInDTO() {}


    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
