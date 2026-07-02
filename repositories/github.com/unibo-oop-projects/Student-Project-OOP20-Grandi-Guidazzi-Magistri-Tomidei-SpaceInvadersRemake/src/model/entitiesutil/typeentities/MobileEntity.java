package model.entitiesutil.typeentities;

import model.physics.EntityCollision.EdgeCollision;

/**
 * Interface that represents all the {@link GenericEntity} that can move
 */
public interface MobileEntity extends GenericEntity{

	/**
	 * Return the movement unit of the {@link GenericEntity} along x-axis
	 * 
	 * @return the integer which represents {@link GenericEntity}'s movement unit along x-axis
	 */
	double getMuX();

	/**
	 * Update the movement unit of the {@link GenericEntity} along x-axis with method input value
	 * 
	 * @param mux integer which is the new movement unit of the {@link GenericEntity} along x-axis
	 */
	void setMuX(double mux);

	/**
	 * Return the movement unit of the {@link GenericEntity} along y-axis
	 * 
	 * @return the integer which represents the movement unit of the {@link GenericEntity} along y-axis
	 */
	double getMuY();

	/**
	 * Update the movement unit of the {@link GenericEntity} along x-axis
	 * 
	 * @param mux 	new movement unit of the {@link GenericEntity} along x-axis
	 */
	void setMuY(double muy);

	/**
	 * {@link GenericEntity} does a specific action based on 
	 * the edge it collided with
	 * 
	 * @param edge where the {@link GenericEntity} collided
	 */
	void doAfterCollisionWithEdge(EdgeCollision edge);

}