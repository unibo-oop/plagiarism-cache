package model.interfaces;

import model.Month;

public interface Day extends Comparable<Day>{
	int getDays();
	Month getMonth();
	int getYear();
	Day next();
	int compareTo(Day d);
	boolean equals(Object o);
	
	
	
	
}
