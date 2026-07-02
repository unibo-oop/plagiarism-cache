package model.ball;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import model.world.WorldSettings;

/**
 * Implementation of {@link Balls}.
 */
public class BallImpl implements Ball {

    private static final double BALL_RADIUS = WorldSettings.WORLD_HEIGHT * 0.01;

    private static final double MIN_MOVMENT = WorldSettings.WORLD_HEIGHT * 0.000_001;

    private Body physicalBody;
    private boolean out;
    private boolean ready;
    private boolean stuck;

    /**
     * Constructor with custom dimension.
     * @param radius of the ball
     */
    public BallImpl(final double radius) {
        this.setPhysicalBody(radius);
        this.out = false;
        this.ready = false;
        this.stuck = false;
    }

    /**
     * Constructor with default dimension.
     */
    public BallImpl() {
        this(BALL_RADIUS);
    }

    private void setPhysicalBody(final double radius) {
        final Circle shape = new Circle(radius);
        final BodyFixture fixture = new BodyFixture(shape);
        this.physicalBody = new Body();
        this.physicalBody.addFixture(fixture);
        this.physicalBody.setMass(MassType.NORMAL);
        this.physicalBody.setBullet(true);
        this.physicalBody.setGravityScale(2);
    }

    @Override
    public final void update() {
        this.out = this.checkIfOut();
        this.stuck = this.checkIfStuck();
    }

    @Override
    public final void move(final Pair<Double, Double> position) {
        final Pair<Double, Double> currentPosition = this.getPosition();
        this.physicalBody.translate(new Vector2(
                                                    position.getLeft()  - currentPosition.getLeft(),
                                                    position.getRight() - currentPosition.getRight()
                                                )
                                    );
    }

    @Override
    public final Pair<Double, Double> getPosition() {
        final Vector2 ballCentre = this.physicalBody.getWorldCenter();
        return Pair.of(ballCentre.x, ballCentre.y);
    }

    @Override
    public final Double getRadius() {
        return this.physicalBody.getFixture(0).getShape().getRadius();
    }

    @Override
    public final Body getPhysicalBody() {
        return this.physicalBody;
    }

    @Override
    public final void setReadyToLaunch(final boolean r) {
        this.ready = r;
        this.physicalBody.setActive(!r);
    }

    @Override
    public final boolean isReadyToLaunch() {
        return this.ready;
    }

    @Override
    public final void launch(final Vector2 impulse) {
        this.physicalBody.applyImpulse(impulse);
    }

    @Override
    public final boolean isOut() {
        return this.out;
    }

    private boolean checkIfOut() {
        return this.getPosition().getRight() <= 0;
    }

    @Override
    public final boolean isStuck() {
        return this.stuck;
    }

    private boolean checkIfStuck() {
        return this.physicalBody.getChangeInPosition().getMagnitude() < MIN_MOVMENT;

    }

}
