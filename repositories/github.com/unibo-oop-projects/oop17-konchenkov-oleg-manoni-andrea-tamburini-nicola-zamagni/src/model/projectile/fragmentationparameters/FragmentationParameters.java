package model.projectile.fragmentationparameters;

/**
 * Contains the parameters of a fragmentation device.
 *
 * @author Nicola Zamagni
 *
 */
public interface FragmentationParameters {

    /**
     *
     * Returns the heat of explosion of the projectile.
     *
     * @return heat of explosion, in Joule per kilogram
     */
    double getHeatOfExplosion();

    /**
     *
     * Returns the fragmentation device geometrical constant of the projectile.
     *
     * @return fragmentation device geometrical constant
     */
    double getFragmentationDeviceGeometricalConstant();

    /**
     *
     * Returns the charge to metal mass ratio of the projectile.
     *
     * @return charge to metal mass ratio
     */
    double getChargeToMetalMassRatio();

    /**
     *
     * Returns the charge density of the projectile.
     *
     * @return charge density, in kilogram per cubic metre
     */
    double getChargeDensity();

    /**
     *
     * Returns the explosion cone of the projectile.
     *
     * @return explosion cone, in radians
     */
    double getExplosionCone();

    /**
     *
     * Returns the initial fragment speed of the projectile.
     *
     * @return the initial fragment speed, in metre per second
     */
    double getgetInitialFragmentSpeed();

}
