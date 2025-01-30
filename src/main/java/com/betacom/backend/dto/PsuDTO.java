package com.betacom.backend.dto;

import com.betacom.backend.model.Psu;

public class PsuDTO extends ProductDTO{

	

	private Long id;

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

	

	public PsuDTO() {
		super();
	}

	public PsuDTO(Integer watt) {
		super();
		this.watt = watt;
	
	}
	
	public PsuDTO(Long id, Integer watt) {
		super();
		this.id = id;
		this.watt = watt;
		
	}
	
	public PsuDTO(Psu req) {
		super(req);
		this.id = req.getId();
		this.watt = req.getWatt();

	}

}
