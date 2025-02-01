package com.betacom.backend.model;

import com.betacom.backend.request.StorageRequest;
import com.betacom.backend.utils.StorageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "storage")
public class Storage extends Product{
	
	@Column(nullable = false)
	private StorageType type;
	
	@Column(nullable = false)
	private Integer size;

	public Storage(Long id, String brand, String model, String description, Integer stock,Double price, StorageType type, Integer size) {
		super(id, brand, model, description, stock,price);
		this.type = type;
		this.size = size;
	}

	public Storage(String brand, String model, String description, Integer stock,Double price,StorageType type, Integer size) {
		super(brand, model, description, stock,price);
		this.type = type;
		this.size = size;
	}

	public Storage() {
		super();
	}
	
	public Storage(StorageRequest req) {
		super(req);
		this.type = StorageType.valueOf(req.getType());
		this.size = req.getSize();
	}

	public StorageType getType() {
		return type;
	}

	public void setType(StorageType type) {
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
		return "Storage [type=" + type + ", size=" + size + "]";
	}
}
