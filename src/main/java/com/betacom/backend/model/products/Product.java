package com.betacom.backend.model.products;

import com.betacom.backend.request.products.ProductRequest;

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
//aggiungrtr dal dto
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String brand;

	@Column(nullable = false)
	private String model;

	//aggiungere campo type 
	@Column(nullable=true)
	private String type;
	
	@Column(nullable = false)
	private Integer stock;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private String imageUrl = "https://i.ibb.co/dJkZ9BRK/products.jpg";

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

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


	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public void addStock(Integer qnt) {
		this.stock += qnt;
	}

	public Boolean removeStock(Integer qnt) {
		if (this.stock - qnt < 0) {
			return false;
		}
		this.stock -= qnt;
		return true;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Product() {
	}

	public Product(Long id) {
		super();
		this.id = id;
	}

	public Product(Long id, String brand, String model,String type, Integer stock, Double price, String imageUrl) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.type=type;
		this.stock = stock;
		this.price = price;
		this.imageUrl = imageUrl;

	}

	public Product(String brand, String model,String type, Integer stock , Double price, String imageUrl) {
		super();
		this.brand = brand;
		this.model = model;
		this.stock = stock;
		this.type=type;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Product(ProductRequest req) {
		this.brand = req.getBrand();
		this.model = req.getModel();
		this.stock = req.getStock();
		this.price = req.getPrice();
		this.imageUrl = req.getImageUrl();
		if (req.getId() != null)
			this.id = req.getId();

	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", brand=" + brand + ", model=" + model  + ", stock=" + stock
				+ ", price=" + price + ", imageUrl=" + imageUrl + "]";
	}

	

}
