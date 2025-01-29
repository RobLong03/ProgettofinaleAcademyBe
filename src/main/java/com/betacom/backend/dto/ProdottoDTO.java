package com.betacom.backend.dto;

import com.betacom.backend.model.Prodotto;

public class ProdottoDTO { //ciao belliiiiiiii



    Long id;

    String marca;

    String modello;

    String descrizione;

    Integer quantita;

    public ProdottoDTO() {
    }

    public ProdottoDTO(String marca, String modello, String descrizione, Integer quantita) {
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
        this.quantita = quantita;
    }

    public ProdottoDTO(Long id, String marca, String modello, String descrizione, Integer quantita) {
        this.id = id;
        this.marca = marca;
        this.modello = modello;
        this.descrizione = descrizione;
        //commento
        this.quantita = quantita;
    }

    public ProdottoDTO(Prodotto prodotto) {
        this.id = prodotto.getId();
        this.marca = prodotto.getMarca();
        this.modello = prodotto.getModello();
        this.descrizione = prodotto.getDescrizione();
        this.quantita = prodotto.getQuantita();
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
        return "prodottoRequest{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modello='" + modello + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", quantita=" + quantita +
                '}';
    }
}
