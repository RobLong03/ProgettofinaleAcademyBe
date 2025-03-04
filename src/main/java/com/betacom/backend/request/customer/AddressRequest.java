package com.betacom.backend.request.customer;


public class AddressRequest {

    private Long id;

    private Long customerID;
    
    
    private String country;

    private String city;

    private String postalCode;

    private String street;

    private Integer houseNumber;

    //copre anche questa casistica , perchè in caso contrario da l' errore nel Address
    //perche volevo far 
   
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

	public AddressRequest() {
		super();
	}

	public AddressRequest(Long id, Long customerID, String country, String city, String postalCode,
			String street, Integer houseNumber) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	public AddressRequest(Long customerID, String country, String city, String postalCode, String street,
			Integer houseNumber) {
		super();
		this.customerID = customerID;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	
	
}
