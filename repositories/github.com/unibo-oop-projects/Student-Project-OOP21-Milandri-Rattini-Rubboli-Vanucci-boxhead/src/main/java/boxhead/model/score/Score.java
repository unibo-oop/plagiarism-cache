package boxhead.model.score;

/**
 * Interface to model the score of a single game.
 */
public interface Score {

	/**
	 * @return
	 * 			The number of kills at a certain point of the game.
	 */
	int getKills();

	/**
	 * Used to add a single kill.
	 */
	void addKill();

	/**
	 * @return
	 * 			The streak of kills you are at a certain moment.
	 */
	int getStreak();
	
	/**
	 * Method to retrieve the max streak reached.
	 * @return maxStreak
	 */
	int getMaxStreak();

	/**
	 * Used to update internally the score.
	 */
	void update();

	/**
	 * Used to get the playtime.
	 */
	String getTimePlayed();

	/**
	 * Used to internally set the actual time played.
	 */
	void setGameEnd();
}