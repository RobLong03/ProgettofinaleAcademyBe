package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Storage;

public class StorageDTO extends ProductDTO{

	private String type;
	private Integer size;
	
	public StorageDTO(Long id, String brand, String model, String description, Integer stock,Double price, String type, Integer size,String imageUrl) {
		super(id, brand, model, description, stock, price, imageUrl);
		this.type = type;
		this.size = size;
	}
	public StorageDTO(String type, Integer size) {
		super();
		this.type = type;
		this.size = size;
	}
	public StorageDTO() {
		super();
	}
	public StorageDTO(Storage storage) {
		super(storage);
		this.type = storage.getType().toString();
		this.size = storage.getSize();
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
		return "StorageDTO [type=" + type + ", size=" + size + "]";
	}
}
