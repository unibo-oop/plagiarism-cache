package model.collidable.terrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.collidable.terrain.noise.NoiseGenerator1D;
import model.collidable.terrain.noise.PerlinNoiseGenerator1D;
import model.explosion.Explosion;
import model.physics.collider.Polycollider2D;
import model.physics.collider.Polycollider2DImpl;

/**
 * Terrain.
 *
 * @author Nicola Tamburini
 *
 *
 */
public final class TerrainImpl implements Terrain {

    private static final double NEGATIVE_INFINITY = -100000.0;
    private static final double POSITIVE_INFINITY = 100000.0;
    private static final int DEFAULT_SAMPLING = 5;

    private final int sampling;
    private final Predicate<Integer> predicate;

    private List<Vector2D> surface;
    private List<Vector2D> terrain;
    private List<Vector2D> allFallingPoints = new ArrayList<>();
    private final List<FallingChunk> fall;

    private final Vector2D gravitationalAcceleration;

    private boolean polyUpdated;
    private Polycollider2D poly;

    private final long seed;
    private final int height;
    private final int waveLength;
    private final int width;

    /**
     *
     * @param seed
     *            terrain generator seed
     * @param height
     *            terrain's height
     * @param width
     *            terrain's width
     * @param waveLength
     *            terrain's wave length
     * @param gravitationalAcceleration
     *            gravitational acceleration present in the battlefield
     * @param sampling
     *            scale of terrain
     */
    protected TerrainImpl(final long seed, final int height, final int width, final int waveLength,
            final Vector2D gravitationalAcceleration, final int sampling) {
        this.seed = seed;
        this.height = height;
        this.waveLength = waveLength;
        this.sampling = sampling;
        this.width = width;
        predicate = sampling == 1 ? i -> true : i -> i % this.sampling == 0;
        this.gravitationalAcceleration = gravitationalAcceleration;

        fall = new ArrayList<>();
        generateTerrain();
        updatePolycollider();
    }

    /**
     * 
     *
     * @param seed
     *            terrain generator seed
     * @param height
     *            terrain's height
     * @param width
     *            terrain's width
     * @param waveLength
     *            terrain's wave length
     * @param gravitationalAcceleration
     *            gravitational acceleration present in the battlefield
     */
    protected TerrainImpl(final long seed, final int height, final int width, final int waveLength,
            final Vector2D gravitationalAcceleration) {
        this(seed, height, width, waveLength, gravitationalAcceleration, DEFAULT_SAMPLING);
    }

    private void generateTerrain() {
        final NoiseGenerator1D perlin = new PerlinNoiseGenerator1D(seed, height, waveLength);

        surface = IntStream.rangeClosed(0, width).filter(i -> predicate.test(i) || i == width)
                .mapToObj(i -> new Vector2D(i, dtoi(perlin.noise(i)))).collect(Collectors.toList());

        terrain = new ArrayList<>();
        surface.forEach(v -> {
            terrain.addAll(getTerrainPointsFromPointToZero(v));
        });
    }

    private List<Vector2D> getTerrainPointsFromPointToZero(final Vector2D p) {
        return IntStream.rangeClosed(0, dtoi(p.getY())).boxed().filter(i -> predicate.test(i) || i == dtoi(p.getY()))
                .map(i -> new Vector2D(p.getX(), i)).collect(Collectors.toList());

    }

    /**
     * Method for convert type double to int.
     * 
     * @param d
     *            double number to convert
     * @return Returns the closest long to the argument, with ties rounding to
     *         positive infinity.
     */
    private int dtoi(final double d) {
        return (int) Math.round(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStationary() {
        return fall.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Polycollider2D getPolycollider() {
        if (!isStationary()) {
            throw new IllegalStateException();
        }
        if (!polyUpdated) {
            updatePolycollider();
            polyUpdated = true;
        }
        return poly;
    }

    private void updatePolycollider() {
        final List<Vector2D> t = new LinkedList<>(surface);
        t.add(0, new Vector2D(NEGATIVE_INFINITY, 0));
        t.add(new Vector2D(POSITIVE_INFINITY, surface.get(surface.size() - 1).getY()));
        poly = new Polycollider2DImpl(t);
    }

    private List<Vector2D> getSurfacePoints() {
        final List<Vector2D> s = new ArrayList<>(surface);
        s.add(new Vector2D(s.get(s.size() - 1).getX(), 0));
        s.add(new Vector2D(0, 0));
        return Collections.unmodifiableList(s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double timestep) {
        if (!isStationary()) {
            updatePolycollider();
            fall.stream().peek(f -> f.update(timestep)).peek(f -> f.collide(poly, timestep, 0.0))
                    .filter(f -> f.isStationary()).collect(Collectors.toList()).forEach(f -> {
                        terrain.addAll(f.getAllPoints());
                        updateSurface(f.getInitialPoint().getX(), f.getFinalPoint().getX());
                        fall.remove(f);
                    });

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void explode(final List<Explosion> explosions) {
        polyUpdated = false;
        explosions.forEach(ex -> {
            terrain = terrain.stream().filter(v -> !checkExplosionRadius(v, ex.getPosition(), ex.getBlastRadius())
                    || Double.compare(v.getY(), 0) == 0).collect(Collectors.toList());
            allFallingPoints = allFallingPoints.stream()
                    .filter(v -> !checkExplosionRadius(v, ex.getPosition(), ex.getBlastRadius()))
                    .collect(Collectors.toList());

            final List<Vector2D> pointsOverExp = getPointsOverExplosion(ex.getPosition(), ex.getBlastRadius());
            allFallingPoints.addAll(pointsOverExp);
            terrain.removeAll(pointsOverExp);
            updateSurface(ex.getPosition().getX() - ex.getBlastRadius(), ex.getPosition().getX() + ex.getBlastRadius());

            final int xi = pointsOverExp.stream().map(v -> v.getX()).mapToInt(Double::intValue).distinct().min()
                    .orElse((int) -1.0);
            final int xf = pointsOverExp.stream().map(v -> v.getX()).mapToInt(Double::intValue).distinct().max()
                    .orElse((int) -1.0);
            if (xi != -1 && xi <= ex.getPosition().getX() - ex.getBlastRadius() + sampling) {
                surface.add(surface.indexOf(surface.stream().filter(v -> v.getX() == xi).findFirst().get()),
                        getVector2DWhitMaxY(pointsOverExp, xi));
            }
            if (xf != -1 && xf >= ex.getPosition().getX() + ex.getBlastRadius() - sampling) {
                surface.add(surface.indexOf(surface.stream().filter(v -> v.getX() == xf).findFirst().get()) + 1,
                        getVector2DWhitMaxY(pointsOverExp, xf));
            }
        });

        if (!allFallingPoints.isEmpty()) {
            final List<Vector2D> temp = new ArrayList<>();
            for (final Double x : allFallingPoints.stream().map(v -> v.getX()).distinct().sorted()
                    .collect(Collectors.toList())) {
                if (allFallingPoints.stream().anyMatch(v -> Double.compare(v.getX(), x + sampling) == 0)) {
                    temp.addAll(consecutivePoints(allFallingPoints, x));
                } else {
                    temp.addAll(consecutivePoints(allFallingPoints, x));
                    fall.add(new FallingChunkImpl(temp, gravitationalAcceleration, sampling));
                    temp.clear();
                }
            }
            allFallingPoints.clear();
        }
    }

    private List<Vector2D> consecutivePoints(final List<Vector2D> l, final double x) {
        return l.stream().filter(v -> Double.compare(v.getX(), x) == 0).collect(Collectors.toList());
    }

    private boolean checkExplosionRadius(final Vector2D p, final Vector2D collisionPoint,
            final double explosionRadius) {
        return Math.pow(p.getX() - collisionPoint.getX(), 2) + Math.pow(p.getY() - collisionPoint.getY(), 2) <= Math
                .pow(explosionRadius, 2);

    }

    private List<Vector2D> getPointsOverExplosion(final Vector2D collisionPoint, final double explosionRadius) {
        return terrain.stream()
                .filter(v -> v.getX() >= collisionPoint.getX() - explosionRadius
                        && v.getX() <= collisionPoint.getX() + explosionRadius && v.getY() >= collisionPoint.getY())
                .collect(Collectors.toList());
    }

    /**
     * Update Surface from initial point xIn to final point XFin.
     * 
     * @param xIn
     *            x coordinate to initial point
     * @param xFin
     *            x coordinate to final point
     */
    private void updateSurface(final double xIn, final double xFin) {

        surface.removeIf(v -> v.getX() >= xIn && v.getX() <= xFin);
        surface.addAll(terrain.stream().map(v -> v.getX()).filter(d -> d >= xIn && d <= xFin).distinct()
                .map(d -> getVector2DWhitMaxY(terrain, d)).collect(Collectors.toList()));
        surface = surface.stream().sorted((v1, v2) -> Double.compare(v1.getX(), v2.getX())).distinct()
                .collect(Collectors.toList());

    }

    private Vector2D getVector2DWhitMaxY(final List<Vector2D> l, final double x) {
        return l.stream().filter(v -> Double.compare(v.getX(), x) == 0)
                .max((o1, o2) -> Double.compare(o1.getY(), o2.getY())).orElse(new Vector2D(x, 0.0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<Vector2D>> getOutlinesPoints() {
        final List<List<Vector2D>> o = getChunksPoints();
        o.add(getSurfacePoints());
        return Collections.unmodifiableList(o);
    }

    private List<List<Vector2D>> getChunksPoints() {
        return fall.stream().map(f -> f.getOutlinePoints()).collect(Collectors.toList());
    }

    /**
     * Builder class for TerrainImpl.
     * 
     * @author Nicola Tamburini
     *
     */
    public static class Builder {
        private long seed;
        private int height;
        private int width;
        private int waveLength;
        private Vector2D gravitationalAcceleration;
        private Optional<Integer> sampling = Optional.empty();

        /**
         * @param seed
         *            terrain generator seed
         * @return a istance of {@link Builder}
         */
        public Builder setSeed(final long seed) {
            this.seed = seed;
            return this;
        }

        /**
         * @param height
         *            terrain's height
         * @return a istance of {@link Builder}
         */
        public Builder setHeight(final int height) {
            this.height = height;
            return this;
        }

        /**
         * @param width
         *            terrain's width
         * @return a istance of {@link Builder}
         */
        public Builder setWidth(final int width) {
            this.width = width;
            return this;
        }

        /**
         * @param waveLength
         *            terrain's wave length
         * @return a istance of {@link Builder}
         */
        public Builder setWaveLenght(final int waveLength) {
            this.waveLength = waveLength;
            return this;
        }

        /**
         * @param gravitationalAcceleration
         *            gravitational acceleration present in the battlefield
         * @return a istance of {@link Builder}
         */
        public Builder setGravitationalAcceleration(final Vector2D gravitationalAcceleration) {
            this.gravitationalAcceleration = gravitationalAcceleration;
            return this;
        }

        /**
         * @param sampling
         *            scale of terrain
         * @return a istance of {@link Builder}
         */
        public Builder setSampling(final int sampling) {
            this.sampling = Optional.of(sampling);
            return this;
        }

        /**
         * Build terrain.
         * 
         * @return a istance of {@link TerrainImpl}
         * @throws IllegalStateException
         *             when {@code gravitationalAcceleration} is null
         * @throws IllegalArgumentException
         *             {@code sampling} must be positive {@code waveLength} must be
         *             positive, in range {@code 1-width}
         */
        public TerrainImpl build() throws IllegalStateException, IllegalArgumentException {

            if (gravitationalAcceleration == null) {
                throw new IllegalStateException();
            }
            if (sampling.isPresent() && sampling.get().intValue() <= 0) {
                throw new IllegalArgumentException();
            }
            if (waveLength <= 0 || waveLength > width) {
                throw new IllegalArgumentException();
            }
            return this.sampling.isPresent()
                    ? new TerrainImpl(seed, height, width, waveLength, gravitationalAcceleration,
                            sampling.get().intValue())
                    : new TerrainImpl(seed, height, width, waveLength, gravitationalAcceleration);
        }
    }
}
