package game;

import java.awt.Rectangle;

import utilities.Pair;
import view.EntityAnimator;

/**
 * A special effect that is spawned when a certain event occurs.
 */
public final class SpecialEffect extends TimedEntity {

    /**
     * This enum identifies the different types of Explosions in the game.
     */
    public enum SpecialEffectType {
        /**
         * What happens when an enemy dies.
         */
        EXPLOSION(81 * EntityAnimator.FRAMES_FOR_EACH_IMAGE);
        private final int lifetime;
        /**
         * @param lifetime the lifetime of the explosion
         */
        SpecialEffectType(final int lifetime) {
            this.lifetime = lifetime;
        }
        /**
         * @return the lifetime of this explosionType
         */
        public int getLifetime() {
            return this.lifetime;
        }
    }
 
    private final SpecialEffectType type;

    /**
     * @param id the {@link ID} of the Entity
     * @param position position of this powerUp
     * @param hitbox hitbox of this powerUp
     * @param type the type of this effect
     */
    public SpecialEffect(final ID id, final Pair<Integer, Integer> position, final Rectangle hitbox, final SpecialEffectType type) {
        super(position, 0, 0, id, type.getLifetime());
        this.setHitbox(hitbox);
        this.type = type;
    }

    @Override
    public void update() {
        this.tick();
        if (this.isEnded()) {
            this.setDead();
        }
    }

    @Override
    public void collide(final GameObject entity) {
    }

    /**
     * @return the type of the effect
     */
    public SpecialEffectType getType() {
        return this.type;
    }
}
