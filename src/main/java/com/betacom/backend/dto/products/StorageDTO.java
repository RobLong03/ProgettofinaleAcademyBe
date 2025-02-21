package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Storage;

public class StorageDTO extends ProductDTO{

	private String stype;
	private Integer size;
	
	public StorageDTO(Long id, String brand, String model, ProductDescriptionDTO description,String type, Integer stock,Double price, String stype, Integer size,String imageUrl) {
		super(id, brand, model, description,type, stock, price, imageUrl);
		this.stype = stype;
		this.size = size;
	}
	public StorageDTO(String stype, Integer size) {
		super();
		this.stype = stype;
		this.size = size;
	}
	public StorageDTO() {
		super();
	}
	public StorageDTO(Storage storage) {
		super(storage);
		this.stype = storage.getType().toString();
		this.size = storage.getSize();
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
		return "StorageDTO [stype=" + stype + ", size=" + size + "]";
	}
	
}
