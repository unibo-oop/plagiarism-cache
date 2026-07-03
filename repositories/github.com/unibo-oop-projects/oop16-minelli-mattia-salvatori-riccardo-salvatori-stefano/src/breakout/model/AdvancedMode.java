package breakout.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import breakout.model.entities.Brick;
import breakout.model.entities.PowerUp;
import breakout.model.entities.PowerUpEffect;
import breakout.model.entities.Projectile;
import breakout.model.entities.Wall;
import breakout.model.levels.BasicLevel;
import breakout.model.physics.GameObject;

/**
 * Class that implements the advance mode.
 */
public class AdvancedMode extends AbstractModel {

    // Power up
    private final List<PowerUp> powerUpList;
    private final PowerUpGenerator generator;
    private final List<PowerUpEffect> activePowerUp;

    // Player bullets
    private final List<Projectile> bullets;

    /**
     * Creates and advanced game.
     * 
     * @param lvlList
     *            the list of level to be played
     */
    public AdvancedMode(final List<BasicLevel> lvlList) {
        super(lvlList);
        this.powerUpList = new ArrayList<>();
        this.generator = new PowerUpGenerator(this.getCurrentLevel().getSpawnProb());
        this.activePowerUp = new ArrayList<>();
        this.bullets = new ArrayList<>();
    }

    @Override
    public void updateAll(final double time) {
        super.updateAll(time);
        // Power up update
        if (this.getGameStatus().equals(GameStatus.Dead) || this.getGameStatus().equals(GameStatus.Won)) {
            this.clearPowerUp();
        }
        updateAndRemove(this.powerUpList, time, p -> this.checkFall(p));
        updateAndRemove(this.bullets, time, p -> p.isOutOfBounds());
    }

    private void clearPowerUp() {
        this.powerUpList.clear();
        this.bullets.clear();
        // To avoid cuncurrent modification exception
        for (int i = this.activePowerUp.size() - 1; i >= 0; i--) {
            this.activePowerUp.get(i).disable(this);
        }
    }

    @Override
    public List<GameObject> checkCollisions() {
        final List<GameObject> collisions = super.checkCollisions();
        // Paddle taking power ups
        this.powerUpList.stream().filter(powerUp -> powerUp.collidedWith(this.getPaddle())).findAny()
                        .ifPresent(powerUp -> {
                            if (!this.activePowerUp.contains(powerUp.getEffectType())) {
                                this.activePowerUp.add(powerUp.getEffectType());
                                powerUp.applyTo(this);
                            }
                            collisions.add(powerUp);
                            this.powerUpList.remove(powerUp);
                        });

        // Check bullets hitting bricks
        final List<Projectile> bullets = new ArrayList<>(this.getBullets());
        bullets.forEach(projectile -> {
            this.getBricks().stream().filter(brick -> projectile.collidedWith(brick)).findAny().ifPresent(brick -> {
                collisions.add(projectile);
                this.bullets.remove(projectile);
                brick.hit();
                this.gameUpdateOnBrickCollision(brick);
                if (brick.isDead()) {
                    this.setScore(this.getScore() + brick.getBrickValue());
                }
            });
        });
        return new ArrayList<>(collisions);
    }

    /**
     * Creates projectiles at paddle's sides.
     */
    public void bulletSpawn() {
        this.bullets.addAll(Arrays.asList(AdvancedFactory.get().createProjectile(this.getPaddle().getPosition()),
                AdvancedFactory.get()
                               .createProjectile(this.getPaddle().getPosition().add(this.getPaddle().getWidth(), 0))));
    }

    /**
     * Getter for all the power up on the game space.
     * 
     * @return a list of power up
     */
    public List<PowerUp> getPowerUps() {
        return Collections.unmodifiableList(this.powerUpList);
    }

    /**
     * Getter for all the active power up currently affecting the game.
     * 
     * @return a list of all active power up
     */
    public List<PowerUpEffect> getActivePowerUps() {
        return Collections.unmodifiableList(this.activePowerUp);
    }

    /**
     * Getter for all the projectiles on the game space.
     * 
     * @return a list of projectiles
     */
    public List<Projectile> getBullets() {
        return Collections.unmodifiableList(this.bullets);
    }

    /**
     * Remove a powerUp from the list.
     * 
     * @param powerUpType
     *            The type of powerup to remove
     */

    public void removePowerUp(final PowerUpEffect powerUpType) {
        this.activePowerUp.remove(powerUpType);
    }

    @Override
    protected void gameUpdateOnBrickCollision(final Brick brick) {
        if (brick.isDead()) {
            // Randomly generate a power up after a brick destruction
            final Optional<PowerUp> randPowerUP = this.generator.generateFrom(brick.getPosition());
            if (randPowerUP.isPresent()) {
                this.powerUpList.add(randPowerUP.get());
            }
        }
    }

    @Override
    protected void gameUpdateOnPaddleCollision() {

    }

    @Override
    protected void gameUpdateOnWallCollision(final Wall wall) {

    }

    @Override
    protected GameObjectFactory makeFactory() {
        return AdvancedFactory.get();
    }
}
