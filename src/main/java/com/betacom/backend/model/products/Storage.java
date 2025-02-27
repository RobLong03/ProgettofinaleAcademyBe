package com.betacom.backend.model.products;

import com.betacom.backend.request.products.StorageRequest;
import com.betacom.backend.utils.StorageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "storage")
public class Storage extends Product {
	
	@Column(nullable = false)
	private StorageType stype;
	
	@Column(nullable = false)
	private Integer size;

	public Storage(Long id, String brand, String model,String type, Integer stock,Double price, StorageType stype, Integer size, String imageUrl) {
		super(id, brand, model,"Storage",stock,price, imageUrl);
		this.stype = stype;
		this.size = size;
	}

	public Storage(String brand, String model,String type, Integer stock,Double price,StorageType stype, Integer size, String imageUrl) {
		super( brand, model,"Storage",stock,price, imageUrl);
		this.stype = stype;
		this.size = size;
	}

	public Storage() {
		super();
	}
	
	public Storage(StorageRequest req) {
		super(req);
		
		
		super.setType(StorageType.valueOf(req.getStype()).toString());
		this.stype = StorageType.valueOf(req.getStype());
		this.size = req.getSize();
	}



	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	
	@Override
	public String toString() {
		return "Storage [stype=" + stype + ", size=" + size + "]";
	}

	public StorageType getStype() {
		return stype;
	}

	public void setStype(StorageType stype) {
		this.stype = stype;
	}

}
