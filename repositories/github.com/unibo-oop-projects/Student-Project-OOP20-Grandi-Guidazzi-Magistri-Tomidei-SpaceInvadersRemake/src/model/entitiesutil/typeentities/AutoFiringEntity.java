package model.entitiesutil.typeentities;

/**
 * Interface that represent all the {@link GenericEntity} that can fire automatically
 */
public interface AutoFiringEntity extends EntityCapableOfShooting {

	/**
	 * Return true if {@link GenericEntity} can shoot , false otherwise
	 * 
	 * @param cycles current number of game loop cycles
	 * 
	 * @return	a boolean which represents if {@link GenericEntity} can shoot
	 */
	public boolean canShoot(int cycles);
}
