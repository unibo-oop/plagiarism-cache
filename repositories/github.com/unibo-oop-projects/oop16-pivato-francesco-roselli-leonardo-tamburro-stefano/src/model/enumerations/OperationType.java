package model.enumerations;

public enum OperationType {
	
	PRODUCT_SOLD("The profit obtained by the sale of a product", "PROFIT"),
	PRODUCT_BOUGHT("Expenses derived from the purchase of  product/s","EXPENSE"),
	EMPLOYEE_SALARY("Expenses derived from the salary paied to employee", "EXPENSE"),
	USER_REGISTRATION("Profit obtained by the inscription of a user", "PROFIT"),
	SUBSCRIPTION_RENEWAL("Profit obtained by the renewal of an user's subscription", "PROFIT");
	
	private String description;
	private String type;
	
	public String getDescription() {
		return this.description;
	}
	
	public String getType() {
		return this.type;
	}
	
	private OperationType(String description, String type) {
		this.description = description;
		this.type = type;
	}

}
