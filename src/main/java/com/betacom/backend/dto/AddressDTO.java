package com.betacom.backend.dto;

import com.betacom.backend.model.Address;

public class AddressDTO {

	
	    private Long id;

	    private CustomerDTO customer;

	    private String country;

	    private String city;

	    private String postalCode;

	    private String street;

	    private Integer houseNumber;

	    

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public CustomerDTO getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerDTO customer) {
			this.customer = customer;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public Integer getHouseNumber() {
			return houseNumber;
		}

		public void setHouseNumber(Integer houseNumber) {
			this.houseNumber = houseNumber;
		}

	    
	    public AddressDTO() {
			super();
		}
	    
		public AddressDTO(Long id, CustomerDTO customer, String country, String city, String postalCode, String street,
				Integer houseNumber) {
			super();
			this.id = id;
			this.customer = customer;
			this.country = country;
			this.city = city;
			this.postalCode = postalCode;
			this.street = street;
			this.houseNumber = houseNumber;
		}

		public AddressDTO(CustomerDTO customer, String country, String city, String postalCode, String street,
				Integer houseNumber) {
			super();
			this.customer = customer;
			this.country = country;
			this.city = city;
			this.postalCode = postalCode;
			this.street = street;
			this.houseNumber = houseNumber;
		}
		
		
		   public AddressDTO(Address req) {
				this.id=req.getId();
				this.customer=new CustomerDTO(req.getCustomer());
				this.country=req.getCountry();
				this.city=req.getCity();
				this.postalCode=req.getPostalCode();
				this.street=req.getStreet();
				this.houseNumber=req.getHouseNumber();
			}

		@Override
		public String toString() {
			return "AddressDTO {id=" + id + ", customer=" + customer + ", country=" + country + ", city=" + city
					+ ", postalCode=" + postalCode + ", street=" + street + ", houseNumber=" + houseNumber + "}";
		}

		

}
