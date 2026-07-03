package tq2.implementations.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tq2.implementations.entity.EntityImpl;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;
import tq2.interfaces.platform.Actor;
import tq2.interfaces.platform.tqgame.TQPlayer;

/**
 * The Class GUI.
 * 
 * @author Francesco Gori
 */
public class GUI extends EntityImpl {

	/** The Constant imagePath. */
	protected static final String imagePath = "/gui.png";
	
	/** The target. */
	protected TQPlayer target;
	
	/** The max hp. */
	protected Double maxHp;
	
	/** The hp. */
	protected Double hp;
	
	/** The max stamina. */
	protected Double maxStamina;
	
	/** The stamina. */
	protected Double stamina;
	
	/** The image. */
	protected BufferedImage image;
	
	/**
	 * Instantiates a new GUI element that displays the current health and stamina of a TQPlayer.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param width the width of the object
	 * @param height the height of the object
	 * @param enabled whether the object is enabled
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 * @param target the target this object will display the health and stamina of
	 */
	public GUI(Integer x, Integer y, Integer width, Integer height,	Boolean enabled, Float alpha, Handler handler, LevelLayer layer, TQPlayer target) {// Integer max, Integer val) {
		super(x, y, width, height, 0, false, enabled, 1.0, 1.0, 0.0, 0.0, 1.0f, handler, layer);
		this.setMaxHp(target.getMaxHp());
		this.setHp(target.getHp());
		this.setMaxStamina(target.getMaxStamina());
		this.setStamina(target.getStamina());
		
		this.setTarget(target);
		try {
			this.image = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println ("Unable to find \"" + imagePath + "\"");
		}
	}
	
	/** 
	 * Instantiates a new GUI element that displays the current health and stamina of a TQPlayer.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param width the width of the object
	 * @param height the height of the object
	 * @param handler the Handler
	 * @param layer the layer the object resides in
	 * @param target the target this object will display the health and stamina of
	 */
	public GUI(Integer x, Integer y, Integer width, Integer height, Handler handler, LevelLayer layer, TQPlayer target) {
		this(x, y, width, height, true, 1.0f, handler, layer, target);
	}

	/**
	 * Renders the object through a Graphics2D.
	 *
	 * @param g the Graphics2D object
	 */
	@Override
	public void render(Graphics2D g) {
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		//HP bar
        g.setColor(Color.RED);
		g.fillRect(
				this.getX() + 49,
				this.getY() + 5, 
				( (Double)(91 * this.getScaleX() * Math.max ( this.getHp()/this.getMaxHp(), 0.0) ) ).intValue(),
				( (Double)(18 * this.getScaleY() )
				).intValue());
		//Stamina bar
        g.setColor(Color.BLUE);
		g.fillRect(
				this.getX() + 5,
				this.getY() + 44, 
				( (Double)(9 * this.getScaleX() ) ).intValue(),
				( (Double)(95 * this.getScaleY() * Math.max ( this.getStamina() /this.getMaxStamina(), 0.0) )).intValue() );
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.drawImage(image, this.getX(), this.getY(), image.getWidth(), image.getHeight(), null);
	}

	/**
	 * Updates the information contained in this object.
	 */
	@Override
	public void tick() {
		this.setMaxHp(target.getMaxHp());
		this.setHp(target.getHp());
		this.setMaxStamina(target.getMaxStamina());
		this.setStamina(target.getStamina());
		
		
	}
	
	/**
	 * Returns the target this object keeps the information of.
	 *
	 * @return the target
	 */
	public Actor getTarget() {
		return this.target;
	}
	
	/**
	 * Sets the target this object will show the information of.
	 *
	 * @param target the new target
	 */
	public void setTarget(TQPlayer target) {
		this.target = target;
	}

	/**
	 * Returns the maximum HP value for the target of this object.
	 *
	 * @return the maximum HP value
	 */
	protected Double getMaxHp() {
		return maxHp;
	}

	/**
	 * Returns the current HP value of the target of this object.
	 *
	 * @return the HP value
	 */
	protected Double getHp() {
		return hp;
	}

	/**
	 * Returns the maximum stamina value for the target of this object.
	 *
	 * @return the maximum stamina value
	 */
	protected Double getMaxStamina() {
		return maxStamina;
	}

	/**
	 * Returns the current stamina value of the target of this object.
	 *
	 * @return the stamina value
	 */
	protected Double getStamina() {
		return stamina;
	}

	/**
	 * Sets the maximum HP amount this object can store. This will not affect the target.
	 *
	 * @param maxHp the new maximum HP value
	 */
	protected void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp.doubleValue();
	}

	/**
	 * Sets the HP value displayed by this object. This will not affect the target.
	 *
	 * @param hp the new HP value
	 */
	protected void setHp(Integer hp) {
		this.hp = hp.doubleValue();
	}

	/**
	 * Sets the maximum stamina amount this object can store. This will not affect the target.
	 *
	 * @param maxStamina the new maximum stamina amount
	 */
	protected void setMaxStamina(Integer maxStamina) {
		this.maxStamina = maxStamina.doubleValue();
	}

	/**
	 * Sets the stamina value displayed by this object. This will not affect the target.
	 *
	 * @param stamina the new stamina value
	 */
	protected void setStamina(Integer stamina) {
		this.stamina = stamina.doubleValue();
	}

	
}
