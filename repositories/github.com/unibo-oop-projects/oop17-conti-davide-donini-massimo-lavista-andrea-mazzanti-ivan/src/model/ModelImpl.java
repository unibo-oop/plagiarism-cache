package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.data.GameData;
import model.data.GameDataImpl;
import model.data.GlobalData;
import model.data.GlobalDataImpl;
import model.entities.ActiveEnemy;
import model.entities.Bullet;
import model.entities.BulletType;
import model.entities.Enemy;
import model.entities.Entity;
import model.entities.Spaceship;
import model.entities.SpaceshipImpl;
import model.entities.properties.DirectionX;
import model.entities.properties.DirectionY;
import model.entities.properties.Position;
import model.factories.PowerUpFactoryImpl;
import model.manager.Collisions;
import model.manager.GarbageCollector;
import model.manager.GarbageCollectorImpl;
import model.spawner.StoryModeSpawner;
import model.spawner.InfiniteModeSpawner;
import model.spawner.Spawner;
import model.utilities.Shapes;
import settings.SettingsImpl;
import utility.GameModes;
import model.entities.powerup.PowerUp;
import model.entities.powerup.PowerUpType;

/**
 * Defines the main class of the model. Contains all the model entities and
 * updates them.
 */
public class ModelImpl implements Model {

    /**
     * world's width.
     */
    public static final int GAME_WIDTH = 600 * 6 / 7;
    /**
     * world's height.
     */
    public static final int GAME_HEIGHT = 600;

    private Spaceship spaceship;
    private final List<Enemy> enemies;
    private final List<Bullet> bullets;
    private final List<PowerUp> powerUp;
    private GameData gameData;
    private final GlobalData globalData;
    private Spawner enemySpawner;
    private final GarbageCollector garbageCollector;

    /**
     * 
     * Initialize fields.
     * 
     * @param globalData
     *            restore saves.
     */
    public ModelImpl(final Optional<GlobalData> globalData) {
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.powerUp = new ArrayList<>();
        this.globalData = globalData.isPresent() ? globalData.get() : new GlobalDataImpl();
        this.garbageCollector = new GarbageCollectorImpl(GAME_WIDTH, GAME_HEIGHT);
    }

    @Override
    public final List<Entity> getEntities() {
        final List<Entity> entities = new ArrayList<>();
        entities.addAll(this.bullets);
        entities.addAll(this.powerUp);
        entities.addAll(this.enemies);
        entities.add(this.spaceship);
        return entities.stream().collect(Collectors.toList());
    }

    @Override
    public final GameData getGameData() {
        return this.gameData.unmodifiableGameData();
    }

    @Override
    public final GlobalData getGlobalData() {
        return this.globalData;
    }

    @Override
    public final void initGame(final GameModes gameMode) {
        this.gameData = new GameDataImpl();
        this.spaceship = new SpaceshipImpl();
        this.gameData.setLife(this.spaceship.getLife());
        this.gameData.setDamage(this.spaceship.getSpaceshipDamage());
        this.gameData.setRateOfFire(this.spaceship.getBulletFiredForMinute());

        switch (gameMode) {
        case STORY_MODE:
            this.enemySpawner = new StoryModeSpawner(this.spaceship);
            break;

        case SURVIVAL_MODE:
            this.enemySpawner = new InfiniteModeSpawner(this.spaceship);
            break;

        default:
            this.enemySpawner = new StoryModeSpawner(this.spaceship);
            break;

        }

        this.enemies.clear();
        this.bullets.clear();
        this.powerUp.clear();
    }

    @Override
    public final void update(final int timeElapsed) {
        // delete unnecessary entities
        this.garbageCollector.deleteUnnecessaryEntities(this.enemies, this.powerUp, this.bullets);
        // check collisions
        this.performCollisions();
        // update game data
        this.gameData.addTime(timeElapsed);
        if (this.spaceship.isActiveShield()) {
            this.gameData.turnOnShield();
        } else {
            this.gameData.turnOffShield();
        }
        this.gameData.setDamage(this.spaceship.getSpaceshipDamage());
        this.gameData.setRateOfFire(this.spaceship.getBulletFiredForMinute());
        this.gameData.setLife(this.spaceship.getLife());
        // update enemy spawner
        this.enemySpawner.update(timeElapsed);
        if (this.enemySpawner.canSpawn()) {
            this.enemies.addAll(this.enemySpawner.spawn());
        }
        // update entities
        this.powerUp.forEach(p -> p.update(timeElapsed));
        this.spaceship.update(timeElapsed);
        if (this.spaceship.canShoot()) {
            this.bullets.addAll(this.spaceship.shoot());
        }
        this.enemies.forEach(e -> {
            e.update(timeElapsed);
            if (e instanceof ActiveEnemy) {
                final ActiveEnemy enemy = (ActiveEnemy) e;
                if (enemy.canShoot()) {
                    this.bullets.addAll(enemy.shoot());
                }
            }
        });
        this.bullets.forEach(b -> {
            b.update(timeElapsed);
        });

    }

    @Override
    public final boolean isGameOver() {
        return ((Double) this.spaceship.getLife()).equals(0.0)
                || (this.enemySpawner.isSpawnFinished() && this.enemies.isEmpty());
    }

    @Override
    public final boolean isHighScore() {
        return this.globalData.isHighscore(this.gameData.getScore());
    }

    @Override
    public final void endGame(final Optional<String> name) {
        if (!SettingsImpl.getSettings().isTrainingMode()) {
            this.globalData.addGameData(this.gameData, name);
        }
        this.enemies.clear();
        this.powerUp.clear();
        this.bullets.clear();
    }

    @Override
    public final void setSpaceshipShoot(final boolean shoot) {
        this.spaceship.setFire(shoot);
    }

    @Override
    public final void setSpaceshipDirectionX(final DirectionX directionX) {
        if (this.spaceship.getDirection().getLeft() != directionX) {
            this.spaceship.setDirectionX(directionX);
        }
    }

    @Override
    public final void setSpaceshipDirectionY(final DirectionY directionY) {
        if (this.spaceship.getDirection().getRight() != directionY) {
            this.spaceship.setDirectionY(directionY);
        }
    }

    /* Handles the collisions between Game Entities. */
    private void performCollisions() {
        this.handleSpaceshipCollisions();
        this.handleFriendlyBulletsCollisions();
    }

    private void handleSpaceshipCollisions() {

        final List<Pair<Spaceship, Entity>> detectedCollisions = new ArrayList<>();

        this.powerUp.forEach(powerup -> {
            if (Collisions.checkShapesIntersection(powerup.getShape(), this.spaceship.getShape())) {
                detectedCollisions.add(new ImmutablePair<Spaceship, Entity>(this.spaceship, powerup));
            }
        });

        this.enemies.forEach(enemy -> {
            if (Collisions.checkShapesIntersection(enemy.getShape(), this.spaceship.getShape())) {
                detectedCollisions.add(new ImmutablePair<Spaceship, Entity>(this.spaceship, enemy));
            }
        });

        this.bullets.stream().filter(bullet -> !bullet.getBulletType().equals(BulletType.FRIENDLY)).forEach(bullet -> {
            if (Collisions.checkShapesIntersection(bullet.getShape(), this.spaceship.getShape())) {
                detectedCollisions.add(new ImmutablePair<Spaceship, Entity>(this.spaceship, bullet));
            }
        });

        // Manage detected collisions.
        detectedCollisions.forEach(collision -> {
            final Spaceship spaceship = collision.getKey();
            final Entity entity = collision.getValue();

            if (entity instanceof PowerUp) {
                spaceship.pickUpPowerUp(((PowerUp) entity).getType());
                this.gameData.increasePowerUpByType(((PowerUp) entity).getType());
                this.powerUp.remove(entity);
            } else if (entity instanceof Enemy) {
                final Enemy enemy = ((Enemy) entity);
                if (!SettingsImpl.getSettings().isTrainingMode()) {
                    spaceship.decreaseLife(enemy.getCollisionDamage());
                }

                enemy.decreaseLife(spaceship.getCollisionDamage());
                if (enemy.getLife() <= 0) {
                    this.gameData.increaseNumDeadEemies();
                    this.enemies.remove(enemy);
                }
            } else if (entity instanceof Bullet && !((Bullet) entity).getBulletType().equals(BulletType.FRIENDLY)) {
                if (!SettingsImpl.getSettings().isTrainingMode()) {
                    spaceship.decreaseLife(((Bullet) entity).getDamage());
                }
                this.bullets.remove(entity);
            }

        });
    }

    private void handleFriendlyBulletsCollisions() {

        final List<Pair<Bullet, Entity>> detectedCollisions = new ArrayList<>();

        this.bullets.stream().filter(bullet -> bullet.getBulletType().equals(BulletType.FRIENDLY)).forEach(bullet -> {
            this.enemies.forEach(enemy -> {
                if (Collisions.checkShapesIntersection(bullet.getShape(), enemy.getShape())) {
                    detectedCollisions.add(new ImmutablePair<Bullet, Entity>(bullet, enemy));
                }
            });
        });

        // Manage detected collisions.
        detectedCollisions.forEach(collision -> {
            final Bullet friendlyBullet = collision.getKey();
            final Entity entity = collision.getValue();

            if (entity instanceof Enemy) {
                final Enemy enemy = ((Enemy) entity);
                enemy.decreaseLife(friendlyBullet.getDamage());
                if (enemy.getLife() <= 0) {
                    if (enemy.getPowerUp().isPresent()) {
                        final Position powerUpPosition = Shapes.getEntityCenter(enemy);
                        final PowerUpType type = enemy.getPowerUp().get();
                        this.powerUp.add(new PowerUpFactoryImpl().createPowerUp(type, powerUpPosition));
                    }
                    this.gameData.increaseNumDeadEemies();
                    this.gameData.addScore(enemy.getScore());
                    this.enemies.remove(enemy);

                }
                this.bullets.remove(friendlyBullet);
            }
        });
    }

}
