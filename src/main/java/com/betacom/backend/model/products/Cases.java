package com.betacom.backend.model.products;

import com.betacom.backend.request.products.CaseRequest;
import com.betacom.backend.utils.CaseSize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cases")
public class Cases extends Product {

	@Column(nullable = false)
	private CaseSize size;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Color color;

	public Cases(Long id, String brand, String model, String description, Integer stock,Double price, CaseSize size, Color color) {
		super(id, brand, model, description, stock,price);
		this.size = size;
		this.color = color;
	}

	public Cases(String brand, String model, String description, Integer stock,Double price, CaseSize size, Color color) {
		super(brand, model, description, stock,price);
		this.size = size;
		this.color = color;
	}
	
	public Cases(CaseRequest req) {
		super(req);
		this.size = CaseSize.valueOf(req.getsize().toUpperCase());
		this.color = new Color(req.getColor());
	}

	public Cases() {
		super();
	}

	public CaseSize getSize() {
		return size;
	}

	public void setSize(CaseSize size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Case [size=" + size + ", Color=" + color + "]";
	}
}
