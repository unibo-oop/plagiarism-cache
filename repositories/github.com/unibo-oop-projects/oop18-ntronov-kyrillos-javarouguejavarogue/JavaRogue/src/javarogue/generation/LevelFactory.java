package javarogue.generation;

import javarogue.level.Level;

/**
 * 
 * Level Factory.
 *
 */
public interface LevelFactory {

	/**
	 * 
	 * Generate a level of given depth. First three levels grow progressively, 4th
	 * level is Boss.
	 * 
	 * @param depth of the desired level.
	 * @return a generated Level.
	 */
	public Level generate(int depth);

}
