package pvz.model.entities.impl;

import pvz.model.collisions.api.HitBox;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.collisions.impl.HitBoxFactory.HitBoxType;
import pvz.model.entities.api.Entity;
import pvz.utilities.Position;

/**
 * Abstract base class for all entities in the game.
 * <p>
 * Provides common implementations for position handling and hitbox management.
 * Specific entity types (e.g., plants, zombies, bullets) should extend this class.
 */
public abstract class AbstractEntity implements Entity {

    private Position position;
    private final HitBox hitBox;

    /**
     * Constructs an entity with the specifies initial position and hitbox type.
     *
     * @param position the initial position of the entity; must not be {@code null}.
     * @param hitBoxType the type of hitbox for collision detencion.
     */
    public AbstractEntity(final Position position, final HitBoxType hitBoxType) {
        this.position = position;
        this.hitBox = HitBoxFactory.createHitBox(position, hitBoxType);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * @inheritDoc
     */
    @Override
    public HitBox getHitBox() {
        return hitBox;
    }
}
