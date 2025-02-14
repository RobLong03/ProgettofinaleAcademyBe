package com.betacom.backend.dto.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.dto.cart.CartItemDTO;
import com.betacom.backend.model.customer.Customer;

public class CustomerDTO {

	
	private Long id;
	
    private String name;

    private String surname;

    private String taxId;
  
    private String email;
    
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

	public List<AddressDTO> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}


	public CustomerDTO() {
		super();
	}


	public CustomerDTO(Long id, String name, String surname, String taxId, String email,
			List<AddressDTO> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.addresses = addresses;
	}


	public CustomerDTO(String name, String surname, String taxId, String email,
			List<AddressDTO> addresses) {
		super();
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.addresses = addresses;
	}
	

	public CustomerDTO(Customer req) {
		this.id=req.getId();
		this.name = req.getName();
		this.surname = req.getSurname();
		this.taxId = req.getTaxId();
		this.email=req.getEmail();
		
		try {
			if (req.getAddresses()!=null) {
				this.addresses = req.getAddresses()
					    .stream()
					    .map(x -> new AddressDTO(
					    	    x.getId(),
					    	    x.getCustomer() != null ? x.getCustomer().getId() : null,
					    	    x.getCountry(),
					    	    x.getCity(),
					    	    x.getPostalCode(),
					    	    x.getStreet(),
					    	    x.getHouseNumber()
					    	)).collect(Collectors.toList());
			}
		} catch (Exception e) {
			this.addresses=new ArrayList<AddressDTO>();
		}
		
		//può uscire un errore perchè puo uscire nullo il valore altrimenti va in null pointer exception
		



/*	this.id = id;
			this.customerID = customerID;
			this.country = country;
			this.city = city;
			this.postalCode = postalCode;
			this.street = street;
			this.houseNumber = houseNumber;*/
	}


	@Override
	public String toString() {
		return "CustomerDTO {id=" + id + ", name=" + name + ", surname=" + surname + ", taxId=" + taxId + ", email="
				+ email  + ", addresses=" + addresses + "}";
	}

	
	
}
