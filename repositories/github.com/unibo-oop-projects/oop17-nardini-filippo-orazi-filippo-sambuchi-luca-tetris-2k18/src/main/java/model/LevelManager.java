package main.java.model;

/**
 * Functional interface for a level manager. 
 */
@FunctionalInterface
public interface LevelManager {
	
	/**
	 * Returns the level given the number of cleared lines.
	 */
	int get(int lines);
}
