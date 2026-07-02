package main.java.model;


/**
 * Functional interface for a gravity manager.
 */
@FunctionalInterface
public interface GravityManager {

	/**
	 * Returns the gravity value of a given level. Gravity means the frequency
	 * at which the current tetromino moves down in Hz.
	 */
	double get(int level);
}
