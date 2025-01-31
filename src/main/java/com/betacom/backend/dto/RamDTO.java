package com.betacom.backend.dto;

import com.betacom.backend.model.Product;
import com.betacom.backend.model.Ram;

public class RamDTO extends ProductDTO{

	private Integer mhz;
	private Integer size;
	
	public RamDTO() {
		super();
	}

	public RamDTO(Long id, String brand, String model, String description, Integer stock, Integer mhz, Integer size) {
		super(id, brand, model, description, stock);
		this.mhz = mhz;
		this.size = size;
	}
	
	public RamDTO(String brand, String model, String description, Integer stock, Integer mhz, Integer size) {
		super(brand, model, description, stock);
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
