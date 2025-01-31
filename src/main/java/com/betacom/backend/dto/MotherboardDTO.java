package com.betacom.backend.dto;

import com.betacom.backend.model.Motherboard;
import com.betacom.backend.model.Product;

public class MotherboardDTO extends ProductDTO {

	private String cpuCompatibility;
	
	public MotherboardDTO() {
		super();
	}

	public MotherboardDTO(Long id, String brand, String model, String description, Integer stock, String cpuCompatibility) {
		super(id, brand, model, description, stock);
		this.cpuCompatibility = cpuCompatibility;
	}
	
	public MotherboardDTO(String brand, String model, String description, Integer stock, String cpuCompatibility) {
		super(brand, model, description, stock);
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
