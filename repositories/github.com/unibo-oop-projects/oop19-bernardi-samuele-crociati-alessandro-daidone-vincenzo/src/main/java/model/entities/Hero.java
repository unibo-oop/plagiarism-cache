package model.entities;

import javafx.util.Pair;

public final class Hero extends EntityAbstract {

    public Hero(final String name, final int maxHP, final int attack, final int defense,
            final MovementType movementType, final AttackType attackType,
            final Pair<Integer, Integer> initialPosition) {
        this(name, maxHP, attack, defense, EntityType.HERO, EntityStatus.ALIVE, movementType, attackType,
                initialPosition);
    }

    private Hero(final String name, final int maxHP, final int attack, final int defense, final EntityType entityType,
            final EntityStatus entityStatus, final MovementType movementType, final AttackType attackType,
            final Pair<Integer, Integer> initialPosition) {
        super(name, maxHP, attack, defense, entityType, entityStatus, movementType, attackType, initialPosition);
    }

    @Override
    protected int calculateDamage(final int incomingDamage) {
        return incomingDamage - this.getDefense();
    }

}
