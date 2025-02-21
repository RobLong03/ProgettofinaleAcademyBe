package com.betacom.backend.request.products;

public class ProductDescriptionRequest {
	
	private Long id;
	
	
	private String lang;
	//idprodotto sul json
	private Long  Idprodotto;
	private String description;
	
	public ProductDescriptionRequest() {
		super();
	}
	public ProductDescriptionRequest(Long id, String lang, Long idprodotto, String description) {
		super();
		this.id = id;
		this.lang = lang;
		this.Idprodotto = idprodotto;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getIdprodotto() {
		return Idprodotto;
	}
	public void setIdprodotto(Long idprodotto) {
		this.Idprodotto = idprodotto;
	}
	@Override
	public String toString() {
		return "ProductDescriptionRequest [id=" + id + ", lang=" + lang + ", Idprodotto=" + Idprodotto
				+ ", description=" + description + "]";
	}
	
	
	
}
