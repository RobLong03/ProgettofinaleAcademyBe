package com.betacom.backend.request;

public class StorageRequest {

	private Long id;
	private String type;
	private Integer size;
	
	public StorageRequest(Long id, String type, Integer size) {
		super();
		this.id = id;
		this.type = type;
		this.size = size;
	}
	public StorageRequest(String type, Integer size) {
		super();
		this.type = type;
		this.size = size;
	}
	public StorageRequest() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "StorageRequest [id=" + id + ", type=" + type + ", size=" + size + "]";
	}
}
