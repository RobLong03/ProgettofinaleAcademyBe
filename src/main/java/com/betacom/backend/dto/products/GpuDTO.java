package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Gpu;

public class GpuDTO extends ProductDTO {

	private Integer vram;
	private Double ghz;

	public Integer getVram() {
		return vram;
	}

	public void setVram(Integer vram) {
		this.vram = vram;
	}

	public GpuDTO() {
		super();
	}

	public GpuDTO(String brand, String model, String description, Integer stock,Double price, Integer vram,Double ghz,String imageUrl) {
		super(brand, model, description, stock, price, imageUrl);
		this.vram = vram;
		this.ghz=ghz;
	}

	public GpuDTO(Long id, String brand, String model, String description, Integer stock,Double price, Integer vram,Double ghz,String imageUrl) {
		super(id, brand, model, description, stock, price, imageUrl);
		this.vram = vram;
		this.ghz=ghz;
	}

	public GpuDTO(Gpu gpu) {
		super(gpu);
		this.vram = gpu.getVram();
		this.ghz=gpu.getGhz();
	}
	
	@Override
	public String toString() {
		return "GpuDTO {vram=" + vram + "}"+super.toString();
	}

	public Double getGhz() {
		return ghz;
	}

	public void setGhz(Double ghz) {
		this.ghz = ghz;
	}

}
