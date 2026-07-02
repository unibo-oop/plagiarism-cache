package model;

import java.awt.Rectangle;

import controller.ChronometerEntity;
import utility.Pair;
import view.EntityView;

/**
 * The Class SpecialEffect.
 */
public class SpecialEffect extends ChronometerEntity {
	
	/**
	 * The Enum SpecialEffectT.
	 */
	public enum SpecialEffectT {
		
		/** The explosion. */
		EXPLOSION (81 * EntityView.FRAME_IMAGE);
		
		/** The lifetime. */
		private final int lifetime;
		
		/**
		 * Instantiates a new special effect T.
		 *
		 * @param lifetime the lifetime
		 */
		SpecialEffectT(final int lifetime){
			this.lifetime = lifetime;
		}
		
		/**
		 * Gets the lifetime.
		 *
		 * @return the lifetime
		 */
		public int getLifetime() {
			return this.lifetime;
		}
		
	}
	
	/** The type. */
	private final SpecialEffectT type;
	
	/**
	 * Instantiates a new special effect.
	 *
	 * @param id the id
	 * @param position the position
	 * @param hitbox the hitbox
	 * @param type the type
	 */
	public SpecialEffect(final ID id, final Pair<Integer, Integer> position, final Rectangle hitbox, final SpecialEffectT type) {
		super (position, 0, 0, id, type.getLifetime());
		this.setHitbox(hitbox);
		this.type = type;
	}

	/**
	 * Update.
	 */
	@Override
	public void update() {
		this.tick();
		if(this.isEnded()) {
			this.setDead();
		}
	}
	
	/**
	 * Collide.
	 *
	 * @param entity the entity
	 */
	@Override
	public void collide (final Entity entity) {
		
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public SpecialEffectT getType() {
		return this.type;
	}
}
