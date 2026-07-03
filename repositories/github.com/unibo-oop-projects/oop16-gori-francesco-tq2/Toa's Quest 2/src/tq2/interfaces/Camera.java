package tq2.interfaces;


/**
 * The Interface Camera.
 * 
 * @author Francesco Gori
 */
public interface Camera {

	/**
	 * Place the Camera at the coordinates of the specified Entity.
	 *
	 * @param e the Entity the camera will be placed at.
	 */
	public abstract void follow(Entity e);

	/**
	 * Gets the X coordinate of the Camera.
	 *
	 * @return the X coordinate
	 */
	public abstract Integer getX();

	/**
	 * Gets the Y coordinate of the Camera.
	 *
	 * @return the Y coordinate
	 */
	public abstract Integer getY();

	/**
	 * Sets the X coordinate of the Camera.
	 *
	 * @param x the new X coordinate
	 */
	public abstract void setX(Integer x);

	/**
	 * Sets the Y coordinate of the Camera.
	 *
	 * @param y the new Y coordinate
	 */
	public abstract void setY(Integer y);

}