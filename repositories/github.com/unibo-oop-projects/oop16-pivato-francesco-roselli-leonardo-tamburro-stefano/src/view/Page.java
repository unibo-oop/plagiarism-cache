package view;

public enum Page {
	
	LOGIN("/view/resources/Login.fxml"),
	MENU("/view/resources/Menu.fxml"),
	USER_PROFILE("/view/resources/UserProfile.fxml"),
	USERS_TABLE("/view/resources/UsersTable.fxml"),
	WAREHOUSE("/view/resources/Warehouse.fxml"),
	WORKOUT_TABLE("/view/resources/WorkoutTable.fxml"),
	EMPLOYEE_PROFILE("/view/resources/EmployeeProfile.fxml"),
	PRODUCT_DETAILS("/view/resources/ProductDetails.fxml"),
	ADMINISTRATION("/view/resources/Administration.fxml");
	
	private String url;
	
	private Page(final String url) {
		this.url = url;
	}
	
	public String url(){
		return this.url;
	}

}
