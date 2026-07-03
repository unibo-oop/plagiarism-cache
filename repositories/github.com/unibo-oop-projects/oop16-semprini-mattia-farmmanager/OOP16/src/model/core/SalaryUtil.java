package model.core;

import java.util.Calendar;
import java.util.List;

import model.Interfaces.EmployeeModel;
import model.Interfaces.ShiftModel;

public class SalaryUtil {

	/**
	 * this method is used to get the salary of an employee in a specific month
	 * @param shifts
	 * @param employee
	 * @param Month
	 * @param pricePerH
	 * @return a double that contains the salary
	 */
	public static Double getSalary(List<ShiftModel>shifts, EmployeeModel employee, Calendar Month, Double pricePerH){
		Double result = new Double(0);
		for(ShiftModel sm : shifts){
			if (sm.getEmployee().getCF().equals(employee.getCF())&&sm.getData().get(Calendar.MONTH) == Month.get(Calendar.MONTH)){
				result+=sm.getMinutes()*pricePerH/60;
			}
		}		
		return result;
	}
	
	/**
	 * this method is used to get the amount of minutes in a specific month of a specific employee
	 * @param shifts
	 * @param employee
	 * @param Month
	 * @return an integer that contains the minutes
	 */
	public static Integer getMinutes(List<ShiftModel>shifts, EmployeeModel employee, Calendar Month){
		Integer result = new Integer(0);
		for(ShiftModel sm : shifts){
			if (sm.getEmployee().getCF().equals(employee.getCF())&&sm.getData().get(Calendar.MONTH) == Month.get(Calendar.MONTH)){
				result+=sm.getMinutes();
			}
		}	
		return result;
	}
}
