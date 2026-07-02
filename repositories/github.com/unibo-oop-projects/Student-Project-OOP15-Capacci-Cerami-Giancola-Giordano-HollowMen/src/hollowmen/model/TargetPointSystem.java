package hollowmen.model;

import java.util.Collection;

/**
 * This interface encloses the logic of a system that has some targets to spend
 * points on
 * @author pigio
 *
 * @param <T> any
 */
public interface TargetPointSystem<T> {

	/**
	 * This method gives the possible targets in this system;
	 * @return {@link Collection} of T
	 */
	public Collection<T> getTargets();
	
	/**
	 * This method gives how many point are unspent
	 * @return {@code int}
	 */
	public int getUnspentPoint();
	
	/**
	 * This method add <b>pointToAdd</b> points at the system
	 * @param pointToAdd
	 */
	public void addPoint(int pointToAdd);
	
	/**
	 * This method spends one point on the <b>target</b>
	 * @param target
	 * @throws IllegalStateException If system hasn't points to spend
	 * @throws IllegalArgumentException If <b>target</b> isn't in this system
	 */
	public void spendPointOn(T target) throws IllegalStateException, IllegalArgumentException;
	
	/**
	 * This method retrieves one point from the <b>target</b>
	 * @param target
	 * @throws IllegalArgumentException If <b>target</b> isn't in this system
	 */
	public void retrievePointFrom(T target) throws IllegalArgumentException;
}
