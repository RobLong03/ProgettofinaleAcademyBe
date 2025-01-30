package com.betacom.backend.dto;

import com.betacom.backend.model.Product;


public class ProductDTO { //ciao belliiiiiiii



    private Long id;

    private String brand;

    private String model;

    private String description;

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
    
    
    public ProductDTO() {
    }

    public ProductDTO(String brand, String model, String description, Integer stock) {
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.stock = stock;
    }

    public ProductDTO(Long id,String brand, String model, String description, Integer stock) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        //commento
        this.stock = stock;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.brand = product.getBrand();
        this.model = product.getModel();
        this.description = product.getDescription();
        this.stock = product.getStock();
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", marca='" + brand + '\'' +
                ", modello='" + model + '\'' +
                ", descrizione='" + description + '\'' +
                ", quantita=" + stock +
                '}';
    }

}
