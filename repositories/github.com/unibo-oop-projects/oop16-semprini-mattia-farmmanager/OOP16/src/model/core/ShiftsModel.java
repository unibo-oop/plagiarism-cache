package model.core;

import java.util.Calendar;
import java.util.List;

import model.Interfaces.EmployeeModel;
import model.Interfaces.ShiftModel;

public interface ShiftsModel {
	
	/**
	 * this method is used to store a shift
	 * @param shift
	 * @throws IllegalArgumentException if there are already another shift in the same time of the same employee
	 */
	public void addShift(ShiftModel shift);
	
	/**
	 * this method is used to get stored shifts
	 * @return the list of the shifts
	 */
	
	public List<ShiftModel> getShifts();
	
	/**
	 * this method is used to get shifts of a specific employee
	 * @param employee
	 * @return list of the shifts
	 */
	
	public List<ShiftModel> getShifts(EmployeeModel employee);
	
	/**
	 * this method is used to get shifts of a specific employee in a specific month
	 * @param employee
	 * @param Month
	 * @return list of the shifts
	 */
	
	public List<ShiftModel> getShifts(EmployeeModel employee, Calendar Month);
	
}
