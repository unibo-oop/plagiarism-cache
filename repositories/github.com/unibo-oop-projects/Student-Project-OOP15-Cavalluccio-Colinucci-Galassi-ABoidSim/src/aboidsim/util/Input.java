package aboidsim.util;

/**
 * Enum. This class will contain all the basic input types an user can give
 *
 */
public enum Input {

	// TO DO: Complete the list or find a better way (how to pass the
	// arguments?)
	/**
	 * This input is used when the user wants to create a new boid.
	 */
	CREATE_BOID,
	/**
	 * This input is used when the user wants to remove a boid from the
	 * environment.
	 */
	DESTROY_BOID,
	/**
	 * This input is used when the user wants to set or unset a decisional rule.
	 */
	TOGGLE_RULE,
	/**
	 * This input is used when the user wants to load a default environment.
	 */
	LOAD_ENV,
	/**
	 * This input is used when the user wants to pause the application.
	 */
	PAUSE,
	/**
	 * This input is used when the user wants to resume from a pause.
	 */
	RESUME,
	/**
	 * This input is used when the user wants to close the application.
	 */
	CLOSE;

}
