package game;

import game.buffs.PowerUpSpawner;
import game.enemy.BasicEnemy;
import game.enemy.FastEnemy;
import game.enemy.InterfaceEnemy;
import game.enemy.BigEnemy;
import game.enemy.BossEnemy;
import game.enemy.SmartEnemy;
import game.obstacles.ObstacleFactory;
import game.obstacles.ObstacleFactoryImpl;

/**
 * Class to manage the level {@link Level} implements {@link InterfaceLevel}.
 *
 */
public class Level implements InterfaceLevel {

    private final GameImpl game;
    private static final int TIME_TO_SPAWN_POWER_UPS = 800;
    private static final int LEVELBOSS = 5;
    private final PowerUpSpawner puSpawner;
    private final ObstacleFactory obstacles;
    private final InterfaceEnemy bossEnemy;
    private final InterfaceEnemy fastEnemy;
    private int myLevel = 1;
    private int currentLevel = 1;
    private int nEnemy = 2;
    private int count;
    private int nObstacle = 1;

    /**
     * Classic constructor for Level Class. {@link Level}
     * @param game GameImpl
     */
    public Level(final GameImpl game) {
        this.puSpawner = new PowerUpSpawner();
        this.game = game;
        this.obstacles = new ObstacleFactoryImpl();
        this.bossEnemy = new BossEnemy(this.game);
        this.fastEnemy = new FastEnemy(this.game);
        if (game.getMode() == GameMode.SINGLEPLAYER) {
            createSomething();
        } else {
            count++;
        }
    }
    /**
     * Method of {@link InterfaceLevel}.
     */
    @Override
    public void nextLevelSingle() {
        game.getPlayer().get(0).resetPosition();
        currentLevel++;
        myLevel++;
        if (myLevel <= LEVELBOSS) {
            createSomething();
        } else {
            myLevel = 1;
            nEnemy++;
            nObstacle++;
            createSomething();
        }
    }
    /**
     * Method for create a number of {@link game.enemy.InterfaceEnemy} and to create a number of {@link game.obstacles.AbstractObstacle}.
     */
    private void createSomething() {
        createPowerUps();
        switch (myLevel) {
        case 1: create(nEnemy, ID.BASIC_ENEMY);
        create(nObstacle, ID.SMP_OBSTACLE);
        break;
        case 2: create(nEnemy, ID.FAST_ENEMY);
        create(nObstacle, ID.BNC_OBSTACLE);
        break;
        case 3: create(nEnemy, ID.BIG_ENEMY);
        create(nObstacle, ID.TML_OBSTACLE);
        break;
        case 4: create(nEnemy, ID.SMART_ENEMY);
        create(nObstacle, ID.ENL_BNC_OBSTACLE);
        break;
        case LEVELBOSS: create(1, ID.BOSS_ENEMY);
        break;
        default:
            break;
        }
    }
    /**
     * Method of {@link InterfaceLevel}.
     */
    @Override
    public int getLevel() {
        return this.currentLevel;
    }
    /**
     * Method of {@link InterfaceLevel}.
     */
    @Override
    public void resetLevel() {
        myLevel = 1;
        currentLevel = 1;
        nEnemy = 2;
        nObstacle = 1;
    }
    /**
     * Method to create power up. {@link game.buffs.PowerUpSpawner}
     */
    private void createPowerUps() {
        if (game.getMode() == GameMode.SINGLEPLAYER) {
            game.getPlayerPowerUps().addAll(this.puSpawner.spawnPlayerPowerUps(this.currentLevel));
            game.setGlobalPowerUp(this.puSpawner.spawnGlobalPowerUp().orElse(null));
        } else {
            game.getPlayerPowerUps().addAll(this.puSpawner.spawnPlayerPowerUps(this.currentLevel));
        }
    }
    /**
     * Method of {@link InterfaceLevel}.
     */
    @Override
    public void nextLevelMulti() {
        if (count == (TIME_TO_SPAWN_POWER_UPS)) {
            createPowerUps();
            count = 0;
            currentLevel++;
        }
        count++;
    }
    /**
     * Method to create enemy {@link game.enemy.Interface} or obstacles {@link game.obstacles.AbstractObstacle}.
     * @param number
     * @param id
     */
    private void create(final int number, final ID id) {
        switch (id) {
        case BASIC_ENEMY: for (int i = 0; i < number; i++) {
            game.getEnemies().get().add(new BasicEnemy(game).createThisEnemy());
        }
        break;
        case FAST_ENEMY: for (int i = 0; i < number; i++) {
            game.getEnemies().get().add(new FastEnemy(game).createThisEnemy());
        }
        break;
        case BIG_ENEMY: for (int i = 0; i < number; i++) {
            game.getEnemies().get().add(new BigEnemy(game).createThisEnemy());
        }
        break;
        case SMART_ENEMY: for (int i = 0; i < number; i++) {
            game.getEnemies().get().add(new SmartEnemy(game).createThisEnemy());
        }
        break;
        case BOSS_ENEMY: game.getEnemies().get().add(bossEnemy.createThisEnemy());
            game.getEnemies().get().add(fastEnemy.createThisEnemy());
        break;
        case SMP_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createSimpleObstacle());
        }
        break;
        case BNC_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createBouncingObstacle());
        }
        break;
        case ENL_BNC_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createEnlargingBouncingObstacle());
        }
        break;
        case ENL_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createEnlargingObstacle());
        }
        break;
        case TML_ENL_BNC_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createTimeLimitedEnlargingBouncingObstacle());
        }
        break;
        case TML_ENL_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createTimeLimitedEnlargingObstacle());
        }
        break;
        case TML_BNC_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createTimeLimitedBouncingObstacle());
        }
        break;
        case TML_OBSTACLE: for (int i = 0; i < number; i++) {
            game.getObstacles().add(obstacles.createTimeLimitedObstacle());
        }
        break;
        default: 
            break;
        }
    }
}
