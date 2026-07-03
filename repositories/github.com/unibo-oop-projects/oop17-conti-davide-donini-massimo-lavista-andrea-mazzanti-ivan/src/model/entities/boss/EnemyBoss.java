package model.entities.boss;

import java.util.List;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.ModelImpl;
import model.entities.AimEnemy;
import model.entities.Bullet;
import model.entities.Spaceship;
import model.entities.properties.Velocity;
import model.entities.properties.VelocityImpl;
import model.factories.EnemyFactoryImpl;

/**
 * 
 * Final boss.
 *
 */
public final class EnemyBoss extends AimEnemy {

    private static final double PROPORTION = 1.0 / 6.0;
    private static final int SPEED = 100;
    private static final int DELTA = 50;
    private static final int RADIUS = 100;
    private static final int BOSS_LIFE = 75000;
    private static final int FIRST_PHASE_LIFE = BOSS_LIFE * 5 / 8;
    private static final int SECOND_PHASE_LIFE = BOSS_LIFE * 3 / 8;

    private boolean bossCanShoot;
    private int phaseNumber;
    private Phase phase;

    /**
     * 
     * @param velocity
     *            initial enemy's velocity.
     * @param shape
     *            initial enemy's shape.
     * @param life
     *            initial enemy's life.
     * @param fireRate
     *            of the enemy.
     * @param spaceship
     *            that the boss will try to destroy.
     */
    private EnemyBoss(final Velocity velocity, final Shape shape, final double life, final int fireRate,
            final Spaceship spaceship) {
        super(velocity, shape, life, fireRate, spaceship);
        this.phase = new PhaseOne(spaceship, new EnemyFactoryImpl());
        this.phaseNumber = 1;
        this.bossCanShoot = false;
    }

    /**
     * Boss's public constructor.
     * 
     * @param spaceship
     *            that the boss will try to destroy.
     */
    public EnemyBoss(final Spaceship spaceship) {
        this(new VelocityImpl(0, SPEED), new Circle(ModelImpl.GAME_WIDTH / 2, -RADIUS, RADIUS), BOSS_LIFE, 1,
                spaceship);
    }

    @Override
    public boolean canShoot() {
        return super.canShoot() && this.bossCanShoot;
    }

    @Override
    public List<Bullet> shoot() {
        super.shoot();
        return this.phase.shoot();
    }

    @Override
    public void update(final int time) {
        super.update(time);

        if (this.phaseNumber == 1) {
            this.firstBehaviorManager();
            if (super.getLife() <= FIRST_PHASE_LIFE) {
                this.phase = this.phase.getNextPhase().get();
                this.phaseNumber++;
                super.setVelocity(new VelocityImpl(0, SPEED));
                this.bossCanShoot = false;
            }
        } else if (this.phaseNumber == 2) {
            this.secondBehaviorManager();
            if (this.getLife() <= SECOND_PHASE_LIFE) {
                this.phase = this.phase.getNextPhase().get();
                this.phaseNumber++;
                super.setVelocity(new VelocityImpl(0, -SPEED));
                this.bossCanShoot = false;
            }
        } else if (this.phaseNumber == 3) {
            this.thirdBehaviorManager();
        }
    }

    private void firstBehaviorManager() {
        if (super.getPosition().getY() >= ModelImpl.GAME_HEIGHT * PROPORTION
                && super.getPosition().getY() <= ModelImpl.GAME_HEIGHT * PROPORTION + DELTA) {
            super.setVelocity(new VelocityImpl(0, 0));
            this.bossCanShoot = true;
            super.setFireRate(this.phase.getPhaseFireRate());
        }
    }

    private void secondBehaviorManager() {
        if (super.getPosition().getY() >= ModelImpl.GAME_HEIGHT / 2) {
            super.setVelocity(new VelocityImpl(0, 0));
            this.bossCanShoot = true;
            super.setFireRate(this.phase.getPhaseFireRate());
        }
    }

    private void thirdBehaviorManager() {
        this.firstBehaviorManager();
    }
}
