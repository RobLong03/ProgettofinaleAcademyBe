package com.betacom.backend.model.products;

import com.betacom.backend.request.products.ProductRequest;
import com.betacom.backend.request.products.RamRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="ram")
public class Ram extends Product {

	@Column(nullable=false)
	private Integer mhz;
	
	@Column(nullable=false)
	private Integer size;

	public Ram() {
		super();
	}
	//devo pensare meglio sulla 

	public Ram(Long id, String brand, String model,String type, Integer stock,Double price, Integer mhz, Integer size, String imageUrl) {
		super(id, brand, model,"Ram",stock,price, imageUrl);
		this.mhz = mhz;
		this.size = size;
	}
	
	public Ram(String brand, String model,String type, Integer stock, Integer mhz,Double price, Integer size, String imageUrl) {
		super(brand, model,"Ram",stock,price, imageUrl);
		this.mhz = mhz;
		this.size = size;
	}
	
	public Ram(RamRequest req) {
		super((ProductRequest)req);
		super.setType("Ram");
		this.mhz=req.getMhz();
		this.size=req.getSize();
	}

	public Integer getMhz() {
		return mhz;
	}

	public void setMhz(Integer mhz) {
		this.mhz = mhz;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return super.toString()+" Ram [mhz=" + mhz + ", size=" + size + "]";
	}
}
