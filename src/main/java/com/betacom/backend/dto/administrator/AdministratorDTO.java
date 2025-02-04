package com.betacom.backend.dto.administrator;

import com.betacom.backend.model.administrator.Administrator;

public class AdministratorDTO {

    Long id;

    String username;

    String email;

    String password;

    public AdministratorDTO() {
    }

    public AdministratorDTO(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AdministratorDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AdministratorDTO(Administrator adm) {
        this.id=adm.getId();
        this.username = adm.getUsername();
        this.email = adm.getEmail();
        this.password = adm.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdministratorDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
