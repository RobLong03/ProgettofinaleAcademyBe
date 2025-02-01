package com.betacom.backend.request;

public class CustomerRequest {
	
	private Long id;
	
    private String name;

    private String surname;

    private String taxId;
  
    private String email;

    private String password;
  
   
    //tolto il campo address perchè si pensa poi di aggiungerlo
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



	public CustomerRequest() {
		super();
	}
	public CustomerRequest(Long id, String name, String surname, String taxId, String email, String password
			) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.password = password;
	}

	public CustomerRequest(String name, String surname, String taxId, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.taxId = taxId;
		this.email = email;
		this.password = password;
	
	}

	
	
	
}
