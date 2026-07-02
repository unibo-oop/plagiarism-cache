package tmw.controller.entities;

import tmw.common.V2d;
import tmw.controller.world.WorldController;
import tmw.model.entities.Enemy;

/**
 * This class represents the controller for a standard enemy in the game.
 */
public class EnemyController extends AbstractEnemyController<Enemy> {

    /**
     * Construct a new enemy controller.
     * 
     * @param worldController - The WorldController that is used by the controller
     *                        to communicate with the rest of the game
     * @param enemy           - The enemy that this controller has to handle
     */
    public EnemyController(final WorldController worldController, final Enemy enemy) {
        super(worldController, enemy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected V2d getNewMovementDirection() {
        return new V2d(this.getEntity().getCentralPosition(),
                this.getWorldController().getPlayer().getEntity().getCentralPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean readyToAttack() {
        return this.getEntity().readyToShoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void attack() {
        this.getEntity().shoot();
        this.getWorldController().addBullet(new EnemyBulletController(getWorldController(),
                this.getEntity().getCentralPosition(), this.getNewMovementDirection()));
    }

}
