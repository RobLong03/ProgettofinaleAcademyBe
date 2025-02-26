package com.betacom.backend.request.products;

public class StorageRequest extends ProductRequest{

	private String stype;
	private Integer size;
	
	public StorageRequest(Long id, String brand, String model, String description, Integer stock,Double price, String type, Integer size,String imageUrl) {
		super(id, brand, model, stock, price, imageUrl);
		this.stype = type;
		this.size = size;
	}
	public StorageRequest(String brand, String model, String description, Integer stock,Double price, String type, Integer size,String imageUrl) {
		super(brand, model, stock,price, imageUrl);
		this.stype = type;
		this.size = size;
	}
	public StorageRequest() {
		super();
	}
	
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "StorageRequest [stype=" + stype + ", size=" + size + "]";
	}
	
}
