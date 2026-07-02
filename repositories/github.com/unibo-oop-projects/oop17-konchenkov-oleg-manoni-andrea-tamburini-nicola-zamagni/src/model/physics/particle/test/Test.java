package model.physics.particle.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.particle.BasicParticleCreator;
import model.physics.particle.Particle;
import model.physics.particle.QuadraticDragParticle;
import model.physics.particle.environment.Environment;
import model.physics.particle.environment.GravityOnlyEnvironment;
import model.physics.particle.environment.QuadraticDragEnvironment;
import model.physics.particle.shape.ParticleShape;
import model.physics.particle.shape.Sphere;

/**
 *
 * Tests particle.
 *
 * @author Nicola Zamagni
 *
 */
public class Test {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String FILE_PATH = "src" + FILE_SEPARATOR + "model" + FILE_SEPARATOR + "physics"
            + FILE_SEPARATOR + "particle" + FILE_SEPARATOR + "test" + FILE_SEPARATOR;
    private static final double EPSILON = 10.0 * Double.MIN_VALUE;
    private static final double POSITION_TOLERANCE = 10e-3; /* metre */
    private static final double VELOCITY_TOLERANCE = 10e-3; /* metres per second */
    private static final int NUMBER_OF_TIMESTEPS = 1000;
    private static final double TIMESTEP = 10e-2; /* second */

    /**
     *
     * Gravity only motion.
     *
     * @throws FileNotFoundException
     *             file not found
     */
    @org.junit.Test
    public void gravityOnlyMotion() throws FileNotFoundException {

        /* environment build */
        final double g = -9.8;
        final Environment e = new GravityOnlyEnvironment.Builder().setGravitationalAcceleration(new Vector2D(0.0, g))
                .build();
        /* particle build */
        final double m = 20.0;
        final double r = 0.06;
        final ParticleShape ps = new Sphere(r);
        final Particle p = new BasicParticleCreator().createParticle(Vector2D.ZERO, new Vector2D(200.0, 200.0), e, m,
                ps);

        System.setOut(new PrintStream(new File(FILE_PATH + "Test_" + p.getClass().getSimpleName() + ".log")));
        System.out.println(p.getClass().getSimpleName() + "\n");
        checkUpdate(p);
    }

    /**
     *
     * Quadratic drag vertical motion. Compares the closed-form solution with the
     * RK4 numeric solution.
     *
     * @throws FileNotFoundException
     *             file not found
     */
    @org.junit.Test
    public void quadraticDragVerticalMotion() throws FileNotFoundException {

        /* environment build */
        final double g = -9.8;
        final double fD = 1.225;
        /* no horizontal fluid velocity in this case */
        final double fV = 0.0;
        final Environment e = new QuadraticDragEnvironment.Builder().setGravitationalAcceleration(new Vector2D(0.0, g))
                .setFluidDensity(fD).setFluidVelocity(new Vector2D(fV, 0.0)).build();
        /* particle build */
        /* no horizontal velocity in this case */
        final double m = 20.0;
        final ParticleShape ps = new Sphere(0.06);
        final Particle p = new BasicParticleCreator().createParticle(Vector2D.ZERO, new Vector2D(0.0, 200.0), e, m, ps);

        /* smaller timeStep provide more accurate result */
        System.setOut(
                new PrintStream(new File(FILE_PATH + "Test_" + p.getClass().getSimpleName() + "VerticalMotion.log")));
        System.out.println(p.getClass().getSimpleName() + "\n");
        checkUpdate(p);
    }

    /**
     *
     * Quadratic drag horizontal motion. Compares the closed-form solution with the
     * RK4 numeric solution.
     *
     * @throws FileNotFoundException
     *             file not found
     */
    @org.junit.Test
    public void quadraticDragHorizontalMotion() throws FileNotFoundException {

        /* environment build */
        /* no gravitational acceleration in this case */
        final double g = 0.0;
        final double fD = 1.225;
        /* no vertical fluid velocity in this case */
        final double fV = -600.0;
        final Environment e = new QuadraticDragEnvironment.Builder().setGravitationalAcceleration(new Vector2D(0.0, g))
                .setFluidDensity(fD).setFluidVelocity(new Vector2D(fV, 0.0)).build();
        /* particle build */
        /* no vertical velocity in this case */
        final double m = 20.0;
        final ParticleShape ps = new Sphere(0.06);
        final Particle p = new BasicParticleCreator().createParticle(Vector2D.ZERO, new Vector2D(200.0, 0.0), e, m, ps);

        /* smaller timeStep provide more accurate result */
        System.setOut(
                new PrintStream(new File(FILE_PATH + "Test_" + p.getClass().getSimpleName() + "HorizontalMotion.log")));
        System.out.println(p.getClass().getSimpleName() + "\n");
        checkUpdate(p);
    }

    private Vector2D getGravitationalAcceleration(final Particle p) {
        return ((GravityOnlyEnvironment) p.getEnvironment()).getGravitationalAcceleration();
    }

    private double getFluidDensity(final Particle p) {
        return p instanceof QuadraticDragParticle ? ((QuadraticDragEnvironment) p.getEnvironment()).getFluidDensity()
                : 0.0;
    }

    private Vector2D getFluidVelocity(final Particle p) {
        return p instanceof QuadraticDragParticle ? ((QuadraticDragEnvironment) p.getEnvironment()).getFluidVelocity()
                : Vector2D.ZERO;
    }

    private void checkUpdate(final Particle p) {

        final double g = Math.abs(getGravitationalAcceleration(p).getNorm()) >= EPSILON
                ? ClosedFormSolution.netAcceleration(getGravitationalAcceleration(p).getNorm(), getFluidDensity(p),
                        p.getMass(), p.getShape().getVolume())
                : getGravitationalAcceleration(p).getNorm();

        final Vector2D v0 = p.getVelocity();

        for (int i = 0; i < NUMBER_OF_TIMESTEPS; i++) {

            final double x = ClosedFormSolution.x(getFluidDensity(p), getFluidVelocity(p).getX(), p.getMass(),
                    p.getShape().getDragCoeffigent(), p.getShape().getReferenceArea(), v0.getX(), TIMESTEP * i);

            final double y = ClosedFormSolution.y(g, getFluidDensity(p), getFluidVelocity(p).getY(), p.getMass(),
                    p.getShape().getDragCoeffigent(), p.getShape().getReferenceArea(), v0.getY(), TIMESTEP * i);

            System.out.println("Position closed-form solution: (" + x + ", " + y + ")");
            System.out.println("Position RK4-numeric solution: (" + p.getPosition().getX() + ", "
                    + p.getPosition().getY() + ")\n");

            assertEquals(p.getPosition().getX(), x, POSITION_TOLERANCE);
            assertEquals(p.getPosition().getY(), y, POSITION_TOLERANCE);

            final double vx = ClosedFormSolution.vx(getFluidDensity(p), getFluidVelocity(p).getX(), p.getMass(),
                    p.getShape().getDragCoeffigent(), p.getShape().getReferenceArea(), v0.getX(), TIMESTEP * i);

            final double vy = ClosedFormSolution.vy(g, getFluidDensity(p), getFluidVelocity(p).getY(), p.getMass(),
                    p.getShape().getDragCoeffigent(), p.getShape().getReferenceArea(), v0.getY(), TIMESTEP * i);

            System.out.println("Velocity closed-form solution: (" + vx + ", " + vy + ")");
            System.out.println(
                    "Velocity RK4-numeric solution: (" + p.getVelocity().getX() + ", " + p.getVelocity().getY() + ")");
            System.out.println("---");

            assertEquals(p.getVelocity().getX(), vx, VELOCITY_TOLERANCE);
            assertEquals(p.getVelocity().getY(), vy, VELOCITY_TOLERANCE);

            p.update(TIMESTEP);

        }

    }

}
