package model.collidable.terrain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Polycollider2D;
import model.physics.particle.BasicParticleCreator;
import model.physics.particle.Particle;
import model.physics.particle.environment.Environment;
import model.physics.particle.environment.GravityOnlyEnvironment;
import model.physics.particle.shape.Sphere;

/**
 * Represent a falling terrain chunk.
 * 
 * @author Nicola Tamburini
 *
 */
public final class FallingChunkImpl implements FallingChunk {

    private static final double RADIUS_TERRAIN_PARTICLE = 0.06;

    private final List<Particle> lowerPoints;
    private final List<Integer> distance;

    private final int sampling;

    /**
     * 
     * @param points
     *            points, in metre
     * @param gravitationalAcceleration
     *            gravitational acceleration
     * @param sampling
     *            sampling, in metre
     */
    public FallingChunkImpl(final List<Vector2D> points, final Vector2D gravitationalAcceleration, final int sampling) {
        this.sampling = sampling;

        final Environment env = new GravityOnlyEnvironment.Builder()
                .setGravitationalAcceleration(gravitationalAcceleration).build();
        this.lowerPoints = points.stream().map(p -> p.getX()).distinct().map(x -> getVector2DWhitMinY(points, x))
                .map(v -> new BasicParticleCreator().createParticle(v, new Vector2D(0, 0), env, 10,
                        new Sphere(RADIUS_TERRAIN_PARTICLE)))
                .sorted(new Comparator<Particle>() {

                    @Override
                    public int compare(final Particle arg0, final Particle arg1) {
                        return Double.compare(arg0.getPosition().getX(), arg1.getPosition().getX());
                    }
                }).collect(Collectors.toList());

        this.distance = lowerPoints.stream()
                .map(p -> (int) (getVector2DWhitMaxY(points, p.getPosition().getX()).getY() - p.getPosition().getY()))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vector2D> getOutlinePoints() {
        return Stream.concat(this.lowerPoints.stream().map(p -> p.getPosition()),
                this.lowerPoints.stream()
                        .map(p -> new Vector2D(p.getPosition().getX(),
                                p.getPosition().getY() + this.distance.get(this.lowerPoints.indexOf(p))))
                        .sorted(new Comparator<Vector2D>() {

                            @Override
                            public int compare(final Vector2D arg0, final Vector2D arg1) {
                                return Double.compare(arg1.getX(), arg0.getX());
                            }
                        }))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double timeStep) {
        this.lowerPoints.forEach(p -> p.update(timeStep));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collide(final Polycollider2D poly, final double timeStep, final double coefficentRestitution) {
        this.lowerPoints.stream()
                .filter(p -> poly.isIntersected(p.getPreviousPosition(), p.getPosition(), p.getVelocity()))
                .forEach(p -> p.collide(poly, timeStep, coefficentRestitution));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStationary() {
        return lowerPoints.stream().allMatch(p -> p.isStationary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vector2D> getAllPoints() throws IllegalStateException {
        if (!isStationary()) {
            throw new IllegalStateException();
        }

        final List<Vector2D> allPoints = new ArrayList<>();
        this.lowerPoints.stream().forEach(p -> {
            allPoints.addAll(getinternalPoints((int) Math.round(p.getPosition().getX()),
                    (int) Math.round(p.getPosition().getY()),
                    (int) Math.round(p.getPosition().getY() + this.distance.get(lowerPoints.indexOf(p)))));
        });
        return allPoints;
    }

    private List<Vector2D> getinternalPoints(final int x, final int yIn, final int yFin) {
        return IntStream.rangeClosed(yIn, yFin).boxed().filter(i -> i % sampling == 0 || i == yFin)
                .map(i -> new Vector2D(x, i)).collect(Collectors.toList());
    }

    private Vector2D getVector2DWhitMinY(final List<Vector2D> l, final double x) {
        return l.stream().filter(v -> v.getX() == x).min(new Comparator<Vector2D>() {

            @Override
            public int compare(final Vector2D o1, final Vector2D o2) {
                return Double.compare(o1.getY(), o2.getY());
            }
        }).orElse(new Vector2D(x, 0.0));
    }

    private Vector2D getVector2DWhitMaxY(final List<Vector2D> l, final double x) {
        return l.stream().filter(v -> v.getX() == x).max(new Comparator<Vector2D>() {

            @Override
            public int compare(final Vector2D o1, final Vector2D o2) {
                return Double.compare(o1.getY(), o2.getY());
            }
        }).orElse(new Vector2D(x, 0.0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getInitialPoint() {
        return this.lowerPoints.get(0).getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getFinalPoint() {
        return this.lowerPoints.get(this.lowerPoints.size() - 1).getPosition();
    }

}
