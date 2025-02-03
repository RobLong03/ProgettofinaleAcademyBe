package com.betacom.backend.model;

import com.betacom.backend.request.AddressRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //va a prendere il customer direttamente senza necessità di andare a definirlo
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Column(nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private Integer houseNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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

	public Address(AddressRequest req) {
		this.customer=new Customer();
		this.country=req.getCountry();
		this.city=req.getCity();
		this.postalCode=req.getPostalCode();
		this.street=req.getStreet();
		this.houseNumber=req.getHouseNumber();
		if (this.getId() != null)
			this.id = req.getId();

	}

	public Address() {
		super();
	}
	public Address(Long id, Customer customer, String country, String city, String postalCode, String street,
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

	public Address(Customer customer, String country, String city, String postalCode, String street,
			Integer houseNumber) {
		super();
		this.customer = customer;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
	}

	
	
	@Override
	public String toString() {
		return "Address {id=" + id + ", customer=" + customer + ", country=" + country + ", city=" + city
				+ ", postalCode=" + postalCode + ", street=" + street + ", houseNumber=" + houseNumber + "}";
	}

	



}