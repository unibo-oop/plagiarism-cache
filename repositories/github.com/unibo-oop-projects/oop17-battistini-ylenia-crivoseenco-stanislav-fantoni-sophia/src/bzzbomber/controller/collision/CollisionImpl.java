package bzzbomber.controller.collision;

import java.awt.Rectangle;
import java.util.Set;

import bzzbomber.model.entities.Block;
import bzzbomber.model.entities.Bomb;
import bzzbomber.model.entities.Entity;
import bzzbomber.model.entities.Explosion;
import bzzbomber.model.utilities.Direction;

/**
 * This class implements the interface @Collision .
 *
 */
public class CollisionImpl implements Collision {

    private final Entity entity;
    private final Rectangle entityCollisionBox;

    /**
     * Constructor of @CollisionImpl .
     * 
     * @param entity
     *            The associated @Entity .
     */
    public CollisionImpl(final Entity entity) {
        this.entity = entity;
        this.entityCollisionBox = entity.getCollisionBox();
    }

    @Override
    public final boolean blockCollision(final Set<Block> blockSet) {
        return blockSet.stream().anyMatch((block) -> this.entityCollisionBox.intersects(block.getCollisionBox()));
    }

    @Override
    public final boolean bombCollision(final Set<Bomb> bombSet) {
        return bombSet.stream().anyMatch((bomb) -> this.entityCollisionBox.intersects(bomb.getCollisionBox()));
    }

    @Override

    public final boolean explosionCollision(final Set<Explosion> explosionSet) {
        return explosionSet.stream().anyMatch((expl) -> this.entityCollisionBox.intersects(expl.getCollisionBox()));

    }

    @Override
    public final void updateEntityHitbox(final Direction direction) {
        this.entityCollisionBox.setBounds(direction.getTranslation().x + this.entityCollisionBox.x,
                direction.getTranslation().y + this.entityCollisionBox.y, this.entity.getCollisionBox().width,
                this.entity.getCollisionBox().height);

    }

    @Override
    public final Entity getEntity() {
        return this.entity;
    }

    @Override
    public final Rectangle getCollisionBox() {
        return this.entityCollisionBox;
    }

}
