package com.fincity.example.dto;

public class CarDto {
	
	private Long id;
	private String name;
	private String manufactureName;
	private String model;
	private String manufacturingYear;
	private String color;
	
	public CarDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarDto(Long id, String name, String manufactureName, String model, String manufacturingYear, String color) {
		super();
		this.id = id;
		this.name = name;
		this.manufactureName = manufactureName;
		this.model = model;
		this.manufacturingYear = manufacturingYear;
		this.color = color;
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

}
