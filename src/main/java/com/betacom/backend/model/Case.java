package com.betacom.backend.model;

import com.betacom.backend.request.CaseRequest;
import com.betacom.backend.utils.CaseSize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "case")
public class Case extends Product{

	@Column(nullable = false)
	private CaseSize size;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Color color;

	public Case(Long id, String brand, String model, String description, Integer stock,Double price, CaseSize size, Color color) {
		super(id, brand, model, description, stock,price);
		this.size = size;
		this.color = color;
	}

	public Case(String brand, String model, String description, Integer stock,Double price, CaseSize size, Color color) {
		super(brand, model, description, stock,price);
		this.size = size;
		this.color = color;
	}
	
	public Case(CaseRequest req) {
		super(req);
		this.size = CaseSize.valueOf(req.getsize().toUpperCase());
		this.color = new Color(req.getColor());
	}

	public Case() {
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
