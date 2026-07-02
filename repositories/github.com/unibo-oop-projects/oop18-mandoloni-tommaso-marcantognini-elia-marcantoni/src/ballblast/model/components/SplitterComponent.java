package ballblast.model.components;

import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

import com.google.common.collect.ImmutableList;

import ballblast.commons.events.EventType;
import ballblast.model.data.GameDataManager;
import ballblast.model.events.GameEventManager;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallType;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectManager;
import ballblast.model.gameobjects.GameObjectType;
import ballblast.model.helpers.GameObjectHelper;
import ballblast.model.helpers.SpawnHelper;
import ballblast.model.physics.CollisionManager;

/**
 * Adds the ability to a {@link GameObject} to be split when destroyed.
 */
public class SplitterComponent extends AbstractComponent {
    private static final double Y_SPLIT_VELOCITY = -110;
    private final GameObjectManager gameObjectManager;
    private final CollisionManager collisionManager;
    private final GameDataManager gameDataManager;
    private final GameEventManager eventManager;

    /**
     * Class constructor.
     * 
     * @param gameObjectManager the {@link GameObjectManager} used to add
     *                          {@link Ball}s.
     * @param collisionManager  the {@link CollisionManager} used to create the
     *                          {@link Ball}'s {@link CollisionComponent}.
     * @param gameDataManager   the {@link GameDataManager} used to increment the
     *                          destroyed balls counter.
     * @param eventManager      the {@link GameEventManager}.
     */
    public SplitterComponent(final GameObjectManager gameObjectManager, final CollisionManager collisionManager,
            final GameDataManager gameDataManager, final GameEventManager eventManager) {
        super(ComponentType.SPLITTER);
        this.gameObjectManager = gameObjectManager;
        this.collisionManager = collisionManager;
        this.gameDataManager = gameDataManager;
        this.eventManager = eventManager;
    }

    @Override
    public final void update(final double elapsed) {
        if (this.getParent().isDestroyed()) {
            this.tryToSplitParent();
            this.gameDataManager.incrementDestroyedBalls();
        }
    }

    @Override
    public final void setParent(final GameObject parent) {
        super.setParent(parent);
        if (this.getParent().getType() != GameObjectType.BALL) {
            throw new IllegalStateException("parent's type must be BALL");
        }
    }

    /**
     * Tries to add two new instance of {@link Ball} inside the
     * {@link GameObjectManager}.
     */
    public void tryToSplitParent() {
        final Optional<BallType> childBallType = ((Ball) this.getParent()).getBallType().getChild();
        if (childBallType.isPresent()) {
            this.addParentChilds(childBallType.get());
            this.eventManager.addGameEvent(EventType.SPLIT);
        } else {
            this.eventManager.addGameEvent(EventType.DESTROY);
        }
    }

    private void addParentChilds(final BallType type) {
        this.gameObjectManager.addGameObjects(ImmutableList.of(
                this.generateChildBall(type, this.getChildLife(),  this.getChildXvelocity(), this.getChildPosition()),
                this.generateChildBall(type, this.getChildLife(), -this.getChildXvelocity(), this.getChildPosition()))
        );
    }

    private GameObject generateChildBall(final BallType type, final int life, final double xVel, final Coordinate pos) {
        final GameObject ball = GameObjectHelper.createBall(
                type, life, pos, Vector2D.create(xVel, Y_SPLIT_VELOCITY / 2), 
                this.collisionManager, this.gameObjectManager, this.gameDataManager, this.eventManager);
        SpawnHelper.activeComponents(ball);
        return ball;
    }

    private double getChildXvelocity() {
        return Math.abs(this.getParent().getVelocity().getX());
    }

    private int getChildLife() {
        return ((Ball) this.getParent()).getInitialLife() / 2;
    }

    private Coordinate getChildPosition() {
        return this.getParent().getPosition();
    }
}
