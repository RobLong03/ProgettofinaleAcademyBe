package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Product;
import static com.betacom.backend.utils.TypeConverter.whichSubClassIs;

public class ProductDTO { //ciao belliiiiiiii

	

    private Long id;

    private String brand;

    private String model;
    
   //da aggiungere product
    private ProductDescriptionDTO description;
    
    
    private String type;

    private Integer stock;

    private Double price;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

	public ProductDescriptionDTO getDescription() {
		return description;
	}

	public void setDescription(ProductDescriptionDTO description) {
		this.description = description;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    public ProductDTO() {
    }

    public ProductDTO(String brand, String model, ProductDescriptionDTO description,String type, Integer stock,Double price,String imageUrl) {
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.type=type;
        this.stock = stock;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDTO(Long id,String brand, String model, ProductDescriptionDTO description,String type, Integer stock,Double price,String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.type=type;
        //commento
        this.stock = stock;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDTO(Product product) {
    	
        this.id = product.getId();
        this.brand = product.getBrand();
        this.model = product.getModel();
        this.type=whichSubClassIs(product);
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", brand=" + brand + ", model=" + model + ", description=" + description
				+ ", type=" + type + ", stock=" + stock + ", price=" + price + ", imageUrl=" + imageUrl + "]";
	}


	


}
