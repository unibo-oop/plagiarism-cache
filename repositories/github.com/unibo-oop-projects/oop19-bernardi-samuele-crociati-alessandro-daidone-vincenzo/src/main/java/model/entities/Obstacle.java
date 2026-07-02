package model.entities;

import javafx.util.Pair;

public final class Obstacle extends EntityAbstract {

    public Obstacle(final String name, final int maxHP, final int attack, final int defense,
            final Pair<Integer, Integer> initialPosition) {
        super(name, maxHP, attack, defense, EntityType.OBSTACLE, EntityStatus.ALIVE, MovementType.NONE, AttackType.NONE,
                initialPosition);
    }

    private Obstacle(final String name, final int maxHP, final int attack, final int defense,
            final EntityType entityType, final EntityStatus entityStatus, final MovementType movementType,
            final AttackType attackType, final Pair<Integer, Integer> initialPosition) {
        super(name, maxHP, attack, defense, entityType, entityStatus, movementType, attackType, initialPosition);
    }

    @Override
    protected int calculateDamage(final int incomingDamage) {
        return 1;
    }

}
