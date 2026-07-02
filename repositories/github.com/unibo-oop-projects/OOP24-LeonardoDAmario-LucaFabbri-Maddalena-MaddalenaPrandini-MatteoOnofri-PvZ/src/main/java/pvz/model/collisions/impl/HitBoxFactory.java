package pvz.model.collisions.impl;

import pvz.model.collisions.api.HitBox;
import pvz.utilities.Position;

/**
 * Factory class for creating {@link HitBox} instances for different entity types.
 */
public final class HitBoxFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private HitBoxFactory() { }

    /**
     * Enum representing the types of hitboxes and their widths.
     */
    public enum HitBoxType {

        /**
         * Hitbox for plants.
         */
        PLANT(0.6),
        /**
         * Hitbox for zombies.
         */
        ZOMBIE(0.5),
        /**
         * Hitbox for bullets.
         */
        BULLET(0.2),
        /**
         * Hitbox for lawnmowers.
         */
        LAWNMOWER(0.5);

        private final double width;

        /**
         * Constructs a HitBoxType with the specified width.
         *
         * @param width the width of the hitbox type
         */
        HitBoxType(final double width) {
            this.width = width;
        }

        /**
         * Gets the width of the hitbox type.
         *
         * @return the width
         */
        public double getWidth() {
            return width;
        }
    }

    /**
     * Creates a {@link HitBox} for the given position and type.
     *
     * @param pos  the position of the hitbox
     * @param type the type of hitbox
     * @return a new HitBox instance
     */
    public static HitBox createHitBox(final Position pos, final HitBoxType type) {
        return new HitBox() {

            private double centerX = pos.x();
            private final double width = type.getWidth();

            @Override
            public boolean isColliding(final HitBox hitbox) {
                return Math.abs(centerX - hitbox.getX()) < (width / 2 + hitbox.getWidth() / 2);
            }

            @Override
            public void update(final Position pos) {
                this.centerX = pos.x();
            }

            @Override
            public double getX() {
                return centerX;
            }

            @Override
            public double getWidth() {
                return width;
            }
        };
    }
}
