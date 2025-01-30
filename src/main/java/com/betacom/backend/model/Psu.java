package com.betacom.backend.model;

import com.betacom.backend.request.PsuRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="psu")
public class Psu extends Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer watt;


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWatt() {
		return watt;
	}

	public void setWatt(Integer watt) {
		this.watt = watt;
	}


	public Psu() {
		super();
	}
	
	public Psu(Integer watt) {
		super();
		this.watt = watt;
		
	}
	
	public Psu(Long id, Integer watt) {
		super();
		this.id = id;
		this.watt = watt;
	}



	public Psu(PsuRequest req) {
		super(req);
		this.watt=req.getWatt();
		if (this.getId() != null)
			this.id = req.getId();
	}

	
}
