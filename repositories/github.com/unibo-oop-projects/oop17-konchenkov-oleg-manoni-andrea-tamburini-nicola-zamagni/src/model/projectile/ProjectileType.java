package model.projectile;

import model.physics.particle.shape.ParticleShape;
import model.physics.particle.shape.Sphere;
import model.projectile.fragmentationparameters.FragmentationParameters;
import model.projectile.fragmentationparameters.FragmentationParametersImpl;

/**
 *
 * Represents a Projectile Type.
 *
 * @author Nicola Zamagni
 *
 */
public enum ProjectileType {
    // CHECKSTYLE: MagicNumber OFF
    /**
     * EXPLOSIVE Projectile Type.
     */
    EXPLOSIVE("Bomb", 20.0, new Sphere(0.06), 20.0,
            new FragmentationParametersImpl.Builder().setHeatOfExplosion(2.175E6)
                    .setFragmentationDeviceGeometricalConstant(3.0 / 5.0).setChargeToMetalMassRatio(1.0E-4)
                    .setChargeDensity(1654.0).setExplosionCone(2.0 * Math.PI).build()),
    /**
     * BOUNCY Projectile Type.
     */
    BOUNCY("Bouncing Bomb", 20.0, new Sphere(0.06), 20.0,
            new FragmentationParametersImpl.Builder().setHeatOfExplosion(2.175E6)
                    .setFragmentationDeviceGeometricalConstant(3.0 / 5.0).setChargeToMetalMassRatio(1.0E-4)
                    .setChargeDensity(1654.0).setExplosionCone(2.0 * Math.PI).build()),
    /**
     * FRAG Projectile Type.
     */
    FRAG("Frag Bomb", 20.0, new Sphere(0.06), 20.0,
            new FragmentationParametersImpl.Builder().setHeatOfExplosion(2.175E6)
                    .setFragmentationDeviceGeometricalConstant(3.0 / 5.0).setChargeToMetalMassRatio(1.0E-4)
                    .setChargeDensity(1654.0).setExplosionCone(2.0 * Math.PI).build()),
    /**
     * CLUSTER_EXPLOSIVE Projectile Type.
     */
    CLUSTER_EXPLOSIVE("Cluster Bomb", 20.0, new Sphere(0.06), 20.0,
            new FragmentationParametersImpl.Builder().setHeatOfExplosion(2.175E6)
                    .setFragmentationDeviceGeometricalConstant(3.0 / 5.0).setChargeToMetalMassRatio(1.0E-4)
                    .setChargeDensity(1654.0).setExplosionCone(Math.PI / 3.0).build()),
    /**
     * CLUSTER_BOUNCY Projectile Type.
     */
    CLUSTER_BOUNCY("Cluster Bouncing Bomb", 20.0, new Sphere(0.06), 20.0,
            new FragmentationParametersImpl.Builder().setHeatOfExplosion(2.175E6)
                    .setFragmentationDeviceGeometricalConstant(3.0 / 5.0).setChargeToMetalMassRatio(1.0E-4)
                    .setChargeDensity(1654.0).setExplosionCone(Math.PI / 3.0).build()),
    /**
     * CLUSTER_CLUSTER_EXPLOSIVE Projectile Type.
     */
    CLUSTER_CLUSTER_EXPLOSIVE("Double Triple Cluster Bomb", 20.0, new Sphere(0.06), 20.0,
            new FragmentationParametersImpl.Builder().setHeatOfExplosion(2.175E6)
                    .setFragmentationDeviceGeometricalConstant(3.0 / 5.0).setChargeToMetalMassRatio(1.0E-4)
                    .setChargeDensity(1654.0).setExplosionCone(Math.PI / 3.0).build()),
    /**
     * CLUSTER_CLUSTER_BOUNCY Projectile Type.
     */
    CLUSTER_CLUSTER_BOUNCY("Double Triple Cluster Bouncing Bomb", 20.0, new Sphere(0.06), 20.0,
            new FragmentationParametersImpl.Builder().setHeatOfExplosion(2.175E6)
                    .setFragmentationDeviceGeometricalConstant(3.0 / 5.0).setChargeToMetalMassRatio(1.0E-4)
                    .setChargeDensity(1654.0).setExplosionCone(Math.PI / 3.0).build());
    // CHECKSTYLE: MagicNumber ON

    private final String string;
    private final double mass;
    private final ParticleShape shape;
    private final double blastRadius;
    private final FragmentationParameters fragmentationParameters;

    ProjectileType(final String string, final double mass, final ParticleShape particleShape, final double blastRadius,
            final FragmentationParameters fragmentationParameters) {
        this.string = string;
        this.mass = mass;
        this.shape = particleShape;
        this.blastRadius = blastRadius;
        this.fragmentationParameters = fragmentationParameters;
    }

    /**
     *
     * Returns the mass of the projectile.
     *
     * @return mass, in kilogram
     */
    public double getMass() {
        return mass;
    }

    /**
     *
     * Returns the shape of the projectile.
     *
     * @return shape
     */
    ParticleShape getShape() {
        return shape;
    }

    /**
     *
     * Returns the blast radius of the projectile.
     *
     * @return blast radius, in metre
     */
    public double getBlastRadius() {
        return blastRadius;
    }

    /**
     *
     * Returns the fragmentation parameters of the projectile.
     *
     * @return the fragmentation parameters of the projectile
     */
    public FragmentationParameters getFragmentationParameters() {
        return fragmentationParameters;
    }

    /**
     *
     * Returns the name of the projectile.
     *
     */
    @Override
    public String toString() {
        return string;
    }
}
