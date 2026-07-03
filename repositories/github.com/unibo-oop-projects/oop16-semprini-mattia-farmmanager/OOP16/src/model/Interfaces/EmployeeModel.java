package model.Interfaces;

public interface EmployeeModel {
	
	/**
	 * this method is used to set the name of the employee
	 * @param name
	 */
	
	public void setName(String name);
	
	/**
	 * this method is used to set the surname of the employee
	 * @param surname
	 */
	
	public void setSurname(String surname);
	
	/**
	 * this method is used to set the telephone of the employee
	 * @param telephone
	 */
	
	public void setTelephone(String telephone);
	
	/**
	 * this method is used to set the cf of the customer
	 * @param CF
	 */
	
	
	public void setCF(String CF);
	
	/**
	 * this method is used to get the name
	 * @return name
	 */
	
	public String getName();
	
	/**
	 * this method is used to get the surname of the employee
	 * @return Name
	 */
	
	public String getSurname();
	
	/**
	 * this method is used to get the cf of the customer
	 * @return CF
	 */
	
	public String getCF();
	
	/**
	 * this method is used to get the telephone of the employee
	 * @return Telephone
	 */
	
	public String getTelephone();
	

	
}
