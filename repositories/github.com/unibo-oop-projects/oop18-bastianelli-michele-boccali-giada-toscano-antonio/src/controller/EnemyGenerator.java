package controller;

import java.util.Random;
import java.util.Set;

import org.jbox2d.common.Vec2;

import controller.entities.Enemy;
import controller.entities.Platform;
import enumerators.EnemyCharacter;
import enumerators.Level;
import factories.AbstractFactory;
import utils.EntityCreationUtils;

/**
 * Class that handles the enemy generation, based on difficulty. Must be initialized.
 */
public class EnemyGenerator implements Generator {

    private static final int MAX_ENEMIES = 2;
    private static final int DEFAULT_ENEMY_SPAWN_RATE = 5;
    private static final Random RANDOM = new Random();
    private Level level;
    private EnemyCharacter levelEnemy;

    @Override
    public final void init(final Level level) {
        this.level = level;
        this.levelEnemy = this.level.getEnemyCharacter();
    }

    @Override
    public final void update() {
        final Set<Enemy> enemies = GameController.getInstance().getGameModel().getEnemiesSet();

        final Set<Platform> platforms = GameController.getInstance().getGameModel().getPlatformSet();
        if (enemies.size() < MAX_ENEMIES && !platforms.isEmpty()) {
             final Platform platform = GameController.getInstance().getGameModel().getTopPlatform();
             generateEnemy(platform);
        }
    }

    private void generateEnemy(final Platform platform) {
        final int spawnRate = (int) (DEFAULT_ENEMY_SPAWN_RATE / level.getDifficulty());
        if (RANDOM.nextInt(spawnRate) == 0 && !platform.isBusy()) {
            final Vec2 pos = EntityCreationUtils.getPositionOnPlatform(platform, levelEnemy.getWidth(), levelEnemy.getHeight());
            final Enemy enemy = AbstractFactory.createEnemy(levelEnemy, pos);
            GameController.getInstance().getGameModel().addEntityToMap(enemy, enemy.getBody());
            //Log.add("generated enemy on " + pos.x + "-" + pos.y);
            platform.setBusy(true);
        }
    }
}
