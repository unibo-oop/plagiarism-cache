package model;
//Author: Lorenzo Blancato



public abstract class EntityImpl implements Entity {
	
	protected double width; 
	protected double height; 
	protected double x; 
	protected double y; 
	
	
/**
 * 
 * @param width
 * @param height
 * @param positionX
 * @param positionY
 */
	public EntityImpl(double width, double height, double positionX, double positionY) {
		this.width = width;
		this.height = height;
		this.x = positionX;
		this.y = positionY;		
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getPositionX() {
		return this.x;
	}
	
	public double getPositionY() {
		return this.y;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setPositionX(double positionX) {
		this.x = positionX;
	}
	
	public void setPositionY(double positionY) {
		this.y = positionY;
	}

	
	
	
}
