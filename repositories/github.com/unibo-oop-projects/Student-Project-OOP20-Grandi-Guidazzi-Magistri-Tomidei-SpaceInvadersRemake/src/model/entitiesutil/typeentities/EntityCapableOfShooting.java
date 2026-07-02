package model.entitiesutil.typeentities;

import model.entitiesutil.Bullet;

/**
 * Interface that represents all the {@link GenericEntity} that can fire
 */
public interface EntityCapableOfShooting {

	/**
	 * Create new {@link Bullet} according to the specific type of the {@GenericEntity}
	 */
	public void shoot();

}