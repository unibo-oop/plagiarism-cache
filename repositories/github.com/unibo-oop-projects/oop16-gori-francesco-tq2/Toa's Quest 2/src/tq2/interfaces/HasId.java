package tq2.interfaces;

import tq2.implementations.Id;

/**
 * The Interface HasId. A simple interface meant to be joined with others to create new interfaces.
 * 
 * @author Francesco Gori
 */
public interface HasId {

	/**
	 * Returns the id of the object.
	 *
	 * @return the id
	 */
	public abstract Id getId();
	
}
