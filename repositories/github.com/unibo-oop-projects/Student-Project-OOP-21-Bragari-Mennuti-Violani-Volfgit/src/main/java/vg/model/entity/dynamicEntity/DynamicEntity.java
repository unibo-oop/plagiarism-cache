package vg.model.entity.dynamicEntity;

import vg.model.entity.ShapedEntity;
import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

public abstract class DynamicEntity extends ShapedEntity {
    /**
     * Entity movement speed.
     */
    private V2D speed;
    private V2D speedSaved;

    public DynamicEntity(final V2D position, final V2D speed, final int radius, final Shape shape, final MassTier massTier) {
        super(position, radius, shape, massTier);
        this.speed = speed;
        this.speedSaved = speed;
    }

    /**
     * Sums the speed to position and sets the result to
     * position.
     */
    public void move() {
        setPosition(getPosition().sum(getSpeed()));
    }
    /**
     * Return vector that represent current entity speed.
     * @return current entity speed
     */
    public V2D getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed of the entity.
     * @param newSpeed the speed to set to
     */
    public void setSpeed(final V2D newSpeed) {
        this.speed = newSpeed;
    }

    /* the actual logic of collisions is applied by MapImpl */
    /**
     * Evaluates what happens to the entity after a
     * collision. Generally an entity will "bounce"
     * if a collision occurs with another entity which
     * {@link MassTier} equal or higher, than it will
     * bounce, otherwise not. An exception is for entities
     * with mass tier of {@link MassTier#NOCOLLISION}, this
     * type of mass tier will never "bounce".
     * @param other the mass tier of the entity comparing to
     */
    public void afterCollisionAction(final MassTier other) {
        if (this.getMassTier().compareTo(other) <= 0 && !this.getMassTier().equals(MassTier.NOCOLLISION)) {
            this.bounces();
        }
    }

    private void bounces() {

    }

    public void saveMySpeed() {
        this.speedSaved = speed;
    }

    public void restoreMySpeed() {
        this.speed = speedSaved;
    }
}
