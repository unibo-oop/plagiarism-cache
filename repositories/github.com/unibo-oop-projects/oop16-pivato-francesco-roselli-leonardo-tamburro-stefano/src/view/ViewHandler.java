package view;

public class ViewHandler implements View {

	private static ViewObserver observer;

	public ViewHandler() {}
	
	public static ViewObserver getObserver() {
		return observer;
	}

	@Override
	public void setObserver(ViewObserver o) {
		observer = o;
	}

	@Override
	public Login getLogin() {
		return ViewUtils.getHandler();
	}
	
	@Override
	public Menu getMenu() {
		return ViewUtils.getHandler();
	}

	@Override
	public UserProfile getuserProfile() {
		return ViewUtils.getHandler();
	}

	@Override
	public UsersTable getUsersTable() {
		return ViewUtils.getHandler();
	}

	@Override
	public Warehouse getWarehouse() {
		return ViewUtils.getHandler();
	}

	@Override
	public WorkoutTable getWorkoutTable() {
		return ViewUtils.getHandler();
	}

	 @Override
	public EmployeeProfile getEmployeeProfile() {
	        return ViewUtils.getHandler();
	}

	@Override
    public Administration getAdministration(){
	    return ViewUtils.getHandler();
	}

	@Override
	public ProductDetails getProductDetails() {
		return ViewUtils.getHandler();
	}

}
