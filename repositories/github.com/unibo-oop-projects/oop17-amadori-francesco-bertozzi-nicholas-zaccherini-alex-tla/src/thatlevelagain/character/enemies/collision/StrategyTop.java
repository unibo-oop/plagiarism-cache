package thatlevelagain.character.enemies.collision;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Strategy for the collision between Rectangle at the top of the enemy and a mattone.
 * 
 * @author
 *
 */
public class StrategyTop extends Strategy {

    /**
     * StrategyTop constructor. 
     * 
     * @param enemy 
     *          Object of class that implements Enemy Interface
     */
    public StrategyTop(final EnemyInterface enemy) {
        super(enemy);
    }

    @Override
    public final boolean collision(final Mattoni mattone) {
         return this.getEnemy().getRectUp().intersects(mattone.getRectBottom());
    }

}
