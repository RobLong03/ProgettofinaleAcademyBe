package com.betacom.backend.dto.administrator;

import com.betacom.backend.model.administrator.Administrator;

public class AdministratorDTO {

    Long id;

    String username;

    String email;

    public AdministratorDTO() {
    }

    public AdministratorDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public AdministratorDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public AdministratorDTO(Administrator adm) {
        this.id=adm.getId();
        this.username = adm.getUsername();
        this.email = adm.getEmail();
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

    @Override
    public String toString() {
        return "AdministratorDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
