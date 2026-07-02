package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.interfaces.IModel;
import model.interfaces.ISubject;
import model.interfaces.IWeeklyTime;

/**
 * Implementation of the interface IModel through a Map with semester as key and as associated value {@link IWeeklyTime} and a Set of
 * {@link ISubject} for the list of available courses.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class Model implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4369449608688351336L;
	
	private Map<Integer, IWeeklyTime> semester = new HashMap<>();
	private Set<ISubject> subjects = new HashSet<>();
	
	/**
	 * Constructor that initialize the data structures.
	 */
	public Model() {
		semester.put(FIRST_SEM, new WeeklyTime());
		semester.put(SEC_SEM, new WeeklyTime());
	}
	
	@Override
	public void add(final ISubject sub, final int sem, final Days d, final Classrooms room, final int hour, final int n) {
		checkSem(sem);
		semester.get(sem).add(sub, d, room, hour, n);
	}

	@Override
	public void remove(final int sem, final Days d, final Classrooms room, final int hour, final int n) {
		checkSem(sem);
		semester.get(sem).remove(d, room, hour, n);
	}
	
	@Override
	public void addSubject(final String sub, final String teach, final SubjectType type) {
		final ISubject su = new Subject(sub, teach, type);
		subjects.add(su);
	}
	
	@Override
	public void removeSubject(final ISubject sub) {
		if (subjects.contains(sub)) {
			subjects.remove(sub);
		}
	}
	
	@Override
	public Optional<ISubject> getSubject(final int sem, final Days d, final Classrooms room, final int hour) {
		checkSem(sem);
		return semester.get(sem).getSubject(d, room, hour);
	}
	
	@Override
	public Set<ISubject> getSubjects() {
		return new HashSet<>(subjects);
	}

	@Override
	public void setSubjects(final Set<ISubject> set) {
		subjects = set;
	}
	
	@Override
	public Set<Classrooms> whereTeaching(final String teach, final int sem, final Days d, final int hour) {
		return semester.get(sem).whereTeaching(teach, d, hour);
	}
	
	@Override
	public Set<Classrooms> wherePerforming(final ISubject sub, final int sem, final Days d, final int hour) {
		return semester.get(sem).wherePerforming(sub, d, hour);
	}
	
	@Override
	public Set<String> getTeachers() {
		final Set<String> setProf = new HashSet<>();
		for (final ISubject s : subjects) {
			if (!setProf.contains(s.getTeachName())) {
				setProf.add(s.getTeachName());
			}
		}
		return setProf;
	}
	
	@Override
	public IMemento createMemento() {
		return new Memento(createCopy());
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public void setMemento(final IMemento mem) {
		if (mem == null || mem.getState() == null) {
			throw new IllegalArgumentException("mem and mem.getState() must not be null!");
		}
		semester = (Map<Integer, IWeeklyTime>) mem.getState();
		final Map<Integer, IWeeklyTime> app = createCopy();
		semester = app;
	}
	
	/**
	 * Method that is necessary to control that the value passed as semester is coherent.
	 * 
	 * @param sem Semester that has to be controlled.
	 * @throws IllegalArgumentException if sem is not the same of {@link IModel#FIRST_SEM} nor 
	 * of {@link IModel#SEC_SEM}.
	 */
	private void checkSem(final int sem) {
		if (sem != FIRST_SEM && sem != SEC_SEM) {
			throw new IllegalArgumentException("Invalid semester");
		}
	}
	
	/**
	 * Method to create a copy of the principal structure.
	 * 
	 * @return Copy of the structure.
	 */
	private Map<Integer, IWeeklyTime> createCopy() {
		final Map<Integer, IWeeklyTime> map = new HashMap<>();
		map.put(FIRST_SEM, semester.get(FIRST_SEM).copy());
		map.put(SEC_SEM, semester.get(SEC_SEM).copy());
		return map;
	}
	
	private static class Memento implements IMemento {
		
		private Object st;
		
		/**
		 * Constructor that give to the internal state the object passed as parameter.
		 * 
	 	 * @param s State that will be saved in this object.
	 	 * @throws IllegalArgumentException if s is null.
		 */
		Memento(final Object s) {
			if (s == null) {
				throw new IllegalArgumentException("state must not be null!");
			}
			st = s;
		}
		
		@Override
		public void setState(final Object state) {
			if (state == null) {
				throw new IllegalArgumentException("state must not be null!");
			}
			st = state;
		}

		@Override
		public Object getState() {
			return st;
		}

	}
}
