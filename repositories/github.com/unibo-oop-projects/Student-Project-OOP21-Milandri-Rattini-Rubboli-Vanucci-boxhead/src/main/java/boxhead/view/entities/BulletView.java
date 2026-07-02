package boxhead.view.entities;

import boxhead.model.entities.EntityType;
import boxhead.view.spriteutils.Sprite;

/**
 * View class for the {@link Bullet}.
 */
public class BulletView implements ShotView {

	private final Sprite sprite;
	
	public BulletView() {
		this.sprite = new Sprite(EntityType.BULLET);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDirection(final double angle) {
		this.getSprite().getImageView().setRotate(this.getSprite().getImageView().getRotate() + angle);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Sprite getSprite() {
		return this.sprite;
	}
	
}
