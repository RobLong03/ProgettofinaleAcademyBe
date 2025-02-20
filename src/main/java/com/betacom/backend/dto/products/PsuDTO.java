package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Psu;

public class PsuDTO extends ProductDTO{

	


	private Integer watt;

	
	public Integer getWatt() {
		return watt;
	}

	public void setWatt(Integer watt) {
		this.watt = watt;
	}

	

	public PsuDTO() {
		super();
	}

	public PsuDTO(Long id,String brand, String model, String description, Integer stock,Double price,Integer watt, String imageUrl) {
		super(id, brand, model, description, stock, price, imageUrl);
		this.watt = watt;
	
	}
	
	
	
	public PsuDTO(Psu req) {
		super(req);
		this.watt = req.getWatt();

	}

	@Override
	public String toString() {
		return "PsuDTO {watt=" + watt + "}"+super.toString();
	}

}
