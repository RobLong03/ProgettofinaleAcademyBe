package com.betacom.backend.request.products;



public class PsuRequest extends ProductRequest{

	


	private Integer watt;

	
	public Integer getWatt() {
		return watt;
	}

	public void setWatt(Integer watt) {
		this.watt = watt;
	}


	
	public PsuRequest() {
		super();
	}

	public PsuRequest(String brand, String model, String description,Double price, Integer stock,Integer watt, String imageUrl) {
		super(brand, model, stock,price, imageUrl);
		this.watt = watt;
	}
	public PsuRequest(Long id, String brand, String model, String description,Double price, Integer stock ,Integer watt, String imageUrl) {
		super(id,brand,model,stock , price, imageUrl);
		this.watt = watt;
	}

	@Override
	public String toString() {
		return "PsuRequest {watt=" + watt+ "}"+ super.toString() ;
	}

	
	
}
