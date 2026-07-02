package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.interfaces.IClassroomsDailyTime;
import model.interfaces.ISubject;
import model.interfaces.IWeeklyTime;

/**
 * 
 * Implementation of the interface {@link IWeeklyTime} through the use of a Map that has the values of 
 * {@link Days#values()} as key and it has associated to everyone a {@link IClassroomsDailyTime}.
 * 
 * @author Lorenzo Cottignoli
 * 
 */
public class WeeklyTime implements IWeeklyTime {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266673430374084472L;
	
	private final Map<Days, IClassroomsDailyTime> week = new HashMap<>();
	
	/**
	 * Constructor that creates a new structure.
	 */
	public WeeklyTime() {
		for (final Days d : Days.values()) {
			week.put(d, new ClassroomsDailyTime());
		}
	}
	
	/**
	 Constructor that creates a new structure by copying the old one using it as parameter.
	 * 
	 * @param wt structure to be copied.
	 * @throws IllegalArgumentException if wt is null.

	 */
	public WeeklyTime(final IWeeklyTime wt) {
		if (wt == null) {
			throw new IllegalArgumentException("the parameter 'wt' can't be null!");
		}
		for (final Days d : Days.values()) {
			week.put(d, wt.getClassroomDailyTime(d));
		}
	}
	
	@Override
	public void add(final ISubject sub, final Days d, final Classrooms room, final int hour, final int n) {
		checkDay(d);
		week.get(d).add(sub, room, hour, n);
	}

	@Override
	public void remove(final Days d, final Classrooms room, final int hour, final int n) {
		checkDay(d);
		week.get(d).remove(room, hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Days d, final Classrooms room, final int hour) {
		checkDay(d);
		return week.get(d).getSubject(room, hour);
	}
	
	@Override
	public IClassroomsDailyTime getClassroomDailyTime(final Days d) {
		checkDay(d);
		return week.get(d).copy();
	}

	@Override
	public IWeeklyTime copy() {
		return new WeeklyTime(this);
	}
	
	@Override
	public Set<Classrooms> whereTeaching(final String teach, final Days d, final int hour) {
		checkDay(d);
		return week.get(d).whereTeaching(teach, hour);
	}
	
	@Override
	public Set<Classrooms> wherePerforming(final ISubject sub, final Days d, final int hour) {
		checkDay(d);
		return week.get(d).wherePerforming(sub, hour);
	}

	/**
	  * Method to control if a day is equal to null.
	 * 
	 * @param d Day to be controlled.
	 * @throws IllegalArgumentException if d is null.
	 */
	private void checkDay(final Days d) {
		if (d == null) {
			throw new IllegalArgumentException("The day can't be null!");
		}
	}
}
