package com.betacom.backend.request;

public class StorageRequest extends ProductRequest{

	private String type;
	private Integer size;
	
	public StorageRequest(Long id, String brand, String model, String description, Integer stock,Double price, String type, Integer size) {
		super(id, brand, model, description, stock, price);
		this.type = type;
		this.size = size;
	}
	public StorageRequest(String brand, String model, String description, Integer stock,Double price, String type, Integer size) {
		super(brand, model, description, stock,price);
		this.type = type;
		this.size = size;
	}
	public StorageRequest() {
		super();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "StorageRequest [type=" + type + ", size=" + size + "]";
	}
}
