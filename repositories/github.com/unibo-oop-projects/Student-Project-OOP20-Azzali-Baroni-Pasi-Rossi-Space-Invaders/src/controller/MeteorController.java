package controller;


import java.util.Random;
import model.GameImpl;
import model.ID;
import model.MeteorImpl;
import utility.Pair;

/**
 * The Class MeteorController manages the creation of meteors
 */
public class MeteorController {

	/** The random. */
	private final Random random = new Random(100);
	
	/** The Constant LENGTH. */
	private static final int LENGTH = 50;
	
	/** The Constant VELOCITY. */
	private static final int VELOCITY = 5;
	
	/**
	 * Random spawn.
	 *
	 * @return the pair
	 */
	private Pair<Integer,Integer> RandomSpawn(){
		return new Pair<Integer,Integer>(this.random.nextInt(GameImpl.ARENA_HEIGHT - 2 * LENGTH) + LENGTH, 0);
		//per ora va bene poi si vedra
	}
	
	/**
	 * Creates the meteor.
	 *
	 * @return the meteor impl
	 */
	public MeteorImpl createMeteor() {
		return new MeteorImpl(this.RandomSpawn(), VELOCITY, VELOCITY, LENGTH, ID.METEOR);
	}
}
