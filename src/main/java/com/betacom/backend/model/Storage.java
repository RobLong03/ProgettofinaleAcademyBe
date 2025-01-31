package com.betacom.backend.model;

import com.betacom.backend.request.StorageRequest;
import com.betacom.backend.utils.StorageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "storage")
public class Storage extends Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private StorageType type;
	
	@Column(nullable = false)
	private Integer size;

	public Storage(Long id, StorageType type, Integer size) {
		super();
		this.id = id;
		this.type = type;
		this.size = size;
	}

	public Storage(StorageType type, Integer size) {
		super();
		this.type = type;
		this.size = size;
	}

	public Storage() {
		super();
	}
	
	public Storage(StorageRequest req) {
		this.type = StorageType.valueOf(req.getType());
		this.size = req.getSize();
		if (this.getId() != null)
			this.id = req.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StorageType getType() {
		return type;
	}

	public void setType(StorageType type) {
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
		return "Storage [id=" + id + ", type=" + type + ", size=" + size + "]";
	}
}
