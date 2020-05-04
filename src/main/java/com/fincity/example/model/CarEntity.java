package com.fincity.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="car_details")
public class CarEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column(name="manufacture_name")
	private String manufactureName;
	private String model;
	@Column(name="manufacture_year")
	private String manufacturingYear;
	private String color;
	private Date created;
	private Date lastupdated;
	public CarEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CarEntity(Long id, String name, String manufactureName, String model, String manufacturingYear, String color,
			Date created, Date lastupdated) {
		super();
		this.id = id;
		this.name = name;
		this.manufactureName = manufactureName;
		this.model = model;
		this.manufacturingYear = manufacturingYear;
		this.color = color;
		this.created = created;
		this.lastupdated = lastupdated;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufactureName() {
		return manufactureName;
	}
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacturingYear() {
		return manufacturingYear;
	}
	public void setManufacturingYear(String manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

}
