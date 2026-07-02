package it.unibo.oop.mge.libraries;

import java.util.Locale;

/**
 * The Enum Constant.
 */
public enum Constant implements GenericEnum {
    /**
     * Nepero's number.
     */
    E(Math.E),
    /**
     * Pi greek.
     */
    PI(Math.PI),
    /**
     * Golden ratio.
     */
    PHI(1.6180),
    /**
     * Euler-Mascheroni's number.
     */
    EUMA(0.5772),
    /**
     * Embree-Trefethen's number.
     */
    EMTR(0.7025),
    /**
     * Plastic number.
     */
    PLS(1.3247),
    /**
     * 'Feigenbaum first' number.
     */
    FEIGPR(4.6692),
    /**
     * 'Feigenbaum second' number.
     */
    FEIGSN(2.5029),
    /**
     * Primes twins ratio.
     */
    PRTW(0.6601),
    /**
     * Mills number.
     */
    MILLS(1.3063),
    /**
     * Brun's constant for twin primes.
     */
    BRUNTW(1.9021),
    /**
     * Brun's constant for prime quadruplets.
     */
    BRUMQUAD(0.8705);

    /** The value. */
    private final Double value;

    /**
     * Instantiates a new constant.
     *
     * @param value of the constant.
     */
    Constant(final Double value) {
        this.value = value;
    }

    /**
     * Gives the value of the constant.
     *
     * @return the value of the constant.
     */
    public Double resolve() {
        return value;
    }

    /**
     * Gets the syntax.
     *
     * @return the syntax of the constant.
     */
    public String getSyntax() {
        return this.name().toLowerCase(Locale.getDefault());
    }
}
