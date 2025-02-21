package com.betacom.backend.model.products;

import jakarta.persistence.Embeddable;

@Embeddable
public class DescriptionID {

	private Integer id;
	private String lang;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	public DescriptionID(Integer id, String lang) {
		super();
		this.id = id;
		this.lang = lang;
	}
	@Override
	public String toString() {
		return "DescriptionID [id=" + id + ", lang=" + lang + "]";
	}

	
}
