package com.test.simpleasset.constant;

public enum AssetType {
	GENERAL("general", "gen"), 
	LINCENSES("licenses", "lic"),
	COMPONENTS("components", "com"),
	CONSUMABLE("consumable", "con");	
	
	private String typeName;
	private String typeCode;
	
	private AssetType(String typeName, String typeCode){
		this.typeName = typeName;
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
}
