package model;

import java.util.Optional;

/**
 * interface which represent the current level
 * 
 */
public interface Level {
	
	/**
	 * method that return the number of alien
	 * @return
	 */
	int getAliens();
	
	/**
	 * method that return the boss type
	 * @return
	 */
    Optional<String> getBoss();    
}
