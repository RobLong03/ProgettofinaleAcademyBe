package com.betacom.backend.dto;

import java.util.List;

import com.betacom.backend.model.Customer;

public class CustomerDTO {

	
	private Long id;
	
    private String name;

    private String surname;

    private String taxId;
  
    private String email;

    private String password;
    
    private List<AddressDTO> addresses;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getTaxId() {
		return taxId;
	}


	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<AddressDTO> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}


	public CustomerDTO() {
		super();
	}


	public CustomerDTO(Long id, String name, String surname, String taxId, String email, String password,
			List<AddressDTO> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.password = password;
		this.addresses = addresses;
	}


	public CustomerDTO(String name, String surname, String taxId, String email, String password,
			List<AddressDTO> addresses) {
		super();
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.password = password;
		this.addresses = addresses;
	}
	

	public CustomerDTO(Customer req) {
		this.id=req.getId();
		this.name = req.getName();
		this.surname = req.getSurname();
		this.taxId = req.getTaxId();
		this.password = req.getPassword();
		this.addresses= req.getAddresses().stream()
				.map(x->new AddressDTO(x)).toList();

	}


	@Override
	public String toString() {
		return "CustomerDTO {id=" + id + ", name=" + name + ", surname=" + surname + ", taxId=" + taxId + ", email="
				+ email + ", password=" + password + ", addresses=" + addresses + "}";
	}

	
	
}
