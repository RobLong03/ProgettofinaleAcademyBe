package com.betacom.backend.model;

import com.betacom.backend.request.ProductRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String brand;

	@Column(nullable = false)
	private String model;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Integer stock;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Product() {
	}
	
	public Product(Long id ,String brand, String model, String description, Integer stock) {
		super();
		this.id=id;
		this.brand = brand;
		this.model = model;
		this.description = description;
		this.stock = stock;
	}
	public Product(String brand, String model, String description, Integer stock) {
		super();
		this.brand = brand;
		this.model = model;
		this.description = description;
		this.stock = stock;
	}

	public Product(ProductRequest req) {
		this.brand = req.getBrand();
		this.model = req.getModel();
		this.description = req.getDescription();
		this.stock = req.getStock();
		if (this.getId() != null)
			this.id = req.getId();

	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", brand='" + brand + ", model='" + model  + ", description='"
				+ description  + ", stock=" + stock + '}';
	}

}
