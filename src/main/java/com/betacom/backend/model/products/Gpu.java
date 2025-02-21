package com.betacom.backend.model.products;

import com.betacom.backend.request.products.GpuRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="gpu")
public class Gpu extends Product {
	
	@Column(name="vram")
	private Integer vram;
	

	@Column(name="ghz")
	private Double ghz;


	
	
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


	public Gpu() {
		super();
	}

	public Gpu(Long id, String brand, String model,String type,Integer stock, Double price, Integer vram,
			Double ghz, String imageUrl) {
		super(id, brand, model,"Gpu",stock,price, imageUrl);
		
		this.vram = vram;
		this.ghz = ghz;
	}


	public Gpu(String brand, String model,String type,Integer stock, Double price, Integer vram, Double ghz, String imageUrl) {
		super( brand, model,"Gpu",stock,price, imageUrl);
		this.vram = vram;
		this.ghz = ghz;
	}


	public Gpu(GpuRequest req) {
		super(req);
		super.setType("Gpu");
		this.vram = req.getVram();
		this.ghz = req.getGhz();
	}


	@Override
	public String toString() {
		return super.toString()+"Gpu"+ "{"+", vram=" + vram + ", ghz=" + ghz + "}";
	}




	
	
	
	
	
	
}
