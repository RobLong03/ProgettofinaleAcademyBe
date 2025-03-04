package com.betacom.backend.model.products;

import com.betacom.backend.request.products.MotherboardRequest;
import com.betacom.backend.request.products.ProductRequest;

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
	
	public Motherboard(Long id, String brand, String model,String type, Integer stock,Double price, String cpuCompatibility, String imageUrl) {
		super(id, brand, model,"Motherboard",stock,price, imageUrl);
		this.cpuCompatibility = cpuCompatibility;
	}

	public Motherboard(String brand, String model,String type, Integer stock,Double price,  String cpuCompatibility, String imageUrl) {
		super( brand, model,"Motherboard",stock,price, imageUrl);
		this.cpuCompatibility = cpuCompatibility;
	}
	
	public Motherboard(MotherboardRequest req) {
		super((ProductRequest)req);
		super.setType("Motherboard");
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
