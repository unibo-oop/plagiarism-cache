
package model.entities;

import org.jbox2d.common.Vec2;

import enumerators.EntityType;
import enumerators.SpecificType;
import model.components.Component;
import model.components.ComponentSetImpl;
import model.components.LifeImpl;
import model.components.UpdableComponent;
import model.physics.PhysicEntity;
import model.physics.Size2D;

/**
 * Represents an abstract implementation of the base interface
 * {@link EntityModel}.
 */
public abstract class AbstractEntityModel implements EntityModel {

    private final EntityType entityType;
    private final SpecificType specificType;
    private final PhysicEntity physicEntity;
    private final ComponentSetImpl<Component> myComponents;

    public AbstractEntityModel(final EntityType entityType, final SpecificType type, final PhysicEntity physicEntity) {
        this.physicEntity = physicEntity;
        this.entityType = entityType;
        this.specificType = type;
        this.myComponents = new ComponentSetImpl<>();
    }

    @Override
    public final EntityType getEntityType() {
        return entityType;
    }

    @Override
    public final SpecificType getSpecificType() {
        return specificType;
    }

    @Override
    public final PhysicEntity getPhysicEntity() {
        return physicEntity;
    }

    @Override
    public final Vec2 getPhysicPosition() {
        return physicEntity.getPosition();
    }

    @Override
    public final Size2D getDimension() {
        return physicEntity.getDimension();
    }

    @Override
    public final void add(final Component component) {
        myComponents.add(component);
    }

    @Override
    public final <C extends Component> C getComponent(final Class<C> component) {
        return myComponents.getComponent(component);
    }

    @Override
    public final <C extends Component> boolean contain(final Class<C> component) {
        return myComponents.isPresent(component);
    }

    @Override
    public final void update() {
        myComponents.stream()
                    .filter(c -> (c instanceof UpdableComponent))
                    .map(UpdableComponent.class::cast)
                    .forEach(c -> update());
    }

    @Override
    public final float getTopSide() {
        return (float) physicEntity.getPosition().y;
    }

    @Override
    public final float getLeftSide() {
        return (float) physicEntity.getPosition().x;
    }

    @Override
    public final float getBottomSide() {
        return (float) (physicEntity.getPosition().y + physicEntity.getDimension().y);
    }

    @Override
    public final float getRightSide() {
        return (float) (physicEntity.getPosition().x + physicEntity.getDimension().x);
    }

    @Override
    public final Vec2 getCenter() {
        final float halfH = (float) physicEntity.getDimension().y / 2;
        final float halfW = (float) physicEntity.getDimension().x / 2;
        return this.getPhysicPosition().add(new Vec2(halfW, halfH));
    }

    @Override
    public final void destroyModel() {
        myComponents.clear();
    }

    @Override
    public final boolean isAlive() {
        if (this.contain(LifeImpl.class)) {
            return this.getComponent(LifeImpl.class).isAlive();
        } else {
            return true;
        }
    }
}
