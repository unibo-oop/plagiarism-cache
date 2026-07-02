package dev.spaccabolle.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 * The Class UIObject.
 */
public abstract class UIObject {
	
	/** The y. */
	protected float x, y;
	
	/** The altezza. */
	protected int larghezza, altezza;
	
	/** The bounds. */
	protected Rectangle bounds;
	
	/** The sopra. */
	protected boolean sopra = false;
	
	/**
	 * Instantiates a new UI object.
	 *
	 * @param x the x
	 * @param y the y
	 * @param larghezza the larghezza
	 * @param altezza the altezza
	 */
	public UIObject(float x, float y, int larghezza, int altezza){
		this.x = x;
		this.y = y;
		this.larghezza = larghezza;
		this.altezza = altezza;
		bounds = new Rectangle((int) x, (int) y, larghezza, altezza);
	}
	
	/**
	 * Tick.
	 */
	public abstract void tick();
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public abstract void render(Graphics g);
	
	/**
	 * On click.
	 */
	public abstract void onClick();
	
	/**
	 * On mouse move.
	 *
	 * @param e the e
	 */
	public void onMouseMove(MouseEvent e){
		if(bounds.contains(e.getX(), e.getY()))
			sopra = true;
		else
			sopra = false;
	}
	
	/**
	 * On mouse release.
	 *
	 * @param e the e
	 */
	public void onMouseRelease(MouseEvent e){
		if(sopra)
			onClick();
	}
	
	// Getters and setters

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return larghezza;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.larghezza = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return altezza;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.altezza = height;
	}

	/**
	 * Checks if is hovering.
	 *
	 * @return true, if is hovering
	 */
	public boolean isHovering() {
		return sopra;
	}

	/**
	 * Sets the hovering.
	 *
	 * @param hovering the new hovering
	 */
	public void setHovering(boolean hovering) {
		this.sopra = hovering;
	}

}