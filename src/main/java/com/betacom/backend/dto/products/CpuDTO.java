package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Cpu;

public class CpuDTO extends ProductDTO {
    private Double ghz;
    private Integer core;
//there is no need to set the type because is already setted by super class 
    public CpuDTO() {}

    public CpuDTO(Long id, String brand, String model, ProductDescriptionDTO description,String type, Integer stock, Double price, Integer core, Double ghz, String imageUrl) {
        // Directly pass "cpu" instead of using setType later
        super(id, brand, model, description, type, stock, price, imageUrl);
        this.core = core;
        this.ghz = ghz;
    }
    public CpuDTO(String brand, String model, ProductDescriptionDTO description,String type, Integer stock, Double price, Integer core, Double ghz, String imageUrl) {
        // Directly pass "cpu" instead of using setType later
        super( brand, model, description, type, stock, price, imageUrl);
        this.core = core;
        this.ghz = ghz;
    }

    public CpuDTO(Cpu cpu){
        super(cpu);
        this.ghz = cpu.getGhz();
        this.core = cpu.getCore();
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
        return "CpuDTO{" +
                "ghz=" + ghz +
                ", core=" + core +
                "} " + super.toString();
    }
}
