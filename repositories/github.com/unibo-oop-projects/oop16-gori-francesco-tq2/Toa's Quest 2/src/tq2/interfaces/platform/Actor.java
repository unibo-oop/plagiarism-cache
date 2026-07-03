package tq2.interfaces.platform;

/**
 * The Interface Actor is to be used for characters, either players or enemies or neither.
 * 
 * @author Francesco Gori
 */
public interface Actor extends Active {

	/**
	 * Gets the running speed of the Actor.
	 *
	 * @return the run speed
	 */
	public abstract Integer getRunSpeed();

	/**
	 * Checks whether the movement of the Actor is enabled.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isMovementEnabled();

	/**
	 * Sets the running speed of the Actor.
	 *
	 * @param runSpeed the new run speed
	 */
	public abstract void setRunSpeed(Integer runSpeed);


	/**
	 * If the Actor status lets him move, his movements will be enabled, otherwise disabled.
	 */
	public abstract void resetMovementEnabled();

	/**
	 * Resets the running speed to its starting value.
	 */
	public abstract void resetRunSpeed();


}