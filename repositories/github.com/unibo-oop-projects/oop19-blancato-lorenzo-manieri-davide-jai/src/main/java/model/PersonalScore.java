package model;

/**
 * 
 * Class who record personal scores datas
 *
 */
public interface PersonalScore extends Comparable<PersonalScore> {
	
	/**
	 * Set score position 
	 * @param position
	 */
	public void setPosition(int position);
	
	/** 
	 * 
	 * @return
	 * 		Return this position
	 */
	public int getPosition();
	
	/**
	 * Get this personal score user
	 * @return
	 */
	public String getUser();
	
	/**
	 * Set this personal score user
	 * @param user
	 */
	public void setUser(String user);
	
	/**
	 * Get this score
	 * @return
	 */
	public int getScore();
	
	/**
	 * Set this score
	 * @param score
	 */
	public void setScore(int score);
	
	/**
	 * Get the number of enemy killed of this personal score
	 * @return
	 */
	public int getEnemyKilled();
	
	/**
	 * Set the number of enemy killed in this personal score
	 * @param enemyKilled
	 */
	public void setEnemyKilled(int enemyKilled);
	
	/**
	 * Get the formatted string to write in file
	 * @return
	 */
	public String toString();
	
	/**
	 * Compare if this personal score is bigger, smaller or equal than an other personal score
	 * @param
	 * 		Other Personal score to compare
	 * @return
	 * 		Returns: 1 if this personal score is bigger then other, -1 if it is smaller and 0 if it is equal
	 * 		
	 */
	public int compareTo(PersonalScore o) ;

}
