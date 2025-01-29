package com.betacom.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prodotto")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
