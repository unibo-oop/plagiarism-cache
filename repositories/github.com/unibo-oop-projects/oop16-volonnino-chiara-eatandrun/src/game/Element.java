package game;
/**
 * Implementation of a general element
 */

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import base.Animation;
import base.Drawing;



/**
 * 
 * @author Chiara
 *
 */
public abstract class Element implements Drawing, Animation {
    
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private int len;
    private boolean crash;
    protected Image imagen;
    
    /**
     * 
     * @param resource 
     *        path to load 
     * @param x
     *        point x
     * @param y
     *        point y
     * @param len
     *        length width and height
     * @param speedX
     *        speed point x
     * @param speedY
     *        speed point y
     */
    public Element(final String resource, final int x, final int y, final int len, final int speedX, final int speedY) {
		
        this.setX(x);
	this.setY(y);
	this.setSpeedX(speedX);
	this.setSpeedY(speedY);
	this.setLen(len);
	this.setCrash(false);
	imagen = new ImageIcon(this.getClass().getResource(resource)).getImage();	
    
    }
	
    public abstract void animation();
	
    public abstract void draw(Graphics graphic);
	
    /**
     * This method set speeds
     * @param speedX
     *             point x
     * @param speedY
     *             point y
     */
    public void setSpeeds(final int speedX, final int speedY) {
	this.setSpeedX(speedX);
	this.setSpeedY(speedY);
    }
	
    public boolean getCrash() {
	return this.isCrash(); 
    }
	
    public int getX() {
	return this.x;
    }
	
    public int getY() {
	return this.y;
    }
	
    public int getLen() {
	return this.len;
    }
	
    public int getSpeedX() {
	return this.speedX;
    }
	
    public int getSpeedY() {
        return this.speedY; 
    }
	
    /**
     * Set point x
     * 
     * @param x
     *        point x
     */
    public void setX(final int x) {
	this.x = x; 
    }
	
    /**
     * Set point y
     * 
     * @param y
     *     point y
     */
    public void setY(final int y) {
        this.y = y;
    }
    
    /**
     * Set length. Length is formed by width and high, but is a square, for this 
     * one quantity is sufficient 
     * 
     * @param len
     *        length of this element 
     */
    public void setLen(final int len) {
        this.len = len;
    }
    
    /**
     * Set speed of point x
     * 
     * @param speedX
     *        speed point x
     */
    public void setSpeedX(final int speedX) {
        this.speedX = speedX;
    }
    
    /**
     * Set speed of point y
     * 
     * @param speedY
     *        speed point y
     */
    public void setSpeedY(final int speedY) {
        this.speedY = speedY;
    }
	   
    public boolean isCrash() {
        return crash;
    }
    
    public void setCrash (final boolean crash) {
        this.crash = crash;
    }
    
}
