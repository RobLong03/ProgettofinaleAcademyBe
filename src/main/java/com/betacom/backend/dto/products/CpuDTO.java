package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Cpu;

public class CpuDTO extends ProductDTO {
    private Double ghz;
    private Integer core;

    public CpuDTO() {}

    public CpuDTO(Long id, String brand, String model, String description, Integer stock, Double price, Integer core, Double ghz) {
        super(id, brand, model, description, stock, price);
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
