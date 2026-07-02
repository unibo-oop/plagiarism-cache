package hollowmen.model;

import hollowmen.utilities.Pair;

/**
 * This interface represents an object that can be looted after
 * meet some condition
 * @author pigio
 *
 */
public interface Achievement extends InformationUser{

	/**
	 * This method check if the conditions for looting are met, if so the
	 * {@code Challenge} become lootable
	 */
	public void check();
	
	/**
	 * This method gives the current state of this {@code Achievement}
	 * @return {@link Pair} X reached progress, Y limit progress
	 */
	public Pair<Integer, Integer> getProgress();
	/**
	 * 
	 * @return {@link Lootable} reward
	 * @throws IllegalStateException If {@code isLootable} return false
	 */
	public Lootable getLoot() throws IllegalStateException;
}
