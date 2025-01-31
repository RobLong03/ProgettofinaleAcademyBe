package com.betacom.backend.model;

import com.betacom.backend.request.MotherboardRequest;
import com.betacom.backend.request.ProductRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="motherboard")
public class Motherboard extends Product {

	@Column(nullable=false)
	private String cpuCompatibility; // cpu compatibility whit brend

	public Motherboard() {
		super();
	}
	
	public Motherboard(Long id, String brand, String model, String description, Integer stock, String cpuCompatibility) {
		super(id, brand, model, description, stock);
		this.cpuCompatibility = cpuCompatibility;
	}

	public Motherboard(String brand, String model, String description, Integer stock, String cpuCompatibility) {
		super(brand, model, description, stock);
		this.cpuCompatibility = cpuCompatibility;
	}
	
	public Motherboard(MotherboardRequest req) {
		super((ProductRequest)req);
		this.cpuCompatibility = req.getCpuCompatibility();
	}


	public String getCpuCompatibility() {
		return cpuCompatibility;
	}

	public void setCpuCompatibility(String cpuCompatibility) {
		this.cpuCompatibility = cpuCompatibility;
	}

	@Override
	public String toString() {
		return super.toString()+" Motherboard [cpuCompatibility=" + cpuCompatibility + "]";
	}
}
