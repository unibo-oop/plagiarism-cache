package model.bucket;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Triangle;
import org.dyn4j.geometry.Vector2;

import model.world.WorldSettings;

/**
 * The BucketImpl class implements the {@link Bucket} interface to create a bucket moving one side to the other of the stage. This class has two constructor: one which
 * creates a bucket with the given positions of the vertices, while the other create a bucket with default vertices.
 */

public class BucketImpl implements Bucket {

    private static final double DEFAULT_VELOCITY = 0.005;
    private static final double RECT_HEIGHT = WorldSettings.WORLD_HEIGHT * 0.0001;
    private static final double RECT_WIDTH = WorldSettings.WORLD_HEIGHT * 0.1;
    private static final double BUCKET_WIDTH = WorldSettings.WORLD_HEIGHT * 0.3;
    private static final double BUCKET_HEIGHT = WorldSettings.WORLD_HEIGHT * 0.05;
    private double linearVelocity = DEFAULT_VELOCITY * WorldSettings.WORLD_WIDTH;
    private static final int RIGHT_SIDE_UPPER = 0;
    private static final int RIGHT_SIDE_LOWER_LEFT = 1;
    private static final int RIGHT_SIDE_LOWER_RIGHT = 2;
    private static final int LEFT_SIDE_LOWER_LEFT = 3;
    private static final int LEFT_SIDE_LOWER_RIGHT = 4;
    private static final int LEFT_SIDE_UPPER = 5;
    private Body triangle;
    private Body rectangle;
    private List<Pair<Double, Double>> positions;

    /**
     * Constructor of BucketImpl.
     * @param bucketWidth the width of the bucket to set.
     */
    public BucketImpl(final double bucketWidth) {
        this.positions = new ArrayList<>();
        this.triangle = new Body();
        this.rectangle = new Body(); 
        this.positions.addAll(List.of(
                Pair.of(RECT_WIDTH / 2, BUCKET_HEIGHT), Pair.of(RECT_WIDTH / 2, 0.), Pair.of(bucketWidth / 2, 0.), 
                Pair.of(-bucketWidth / 2, 0.), Pair.of(-RECT_WIDTH / 2, 0.), Pair.of(-RECT_WIDTH / 2, BUCKET_HEIGHT)
        ));
        this.createPhysicalBody();
    }

    public BucketImpl() {
        this(BUCKET_WIDTH);
    }

    private void createPhysicalBody() {
        final BodyFixture fix = new BodyFixture(new Triangle(new Vector2(this.positions.get(RIGHT_SIDE_UPPER).getLeft(), this.positions.get(RIGHT_SIDE_UPPER).getRight()),
                new Vector2(this.positions.get(RIGHT_SIDE_LOWER_LEFT).getLeft(), this.positions.get(RIGHT_SIDE_LOWER_LEFT).getRight()), 
                new Vector2(this.positions.get(RIGHT_SIDE_LOWER_RIGHT).getLeft(), this.positions.get(RIGHT_SIDE_LOWER_RIGHT).getRight())));
        final BodyFixture fix1 = new BodyFixture(new Triangle(new Vector2(this.positions.get(LEFT_SIDE_LOWER_LEFT).getLeft(), this.positions.get(LEFT_SIDE_LOWER_LEFT).getRight()),
                new Vector2(this.positions.get(LEFT_SIDE_LOWER_RIGHT).getLeft(), this.positions.get(LEFT_SIDE_LOWER_RIGHT).getRight()),
                new Vector2(this.positions.get(LEFT_SIDE_UPPER).getLeft(), this.positions.get(LEFT_SIDE_UPPER).getRight())));
        final BodyFixture fix2 = new BodyFixture(new Rectangle(RECT_WIDTH, RECT_HEIGHT));
        this.triangle.addFixture(fix);
        this.triangle.addFixture(fix1);
        this.rectangle.addFixture(fix2);
        this.triangle.getFixture(0).setRestitution(0.5);
        this.triangle.getFixture(1).setRestitution(0.5);
        this.rectangle.translate(0, this.positions.get(1).getRight());
        this.triangle.setMass(MassType.INFINITE);
        this.rectangle.setMass(MassType.INFINITE);
    }

    @Override
    public final List<Pair<Double, Double>> getPositions() {
        return this.positions;
    }

    @Override
    public final List<Body> getPhysicalBody() {
        return List.of(rectangle, triangle);
    }

    @Override
    public final boolean slamDunk() {
        final boolean isHit = !this.rectangle.getContacts(false).isEmpty();
        this.rectangle.getContacts(false).clear();
        return isHit;
    }

    @Override
    public final void update() {
        final List<Pair<Double, Double>> position = List.copyOf(this.positions);
        this.invertX(position);
        this.triangle.translate(new Vector2(linearVelocity, 0.));
        this.rectangle.translate(new Vector2(linearVelocity, 0.));
        this.positions.clear();
        position.forEach(o -> this.positions.add(Pair.of(o.getLeft() + linearVelocity, o.getRight())));
    }

    private void invertX(final List<Pair<Double, Double>> positions) {
        if (positions.get(2).getLeft() >= WorldSettings.WORLD_WIDTH / 2 || positions.get(3).getLeft() <= -WorldSettings.WORLD_WIDTH / 2) {
            this.linearVelocity = -this.linearVelocity;
        }
    }

}
