package utilities;

/**
*
*/
public enum PowerUpEnum {

    /**
     *
     */
    WeaponDamage;

    /**
     * @return PowerUp type.
     */
    public static PowerUpEnum getPowerUp() {
        return PowerUpEnum.values()[0];
    }
}
