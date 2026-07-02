package model.projectile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Polycollider2D;
import model.physics.particle.BasicParticleCreator;
import model.physics.particle.Particle;
import model.physics.particle.environment.Environment;
import model.physics.particle.shape.ParticleShape;
import model.projectile.fragmentationparameters.FragmentationParameters;

/**
 *
 * Represents a basic projectile.
 *
 * @author Nicola Zamagni
 *
 */
public final class BasicProjectile extends AbstractProjectile {

    private List<Particle> particleList;
    private final double blastRadius;
    private final FragmentationParameters fragmentationParameters;

    /**
     *
     * Constructor.
     *
     * @param particle
     *            particle
     * @param blastRadius
     *            blast radius, in metre
     * @param fragmentationParameters
     *            fragmentation parameters
     * @throws IllegalArgumentException
     *             blast radius must be positive and the composition of the
     *             projectile (metal + charge) must be consistent
     */
    protected BasicProjectile(final Particle particle, final double blastRadius,
            final FragmentationParameters fragmentationParameters) throws IllegalArgumentException {
        super();
        particleList = new ArrayList<>(Arrays.asList(new BasicParticleCreator().createParticle(particle)));
        this.blastRadius = blastRadius;
        this.fragmentationParameters = fragmentationParameters;
        if (this.blastRadius < 0.0 || particle.getShape().getVolume()
                - particle.getMass() * fragmentationParameters.getChargeToMetalMassRatio()
                        / ((fragmentationParameters.getChargeToMetalMassRatio() + 1)
                                * fragmentationParameters.getChargeDensity()) <= 0.0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Vector2D> getPreviousPosition() {
        return particleList.stream().map(p -> p.getPreviousPosition()).collect(Collectors.toList());
    }

    @Override
    public List<Vector2D> getPreviousVelocity() {
        return particleList.stream().map(p -> p.getPreviousVelocity()).collect(Collectors.toList());
    }

    @Override
    public List<Vector2D> getPreviousAcceleration() {
        return particleList.stream().map(p -> p.getPreviousAcceleration()).collect(Collectors.toList());
    }

    @Override
    public List<Vector2D> getPosition() {
        return particleList.stream().map(p -> p.getPosition()).collect(Collectors.toList());
    }

    @Override
    public List<Vector2D> getVelocity() {
        return particleList.stream().map(p -> p.getVelocity()).collect(Collectors.toList());
    }

    @Override
    public List<Vector2D> getAcceleration() {
        return particleList.stream().map(p -> p.getAcceleration()).collect(Collectors.toList());
    }

    @Override
    public boolean isStationary() {
        return particleList.stream().allMatch(p -> p.isStationary());
    }

    @Override
    public List<Double> getMass() {
        return particleList.stream().map(p -> p.getMass()).collect(Collectors.toList());
    };

    @Override
    public List<Environment> getEnvironment() {
        return particleList.stream().map(p -> p.getEnvironment()).collect(Collectors.toList());
    };

    @Override
    public List<ParticleShape> getShape() {
        return particleList.stream().map(p -> p.getShape()).collect(Collectors.toList());
    };

    @Override
    public void update(final double timeStep) throws IllegalArgumentException {
        particleList.stream().parallel().filter(p -> !p.isStationary()).forEach(p -> p.update(timeStep));
    }

    @Override
    public void collide(final Polycollider2D polycollider2D, final double timeStep,
            final double coefficientOfRestitution) throws IllegalArgumentException {
        particleList.stream().parallel().filter(p -> !p.isStationary())
                .filter(p -> polycollider2D.isIntersected(p.getPreviousPosition(), p.getPosition(), p.getVelocity()))
                .forEach(p -> p.collide(polycollider2D, timeStep, coefficientOfRestitution));
    }

    private Vector2D getInitialFragmentVelocity(final int i, final int numberOfFragment, final Particle p) {
        final double initialFragmentSpeed = fragmentationParameters.getgetInitialFragmentSpeed();
        final double vx = p.getVelocity().equals(Vector2D.ZERO) ? 0.0
                : p.getVelocity().normalize().scalarMultiply(initialFragmentSpeed).getX();
        final double vy = p.getVelocity().equals(Vector2D.ZERO) ? initialFragmentSpeed
                : p.getVelocity().normalize().scalarMultiply(initialFragmentSpeed).getY();
        final double explosionCone = fragmentationParameters.getExplosionCone() >= 2.0 * Math.PI
                ? (numberOfFragment - 1.0) / numberOfFragment * fragmentationParameters.getExplosionCone()
                : fragmentationParameters.getExplosionCone();
        final double fragmentAngle = p.getVelocity().equals(Vector2D.ZERO)
                ? Math.PI - explosionCone / 2.0 + i * explosionCone / (numberOfFragment - 1.0)
                : explosionCone / 2.0 - i * explosionCone / (numberOfFragment - 1.0);

        return new Vector2D(Math.cos(fragmentAngle) * vx - Math.sin(fragmentAngle) * vy,
                Math.sin(fragmentAngle) * vx + Math.cos(fragmentAngle) * vy);
    }

    private double getFragmentMass(final int numberOfFragment, final Particle p) {
        final double massMetal = p.getMass() / (fragmentationParameters.getChargeToMetalMassRatio() + 1.0);
        return massMetal / numberOfFragment;
    }

    private ParticleShape getFragmentShape(final int numberOfFragment, final Particle p) {
        final double massCharge = p.getMass() * fragmentationParameters.getChargeToMetalMassRatio()
                / (fragmentationParameters.getChargeToMetalMassRatio() + 1.0);
        final double volumeCharge = massCharge / fragmentationParameters.getChargeDensity();
        final double volumeMetal = p.getShape().getVolume() - volumeCharge;
        final double volumeFragment = volumeMetal / numberOfFragment;
        return p.getShape().getShapeWithVolumeScaleFactor(volumeFragment / p.getShape().getVolume());
    }

    /**
     * Fragments the projectile in a certain number of fragments.
     *
     * @param numberOfFragment
     *            number of fragments
     * @throws IllegalArgumentException
     *             the number of fragments must be at least 2
     */
    @Override
    protected void fragment(final int numberOfFragment) throws IllegalArgumentException {
        if (numberOfFragment < 2) {
            throw new IllegalArgumentException();
        }
        if (!particleList.stream().filter(p -> !p.isStationary()).collect(Collectors.toList()).isEmpty()) {
            final List<Particle> stationaryParticleList = particleList.stream().filter(p -> p.isStationary())
                    .collect(Collectors.toList());
            particleList = particleList.stream().filter(p -> !p.isStationary()).map(p -> IntStream
                    .range(0, numberOfFragment)
                    .mapToObj(i -> new BasicParticleCreator().createParticle(p.getPosition(),
                            p.getVelocity().add(getInitialFragmentVelocity(i, numberOfFragment, p)), p.getEnvironment(),
                            getFragmentMass(numberOfFragment, p), getFragmentShape(numberOfFragment, p))))
                    .flatMap(Function.identity()).collect(Collectors.toList());
            particleList.addAll(stationaryParticleList);
        }
    }

    @Override
    public int getNumberOfFragments() {
        return particleList.size();
    };

    @Override
    public List<Double> getKineticEnergy() {
        return particleList.stream().map(p -> p.getKineticEnergy()).collect(Collectors.toList());
    }

    @Override
    public boolean checkAnyFragmentCollision(final Polycollider2D polycollider2D) {
        return particleList.stream()
                .anyMatch(p -> polycollider2D.isIntersected(p.getPreviousPosition(), p.getPosition(), p.getVelocity()));
    }

    @Override
    public boolean checkAllFragmentCollision(final Polycollider2D polycollider2D) {
        return particleList.stream()
                .allMatch(p -> polycollider2D.isIntersected(p.getPreviousPosition(), p.getPosition(), p.getVelocity()));
    }

    private List<Particle> getCollidingFragment(final Polycollider2D polycollider2D, final double timeStep)
            throws IllegalArgumentException {
        final List<Particle> collidingParticles = particleList.stream()
                .filter(p -> polycollider2D.isIntersected(p.getPreviousPosition(), p.getPosition(), p.getVelocity()))
                .map(p -> new BasicParticleCreator().createParticle(p)).collect(Collectors.toList());
        collidingParticles.forEach(p -> p.collide(polycollider2D, timeStep, 1.0));
        return collidingParticles;
    }

    @Override
    public List<Vector2D> getCollidingFragmentPosition(final Polycollider2D polycollider2D, final double timeStep)
            throws IllegalArgumentException {
        return getCollidingFragment(polycollider2D, timeStep).stream().map(p -> p.getPosition())
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getCollidingFragmentKineticEnergy(final Polycollider2D polycollider2D, final double timeStep)
            throws IllegalArgumentException {
        return getCollidingFragment(polycollider2D, timeStep).stream().map(p -> p.getKineticEnergy())
                .collect(Collectors.toList());
    }

    @Override
    public List<Vector2D> getFragmentLandingPoint() {
        return particleList.stream().filter(p -> p.isStationary()).map(p -> p.getPosition())
                .collect(Collectors.toList());
    }

    @Override
    public double getBlastRadius() {
        return blastRadius;
    }

}
