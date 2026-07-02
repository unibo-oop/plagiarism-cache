package model;

public interface Entity {

	/**
	 * @return	
	 * 		Return the entity width
	 */
	public double getWidth();
	
	/**
	 * @return	
	 * 		Return the entity height
	 * 	
	 */
	public double getHeight();
	
	/**
	 * 
	 * 	@return
	 * 		Return the entity x position
	 */
	public double getPositionX();
	
	/**
	 * 
	 * 	@return
	 * 		Return the entity y position
	 */
	public double getPositionY();
	
	/**
	 *Set the entity width
	 * @param width
	 */
	public void setWidth(double width);
	
	/**
	 * Set the entity height
	 * @param height
	 */
	public void setHeight(double height);
	
	/**
	 * Set entity x position
	 * @param positionX
	 */
	public void setPositionX(double positionX);
	
	/**
	 * Set entity y position
	 * @param positionY
	 */
	public void setPositionY(double positionY);
}
