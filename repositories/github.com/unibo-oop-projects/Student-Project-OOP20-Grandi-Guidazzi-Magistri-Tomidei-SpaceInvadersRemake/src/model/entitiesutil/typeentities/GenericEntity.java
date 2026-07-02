package model.entitiesutil.typeentities;

import util.Pair;
import model.entities.SpecificEntityType;

/**
 * Interface that represents any object inside the game.
 */
public interface GenericEntity {

	/**
	 * Return  the current position of the {@link GenericEntity}
	 * 
     * @return the {@link Pair} of Integers which represents the current position of the {@link GenericEntity}
     *        
     */
	public Pair<Double, Double> getPos();

	/**
	 * Update the position of the {@link GenericEntity}
	 * 
	 * @param x is the new x coordinate
	 * @param y is the new y coordinate
	 */
	public void setPos(double x, double y);

	/**
	 * Return the current x coordinate of the {@link GenericEntity}
	 * 
	 * @return the integer which represents the current {@link GenericEntity}'s x coordinate
	 */
	public double getX();

	/**
	 * Return the current y coordinate of the {@link GenericEntity}
	 * 
	 * @return the integer which represents {@link GenericEntity}'s y coordinate
	 */
	public double getY();

	/**
	 * Set {@link GenericEntity}'s x coordinate with the method input value
	 *
	 * @param x integer which represents the new x coordinate of the {@link GenericEntity}
	 */
	public void setX(double x);

	/**
	 * Set {@link GenericEntity}'s y coordinate with the method input value
	 * 
	 * @param y integer which represents the new y coordinate of the {@link GenericEntity}
	 */
	public void setY(double y);

	/**
	 * Return the current width of the {@link GenericEntity}
	 * 
     * @return the integer which represents {@link GenericEntity}'s current width
     */
	public int getWidth();

	/**
	 * Return the current height of the {@link GenericEntity}
	 * 
     * @return the integer which represents {@link GenericEntity}'s current height
     */
	public int getHeight();

	/**
	 * Return true if {@link GenericEntity} is alive, false otherwise
	 * 
	 * @return a boolean which represents if {@link GenericEntity} is alive
	 */
	public boolean isAlive();

	/**
	 * {@link GenericEntity} does a specific action based on 
	 * the {@link GenericEntity} it collided with
	 * 
	 * 
	 * @param entity that collided with the {@link GenericEntity}
	 */
	void doAfterCollisionWithEntity(GenericEntity entity);

	/**
	 * Return the types of {@link GenericEntity}
	 * 
	 * @return a value of {@link SpecificEntityType} which represents the {@link GenericEntity} type
	 */
	public SpecificEntityType getEntityType();

}
