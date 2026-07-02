package thatlevelagain.character.enemies.collision;

import thatlevelagain.character.enemies.EnemyInterface;
import thatlevelagain.view.sprite.Mattoni;

/**
 * Strategy for the collision between Rectangle at the side of the enemy and a mattone.
 * 
 * @author 
 * 
 */

public class StrategySide extends Strategy {

    /**
     * StrategySide constructor. 
     * 
     * @param enemy 
     *          Object of class that implements Enemy Interface
     */
    public StrategySide(final EnemyInterface enemy) {
        super(enemy);
    }

    @Override
    public final boolean collision(final Mattoni mattone) {
        return this.getEnemy().getRectLeft().intersects(mattone.getRectRight())
                || this.getEnemy().getRectRight().intersects(mattone.getRectLeft());
    }

}
