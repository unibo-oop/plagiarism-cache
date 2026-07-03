package tq2.implementations.entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import tq2.implementations.graphics.AnimationImpl;
import tq2.implementations.graphics.Frame;
import tq2.interfaces.Animation;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * A TrailEffect object displays the given Animation for a short while before fading. It's useful to create a trail effect behind
 * objects to give an illusion of speed.
 * 
 * @author Francesco Gori
 */
public class TrailEffect extends EntityImpl {

	/** The Frame variable used to display the frames of the animation. */
	Frame frame;
	
	/**
	 * Instantiates a new TrailEffect.
	 *
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param animation the animation to display
	 * @param facing the direction of the object
	 * @param scaleX the X scale of the object
	 * @param scaleY the Y scale of the object
	 * @param velX the velocity of the object along the X axis
	 * @param velY the velocity of the object along the Y axis
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public TrailEffect(
			Integer x, Integer y,
			Animation animation,
			Integer facing,
			Double scaleX, Double scaleY,
			Double velX, Double velY,
			Float alpha,
			Handler handler, LevelLayer layer) {
		super (x, y, animation.getWidth(facing), animation.getHeight(facing), facing, false, true, scaleX, scaleY, velX, velY, alpha, handler, layer);
		
	}
	
	/**
	 * Instantiates a new TrailEffect.
	 *
	 * @param target the object that leaves behind a trail effect
	 * @param handler the Handler
	 */
	public TrailEffect(
			EntityImpl target,
			Handler handler) {
		super (target.getX(), target.getY(), new Double (target.getWidth()/target.getScaleX()).intValue(), new Double (target.getHeight()/target.getScaleY()).intValue(), target.getFacing(), false, true, target.getScaleX(), target.getScaleY(), 0.0, 0.0, target.getAlpha()/2, handler, target.layer());
		Animation anim = target.animations.get(target.getCurrentAnimation());
		this.frame = anim.getFrame(new Double (target.frameCounter/anim.getSpeed(this.getFacing())).intValue(), this.getFacing());
		animations.put("Idle", new AnimationImpl(frame));
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		this.playAnimation(g, "Idle");
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#tick()
	 */
	@Override
	public void tick() {
		//fade more at each tick and destroy when barely visible
		this.setAlpha(this.getAlpha() - 0.04f);
		if (alpha < 0.05) destroy();
	}
}
