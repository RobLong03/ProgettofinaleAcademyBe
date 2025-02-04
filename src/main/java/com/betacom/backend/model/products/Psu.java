package com.betacom.backend.model.products;

import com.betacom.backend.request.products.PsuRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="psu")
public class Psu extends Product {



	@Column(nullable = false)
	private Integer watt;

	public Integer getWatt() {
		return watt;
	}

	public void setWatt(Integer watt) {
		this.watt = watt;
	}


	public Psu() {
		super();
	}
	
	//with id 
	public Psu(Long id, String brand, String model, String description, Integer stock,Double price, Integer watt) {
		super(id, brand, model, description, stock,price);
		this.watt = watt;
	}
	
	//without id 
	public Psu(String brand, String model, String description, Integer stock,Double price,Integer watt) {
		super(brand, model, description, stock,price);
		this.watt = watt;
		
	}
	
	

	//from request
	public Psu(PsuRequest req) {
		super(req);
		this.watt=req.getWatt();
	}

	
}
