package model.world;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.ContinuousDetectionMode;
import org.dyn4j.dynamics.Settings;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import model.ball.Ball;
import model.ball.BallImpl;
import model.balllaucher.BallLauncher;
import model.balllaucher.BallLauncherImpl;
import model.bucket.Bucket;
import model.bucket.BucketImpl;
import model.obstacle.Obstacle;

/**
 * Implementation of {@link GameWorld}.
 */
public class GameWorldImpl implements GameWorld {

    private static final double BORDER_WIDTH = 5; 
    private static final double DEFAULT_RESITUTION = 0.6;

    private World physicalWorld;
    private Ball ball;
    private final BallLauncher ballLauncher;
    private Bucket bucket;
    private final List<Obstacle> obstacles;
    private final List<Obstacle> hitObstacles;

    /**
     * Default constructor.
     * Create a world with a default {@link Ball}, a default {@link BallLauncher} and a default {@link Bucket}
     */
    public GameWorldImpl() {
        this.setPhysicalWorld();
        this.ball = new BallImpl();
        this.physicalWorld.addBody(this.ball.getPhysicalBody());
        this.ballLauncher = new BallLauncherImpl();
        this.bucket = new BucketImpl();
        this.physicalWorld.addBody(this.bucket.getPhysicalBody().get(0));
        this.physicalWorld.addBody(this.bucket.getPhysicalBody().get(1));
        this.obstacles = new LinkedList<>();
        this.hitObstacles = new LinkedList<>();
    }

    private void setPhysicalWorld() {
        this.physicalWorld = new World();
        // create world setting
        final Settings settings = new Settings();
        settings.setContinuousDetectionMode(ContinuousDetectionMode.BULLETS_ONLY);
        this.physicalWorld.setSettings(settings);
        // create world borders
        final BodyFixture borderFixture = new BodyFixture(new Rectangle(BORDER_WIDTH, WorldSettings.WORLD_HEIGHT));
        borderFixture.setRestitution(DEFAULT_RESITUTION);
        final Body leftBorder = new Body();
        leftBorder.addFixture(borderFixture);
        leftBorder.translate(-(WorldSettings.WORLD_WIDTH / 2 + BORDER_WIDTH / 2), WorldSettings.WORLD_HEIGHT / 2);
        this.physicalWorld.addBody(leftBorder);
        final Body rightBorder = new Body();
        rightBorder.addFixture(borderFixture);
        rightBorder.translate(WorldSettings.WORLD_WIDTH / 2 + BORDER_WIDTH / 2, WorldSettings.WORLD_HEIGHT / 2);
        this.physicalWorld.addBody(rightBorder);
        //create world ceiling
        final BodyFixture ceilingFixture = new BodyFixture(new Rectangle(WorldSettings.WORLD_WIDTH, BORDER_WIDTH));
        ceilingFixture.setRestitution(DEFAULT_RESITUTION);
        final Body ceiling = new Body();
        ceiling.addFixture(ceilingFixture);
        ceiling.translate(0, WorldSettings.WORLD_HEIGHT + BORDER_WIDTH / 2);
        this.physicalWorld.addBody(ceiling);
    }

    @Override
    public final void update(final double elapsedTime, final Pair<Double, Double> mousePosition) {
        this.physicalWorld.update(elapsedTime);
        this.obstacles.forEach(o -> {
            if (this.obstacles.contains(o) && o.hit()) {
                this.hitObstacles.add(o);
            }
        });
        this.obstacles.removeAll(this.hitObstacles);
        this.ball.update();
        this.ballLauncher.update(mousePosition);
        this.bucket.update();

    }

    @Override
    public final void setBall(final Ball ball) {
        this.physicalWorld.removeBody(this.ball.getPhysicalBody());
        this.ball = ball;
        this.physicalWorld.addBody(this.ball.getPhysicalBody());
    }

    @Override
    public final void setBucket(final Bucket bucket) {
        this.bucket.getPhysicalBody().forEach(b -> this.physicalWorld.removeBody(b));
        this.bucket = bucket;
        this.bucket.getPhysicalBody().forEach(b -> this.physicalWorld.addBody(b));
    }

    @Override
    public final void addObstacles(final List<Obstacle> obstacles) {
        obstacles.forEach(o -> this.physicalWorld.addBody(o.getPhysicalBody()));
        this.obstacles.addAll(obstacles);
    }

    @Override
    public final Ball getBall() {
        return this.ball;
    }

    @Override
    public final BallLauncher getBallLauncher() {
        return this.ballLauncher;
    }

    @Override
    public final Bucket getBucket() {
        return this.bucket;
    }

    @Override
    public final List<Obstacle> getHitObstacles() {
        return this.hitObstacles;
    }

    @Override
    public final List<Obstacle> getNotHitObstacles() {
        return this.obstacles;
    }

    @Override
    public final void setNewTurn() {
        this.setBall(new BallImpl());
        this.ballLauncher.load(this.ball);
        this.removeHitObstacles();
    }

    @Override
    public final void removeHitObstacles() {
        this.hitObstacles.forEach(o -> this.physicalWorld.removeBody(o.getPhysicalBody()));
        this.hitObstacles.clear();
    }

    @Override
    public final void setGravity(final double gravity) {
        this.physicalWorld.setGravity(new Vector2(0., -gravity));
    }

}
