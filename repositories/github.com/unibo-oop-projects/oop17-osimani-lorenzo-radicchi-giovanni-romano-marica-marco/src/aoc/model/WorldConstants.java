package aoc.model;

import aoc.controller.GameConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class WorldConstants {
    
    /**
     * The number of rows in the map.
     */
    public static final int WORLD_HEIGHT = 5;
    
    /**
     * The number of cells in every row.
     */
    public static final double WORLD_WIDTH = 10;
    
    /**
     * The width of every child.
     */
    public static final double CELL_WIDTH = 50;
    
    /**
     * The center of every child.
     */
    public static final double CELL_CENTER = CELL_WIDTH/2;
   
    /**
     * An array containing the center of each row.
     */
    public static final List<Double> ROW_CENTERS = getCenters();
    
    /**
     * If this point is reached, the level is over and lost.
     */
    public static final double GAMEOVER_LINE = 0;
    
    /**
     * The height of every child.
     */
    public static final double CHILD_HEIGHT = CELL_WIDTH/4;
    
    /**
     * The amount of life of a normal enemy.
     */
    public static final int NORMAL_LIFE = 500;
    
    /**
     * The amount of life of a thin enemy.
     */
    public static final int LOW_LIFE = (int) (NORMAL_LIFE*0.75);
    
    /**
     * The amount of life of a big enemy.
     */
    public static final int BIG_LIFE = NORMAL_LIFE*2;
    
    /**
     * The amount of life of a large enemy.
     */
    public static final int LARGE_LIFE = (int) (NORMAL_LIFE*1.5);
    
    /**
     * The amount of damage dealt by a normal projectile.
     */
    public static final int NORMAL_DAMAGE = NORMAL_LIFE/10;
  
    /** 
     * The amount of time required to cross the world with a speed equal to the speed factor.
     */
    public static final int SECONDS_FOR_CROSSING = 15;
    
    /**
     * The basic factor from which speed will be calculated.
     */
    public static final double SPEED_FACTOR = (WORLD_WIDTH * CELL_WIDTH) / (SECONDS_FOR_CROSSING * GameConstants.UPDATES_PER_SECOND);
    //width of the world / number of seconds for crossing all the world * number of FPS
    
    /**
     * The time between projectiles shoot with rapid fire.
     * The actual value correspond to 1/4 of a second.
     */
    public static final long TIME_BETWEEN_SHOOT = 150000000;

    /**
     * The standard speed of normal entities.
     */
    public static final double NORMAL_SPEED = SPEED_FACTOR;
    
    /**
     * The standard speed of slow entities.
     */
    public static final double SLOW_SPEED = SPEED_FACTOR*0.75;
    
    /**
     * The standard speed of fast entities.
     */
    public static final double FAST_SPEED = SPEED_FACTOR*1.5;
    
    /**
     * The standard speed of really fast entities.
     */
    public static final double GREAT_SPEED = SPEED_FACTOR*2;
    
    /**
     * The standard speed of REALLY fast entities.
     * Gotta go fast.
     */
    public static final double SONIC_SPEED = SPEED_FACTOR*10;
    
    /**
     * A range to calculate when an entity is hit.
     */
    public static final double HIT_RANGE = (double) SONIC_SPEED*2;
    
    
    /**
     * This method calculates the center of each row.
     * 
     * @return The list containing each center.
     */
    private static List<Double> getCenters() {
	final List<Double> centers = new ArrayList<>();
	Stream.generate(() -> null).limit(WORLD_HEIGHT).forEach(x -> centers.add(centers.size() * CELL_WIDTH + CELL_CENTER));
	return centers;
    }
}
