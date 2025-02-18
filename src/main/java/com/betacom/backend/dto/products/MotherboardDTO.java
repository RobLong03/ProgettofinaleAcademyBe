package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Motherboard;
import com.betacom.backend.model.products.Product;

public class MotherboardDTO extends ProductDTO {

	private String cpuCompatibility;
	
	public MotherboardDTO() {
		super();
	}

	public MotherboardDTO(Long id, String brand, String model, String description, Integer stock,Double price, String cpuCompatibility,String imageUrl) {
		super(id, brand, model, description, stock, price, imageUrl);
		this.cpuCompatibility = cpuCompatibility;
	}
	
	public MotherboardDTO(String brand, String model, String description, Integer stock,Double price, String cpuCompatibility,String imageUrl) {
		super(brand, model, description, stock,price, imageUrl);
		this.cpuCompatibility = cpuCompatibility;
	}
	
	public MotherboardDTO(Motherboard motherboard) {
		super((Product)motherboard);
		this.cpuCompatibility = motherboard.getCpuCompatibility();
	}

	public String getCpuCompatibility() {
		return cpuCompatibility;
	}

	public void setCpuCompatibility(String cpuCompatibility) {
		this.cpuCompatibility = cpuCompatibility;
	}

	@Override
	public String toString() {
		return super.toString()+" MotherboardDTO [cpuCompatibility=" + cpuCompatibility + "]";
	}
}
