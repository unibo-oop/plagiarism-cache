package zombieversity.model.entities.zombie;

import java.util.Optional;

import javafx.geometry.Point2D;
import zombieversity.model.entities.AbstractActiveLivingEntity;
import zombieversity.model.entities.EntityType;

/**
 * Models a zombie.
 *
 */
public class Zombie extends AbstractActiveLivingEntity {

    /**
     * Width of zombie's BoundingBox.
     */
    private static final int WIDTH = 15;
    /**
     * Height of zombie's BoundingBox.
     */
    private static final int HEIGHT = 15;

    private final int damageDealt;

    /**
     * 
     * @param startingPos starting position.
     * @param velocity    movement speed.
     * @param maxHp       health points at spawn moment.
     * @param damageDealt damage dealt when attacking player.
     */
    public Zombie(final Point2D startingPos, final double velocity, final int maxHp, final int damageDealt) {
        super(new Point2D(velocity, velocity), maxHp, startingPos, EntityType.ZOMBIE);
        super.setBBox(WIDTH, HEIGHT);
        this.damageDealt = damageDealt;
    }

    /**
     * 
     * @return damage dealt to player when hit.
     */
    public final int getDamage() {
        return this.damageDealt;
    }

    /**
     * Used to build a zombie depending on his charateristics.
     *
     */
    public static class Builder {

        private final Point2D spawnPoint;
        private Optional<Double> velocity;
        private Optional<Integer> maxHp;
        private Optional<Integer> damageDealt;

        /**
         * Instantiates a builder for a basic (weak) zombie.
         * @param spawnPoint zombie's starting position.
         */
        public Builder(final Point2D spawnPoint) {
            this.spawnPoint = spawnPoint;
            this.velocity = Optional.empty();
            this.maxHp = Optional.empty();
            this.damageDealt = Optional.empty();
        }

        /**
         * Sets zombie's movement speed.
         * @param velocity movement speed.
         * @return builder with velocity field set.
         */
        public final Builder velocity(final double velocity) {
            this.velocity = Optional.of(velocity);
            return this;
        }

        /**
         * Sets zombie's health points at spawn moment.
         * @param maxHp health points.
         * @return builder with maxHp field set.
         */
        public final Builder maxHp(final int maxHp) {
            this.maxHp = Optional.of(maxHp);
            return this;
        }

        /**
         * Sets zombie's damage dealt when attacking player.
         * @param dmg damage dealt.
         * @return builder with damageDealt field set.
         */
        public final Builder damageDealt(final int dmg) {
            this.damageDealt = Optional.of(dmg);
            return this;
        }

        /**
         * Builds zombie with fields set. 
         * If a field is not set before calling this method it is automatically set as the corresponding field
         * in WEAK tier.
         * @return a {@link Zombie} with all fields set.
         */
        public final Zombie build() {
            return new Zombie(spawnPoint, velocity.orElse(Tiers.WEAK.getVelocity()), maxHp.orElse(Tiers.WEAK.getMxHp()), damageDealt.orElse(Tiers.WEAK.getDamageDealt()));
        }
    }

}
