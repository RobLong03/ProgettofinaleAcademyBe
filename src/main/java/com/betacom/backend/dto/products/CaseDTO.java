package com.betacom.backend.dto.products;

import com.betacom.backend.model.products.Cases;
import static com.betacom.backend.utils.TypeConverter.whichSubClassIs;
public class CaseDTO extends ProductDTO{

	//da cambiare facendo visualizzare solo il type non andando a modificare il model dto e 
	private String size;
	private ColorDTO color;
	
	public CaseDTO(Long id, String brand, String model, ProductDescriptionDTO description,
			String type, Integer stock,Double price, String size, ColorDTO color, String imageUrl) {
		super(id, brand, model, description,"case", stock, price, imageUrl);
		this.size = size;
		this.color = color;
	}

	public CaseDTO(String brand, String model, ProductDescriptionDTO description,String type
			, Integer stock,Double price, String size, ColorDTO color, String imageUrl) {
		super(brand, model, description,"case", stock, price, imageUrl);
		this.size = size;
		this.color = color;
	}

	public CaseDTO() {
		super();
	}
	
	public CaseDTO(Cases caseObj) {
		super(caseObj);
		super.setType(whichSubClassIs(caseObj));
		this.size = caseObj.getSize().toString();
		this.color = new ColorDTO(caseObj.getColor().getId(), caseObj.getColor().getColor());
	}

	public String getsize() {
		return size;
	}

	public void setsize(String size) {
		this.size = size;
	}

	public ColorDTO getColor() {
		return color;
	}

	public void setColor(ColorDTO color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CaseDTO [size=" + size + ", color=" + color + "]";
	}
}
