package com.betacom.backend.request.products;

public class CpuRequest extends ProductRequest{
    private Double ghz;
    private Integer core;

    public CpuRequest() {}

    public CpuRequest(String brand, String model,  Integer stock,Double price, Double ghz, Integer core,String imageUrl) {
        super(brand, model,stock,price,imageUrl);
        this.ghz = ghz;
        this.core = core;
    }

    public CpuRequest(Long id, String brand, String model,Integer stock,Double price, Double ghz, Integer core,String imageUrl) {
        super(id, brand, model,  stock,price, imageUrl);
        this.ghz = ghz;
        this.core = core;
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
        return "CpuRequest{" +
                "ghz=" + ghz +
                ", core=" + core +
                "} " + super.toString();
    }
}
