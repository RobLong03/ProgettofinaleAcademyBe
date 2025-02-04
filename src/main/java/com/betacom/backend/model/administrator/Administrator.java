package com.betacom.backend.model.administrator;

import com.betacom.backend.request.administrator.AdministratorRequest;
import jakarta.persistence.*;

@Entity
@Table
public class Administrator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String email;

    String password;

    public Administrator() {
    }

    public Administrator(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Administrator(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Administrator(AdministratorRequest req) {
        if(req.getId()!=null)
            this.id=req.getId();

        this.username = req.getUsername();
        this.email = req.getEmail();
        this.password = req.getPassword();
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
        return "Amministratore{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


