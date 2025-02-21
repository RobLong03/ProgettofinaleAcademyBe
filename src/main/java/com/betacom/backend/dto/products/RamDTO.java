package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Product;
import com.betacom.backend.model.products.Ram;

public class RamDTO extends ProductDTO{

	private Integer mhz;
	private Integer size;
	
	public RamDTO() {
		super();
	}

	public RamDTO(Long id, String brand, String model, ProductDescriptionDTO description,String type,Integer stock,Double price, Integer mhz, Integer size,String imageUrl) {
		super(id, brand, model, description,type, stock, price, imageUrl);
		this.mhz = mhz;
		this.size = size;
	}
	
	public RamDTO(String brand, String model, ProductDescriptionDTO description,String type ,Integer stock,Double price, Integer mhz, Integer size, String imageUrl) {
		super( brand, model, description,type, stock, price, imageUrl);
		this.mhz = mhz;
		this.size = size;
	}
	
	public RamDTO(Ram ram) {
		super((Product)ram);
		this.mhz=ram.getMhz();
		this.size=ram.getSize();
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
		return super.toString()+ "RamDTO [mhz=" + mhz + ", size=" + size + "]";
	}
}
