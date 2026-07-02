package controller.PowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import model.GameImpl;
import model.ID;
import model.powerup.GPowerUp;
import model.powerup.PPowerUp;
import model.powerup.PowerUpImpl;
import model.powerup.PowerUpT;
import utility.Clamp;
import utility.Pair;

/**
 * The Class to spawner PowerUp.
 */
public class SpawnerP {
	
	/** The Constant PROBABILITY_POWER_UP. */
	private static final int PROBABILITY_POWER_UP = 5;
	
	/** The Constant LEVEL_LIMIT_FOR_LOW. */
	private static final int LEVEL_LIMIT_FOR_LOW = 10;
    
    /** The Constant LEVEL_LIMIT_FOR_BASIC. */
    private static final int LEVEL_LIMIT_FOR_BASIC = 20;
	
	/** The spawn frezee. */
	private final int spawnFrezee;

	
	/**
	 * Instantiates a new spawner P.
	 */
	public SpawnerP() {
		final Random random = new Random();
		this.spawnFrezee = random.nextInt(PROBABILITY_POWER_UP);
		
	}
	
	/**
	 * Spawn power up player.
	 *
	 * @param level the level
	 * @return the list of powerUp
	 */
	public List<PPowerUp> SpawnPowerUpPlayer(final int level){
		final List<PPowerUp>list = new ArrayList<>();
		final Random random = new Random();
		final Strategy strategy = level < LEVEL_LIMIT_FOR_LOW ? new LowStrategy() : level < LEVEL_LIMIT_FOR_BASIC ? new BasicStrategy(): new HighStrategy();
		int Number;
		PowerUpT type;
		int numberofSpawn = random.nextInt(3) +1;
		while(numberofSpawn > 0) {
			Number = random.nextInt(3);
			switch(Number) {
			case 0 : type = PowerUpT.HEALTH;
			break;
			case 1 : type = PowerUpT.SHIELD;
			break;
			default : type =PowerUpT.FIRE_BOOST;
			}
			list.add(new PPowerUp(this.generatePosition(),this.generateVelocity(),this.generateVelocity(),ID.POWER_UP, type, strategy));
			numberofSpawn--;
		}
		return list;
	}
	
	/**
	 * Generate position.
	 *
	 * @return the pair
	 */
	private Pair<Integer,Integer> generatePosition(){
		final Random random = new Random();
        final int xP = Clamp.clamp(random.nextInt(GameImpl.ARENA_WIDTH), 0 + PowerUpImpl.WIDTH / 2, GameImpl.ARENA_WIDTH - PowerUpImpl.WIDTH / 2);
        final int yP = 0;
        return new Pair<Integer, Integer>(xP, yP);
	}
	
	/**
	 * Generate velocity.
	 *
	 * @return the int
	 */
	private int generateVelocity() {
        final Random random = new Random();
        return random.nextInt(PowerUpT.values().length) + 1;
    }
	
	 /**
 	 * Spawn global power up.
 	 *
 	 * @return the optional
 	 */
 	public Optional<GPowerUp> spawnGlobalPowerUp() {
	        final Random random = new Random();
	        final int Number;
	        final PowerUpT type;
	        Number = random.nextInt(PROBABILITY_POWER_UP);
	         if (Number == this.spawnFrezee) {
	            type = PowerUpT.FREEZE;
	        } else {
	            return Optional.empty();
	        }
	        return Optional.of(new GPowerUp(this.generatePosition(), this.generateVelocity(), this.generateVelocity(),ID.POWER_UP, type));
	    }
	
}
