package boxhead.view.entities;

import boxhead.model.entities.EntityType;
import boxhead.view.spriteutils.Sprite;

/**
 * View class for the {@link Ammo}.
 */
public class AmmoView {
	
	private final Sprite sprite;
	
	public AmmoView() {
		this.sprite = new Sprite(EntityType.AMMO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Sprite getSprite() {
		return this.sprite;
	}
}
