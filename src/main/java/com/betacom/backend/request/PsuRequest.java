package com.betacom.backend.request;



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

	public PsuRequest(String brand, String model, String description,Double price, Integer stock,Integer watt) {
		super(brand, model, description, stock,price);
		this.watt = watt;
	}
	public PsuRequest(Long id, String brand, String model, String description,Double price, Integer stock ,Integer watt) {
		super(id,brand,model,description,stock , price);
		this.watt = watt;
	}

	@Override
	public String toString() {
		return "PsuRequest {watt=" + watt+ "}"+ super.toString() ;
	}

	
	
}
