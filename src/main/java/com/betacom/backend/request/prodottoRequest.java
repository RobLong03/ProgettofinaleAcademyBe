package com.betacom.backend.request;

public class prodottoRequest {

    Integer id;

    String marca;

    String modello;

    String descrizione;

    Integer quantita;

    public prodottoRequest() {
    }

    public prodottoRequest(String marca, String modello, String descrizione, Integer quantita) {
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
        this.quantita = quantita;
    }

    public prodottoRequest(Integer id, String marca, String modello, String descrizione, Integer quantita) {
        this.id = id;
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
        this.quantita = quantita;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "prodottoRequest{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", quantita=" + quantita +
                '}';
    }
}
