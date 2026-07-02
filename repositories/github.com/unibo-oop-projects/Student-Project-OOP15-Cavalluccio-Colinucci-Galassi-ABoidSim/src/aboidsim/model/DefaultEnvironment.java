package aboidsim.model;

import java.util.Set;

/**
 * Functional interface.
 *
 */
@FunctionalInterface
public interface DefaultEnvironment {
	
	/**
	 * 
	 * @return the selected environment. These environment are previously created and the user can switch
	 *  environment whenever he wants during the simulation.
	 */
	Set<Boid> getDefaultEnvironment();
}
