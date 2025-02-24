package com.betacom.backend.model.products;

import com.betacom.backend.request.products.ProductDescriptionRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="product-description", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"lang", "product_id"})})
public class ProductDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
    @Column(nullable = false)
	private String lang;
	
	@ManyToOne
	@JoinColumn(name="product_id",nullable = false)
	private Product  product;
	
	@Column(nullable = false,columnDefinition = "VARCHAR(2500)")
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


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDescription(Long id, String lang, Product id_prodotto, String description) {
		super();
		this.id = id;
		this.lang = lang;
		this.product = id_prodotto;
		this.description = description;
	}
	public ProductDescription(ProductDescriptionRequest req) {
		this.id = req.getId();
		this.lang = req.getLang();
		this.product = null;
		this.description = req.getDescription();
	}

	@Override
	public String toString() {
		return "ProductDescription [id=" + id + ", lang=" + lang + ", Id_prodotto=" + product + ", description="
				+ description + "]";
	}

}
