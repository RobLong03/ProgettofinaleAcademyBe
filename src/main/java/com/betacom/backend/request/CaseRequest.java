package com.betacom.backend.request;

public class CaseRequest {

	private Long id;
	private String size;
	private String color;
	
	public CaseRequest(Long id, String size, String color) {
		super();
		this.id = id;
		this.size = size;
		this.color = color;
	}

	public CaseRequest(String size, String color) {
		super();
		this.size = size;
		this.color = color;
	}

	public CaseRequest() {
		super();
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CaseRequest [id=" + id + ", size=" + size + ", color=" + color + "]";
	}
}
