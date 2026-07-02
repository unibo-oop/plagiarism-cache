package tmw.controller.entities;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import tmw.common.V2d;
import tmw.controller.world.WorldController;
import tmw.model.entities.BossEntity;

/**
 * This Class represents the controller for the boss of the game.
 */
public class BossController extends AbstractEnemyController<BossEntity> {

    private static final int TIME_TO_CHANGE_DIRECTION = 2000;
    private static final int SPECIAL_ATTACK_TIME = 1500;

    private final Random velXGenerator;
    private final Random velYGenerator;
    private V2d newDirection;
    private V2d chargeDirection;

    /**
     * Construct a new boss controller.
     * 
     * @param worldController - The WorldController that is used by the controller
     *                        to communicate with the rest of the game
     * @param enemy           - The enemy that this controller has to handle
     */
    public BossController(final WorldController worldController, final BossEntity enemy) {
        super(worldController, enemy);

        this.velXGenerator = new Random();
        this.velYGenerator = new Random();
        this.changeDirection();
        this.startChangingDirectionTimer();
    }

    private void startChangingDirectionTimer() {
        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                changeDirection();
                startChangingDirectionTimer();
            }

        }, TIME_TO_CHANGE_DIRECTION);
    }

    private void startChargingTimer() {
        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                getEntity().stopSpecialAttack();
            }
        }, SPECIAL_ATTACK_TIME);
    }

    /**
     * Returns the movement direction that the boss has to follow. If the boss is
     * charging the main character this returns the direction to reach it, otherwise
     * a random direction is returned.
     */
    @Override
    protected V2d getNewMovementDirection() {
        if (this.getEntity().isUsingTheSpecialAttack()) {
            return this.chargeDirection;
        }
        return this.newDirection;
    }

    private synchronized void changeDirection() {
        this.newDirection = new V2d(this.velXGenerator.nextInt(), this.velYGenerator.nextInt());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean readyToAttack() {
        if (this.getEntity().readyForSpecialAttack()) {
            this.getEntity().startSpecialAttack();
            this.chargeDirection = directionToMilk();
            this.startChargingTimer();
        }
        return this.getEntity().isUsingTheSpecialAttack() || this.getEntity().readyToShoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void attack() {
        if (!this.getEntity().isUsingTheSpecialAttack()) {
            this.getEntity().shoot();
            this.getWorldController().addBullet(new EnemyBulletController(getWorldController(),
                    this.getEntity().getCentralPosition(), this.directionToMilk()));
        }
    }

    private V2d directionToMilk() {
        return new V2d(this.getEntity().getCentralPosition(),
                this.getWorldController().getPlayer().getEntity().getCentralPosition());
    }

}
