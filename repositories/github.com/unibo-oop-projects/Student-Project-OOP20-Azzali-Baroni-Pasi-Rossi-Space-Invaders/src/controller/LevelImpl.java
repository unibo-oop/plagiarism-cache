package controller;

import controller.PowerUp.SpawnerP;
import model.ID;
import model.BasicEnemy;
import model.BossEnemy;
import model.EnemyBehaviour;
import model.GameImpl;

/**
 * The Class LevelImpl manages the creation of the level.
 */
public class LevelImpl implements Level{
	
	/** The game impl. */
	private final GameImpl gameImpl;
	
	/** The Constant LEVEL_BOSS. */
	private static final int LEVEL_BOSS = 5;
    
    /** The spawner power up. */
    private final SpawnerP spawnerPowerUp;
    
    /** The meteors. */
    private final MeteorController meteors;
    
    /** The boss enemy. */
    private final EnemyBehaviour bossEnemy;
    
    /** The my level. */
    private int myLevel = 1;
    
    /** The current level. */
    private int currentLevel = 1;
    
    /** The n enemy. */
    private int nEnemy = 2;
    
    /** The n meteor. */
    private int nMeteor = 1;
    
    /**
     * Instantiates a new level impl.
     *
     * @param gameImpl the game impl
     */
    public LevelImpl(final GameImpl gameImpl) {
    	this.gameImpl = gameImpl;
    	this.spawnerPowerUp = new SpawnerP();
    	this.meteors = new MeteorController();
    	this.bossEnemy = new BossEnemy(this.gameImpl);
    	createSomething();
    }
    
    /**
     * Creates the power up.
     */
    private void createPowerUp() {
            gameImpl.getPlayerPowerUps().addAll(this.spawnerPowerUp.SpawnPowerUpPlayer(this.currentLevel));
            gameImpl.setGlobalPowerUp(this.spawnerPowerUp.spawnGlobalPowerUp().orElse(null));
    }
    
    /**
     * Creates the.
     *
     * @param number the number
     * @param id the id
     */
    private void create(final int number, final ID id) {
        switch (id) {
        case BASIC_ENEMY: 
        	for (int i = 0; i < number; i++) {
            gameImpl.getEnemies().get().add(new BasicEnemy(gameImpl).createThisEnemy());
        	}
        break;

        case BOSS_ENEMY: 
        	gameImpl.getEnemies().get().add(bossEnemy.createThisEnemy());
        break;
        
        case METEOR: 
        	for (int i = 0; i < number; i++) {
            gameImpl.getMeteor().add(meteors.createMeteor());
        }

        default: 
            break;
        }
    }
    
    /**
     * Creates the something.
     */
    private void createSomething() {
    	createPowerUp();
    	switch (myLevel) {
        case 1: 
        	create(nEnemy, ID.BASIC_ENEMY);
        	create(nMeteor, ID.METEOR);
        break;
        case 2: 
        	create(nEnemy, ID.BASIC_ENEMY);
        	create(nMeteor, ID.METEOR);
        break;
        case 3: 
        	create(nEnemy, ID.BASIC_ENEMY);
        	create(nMeteor, ID.METEOR);
        break;
        case 4: 
        	create(nEnemy, ID.BASIC_ENEMY);
        	create(nMeteor, ID.METEOR);
        break;
        case LEVEL_BOSS: 
        	create(1, ID.BOSS_ENEMY);
        	create(nMeteor, ID.METEOR);
        break;
        default:
            break;
        }
    }

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	@Override
	public int getLevel() {
		return this.currentLevel;
	}

	/**
	 * Next level.
	 */
	@Override
    public void nextLevel() {
		gameImpl.getPlayer().resetPosition();
        currentLevel++;
        myLevel++;
        nEnemy++;
        if (myLevel <= LEVEL_BOSS) {
            createSomething();
        } else {
            myLevel = 1;
            nEnemy++;
            nMeteor++;
            createSomething();
        }
    }

}
