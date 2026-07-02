package it.unibo.isaccoop.model.ai;

import java.util.ArrayList;
import java.util.List;

import it.unibo.isaccoop.model.boundingbox.BoundingBox;
import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.enemy.Enemy;

/**
 * ConcreteAIEnemy which is a base AIEnemy implementation.
 * */
public final class ConcreteAIEnemy implements AIEnemy {

    private final List<Enemy> controlledEnemies;

    /**
     * Constructor for {@link ConcreteAIEnemy}.
     *
     * @param enemies enemies to attach to {@link ConcreteAIEnemy} as a {@link List}
     * */
    public ConcreteAIEnemy(final List<Enemy> enemies) {
        this.controlledEnemies = new ArrayList<>(enemies);
    }

    @Override
    public void updateEnemies(final MapElement player, final BoundingBox containerBox) {
        this.controlledEnemies.forEach(enemy -> {
            enemy.move(player.getCoords(), containerBox);
            enemy.hit(player.getCoords());
            enemy.getWeaponShots().ifPresent(shots -> shots.forEach(shot -> shot.tickShot()));
        });
    }

    @Override
    public List<Enemy> getControlledEnemies() {
        return List.copyOf(this.controlledEnemies);
    }

    /**
     * Remove the enemy 'e' from the list.
     * @param e
     */
    @Override
    public void remove(final MapElement e) {
        this.controlledEnemies.remove(e);
    }

}
