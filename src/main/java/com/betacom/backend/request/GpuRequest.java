package com.betacom.backend.request;

public class GpuRequest extends ProductRequest{


	private Integer vram;
	private Double ghz;

	public GpuRequest() {
		super();
	}
	public GpuRequest(String brand, String model, String description, Integer stock, Double price, Integer vram,
			Double ghz) {
		super(brand, model, description, stock, price);
		this.vram = vram;
		this.ghz = ghz;
	}

	public GpuRequest(Long id, String brand, String model, String description, Integer stock, Double price,
			Integer vram, Double ghz) {
		super(id, brand, model, description, stock, price);
		this.vram = vram;
		this.ghz = ghz;
	}
	public Integer getVram() {
		return vram;
	}
	public void setVram(Integer vram) {
		this.vram = vram;
	}
	public Double getGhz() {
		return ghz;
	}
	public void setGhz(Double ghz) {
		this.ghz = ghz;
	}
	
	@Override
	public String toString() {
		return "GpuRequest {"+"vram=" + vram + ", ghz=" + ghz + "}"+super.toString();
	}
	


}
