package main.java.model;

/**
 * Functional interface for a score manager.
 */
public interface ScoreManager {

	/**
	 * Returns the amount of points to reward some cleared lines.
	 * 
	 * @param n	the number of lines cleared
	 * @param level	the current level
	 */
	int get(int n, int level);
}
