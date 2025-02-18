package com.betacom.backend.request.products;

public class CaseRequest extends ProductRequest{

	private String size;
	private String color;
	
	public CaseRequest(String brand, String model, String description, Integer stock,Double price, String size, String color,String imageUrl) {
		super(brand, model, description, stock,price, imageUrl);
		this.size = size;
		this.color = color;
	}

	public CaseRequest(Long id, String brand, String model, String description, Integer stock,Double price,String size, String color,String imageUrl) {
		super(id, brand, model, description, stock, price,imageUrl);
		this.size = size;
		this.color = color;
	}

	public CaseRequest() {
		super();
	}

	public String getsize() {
		return size;
	}

	public void setsize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CaseRequest [size=" + size + ", color=" + color + "]";
	}
}
