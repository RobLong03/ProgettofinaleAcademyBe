package com.betacom.backend.model;

import com.betacom.backend.request.ProdottoRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "prodotto")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modello;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private Integer quantita;

    public Prodotto() {
    }

    public Prodotto(Long id, String marca, String modello, String descrizione, Integer quantita) {
        this.id = id;
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
        this.quantita = quantita;
    }

    public Prodotto(String marca, String modello, String descrizione, Integer quantita) {
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
        this.quantita = quantita;
    }

    public Prodotto(ProdottoRequest req){
        this.marca = req.getMarca();
        this.modello = req.getModello();
        this.descrizione = req.getDescrizione();
        this.quantita = req.getQuantita();
        if(this.getId() != null)
            this.id = req.getId();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", quantita=" + quantita +
                '}';
    }
}
