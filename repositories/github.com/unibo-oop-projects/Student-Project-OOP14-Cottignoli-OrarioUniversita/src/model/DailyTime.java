package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.interfaces.IDailyTime;
import model.interfaces.ISubject;

/**
 * Implementation of the interface {@link IDailyTime} through the usage of a List of {@value IDailyTime#HOURS} elements
 * that represent all the hours of the day.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class DailyTime implements IDailyTime {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4080899701940069606L;
	
	private final List<ISubject> daily = new ArrayList<ISubject>();
	
	/**
	 * It creates a new daily timetable with all available hours, namely in null form.
	 */
	public DailyTime() {
		for (int i = 0; i < HOURS; i++) {
			daily.add(null);
		}
	}
	
	/**
	 * It creates a new daily timetable by copying the old one using it as a parameter.
	 * 
	 * @param dt Daily timetable to be copied.
	 * @throws IllegalArgumentException if the parameter dt is null.
	 */
	public DailyTime(final IDailyTime dt) {
		if (dt == null) {
			throw new IllegalArgumentException("the parameter 'dt' can't be null!");
		}
		for (int i = 0; i < HOURS; i++) {
			daily.add(dt.getSubject(i + FIRST_HOUR).orElse(null));
		}
	}
	

	@Override
	public void add(final ISubject sub, final int hour, final int n) {
		checkHour(hour, n);
		for (int i = 0; i < n; i++) {
			if (daily.get(hour - FIRST_HOUR + i) != null) {
				throw new IllegalArgumentException("These hours are already busy");
			}
		}
		
		for (int i = 0; i < n; i++) {
			daily.set(hour - FIRST_HOUR + i, sub);
		}
	}
	
	@Override
	public void remove(final int hour, final int n) {
		checkHour(hour, n);
		for (int i = 0; i < n; i++) {
			daily.set(hour - FIRST_HOUR + i, null);
		}
	}
	
	@Override
	public Optional<ISubject> getSubject(final int hour) {
		checkHour(hour);
		return Optional.ofNullable(daily.get(hour - FIRST_HOUR));
	}
	
	@Override
	public IDailyTime copy() {
		return new DailyTime(this);
	}
	
	/**
	 * It carries out the control on hour as specified in the method {@link #getSubject(int)}.
	 * 
	 * @param hour Value of the day that has to be controlled.
	 */
	private void checkHour(final int h) {
		if (h < FIRST_HOUR || h >= (FIRST_HOUR + HOURS)) {
			throw new IllegalArgumentException("Wrong hour! Must be beetween " + FIRST_HOUR + " to " + (FIRST_HOUR + HOURS - 1));
		}
	}
	
	/**
	 * It carries out all the controls declared in the method {@link #add(ISubject, int, int)} 
	 * and in the method {@link #remove(int, int)}.
	 * 
	 * @param hour Starting time.
	 * @param n Number of consecutive hours starting from hour.
	 */
	private void checkHour(final int hour, final int n) {
		checkHour(hour);
		if (n <= 0) {
			throw new IllegalArgumentException("Wrong number of hour! Must be greater than 0!");
		}
		if ((hour + n) > (FIRST_HOUR + HOURS)) {
			throw new IllegalArgumentException("Wrong number of hour! Hour + Number must not be greater than " + (FIRST_HOUR + HOURS));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((daily == null) ? 0 : daily.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DailyTime other = (DailyTime) obj;
		if (daily == null) {
			if (other.daily != null) {
				return false;
			}
		} else if (!daily.equals(other.daily)) {
			return false;
		}
		return true;
	}
	
}
