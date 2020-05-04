package com.fincity.example.dto;

public enum OperatorType {
	
	AND("and"),
	OR("or");
	
	private String type;
	
	
	private OperatorType(final String type) {
		this.type = type;
	}


	public String getType() {
		return type;
	}
	
	public static OperatorType getOperatorByType(OperatorType type) {
		for( OperatorType operatorType : values()) {
			if (type == operatorType) {
				return type;
			}
		}
		return null;
	}
	
	

}
