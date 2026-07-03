package model.enumerations;

/**
 * Enumeration to manage Errors
 *
 */

public enum Status {

	PASSWORD_WRONG ("The password entered is wrong"),
	ADMIN_NOT_FOUND ("Sorry, the Admin is not found"),
	NO_INPUT ("Please, insert all the necessary data "),
	ALL_RIGHT ("the operation was performed successfully"),
	NAME_MISSING ("Please insert the name"),

	USER_NOT_FOUND ("Sorry, the user specified is not found"),
	USER_ALREADY_EXIST ("The user ulready exist"),
	EMPLOYEE_ALREADY_EXIST ("The employee already exist"),
	EMPLOYEE_NOT_FOUND ("Sorry, the employee specified is not found"),
	QUANTITY_NOT_AVAILABLE ("Sorry, the quantity choses for the product is not available"),
	EXERCISE_NOT_FOUND("Sorry, the Exercise is not found"),
	PRODUCT_NOT_FOUND("Sorry, the Product is not found"),
	EXERCISE_ALREADY_EXIST ("The Exercise already exists in workout"),
	WRONG_VALUE ("The input value is not correct");


	private String text;

	private Status(String text){
		this.text = text;
	}

	public String getText(){
		return this.text;
	}

}
