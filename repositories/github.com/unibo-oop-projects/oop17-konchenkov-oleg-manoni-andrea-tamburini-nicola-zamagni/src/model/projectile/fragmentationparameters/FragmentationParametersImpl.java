package model.projectile.fragmentationparameters;

/**
 * Contains the parameters of a fragmentation device.
 *
 * Instances of this class are guaranteed to be immutable.
 *
 * @author Nicola Zamagni
 *
 */
public final class FragmentationParametersImpl implements FragmentationParameters {

    private final double heatOfExplosion;
    private final double fragmentationDeviceGeometricalConstant;
    private final double chargeToMetalMassRatio;
    private final double chargeDensity;
    private final double explosionCone;

    /**
     *
     * Constructor.
     *
     * @param heatOfExplosion
     *            heat of explosion, in Joule per kilogram
     * @param fragmentationDeviceGeometricalConstant
     *            fragmentation device geometrical constant
     * @param chargeToMetalMassRatio
     *            charge to metal mass ratio
     * @param chargeDensity
     *            charge density, in kilogram per cubic metre
     * @param explosionCone
     *            explosion cone, in radians
     * @throws IllegalArgumentException
     *             heat of explosion, fragmentation device geometrical constant,
     *             charge to metal mass ratio, charge density must all be
     *             nonnegative. Explosion cone must be in the range [0.0, 2 *
     *             Math.Pi] radians.
     */
    protected FragmentationParametersImpl(final double heatOfExplosion,
            final double fragmentationDeviceGeometricalConstant, final double chargeToMetalMassRatio,
            final double chargeDensity, final double explosionCone) throws IllegalArgumentException {
        super();
        this.heatOfExplosion = heatOfExplosion;
        this.fragmentationDeviceGeometricalConstant = fragmentationDeviceGeometricalConstant;
        this.chargeToMetalMassRatio = chargeToMetalMassRatio;
        this.chargeDensity = chargeDensity;
        this.explosionCone = explosionCone;
        if (this.heatOfExplosion < 0 || this.fragmentationDeviceGeometricalConstant < 0
                || this.chargeToMetalMassRatio < 0 || this.chargeDensity < 0 || this.explosionCone < 0.0
                || this.explosionCone > 2 * Math.PI) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getHeatOfExplosion() {
        return heatOfExplosion;
    }

    @Override
    public double getFragmentationDeviceGeometricalConstant() {
        return fragmentationDeviceGeometricalConstant;
    }

    @Override
    public double getChargeToMetalMassRatio() {
        return chargeToMetalMassRatio;
    }

    @Override
    public double getChargeDensity() {
        return chargeDensity;
    }

    @Override
    public double getExplosionCone() {
        return explosionCone;
    }

    @Override
    public double getgetInitialFragmentSpeed() {
        return Math.sqrt(2.0 * heatOfExplosion * chargeToMetalMassRatio
                / (1 + fragmentationDeviceGeometricalConstant * chargeToMetalMassRatio));
    }

    /**
     *
     * Builder.
     *
     * @author Nicola Zamagni
     *
     */
    public static class Builder {

        private double heatOfExplosion;
        private double fragmentationDeviceGeometricalConstant;
        private double chargeToMetalMassRatio;
        private double chergeDensity;
        private double explosionCone;

        /**
         *
         * Sets the heat of explosion.
         *
         * @param heatOfExplosion
         *            heat of explosion, in Joule per kilogram
         * @return builder
         */
        public Builder setHeatOfExplosion(final double heatOfExplosion) {
            this.heatOfExplosion = heatOfExplosion;
            return this;
        }

        /**
         *
         * Sets the fragmentation device geometrical constant.
         *
         * @param fragmentationDeviceGeometricalConstant
         *            fragmentation device geometrical constant
         * @return builder
         */
        public Builder setFragmentationDeviceGeometricalConstant(final double fragmentationDeviceGeometricalConstant) {
            this.fragmentationDeviceGeometricalConstant = fragmentationDeviceGeometricalConstant;
            return this;
        }

        /**
         *
         * Sets the charge to metal mass ratio.
         *
         * @param chargeToMetalMassRatio
         *            charge to metal mass ratio
         * @return builder
         */
        public Builder setChargeToMetalMassRatio(final double chargeToMetalMassRatio) {
            this.chargeToMetalMassRatio = chargeToMetalMassRatio;
            return this;
        }

        /**
         *
         * Sets the charge density.
         *
         * @param chergeDensity
         *            metal density, in kilogram per cubic metre
         * @return builder
         */
        public Builder setChargeDensity(final double chergeDensity) {
            this.chergeDensity = chergeDensity;
            return this;
        }

        /**
         *
         * Sets the explosion cone.
         *
         * @param explosionCone
         *            explosion cone, in radians
         * @return builder
         */
        public Builder setExplosionCone(final double explosionCone) {
            this.explosionCone = explosionCone;
            return this;
        }

        /**
         *
         * @return FragmentationParameters
         */
        public FragmentationParametersImpl build() {
            return new FragmentationParametersImpl(heatOfExplosion, fragmentationDeviceGeometricalConstant,
                    chargeToMetalMassRatio, chergeDensity, explosionCone);
        }

    }

}
