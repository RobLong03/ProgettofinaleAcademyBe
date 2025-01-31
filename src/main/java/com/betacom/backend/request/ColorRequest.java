package com.betacom.backend.request;

public class ColorRequest {

	private Long id;
	private String color;
	
	public ColorRequest(Long id, String color) {
		super();
		this.id = id;
		this.color = color;
	}
	public ColorRequest(String color) {
		super();
		this.color = color;
	}
	public ColorRequest() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "ColorRequest [id=" + id + ", color=" + color + "]";
	}
	
}
