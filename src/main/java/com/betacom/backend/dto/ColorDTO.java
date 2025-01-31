package com.betacom.backend.dto;

import com.betacom.backend.model.Color;

public class ColorDTO {

	private long id;
	private String color;
	
	public ColorDTO(long id, String color) {
		super();
		this.id = id;
		this.color = color;
	}

	public ColorDTO(String color) {
		super();
		this.color = color;
	}

	public ColorDTO() {
		super();
	}
	
	public ColorDTO(Color color) {
		super();
		this.id = color.getId();
		this.color = color.getColor();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColore() {
		return color;
	}

	public void setColore(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ColorDTO [id=" + id + ", color=" + color + "]";
	}
}
