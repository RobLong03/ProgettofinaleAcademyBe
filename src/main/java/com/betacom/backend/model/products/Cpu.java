package com.betacom.backend.model.products;

import com.betacom.backend.request.products.CpuRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cpu")
public class Cpu extends Product {

    @Column(nullable = false)
    private Double  ghz;

    @Column(nullable = false)
    private Integer  core;
    
    public Cpu() {
        super();
    }

    //with id
    public Cpu(Long id, String brand, String model, String description, Integer stock,Double price, Double ghz, Integer core,String imageUrl) {
        super(id, brand, model, description, stock,price, imageUrl);
        this.core = core;
        this.ghz = ghz;
    }

    //no id
    public Cpu(String brand, String model, String description, Integer stock,Double price, Double ghz, Integer core, String imageUrl) {
        super(brand, model, description, stock,price, imageUrl);
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
