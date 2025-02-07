package com.betacom.backend.dto.customer;

import com.betacom.backend.model.customer.Address;

public class AddressDTO {

	
	    private Long id;

	    //mettere l' id addresss per il customer 
	    private Long customerID;

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


		public Long getCustomerID() {
			return customerID;
		}

		public void setCustomerID(Long customerID) {
			this.customerID = customerID;
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
	    
	    
		public AddressDTO(Long id, Long customerID, String country, String city, String postalCode, String street,
				Integer houseNumber) {
			super();
			this.id = id;
			this.customerID = customerID;
			this.country = country;
			this.city = city;
			this.postalCode = postalCode;
			this.street = street;
			this.houseNumber = houseNumber;
		}

		public AddressDTO(Long customerID, String country, String city, String postalCode, String street,
				Integer houseNumber) {
			super();
			this.customerID = customerID;
			this.country = country;
			this.city = city;
			this.postalCode = postalCode;
			this.street = street;
			this.houseNumber = houseNumber;
		}
		
		
		   public AddressDTO(Address req) {
				this.id=req.getId();
				this.customerID=req.getCustomer().getId();
				this.country=req.getCountry();
				this.city=req.getCity();
				this.postalCode=req.getPostalCode();
				this.street=req.getStreet();
				this.houseNumber=req.getHouseNumber();
			}

		@Override
		public String toString() {
			return "AddressDTO {id=" + id + ", customerID=" + customerID + ", country=" + country + ", city=" + city
					+ ", postalCode=" + postalCode + ", street=" + street + ", houseNumber=" + houseNumber + "}";
		}


		

		

}
