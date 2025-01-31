package com.betacom.backend.request;

public class MotherboardRequest extends ProductRequest {

	private String cpuCompatibility;
	
	public MotherboardRequest() {
		super();
	}
	
	public MotherboardRequest(Long id, String brand, String model, String description, Integer stock, String cpuCompatibility) {
		super(id, brand, model, description, stock);
		this.cpuCompatibility = cpuCompatibility;
	}

	public MotherboardRequest(String brand, String model, String description, Integer stock, String cpuCompatibility) {
		super(brand, model, description, stock);
		this.cpuCompatibility = cpuCompatibility;
	}

	public String getCpuCompatibility() {
		return cpuCompatibility;
	}

	public void setCpuCompatibility(String cpuCompatibility) {
		this.cpuCompatibility = cpuCompatibility;
	}

	@Override
	public String toString() {
		return super.toString()+" MotherBoardRequest [cpuCompatibility=" + cpuCompatibility + "]";
	}
}
