package model.entitiesutil;

import model.entitiesutil.typeentities.GenericEntity;

/**
 * Possible generic types of the {@link GenericEntity}
 */
public enum GenericEntityType{

	/**
	 * {@link Enemy}
	 */
	GENERIC_ENEMY,

	/**
	 * {@link Enemy} boss
	 */
	BOSS,

	/**
	 * {@link Player}
	 */
	PLAYER,

	/**
	 * {@link Enemy}'s {@link Bullet}
	 */
	ENEMY_BULLET,

	/**
	 * {@link Player}'s {@link Bullet}
	 */
	PLAYER_BULLET;
	
}