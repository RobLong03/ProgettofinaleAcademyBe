package com.betacom.backend.request.products;

public class ProductRequest {



    private Long id;

    private String brand;

    private String model;

    private String description;

    private Integer stock;

    private Double price;

	private String imageUrl;

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
    

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
    
    public ProductRequest() {
    }

    public ProductRequest(String brand, String model, String description, Integer stock,Double price, String imageUrl) {
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.stock = stock;
        this.price=price;
		this.imageUrl=imageUrl;
    }

    public ProductRequest(Long id,String brand, String model, String description, Integer stock,Double price, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        //commento
        this.stock = stock;
        this.price=price;
		this.imageUrl=imageUrl;
    }

    @Override
    public String toString() {
        return "prodottoRequest{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
				", price=" + price +
				", imageUrl='" + imageUrl + '\'' +
                '}';
    }
//    prodottoRequest

}
