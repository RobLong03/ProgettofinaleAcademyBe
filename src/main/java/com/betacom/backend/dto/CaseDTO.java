package com.betacom.backend.dto;

import com.betacom.backend.model.Case;

public class CaseDTO {

	private Long id;
	private String size;
	private ColorDTO color;
	
	public CaseDTO(Long id, String size, ColorDTO color) {
		super();
		this.id = id;
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
	
	public CaseDTO(Case caseObj) {
		this.id = caseObj.getId();
		this.size = caseObj.getSize().toString();
		this.color = new ColorDTO(caseObj.getColor().getId(), caseObj.getColor().getColor());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "CaseDTO [id=" + id + ", size=" + size + ", color=" + color + "]";
	}
}
