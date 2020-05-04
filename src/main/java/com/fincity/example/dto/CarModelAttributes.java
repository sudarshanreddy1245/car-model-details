package com.fincity.example.dto;

public class CarModelAttributes {
	
	private Operator operator;
	private String name;
	private String manufactureName;
	private String model;
	private String manufacturingYear;
	private String color;
	public Operator getOperator() {
		return operator;
	}
	public void setOperatorType(Operator operator) {
		this.operator = operator;
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


