package model.components;

import model.entities.EntityModel;

/**
 * Allow the entity to attack.
 */
public class AttackImpl implements Attack {

    private final int attackValue;

    /**
     * @param attackValue the attack value of the component.
     */
    public AttackImpl(final int attackValue) {
        this.attackValue = attackValue;
    }

    @Override
    public final void applyDamage(final EntityModel entity) {
        if (entity.contain(LifeImpl.class)) {
            entity.getComponent(LifeImpl.class).decreaseLife(attackValue);
        }
    }
}
