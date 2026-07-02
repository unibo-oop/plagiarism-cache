package thatlevelagain.character.enemies.collision;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Strategy for the collision between Rectangle at the bottom of the enemy and a mattone.
 * 
 * @author 
 *
 */
public class StrategyBottom extends Strategy {

    /**
     * StrategyBottom constructor. 
     * 
     * @param enemy 
     *          Object of class that implements Enemy Interface
     */
    public StrategyBottom(final EnemyInterface enemy) {
        super(enemy);
    }

    @Override
    public final boolean collision(final Mattoni mattone) {
        return this.getEnemy().getRectBottom().intersects(mattone.getRectUp());
    }

}
