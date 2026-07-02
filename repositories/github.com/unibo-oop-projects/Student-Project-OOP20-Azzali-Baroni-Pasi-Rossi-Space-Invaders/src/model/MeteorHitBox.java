package model;

import java.awt.Rectangle;

import utility.Pair;

/**
 * The Class MeteorHitBox manages hitbox of each meteor
 */
public class MeteorHitBox extends Rectangle {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
    
    /** The Length. */
    private final int Length;
    
    /**
     * Instantiates a new meteor hit box.
     *
     * @param position the position
     * @param Length the length
     */
    public MeteorHitBox(final Pair<Integer,Integer> position, final int Length) {
		super(position.getX().intValue() - Length / 2, position.getY().intValue() - Length / 2, Length, Length);
    	this.Length = Length;   	
    }
    
    /**
     * Updte hit box.
     *
     * @param position the position
     */
    public void updteHitBox(final Pair<Integer,Integer> position) {
    	this.setLocation(position.getX().intValue() - this.Length/2, position.getY().intValue() - this.Length/2);
    }
    
    /**
     * Checks if is out border.
     *
     * @return true, if is out border
     */
    public boolean isOutBorder() {
    	final Rectangle border = new Rectangle(0, 0, GameImpl.ARENA_WIDTH, GameImpl.ARENA_HEIGHT); 
    	return !border.contains(this) && !border.intersects(this);
    }
}
