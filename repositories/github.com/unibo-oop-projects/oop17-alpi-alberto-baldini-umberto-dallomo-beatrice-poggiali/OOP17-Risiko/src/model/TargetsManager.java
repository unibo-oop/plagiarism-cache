package model;

import java.util.Random;

/**
 * this class creates the targets array and associate one of it with each player .
 *
 */
public final class TargetsManager {

	private final int MAXDICE = 6;
	private final int MINDICE = 1;
	private static int[] targetsArray;

	/**
	 * 
	 * @param numPlayers is the size of the list players .
	 */
	public TargetsManager(final int numPlayers) {
		targetsArray = new int [numPlayers];
		Random rand = new Random();
		for (int i = 0; i < targetsArray.length; i++) {
			targetsArray[i] = rand.nextInt(MAXDICE) + MINDICE;
		}
	}
	
	/**
	 * 
	 * @return targetsArray is the array with inside the targets associated to each player.
	 */
	public int [] getTargetsArray() {
		return targetsArray;
	}
}
