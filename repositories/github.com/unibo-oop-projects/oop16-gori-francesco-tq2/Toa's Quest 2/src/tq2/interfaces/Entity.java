package tq2.interfaces;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The Interface Entity. It contains the basic elements for an element of a 2D game. 
 * 
 * @author Francesco Gori
 */

public interface Entity extends HasId {
	
	/**
	 * Play animation.
	 *
	 * @param g the g
	 * @param animName the anim name
	 */
	public abstract void playAnimation(Graphics2D g, String animName);
	
	/**
	 * Render this Entity on the specified .
	 *
	 * @param g the g
	 */
	public abstract void render(Graphics2D g);

	/**
	 * Update the status of the Entity (movement, collision testing)
	 */
	public abstract void tick();

	/**
	 * Get the Layer of the Entity.
	 *
	 * @return the level layer
	 */
	public abstract LevelLayer layer();

	/**
	 * Destroy the Entity.
	 */
	public abstract void destroy();
	
	/**
	 * Specifies the behaviors of the Entity before being destroyed. By default, this method will only destroy the Entity.
	 */
	public abstract void die();
	
	/**
	 * Gets the Bounding Box of the Entity.
	 *
	 * @return the bounding box
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * Gets the alpha value of the Entity.
	 *
	 * @return the alpha
	 */
	public abstract Float getAlpha();

	/**
	 * Gets the X coordinate of the Entity.
	 *
	 * @return the X coordinate
	 */
	public abstract Integer getX();

	/**
	 * Gets the Y coordinate of the Entity.
	 *
	 * @return the Y coordinate
	 */
	public abstract Integer getY();

	/**
	 * Gets the X coordinate of the player, as a Double.
	 *
	 * @return the X coordinate (as a Double)
	 */
	public abstract Double getXd();

	/**
	 * Gets the Y coordinate of the player, as a Double.
	 *
	 * @return the Y coordinate (as a Double)
	 */
	public abstract Double getYd();

	/**
	 * Gets the scale X of the Entity.
	 *
	 * @return the scale X
	 */
	public abstract Double getScaleX();

	/**
	 * Gets the scale Y of the Entity.
	 *
	 * @return the scale Y
	 */
	public abstract Double getScaleY();

	/**
	 * Gets the width of the Entity.
	 *
	 * @return the width
	 */
	public abstract Integer getWidth();

	/**
	 * Gets the height of the Entity.
	 *
	 * @return the height
	 */
	public abstract Integer getHeight();

	/**
	 * Gets the velocity of the Entity along the X axis.
	 *
	 * @return the velocity along the X axis
	 */
	public abstract Double getVelX();

	/**
	 * Gets the velocity of the Entity along the Y axis.
	 *
	 * @return the velocity along the Y axis
	 */
	public abstract Double getVelY();

	/**
	 * Gets the direction of the Entity.
	 *
	 * @return the direction
	 */
	public abstract Integer getFacing();

	/**
	 * Checks if the Entity is enabled.
	 *
	 * @return if the Entity is enabled or not
	 */
	public abstract Boolean isEnabled();
	
	/**
	 * Checks if the Entity is solid.
	 *
	 * @return if the Entity is solid or not
	 */
	public abstract Boolean isSolid();
	
	/**
	 * Gets the Handler of the Entity.
	 *
	 * @return the Handler
	 */
	public abstract Handler getHandler();

	/**
	 * Sets the X coordinate of the Entity.
	 *
	 * @param x the new X coordinate
	 */
	public abstract void setX(Integer x);

	/**
	 * Sets the X coordinate of the Entity (as a Double).
	 *
	 * @param x the new X coordinate
	 */
	public abstract void setX(Double x);

	/**
	 * Sets the Y coordinate of the Entity.
	 *
	 * @param y the new Y coordinate
	 */
	public abstract void setY(Integer y);

	/**
	 * Sets the Y coordinate of the Entity (as a Double).
	 *
	 * @param y the new Y coordinate
	 */
	public abstract void setY(Double y);

	/**
	 * Sets the scale along the Y axis.
	 *
	 * @param scaleY the new scale Y
	 */
	public abstract void setScaleY(Double scaleY);

	/**
	 * Sets the scale along the X axis.
	 *
	 * @param scaleX the new scale X
	 */
	public abstract void setScaleX(Double scaleX);

	/**
	 * Sets the velocity along the X axis.
	 *
	 * @param velX the new velocity along the X axis
	 */
	public abstract void setVelX(Double velX);

	/**
	 * Sets the velocity along the Y axis.
	 *
	 * @param velY the new velocity along the Y axis
	 */
	public abstract void setVelY(Double velY);

	/**
	 * Sets the velocity along the X axis.
	 *
	 * @param velX the new velocity along the X axis
	 */
	public abstract void setVelX(Integer velX);

	/**
	 * Sets the velocity along the Y axis.
	 *
	 * @param velY the new velocity along the Y axis
	 */
	public abstract void setVelY(Integer velY);

	/**
	 * Sets the direction of the Entity.
	 *
	 * @param facing the new direction
	 */
	public abstract void setFacing(Integer facing);

	/**
	 * Sets the alpha value of the Entity.
	 *
	 * @param alpha the new alpha value
	 */
	public abstract void setAlpha(Float alpha);

	/**
	 * Sets whether the Entity is enabled or not.
	 *
	 * @param enabled if the Entity is enabled or not
	 */
	public abstract void setEnabled(Boolean enabled);

	/**
	 * Toggles the Entity (enables it if it's disabled and vice versa).
	 */
	public abstract void toggle();

	/**
	 * Sets the current animation to the one specified.
	 *
	 * @param anim the animation to play
	 */
	public abstract void setCurrentAnimation(String anim);
	
	/**
	 * Sets whether the Entity is solid or not.
	 *
	 * @param solid if the Entity is solid or not
	 */
	public void setSolid(boolean solid);

}