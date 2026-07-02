package controller;

import java.util.Map;

import model.WorkDay;

public interface WorkingSchedule {

	Map<WorkDay, WorkingDay> getWeek();
	
}
