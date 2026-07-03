package model.Interfaces;

import java.util.Calendar;

public interface ShiftModel {
	
	
	/**
	 * this method is used to set the data of this shift
	 * @param data
	 */
	public void setData(Calendar data);
	
	/**
	 * this method is used to set the start hour of this shift
	 * @param start
	 */
	
	public void setStartHour(Calendar start);
	
	/**
	 * this method is used to set the end hour of this shift	
	 * @param end
	 */
	public void setEndHour(Calendar end);
	
	/**
	 * this method is used to set the description of the employee's shift
	 * @param description
	 */
	
	public void setDescription(String description);
	
	/**
	 * this method is used to set the employee
	 * @param employee
	 */
	
	public void setEmployee(EmployeeModel employee);
	
	/**
	 * this method is used to get the data
	 * @return
	 */
	
	public Calendar getData();
	
	/**
	 * this method is used to get the start hour and minute
	 * @return a string formatted like "hh:mm"
	 */
	
	public String getStartHour();
	
	/**
	 * this method is used to get the end hour and minute
	 * @return a string formatted like "hh:mm"
	 */
	
	public String getEndHour();
	
	/**
	 * this method is used to get the description of this shift
	 * @return the description
	 */
	
	public String getDescription();
	
	/**
	 * this method is used to get the employee
	 * @return an object that contains employee of this shift
	 */
	
	public EmployeeModel getEmployee();
	
	/**
	 *this mehtod is used to get the total minutes of this shift calculated with starthour and endhour 
	 *@return a double which contains the minutes
	 *@throws NullPointerException if start or end are not setted
	 */
	
	public int getMinutes();
	
	
}
