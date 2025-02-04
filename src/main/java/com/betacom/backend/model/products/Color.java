package com.betacom.backend.model.products;

import com.betacom.backend.request.products.ColorRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "color")
public class Color {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String color;

	public Color(Long id, String color) {
		super();
		this.id = id;
		this.color = color;
	}

	public Color(String color) {
		super();
		this.color = color;
	}
	
	public Color(ColorRequest req) {
		this.color = req.getColor();
		if (this.getId() != null)
			this.id = req.getId();
	}
	
	public Color(Color color) {
		super();
		this.id = color.id;
		this.color = color.color;
	}

	public Color() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
