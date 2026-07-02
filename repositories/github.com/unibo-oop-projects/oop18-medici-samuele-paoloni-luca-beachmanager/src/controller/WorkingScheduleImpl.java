package controller;
import java.*;
import java.util.Map;

import model.WorkDay;

import java.util.*;

public class WorkingScheduleImpl implements WorkingSchedule {
	
	Map<WorkDay,WorkingDay> map=new TreeMap<>();
	
	public WorkingScheduleImpl(Map<WorkDay,WorkingDay> map){
		this.map=map;
	}
	@Override
	public Map<WorkDay, WorkingDay> getWeek() {
		return map;
	}

}
