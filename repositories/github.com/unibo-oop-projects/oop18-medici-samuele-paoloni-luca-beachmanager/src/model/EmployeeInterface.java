package model;

import java.util.Date;
import java.util.Map;

import controller.WorkingSchedule;

public interface EmployeeInterface {

	void addWorkingSchedule(final Date date, final WorkingSchedule workingSchedule);
	
	void removeWorkingSchedule(final Date date);
	
	Map<Date, WorkingSchedule> getWorkingSchedule();

	
}
