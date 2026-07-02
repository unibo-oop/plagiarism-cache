package model.physics.particle.test;

import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.cosh;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.signum;
import static java.lang.Math.sinh;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.tanh;

/**
 *
 * The ClosedFormSolution class contains the closed-form solution of the
 * equations of motion of a body in a fluid, assuming that: the resistance of
 * the fluid can be approximated only by the quadratic term; the drag
 * coefficient can be considered constant; the motion takes place in a
 * gravitational field; the motion is exclusively horizontal or exclusively
 * vertical.
 *
 * @author Nicola Zamagni
 *
 */
public final class ClosedFormSolution {

    private static final double EPSILON = 10.0 * Double.MIN_VALUE;

    private ClosedFormSolution() {
    }

    private static double coth(final double x) {
        return 1 / tanh(x);
    }

    private static double atanh(final double x) {
        return 0.5 * log((1.0 + x) / (1.0 - x));
    }

    private static double acoth(final double x) {
        return 0.5 * log((x + 1.0) / (x - 1.0));
    }

    /**
     * Returns the net acceleration taking into account the buoyancy force.
     *
     * @param g
     *            gravitational acceleration
     * @param fD
     *            fluid density (nonnegative)
     * @param m
     *            body mass (positive)
     * @param vol
     *            body volume (nonnegative)
     * @return the net acceleration
     */
    public static double netAcceleration(final double g, final double fD, final double m, final double vol) {
        return g - fD * vol / m;
    }

    /**
     * Returns the horizontal position of the body at a given time.
     *
     * @param fD
     *            fluid density (nonnegative)
     * @param fV
     *            fluid velocity
     * @param m
     *            body mass (positive)
     * @param cD
     *            body drag coefficient (nonnegative)
     * @param rA
     *            body reference area (nonnegative)
     * @param vx0
     *            body horizontal component of the initial velocity
     * @param t
     *            time passed since the beginning of the body motion (nonnegative)
     * @return the body horizontal position
     */
    public static double x(final double fD, final double fV, final double m, final double cD, final double rA,
            final double vx0, final double t) {

        final double k = cD * fD * rA / 2;

        /* no fluid */
        if (abs(k) < EPSILON) {
            return vx0 * t;
            /* headwind */
        } else if (vx0 - fV > 0.0) {
            return m / k * log((k * (vx0 - fV) * t + m) / m) + t * fV;
            /* tailwind */
        } else if (vx0 - fV < 0.0) {
            return -m / k * log((k * (vx0 - fV) * t - m) / -m) + t * fV;
            /* like the wind */
        } else {
            return vx0 * t;
        }

    }

    /**
     * Returns the vertical position of the body at a given time.
     *
     * @param g
     *            acceleration
     * @param fD
     *            fluid density (nonnegative)
     * @param fV
     *            fluid velocity
     * @param m
     *            body mass (positive)
     * @param cD
     *            body drag coefficient (nonnegative)
     * @param rA
     *            body reference area (nonnegative)
     * @param vy0
     *            body vertical component of the initial velocity
     * @param t
     *            time passed since the beginning of the body motion (nonnegative)
     * @return the body vertical position
     */
    public static double y(final double g, final double fD, final double fV, final double m, final double cD,
            final double rA, final double vy0, final double t) {

        final double k = cD * fD * rA / 2.0;

        /* no fluid */
        if (abs(k) < EPSILON) {
            return vy0 * t - 0.5 * g * pow(t, 2.0);
            /* no acceleration */
        } else if (abs(g) < EPSILON) {
            if (vy0 > 0.0) {
                return m / k * log((k * vy0 * t + m) / m);
            } else if (vy0 < 0.0) {
                return -m / k * log((k * vy0 * t - m) / -m);
            } else {
                return 0.0;
            }
            /* initial vertical velocity and acceleration are opposite */
        } else if (vy0 >= 0.0 && g > 0.0 || vy0 <= 0.0 && g < 0.0) {
            final double tTop = sqrt(m / (abs(g) * k)) * atan(sqrt(k * pow(vy0, 2.0) / (m * abs(g))));
            /* rising phase (sinking phase) */
            if (t < tTop) {
                return signum(g) * (m / k)
                        * log(cos(atan(signum(g) * vy0 * sqrt(k / (m * abs(g)))) - t * sqrt(abs(g) * k / m))
                                / cos(atan(signum(g) * vy0 * sqrt(k / (m * abs(g))))));
                /* falling phase (floating phase) */
            } else {
                final double yTop = signum(g) * (m / (2.0 * k)) * log((m * abs(g) + k * pow(vy0, 2.0)) / (m * abs(g)));

                return yTop - signum(g) * (m / k) * log(cosh((t - tTop) * sqrt(abs(g) * k / m)));
            }
            /* initial vertical velocity and acceleration are in the same ways */
        } else {
            /* initial velocity is less than the terminal velocity */
            if (pow(vy0, 2.0) < m * abs(g) / k) {
                return -signum(g) * (m / k)
                        * log(cosh(-atanh(signum(g) * vy0 * sqrt(k / (m * abs(g)))) + t * sqrt(abs(g) * k / m))
                                / cosh(atanh(signum(g) * vy0 * sqrt(k / (m * abs(g))))));
                /* initial velocity is greater than the terminal velocity */
            } else if (pow(vy0, 2.0) > m * abs(g) / k) {
                return -signum(g) * (m / k)
                        * log(sinh(-acoth(signum(g) * vy0 * sqrt(k / (m * abs(g)))) + t * sqrt(abs(g) * k / m))
                                / -sinh(acoth(signum(g) * vy0 * sqrt(k / (m * abs(g))))));
                /* initial velocity is equal to the terminal velocity */
            } else {
                return vy0 * t;

            }
        }

    }

    /**
     * Returns the horizontal component of the body velocity at a given time.
     *
     * @param fD
     *            fluid density (nonnegative)
     * @param fV
     *            fluid velocity
     * @param m
     *            body mass (positive)
     * @param cD
     *            body drag coefficient (nonnegative)
     * @param rA
     *            body reference area (nonnegative)
     * @param vx0
     *            body horizontal component of the initial velocity
     * @param t
     *            time passed since the beginning of the body motion (nonnegative)
     * @return the horizontal component of the body velocity
     */
    public static double vx(final double fD, final double fV, final double m, final double cD, final double rA,
            final double vx0, final double t) {

        final double k = cD * fD * rA / 2;

        /* no fluid */
        if (abs(k) < EPSILON) {
            return vx0;
            /* headwind */
        } else if (vx0 - fV > 0.0) {
            return m * (vx0 - fV) / (k * (vx0 - fV) * t + m) + fV;
            /* tailwind */
        } else if (vx0 - fV < 0.0) {
            return -m * (vx0 - fV) / (k * (vx0 - fV) * t - m) + fV;
            /* like the wind */
        } else {
            return vx0;
        }

    }

    /**
     * Returns the vertical component of the body initial velocity at a given time.
     *
     * @param g
     *            acceleration
     * @param fD
     *            fluid density (nonnegative)
     * @param fV
     *            fluid velocity
     * @param m
     *            body mass (positive)
     * @param cD
     *            body drag coefficient (nonnegative)
     * @param rA
     *            body reference area (nonnegative)
     * @param vy0
     *            body vertical component of the initial velocity
     * @param t
     *            time passed since the beginning of the body motion (nonnegative)
     * @return the vertical component of the body velocity
     */
    public static double vy(final double g, final double fD, final double fV, final double m, final double cD,
            final double rA, final double vy0, final double t) {

        final double k = cD * fD * rA / 2;

        /* no fluid */
        if (abs(k) < EPSILON) {
            return vy0 - g * t;
            /* no acceleration */
        } else if (abs(g) < EPSILON) {
            if (vy0 > 0.0) {
                return m * vy0 / (m + vy0 * k * t);
            } else if (vy0 < 0.0) {
                return m * vy0 / (m - vy0 * k * t);
            } else {
                return 0.0;
            }
            /* initial vertical velocity and acceleration are opposite */
        } else if (vy0 >= 0 && g > 0.0 || vy0 <= 0.0 && g < 0.0) {
            final double tTop = sqrt(m / (abs(g) * k)) * atan(sqrt(k * pow(vy0, 2.0) / (m * abs(g))));
            /* rising phase (sinking phase) */
            if (t < tTop) {
                return signum(g)
                        * (sqrt(m * abs(g) / k) * tan(atan(vy0 * sqrt(k / (m * abs(g)))) - t * sqrt(g * k / m)));
                /* falling phase (floating phase) */
            } else {
                return signum(g) * (-sqrt(m * abs(g) / k) * tanh((t - tTop) * sqrt(g * k / m)));
            }
            /* initial vertical velocity and acceleration are in the same ways */
        } else {
            /* initial velocity is less than the terminal velocity */
            if (pow(vy0, 2.0) < m * abs(g) / k) {
                return -signum(g)
                        * (sqrt(m * abs(g) / k) * tanh(-atanh(vy0 * sqrt(k / (m * abs(g)))) + t * sqrt(g * k / m)));
                /* initial velocity is greater than the terminal velocity */
            } else if (pow(vy0, 2.0) > m * abs(g) / k) {
                return -signum(g)
                        * (sqrt(m * abs(g) / k) * coth(-acoth(vy0 * sqrt(k / (m * abs(g)))) + t * sqrt(g * k / m)));
                /* initial velocity is equal to the terminal velocity */
            } else {
                return vy0;
            }
        }

    }

}
