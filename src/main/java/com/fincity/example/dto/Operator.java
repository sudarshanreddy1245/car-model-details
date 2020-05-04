package com.fincity.example.dto;

public enum Operator {
	
	AND("and"),
	OR("or");
	
	private String type;
	
	
	private Operator(final String type) {
		this.type = type;
	}


	public String getType() {
		return type;
	}
	
	public static Operator getOperatorByType(Operator type) {
		for( Operator operatorType : values()) {
			if (type == operatorType) {
				return type;
			}
		}
		return null;
	}
	
	

}
