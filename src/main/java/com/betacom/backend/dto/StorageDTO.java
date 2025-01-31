package com.betacom.backend.dto;

import com.betacom.backend.model.Storage;

public class StorageDTO {

	private Long id;
	private String type;
	private Integer size;
	
	public StorageDTO(Long id, String type, Integer size) {
		super();
		this.id = id;
		this.type = type;
		this.size = size;
	}
	public StorageDTO(String type, Integer size) {
		super();
		this.type = type;
		this.size = size;
	}
	public StorageDTO() {
		super();
	}
	public StorageDTO(Storage storage) {
		super();
		this.id = storage.getId();
		this.type = storage.getType().toString();
		this.size = storage.getSize();
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
		return "StorageDTO [id=" + id + ", type=" + type + ", size=" + size + "]";
	}
}
