package it.unibo.isaccoop.model.ai;

import java.util.List;

import it.unibo.isaccoop.model.boundingbox.BoundingBox;
import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.Removable;
import it.unibo.isaccoop.model.enemy.Enemy;

/**
 * AIEnemy interface which represents the AI for enemies.
 * */
public interface AIEnemy extends Removable {

    /**
     * Method to perform enemies actions (move and hit) into the room linked to AI implementation.
     *
     * @param player player in game in order to handle enemy actions
     * @param containerBox containerbox of the element
     * */
    void updateEnemies(MapElement player, BoundingBox containerBox);

    /**
     * Get controlled enemies list of this {@link ConcreteAIEnemy}.
     *
     * @return controlled enemies list
     * */
    List<Enemy> getControlledEnemies();

}
