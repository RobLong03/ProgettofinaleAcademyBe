package com.betacom.backend.dto.products;

public class ProductDescriptionDTO {

	private Long id;
	private String lang;
	private Long  id_prodotto;
	private String description;
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
	public Long getId_prodotto() {
		return id_prodotto;
	}
	public void setId_prodotto(Long id_prodotto) {
		this.id_prodotto = id_prodotto;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductDescriptionDTO() {
		super();
	}
	public ProductDescriptionDTO(Long id, String lang, Long id_prodotto,String description) {
		super();
		this.id = id;
		this.lang = lang;
		this.id_prodotto = id_prodotto;
		this.description = description;
	}
	@Override
	public String toString() {
		return "ProductDescriptionDTO [id=" + id + ", lang=" + lang + ", id_prodotto=" + id_prodotto + ", description="
				+ description + "]";
	}
	
	
}
