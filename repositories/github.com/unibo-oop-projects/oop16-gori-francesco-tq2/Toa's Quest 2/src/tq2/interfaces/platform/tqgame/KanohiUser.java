package tq2.interfaces.platform.tqgame;

import java.util.LinkedList;

/**
 * The Interface KanohiUser is meant for the players of the game Toa's Quest 2.
 * It handles the unlocking and use of powers.
 * 
 * @author Francesco Gori
 */
public interface KanohiUser {

	/**
	 * Gets the active Kanohi.
	 *
	 * @return the active Kanohi
	 */
	public abstract Integer getActiveKanohi();

	/**
	 * Adds the Kanohi to the unlocked Kanohi.
	 *
	 * @param mask the unlocked Kanohi mask
	 */
	public abstract void addKanohi(Integer mask);

	/**
	 * Checks if the Entity object has the specified Kanohi.
	 *
	 * @param mask the mask
	 * @return whether the object has the specified mask.
	 */
	public abstract Boolean hasKanohi(Integer mask);

	/**
	 * Activates the specified Kanohi.
	 *
	 * @param mask the Kanohi to activate
	 */
	public abstract void setActiveKanohi(Integer mask);

	/**
	 * Deactivate the active Kanohi.
	 */
	public abstract void deactivateKanohi();
	
	
	/**
	 * Gets the list of Kanohi masks of the object.
	 *
	 * @return the list of Kanohi
	 */
	public abstract LinkedList<Integer> getKanohiList();

}