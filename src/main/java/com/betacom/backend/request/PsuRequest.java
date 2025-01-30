package com.betacom.backend.request;



public class PsuRequest extends ProductRequest{

	
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


	
	public PsuRequest() {
		super();
	}

	public PsuRequest(Integer watt) {
		super();
		this.watt = watt;
	}
	public PsuRequest(Long id ,Integer watt) {
		super();
		this.watt = watt;
	}

	@Override
	public String toString() {
		return "PsuRequest {id=" + id + ", watt=" + watt + "}";
	}

	
}
