package com.betacom.backend.model;

import com.betacom.backend.request.CpuRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cpu")
public class Cpu extends Product{

    @Column(nullable = false)
    private Double  ghz;

    @Column(nullable = false)
    private Integer  core;
    
    public Cpu() {
        super();
    }

    //with id
    public Cpu(Long id, String brand, String model, String description, Integer stock, Integer core, Double ghz) {
        super(id, brand, model, description, stock);
        this.core = core;
        this.ghz = ghz;
    }

    //no id
    public Cpu(String brand, String model, String description, Integer stock, Double ghz, Integer core) {
        super(brand, model, description, stock);
        this.ghz = ghz;
        this.core = core;
    }

    //from req
    public Cpu(CpuRequest req) {
        super(req);
        this.ghz = req.getGhz();
        this.core = req.getCore();
    }

    public Double getGhz() {
        return ghz;
    }

    public void setGhz(Double ghz) {
        this.ghz = ghz;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "ghz=" + ghz +
                ", core=" + core +
                "} " + super.toString();
    }
}
