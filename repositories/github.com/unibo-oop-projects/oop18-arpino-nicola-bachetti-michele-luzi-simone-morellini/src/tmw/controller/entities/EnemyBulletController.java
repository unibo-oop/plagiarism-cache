package tmw.controller.entities;

import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.world.WorldController;

/**
 * This Class represents the controller for a bullet shot by an enemy.
 */
public class EnemyBulletController extends AbstarctBulletController {

    /**
     * Construct a new controller for a bullet shot by an enemy.
     * 
     * @param worldController - The WorldController that is used by the controller
     *                        to communicate with the rest of the game
     * @param pos             - the initial position of the bullet as a {@link P2d}
     * @param vel             - the initial velocity of the bullet as a {@link V2d}
     */
    public EnemyBulletController(final WorldController worldController, final P2d pos, final V2d vel) {
        super(worldController,
                worldController.getFactory().createEnemyBullet(pos, vel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkCollisions(final P2d newPosition) {

        if (this.getWorldController().getPlayer().getEntity().intersect(this.getEntity())) {
            this.getWorldController().getPlayer().getEntity().takeDamage(this.getEntity().getDamage());
            return true;
        }

        return false;
    }

}
