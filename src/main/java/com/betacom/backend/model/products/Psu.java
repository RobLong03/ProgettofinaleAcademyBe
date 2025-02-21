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
	public Psu(Long id, String brand, String model,String type, Integer stock,Double price, Integer watt,String imageUrl) {
		super(id, brand, model,"Psu",stock,price, imageUrl);
	
		this.watt = watt;
	}
	
	//without id 
	public Psu(String brand, String model,String type, Integer stock,Double price,Integer watt, String imageUrl) {
		super( brand, model,"Psu",stock,price, imageUrl);
		this.watt = watt;
		
	}
	
	

	//from request
	public Psu(PsuRequest req) {
		super(req);
		super.setType("Psu");
		this.watt=req.getWatt();
	}

	
}
