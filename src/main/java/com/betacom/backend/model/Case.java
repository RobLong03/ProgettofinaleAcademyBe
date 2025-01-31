package com.betacom.backend.model;

import com.betacom.backend.request.CaseRequest;
import com.betacom.backend.utils.CaseSize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "case")
public class Case {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private CaseSize size;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	private Color Color;

	public Case(Long id, CaseSize size, Color color) {
		super();
		this.id = id;
		this.size = size;
		Color = color;
	}

	public Case(CaseSize size, Color color) {
		super();
		this.size = size;
		Color = color;
	}
	
	public Case(CaseRequest req) {
		this.size = CaseSize.valueOf(req.getsize().toUpperCase());
		this.Color = new Color(req.getColor());
		if (this.getId() != null)
			this.id = req.getId();
	}

	public Case() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CaseSize getSize() {
		return size;
	}

	public void setSize(CaseSize size) {
		this.size = size;
	}

	public Color getColor() {
		return Color;
	}

	public void setColor(Color color) {
		Color = color;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", size=" + size + ", Color=" + Color + "]";
	}
}
