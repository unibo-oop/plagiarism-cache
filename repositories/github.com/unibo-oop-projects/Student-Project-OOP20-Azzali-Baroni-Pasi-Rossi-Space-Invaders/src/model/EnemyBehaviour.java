package model;

/**
 * The Interface EnemyBehaviour.
 */
public interface EnemyBehaviour {
	
	/**
	 * Method for create the enemies.
	 *
	 * @return {@link AbstarctEnemy}.
	 */
	AbstractEnemy createThisEnemy();
	
	/**
	 * Method for the enemies's movement.
	 *
	 * @return {@link DirEnemy}.
	 */
	DirEnemy casualMovs();
	

}
