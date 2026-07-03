package model.enumerations;

public enum EmployeeType {
	
	CLEANER(6000),
	PERSONAL_TRAINER(1500);
	
	private int salary;
	
	private EmployeeType(int salary) {
		this.salary = salary;
	}
	
	public int getSalary() {
		return this.salary;
	}

}
