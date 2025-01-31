package com.betacom.backend.request;

public class RamRequest extends ProductRequest {

	private Integer mhz;
	private Integer size;
	
	public RamRequest() {
		super();
	}

	public RamRequest(Long id, String brand, String model, String description, Integer stock, Integer mhz, Integer size) {
		super(id, brand, model, description, stock);
		this.mhz = mhz;
		this.size = size;
	}
	
	public RamRequest(String brand, String model, String description, Integer stock, Integer mhz, Integer size) {
		super(brand, model, description, stock);
		this.mhz = mhz;
		this.size = size;
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
		return super.toString()+" RamRequest [mhz=" + mhz + ", size=" + size + "]";
	}
}
