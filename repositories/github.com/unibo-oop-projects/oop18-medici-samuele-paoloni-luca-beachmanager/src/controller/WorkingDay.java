package controller;

import java.time.LocalTime;
import java.util.Deque;

public interface WorkingDay {
	
	Deque<LocalTime> getStarts();
	
	Deque<LocalTime> getStops();
	
	WorkingDay addStart(LocalTime time);
	
	WorkingDay addStop(LocalTime time);
	
	LocalTime workHours();
	
}
