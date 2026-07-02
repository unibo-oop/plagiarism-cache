package aoc.model;


import aoc.utilities.*;


public interface Model extends LevelProxy {
    
    /**
     * The possible states of the game currently played.
     */
    public enum GameStatus {
        
        /**
         * The level is being played.
         */
        PLAYING,
        
        /**
         * The level has been won.
         */
        WON,
        
        /**
         * The level has been lost.
         */
        LOST;
    }
    
    /**
     * The possible shooting styles.
     */
    public enum ShootingStyle {
   
        /**
	 * Rapid Fire.
	 */
	RAPID,

	/**
	 * Single Shot.
	 */
	SINGLE;
    }
    
    /**
     * This method moves the Mother object in
     * the Direction passed as a parameter.
     * 
     * @param dir
     *            The direction the mother has to move to
     */
    void moveMother(Direction dir);
    
    /**
     * This method makes the mother shoots a projectile,
     * and it can be a single shot or a rapid fire.
     * In the second case there's a limit of projectiles
     * shot per second.
     * 
     * @param style
     *            The ShootingStyle of the projectile
     */
    void shoot(ShootingStyle style);
}
