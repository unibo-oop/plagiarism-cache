package model.entities;

import model.entitiesutil.Bullet;
import model.entitiesutil.Enemy;
import model.entitiesutil.GenericEntityType;

/**
 * Possible specific type of the {@link GenericEntity}
 */
public enum SpecificEntityType {

	/**
	 * {@link Alien}
	 */
	ALIEN_1(GenericEntityType.GENERIC_ENEMY, 100),
	
	/**
	 * {@link Alien}
	 */
	ALIEN_2(GenericEntityType.GENERIC_ENEMY, 50),
	
	/**
	 * {@link Alien}
	 */
	ALIEN_3(GenericEntityType.GENERIC_ENEMY, 25),

	/**
	 * {@link Boss1}
	 */
	BOSS_1(GenericEntityType.BOSS, 250),

	/**
	 * {@link Boss2}
	 */
	BOSS_2(GenericEntityType.BOSS, 500),

	/**
	 * {@link Boss3}
	 */
	BOSS_3(GenericEntityType.BOSS, 1000),

	/**
	 * {@link Player}
	 */
	PLAYER_1(GenericEntityType.PLAYER),

	/**
	 * {@link Alien}'s {@link Bullet}
	 */
	ALIEN_BULLET(GenericEntityType.ENEMY_BULLET),

	/**
	 * {@link Enemy} boss's {@link Bullet}
	 */
	BOSS_BULLET(GenericEntityType.ENEMY_BULLET),

	/**
	 * {@link Player}'s {@link Bullet}
	 */
	PLAYER_1_BULLET(GenericEntityType.PLAYER_BULLET, -5);


	private final GenericEntityType type;
	private final int entityValue;

	/**
	 * Possible specific type of the {@link GenericEntity}
	 */
	private SpecificEntityType(GenericEntityType actualType, int entityValue) {
		this.type = actualType;
		this.entityValue = entityValue;
	}

	private SpecificEntityType(GenericEntityType actualType) {
		this.type = actualType;
		this.entityValue = 0;
	}

	/**
	 * Returns the type of entity it belongs to
	 * 
	 * @return a value of {@link GenericEntityType}
	 */
	public GenericEntityType getGenericType() {
		return this.type;
	}

	public int getEntityValue() {
		return this.entityValue;
	}
	
	
}
