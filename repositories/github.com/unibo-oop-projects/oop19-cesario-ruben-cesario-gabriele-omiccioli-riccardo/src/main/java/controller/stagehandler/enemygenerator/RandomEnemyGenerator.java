package controller.stagehandler.enemygenerator;

import model.entity.EntityID;

/**
 * Randomly chooses what types of enemy to spawn.
 */
public class RandomEnemyGenerator extends AbstractEnemyGenerator {

    /**
     * {@inheritDoc}
     * A RandomEnemyGenerator generates a random enemy chosen
     * between the spawnable enemies.
     */
    @Override
    public EntityID next() {
        return super.getSpawnableEnemies().stream()
                                          .skip((int) (super.getSpawnableEnemies().size() * Math.random()))
                                          .findFirst().get();
    }

}
