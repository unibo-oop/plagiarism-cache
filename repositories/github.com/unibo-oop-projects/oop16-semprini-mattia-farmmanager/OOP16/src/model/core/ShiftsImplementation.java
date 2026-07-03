package model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import model.Interfaces.EmployeeModel;
import model.Interfaces.ShiftModel;

public class ShiftsImplementation implements ShiftsModel, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1864122540924640862L;
	private List<ShiftModel> shifts = new ArrayList<>();

	@Override
	public void addShift(ShiftModel shift) {
		for(ShiftModel s : this.shifts){
			if(s.equals(shift)){
				throw new IllegalArgumentException("this shift has been already added");
			}
		}
		this.shifts.add(shift);
	}

	@Override
	public List<ShiftModel> getShifts(){
		return this.shifts;
	}

	@Override
	public List<ShiftModel> getShifts(EmployeeModel employee) {
		List<ShiftModel> result = new ArrayList<>();
		for(ShiftModel s : this.shifts){
			if(s.getEmployee().getCF().equals(employee.getCF())){
				result.add(s);
			}
		}
		if (result.size()==0){
			return Collections.emptyList();
		}
		return result;
	}
	
	public List<ShiftModel> getShifts(EmployeeModel employee, Calendar month){
		List<ShiftModel> result = new ArrayList<>();
		for(ShiftModel s : this.getShifts(employee)){
			if(s.getData().get(Calendar.MONTH) == month.get(Calendar.MONTH)){
				result.add(s);
			}
		}
		if (result.size()==0){
			return Collections.emptyList();
		}
		return result;
	}

}
