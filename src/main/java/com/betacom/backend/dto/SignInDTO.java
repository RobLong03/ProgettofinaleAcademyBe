package com.betacom.backend.dto;

import com.betacom.backend.utils.Roles;

public class SignInDTO {
    private Boolean logged;
    private String role;

    public SignInDTO() {}

    public SignInDTO(Boolean logged, String role) {
        this.logged = logged;
        this.role = role;
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "AmministratoreSIgnInDTO{" +
                "logged=" + logged +
                ", role=" + role +
                '}';
    }

    public void setRole(String role) {
        this.role = role;
    }
}
