package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Cases;

public class CaseDTO extends ProductDTO{

	private String size;
	private ColorDTO color;
	
	public CaseDTO(Long id, String brand, String model, String description, Integer stock,Double price, String size, ColorDTO color) {
		super(id, brand, model, description, stock, price);
		this.size = size;
		this.color = color;
	}

	public CaseDTO(String size, ColorDTO color) {
		super();
		this.size = size;
		this.color = color;
	}

	public CaseDTO() {
		super();
	}
	
	public CaseDTO(Cases caseObj) {
		this.size = caseObj.getSize().toString();
		this.color = new ColorDTO(caseObj.getColor().getId(), caseObj.getColor().getColor());
	}

	public String getsize() {
		return size;
	}

	public void setsize(String size) {
		this.size = size;
	}

	public ColorDTO getColor() {
		return color;
	}

	public void setColor(ColorDTO color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CaseDTO [size=" + size + ", color=" + color + "]";
	}
}
