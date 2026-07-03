package elements;

import resources.LoadResources;

public interface Element {
	/**
	 * Return the instance of resource.
	 * 
	 * @return a LoadResources instance.
	 */
	LoadResources getResources();

	/**
	 * Return the value of X coordinate of this Element.
	 * 
	 * @return a int value of X coordinate.
	 */
	int getX();

	/**
	 * Return the value of Y coordinate of this Element.
	 * 
	 * @return a int value of Y coordinate.
	 */
	int getY();

	/**
	 * Set the value of X coordinate of this Element in "value".
	 * 
	 * @param value
	 *            a int value for X coordinate of Element.
	 */
	void setX(final int value);

	/**
	 * Set the value of Y coordinate of this Element in "value".
	 * 
	 * @param value
	 *            a int value for Y coordinate of Element.
	 */
	void setY(final int value);

	/**
	 * Indicates if the Element is turned left.
	 * 
	 * @return a boolean that indicate if the Element is turned left.
	 */
	boolean isTurnedLeft();

	/**
	 * Indicates if the Element is turned right.
	 * 
	 * @return a boolean that indicate if the Element is turned right.
	 */
	boolean isTurnedRight();

	/**
	 * Turn the Element in the required direction.
	 * 
	 * @param turnLeft
	 *            a boolean value, set it true to request to turn left.
	 * @param turnRight
	 *            a boolean value, set it true to request to turn right.
	 */
	void turn(final boolean turnLeft, final boolean turnRight);

	/**
	 * Print the principal information of this Element.
	 */
	String toString();
}
