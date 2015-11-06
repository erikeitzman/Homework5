package com.airamerica;

public class SpecialAssistance extends Product{
	private String typeOfService;

	public SpecialAssistance(String productCode, String typeOfService) {
		super(productCode);
		this.typeOfService = typeOfService;
	}

	public String getTypeOfService() {
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
}
