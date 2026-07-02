package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.interfaces.IClassroomsDailyTime;
import model.interfaces.IDailyTime;
import model.interfaces.ISubject;

/**
 * Implementation of the interface {@link IClassroomsDailyTime} through the usage of a Map that as a key 
 * shows the values of {@link Classrooms#values()} and to everyone it is associated a {@link IDailyTime}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class ClassroomsDailyTime implements IClassroomsDailyTime {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3887851499745557411L;
	
	private final Map<Classrooms, IDailyTime> m = new HashMap<>();
	
	/**
	 * It creates a new daily timetable with all available hours in every classroom.
	 */
	public ClassroomsDailyTime() {
		for (final Classrooms c : Classrooms.values()) {
			m.put(c, new DailyTime());
		}
	}
	
	/**
	 * It creates a new daily timetable for every classroom by copying the old one using it as a parameter.
	 * 
	 * @param cdt Daily timetable of every classroom to be copied .
	 * @throws IllegalArgumentException if parameter cdt is null.
	 */
	public ClassroomsDailyTime(final IClassroomsDailyTime cdt) {
		if (cdt == null) {
			throw new IllegalArgumentException("the parameter 'cdt' can't be null!");
		}
		for (final Classrooms c : Classrooms.values()) {
			m.put(c, cdt.getDailyTime(c));
		}
	}

	@Override
	public void add(final ISubject sub, final Classrooms room, final int hour, final int n) {
		checkRoom(room);
		if (!whereTeaching(sub.getTeachName(), hour).isEmpty()) {
			for (final Classrooms cls : whereTeaching(sub.getTeachName(), hour)) {
				if (!getSubject(cls, hour).get().equals(sub)) {
					throw new IllegalArgumentException(sub.getTeachName() + "is already teaching another subject in this hour!");
				}
			}
		}
		m.get(room).add(sub, hour, n);
	}

	@Override
	public void remove(final Classrooms room, final int hour, final int n) {
		checkRoom(room);
		m.get(room).remove(hour, n);
	}

	@Override
	public Optional<ISubject> getSubject(final Classrooms room, final int hour) {
		checkRoom(room);
		return m.get(room).getSubject(hour);
	}
	
	@Override
	public IDailyTime getDailyTime(final Classrooms room) {
		checkRoom(room);
		return  m.get(room).copy();
	}
	 
	@Override
	public IClassroomsDailyTime copy() {
		return new ClassroomsDailyTime(this);
	}
	
	@Override
	public Set<Classrooms> whereTeaching(final String teach, final int hour) {
		final Set<Classrooms> set = new HashSet<>();
		for (final Classrooms room : Classrooms.values()) {
			if (m.get(room).getSubject(hour).isPresent() && m.get(room).getSubject(hour).get().getTeachName().equals(teach)) {
				set.add(room);
			}
		}
		return set;
	}
	
	@Override
	public Set<Classrooms> wherePerforming(final ISubject sub, final int hour) {
		final Set<Classrooms> set = new HashSet<>();
		for (final Classrooms room : Classrooms.values()) {
			if (m.get(room).getSubject(hour).isPresent() && m.get(room).getSubject(hour).get().equals(sub)) {
				set.add(room);
			}
		}
		return set;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
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
		final ClassroomsDailyTime other = (ClassroomsDailyTime) obj;
		if (m == null) {
			if (other.m != null) {
				return false;
			}
		} else if (!m.equals(other.m)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ClassroomsDailyTime [m=" + m + "]";
	}

	/**
	 * Method which is necessary to control that the classroom is not null.
	 * 
	 * @param room Classroom that has to be checked.
	 * @throws IllegalArgumentException if room is null.

	 */
	private void checkRoom(final Classrooms room) {
		if (room == null) {
			throw new IllegalArgumentException("The classroom can't be null!");
		}
	}
}
