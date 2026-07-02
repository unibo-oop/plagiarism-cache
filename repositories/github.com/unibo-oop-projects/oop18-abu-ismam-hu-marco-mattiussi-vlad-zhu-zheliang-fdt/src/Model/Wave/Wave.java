package model.wave;

import model.enemy.Enemy;
import model.enemy.EnemyType;
public interface Wave {

	int getWave();
	
	void populate(int quantity, EnemyType type);
	
	Enemy spawn();
	
	Wave nextWave();

	boolean hasEnemies();

}
