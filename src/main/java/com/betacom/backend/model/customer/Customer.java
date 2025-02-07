package com.betacom.backend.model.customer;

import java.util.List;

import com.betacom.backend.request.customer.CustomerRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "tax_id", nullable = false, length = 16, unique = true)
    private String taxId;

    @Column(nullable = false, unique = true )
    private String email;

    @Column(nullable = false)
    private String password;
    

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
    
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

	


	public Customer() {
		super();
	}

	public Customer(Long id, String name, String surname, String taxId, String email, String password,
			List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.password = password;
		this.addresses = addresses;
	}

	public Customer(String name, String surname, String taxId, String email, String password, List<Address> addresses) {
		super();
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.password = password;
		this.addresses = addresses;
	}

	public Customer(CustomerRequest req) {
		this.name=req.getName();
		this.surname=req.getSurname();
		this.taxId=req.getTaxId();
		this.email=req.getEmail();
		this.password=req.getPassword();
		//da aggiungere poi in seguito per gli indirizzi
		//si aggiunge dopo 
		//da verificare il giro di Addrss per Customer
		if (this.getId() != null)
			this.id = req.getId();

	}
	
	@Override
	public String toString() {
		return "Customer {id=" + id + ", name=" + name + ", surname=" + surname + ", taxId=" + taxId + ", email="
				+ email + ", password=" + password + ", addresses=" + addresses + "}";
	}

    
}
