package tq2.implementations.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import tq2.implementations.Id;
import tq2.implementations.SoundImpl;
import tq2.implementations.graphics.Frame;
import tq2.interfaces.*;
import tq2.interfaces.platform.*;

/**
 * The Class EntityImpl implements the interface Entity that contains the base elements for a 2D object.
 * The coordinates are relative to the bottom left corner of the bounding box of the object.
 * 
 * @author Francesco Gori
 */
public abstract class EntityImpl implements Entity, SoundSource, HasId {
	
	/** The default width of the object. */
	protected Integer defaultWidth;
	
	/** The default height of the object. */
	protected Integer defaultHeight;
		
	/** The Y coordinate of the object. */
	protected Double x, y;
	
	/** The height of the object. */
	protected Integer width, height;
	
	/** The scale of the object along the Y axis. */
	protected Double scaleX, scaleY;
	
	/** The velocity of the object along the Y axis. */
	protected Double velX, velY;

	/** The id of the object. */
	protected Id id;
	
	/** Whether the object is solid. */
	protected Boolean solid;
	
	/** Whether the object is enabled. */
	protected Boolean enabled;
	
	/** The alpha value of this object. */
	protected Float alpha;
	
	/** The Handler. */
	protected Handler handler;
	
	/** The layer this object resides in. */
	protected LevelLayer layer;
	
	/** The list of the animations of the object, each associated to a name. */
	protected HashMap<String, Animation> animations;
	
	/** The list of the sounds this object can play, each associated to a name. */
	protected HashMap<String, SoundImpl> sounds;
	
	/** The value of the index of the frame currently displayed. */
	protected Integer frameCounter;
	
	/** The name of the animation the object is currently playing. */
	protected String currentAnimation;
	
	/** The counter of the times the current animation has played. */
	protected Integer animLoop;
	
	/** The current direction of the object. */
	protected Integer facing;
	
	/**
	 * Instantiates a new EntityImpl setting or initializing the value of its variables.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param width the width of the bounding box of the object
	 * @param height the height of the bounding box of the object
	 * @param facing the direction of the object
	 * @param solid whether the object is solid
	 * @param enabled whether the object is enabled
	 * @param scaleX the scale of the object along the X axis
	 * @param scaleY the scale of the object along the Y axis
	 * @param velX the velocity of the object along the X axis
	 * @param velY the velocity of the object along the Y axis
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public EntityImpl (
			Integer x, Integer y,
			Integer width, Integer height,
			Integer facing,
			Boolean solid,
			Boolean enabled,
			Double scaleX, Double scaleY,
			Double velX, Double velY,
			Float alpha,
			Handler handler,
			LevelLayer layer
			) {
		
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		
		this.width = width;
		this.defaultWidth = width;
		
		this.height = height;
		this.defaultHeight = height;
		this.setX(x);
		this.setY(y);

		
		this.setFacing(facing);
		
		this.solid = solid;
		this.setEnabled(enabled);
				
		this.setVelX(velX);
		this.setVelY(velY);
		
		this.setAlpha(alpha);
		this.handler = handler;
		this.layer = layer;
		
		this.animations = new HashMap<String, Animation>();
		this.animations.put("Idle", null);
		
		this.frameCounter = 0;
		this.setCurrentAnimation("Idle");
		this.animLoop = 0;
		
		this.sounds = new HashMap<String, SoundImpl>();
		
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getHandler()
	 */
	public Handler getHandler() {
		return this.handler;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#layer()
	 */
	@Override
	public LevelLayer layer() {
		return this.layer;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#destroy()
	 */
	@Override
	public void destroy() {
		this.setEnabled(false);

		//stop and close all sounds contained in this objects before removint it from the layer
	    Iterator<Entry<String, SoundImpl>> it = this.sounds.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, SoundImpl> pair = (Map.Entry<String, SoundImpl>)it.next();
	        pair.getValue().stop();
	        it.remove();
	    }
		
		this.getHandler().removeEntity(this);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#die()
	 */
	@Override
	public void die() {
		this.destroy();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getBounds()
	 */
	@Override
	public Rectangle getBounds() {

		return new Rectangle(this.getX(),this.getY() - (this.getHeight().intValue()), (this.getWidth().intValue()), (this.getHeight().intValue()));
	}

	/* (non-Javadoc)
	 * @see platform.entity.Entity2#playAnimation(java.awt.Graphics2D, java.lang.String)
	 */
	@Override
	public void playAnimation (Graphics2D g, String animName) {
		
		
		Animation currentAnim = this.getAnimation(animName);
		
		if (currentAnim != null) {
			//the animation corresponding to the direction of the entity is selected
			Integer framesNum = this.frameCounter/currentAnim.getSpeed(this.getFacing());
			
			Frame frame = currentAnim.getFrame(framesNum, this.getFacing());

			//draw each frame translated of the relative X and Y offsets
			g.drawImage(
			frame.getBufferedImage(),
			((Double)(this.getX() + frame.getOffsetX()*this.getScaleX()*this.getFacing() + this.getWidth()*Math.max(-this.getFacing(), 0))).intValue(),
			((Double)(this.getY() - this.getHeight() + frame.getOffsetY()*this.getScaleY())).intValue(),
    		((Double)(frame.getWidth()*this.getScaleX()*this.getFacing())).intValue(),
    		((Double)(frame.getHeight()*this.getScaleY())).intValue(),
    		null);

			//don't increase the frame counter while the game is paused
			if (!this.getHandler().isPaused()) {
				this.frameCounter++;
			}
						
			//if the animation is over, start over			
			if ((this.frameCounter/(currentAnim.getSpeed(this.facing))) >= currentAnim.size(this.facing)) {
				this.frameCounter = currentAnim.getLoop(this.facing)*currentAnim.getSpeed(this.facing);
				this.animLoop++;
				if (animLoop >= 15) {
					animLoop = 1;
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.SoundSource#playSound(com.tq2.interfaces.Sound)
	 */
	@Override
	public void playSound (Sound sound) {
		
		//the object isn't on the screen it can't play any sound 
		if (this.getBounds().intersects(this.getHandler().getGame().getWindowBounds(this.layer()))) {
			if (sound != null) {
				sound.play();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.SoundSource#playSound(java.lang.String)
	 */
	@Override	
	public void playSound (String soundName) {
				
		//the object isn't on the screen it can't play any sound 
		if (this.getBounds().intersects(this.getHandler().getGame().getWindowBounds(this.layer()))) {
			Sound sound = this.sounds.get(soundName);
			if (sound != null) {
				sound.play();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.SoundSource#loopSound(java.lang.String, java.lang.Integer)
	 */
	@Override
	public void loopSound (String soundName, Integer loops) {
		
		//the object isn't on the screen it can't play any sound 
		if (this.getBounds().intersects(this.getHandler().getGame().getWindowBounds(this.layer()))) {
		Sound sound = this.sounds.get(soundName);
			if (sound != null) {
				sound.loop(loops);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.SoundSource#loopSound(java.lang.String)
	 */
	@Override
	public void loopSound (String soundName) {
		
		this.loopSound(soundName, 0);
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.SoundSource#stopSound(java.lang.String)
	 */
	@Override	
	public void stopSound (String soundName) {
		Sound sound = this.sounds.get(soundName);
		if (sound != null) {
			sound.reset();
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.platform.SoundSource#playSoundInstance(java.lang.String)
	 */
	@Override	
	public void playSoundInstance (String soundName) {
		
		//the object isn't on the screen it can't play any sound 
		if (this.getBounds().intersects(this.getHandler().getGame().getWindowBounds(this.layer()))) {
			Sound sound = this.sounds.get(soundName);
			if (sound != null) {
				sound.playInstance();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getAlpha()
	 */
	@Override
	public Float getAlpha() {
		return this.alpha;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getX()
	 */
	@Override
	public Integer getX() {		
		return this.x.intValue();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getY()
	 */
	@Override
	public Integer getY() {
		return this.y.intValue() + this.getHeight();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getXd()
	 */
	@Override
	public Double getXd() {
		return this.x;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getYd()
	 */
	@Override
	public Double getYd() {
		return this.y + this.getHeight();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getScaleX()
	 */
	@Override
	public Double getScaleX() {
		return this.scaleX;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getScaleY()
	 */
	@Override
	public Double getScaleY() {
		return this.scaleY;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getWidth()
	 */
	@Override
	public Integer getWidth() {
		return new Double(this.width*this.getScaleX()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getHeight()
	 */
	@Override
	public Integer getHeight() {
		return new Double(this.height*this.getScaleY()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getVelX()
	 */
	@Override
	public Double getVelX() {
		return this.velX;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getVelY()
	 */
	@Override
	public Double getVelY() {
		return this.velY;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#getFacing()
	 */
	@Override
	public Integer getFacing() {
		Integer facing = 1;
		if (this.facing == -1) {
			facing = -1;
		}
		return facing;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.HasId#getId()
	 */
	@Override
	public Id getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#isSolid()
	 */
	public Boolean isSolid() {
		return (this.solid && this.isEnabled());
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#isEnabled()
	 */
	public Boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Returns the name of the animation the object is currently playing.
	 *
	 * @return the name associated to the current animation
	 */
	public String getCurrentAnimation() {
		return this.currentAnimation;
	}
	
	/**
	 * Returns the animation associated to the specified name. The method returns the "Idle" animation if
	 * there's none associated to the specified name.
	 *
	 * @param animName the name of the animation
	 * @return the animation associated to the specified name, if there's any in the list.
	 */
	protected Animation getAnimation(String animName) {
		
		Animation anim;
		if (this.animations.containsKey(animName)) {
			anim = this.animations.get(animName);
		}
		else {
			anim = this.animations.get("Idle");
		}
		
		return anim;
	}
	
	/**
	 * Returns the sound associated to the specified name.
	 *
	 * @param soundName the name of the sound
	 * @return the sound associated to the specified name, if present
	 */
	protected Sound getSound(String soundName) {
		return this.sounds.get(soundName);
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setX(java.lang.Integer)
	 */
	@Override
	public void setX(Integer x) {
		this.x = x.doubleValue();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setX(java.lang.Double)
	 */
	@Override
	public void setX(Double x) {
		this.x = x;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setY(java.lang.Integer)
	 */
	@Override
	public void setY(Integer y) {
		this.y = y.doubleValue() - this.getHeight();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setY(java.lang.Double)
	 */
	@Override
	public void setY(Double y) {
		this.y = y - this.getHeight();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setScaleY(java.lang.Double)
	 */
	@Override
	public void setScaleY(Double scaleY) {
		this.scaleY = scaleY;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setScaleX(java.lang.Double)
	 */
	@Override
	public void setScaleX(Double scaleX) {
		this.scaleX = scaleX;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setVelX(java.lang.Double)
	 */
	@Override
	public void setVelX(Double velX) {

			this.velX = velX;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setVelY(java.lang.Double)
	 */
	@Override
	public void setVelY(Double velY) {
		this.velY = velY;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setVelX(java.lang.Integer)
	 */
	@Override
	public void setVelX(Integer velX) {
		this.velX = velX.doubleValue();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setVelY(java.lang.Integer)
	 */
	@Override
	public void setVelY(Integer velY) {
		this.velY = velY.doubleValue();
	}	
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setFacing(java.lang.Integer)
	 */
	@Override
	public void setFacing(Integer facing) {
		if (facing >= 0) {
			//right
			this.facing = 1;
		}
		else {
			//left
			this.facing = -1;
		}
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setAlpha(java.lang.Float)
	 */
	@Override
	public void setAlpha(Float alpha) {
		this.alpha = alpha;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setEnabled(java.lang.Boolean)
	 */
	@Override
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#toggle()
	 */
	@Override
	public void toggle() {
		this.enabled = !this.isEnabled();		
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Entity#setSolid(boolean)
	 */
	@Override
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	/* (non-Javadoc)
	 * @see platform.entity.Entity2#setCurrentAnimation(java.lang.String)
	 */
	@Override
	public void setCurrentAnimation(String anim) {
		//if the animation is not the one currently playing, reset the frame counter
		if (this.animations.containsKey(anim)) {
			this.currentAnimation = anim;
			this.frameCounter = 0;
			this.animLoop = 0;
		}
	}
}
