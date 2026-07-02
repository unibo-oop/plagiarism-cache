package model.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import controller.event.Event;
import controller.event.EventHandler;
import controller.game.GameState;
import controller.input.ControllerInput;
import model.collision.CollisionController;
import model.collision.CollisionControllerImpl;
import model.utilities.Angle;
import model.utilities.Boundaries;
import model.utilities.Pair;
import model.utilities.PowerUpUtilities;

public class GameBoardImpl implements GameBoard {

    private final Set<Ball> balls;
    private final Set<Brick> bricks;
    private final Set<Paddle> paddle;
    private final Set<PowerUp> pwup;
    private final Wall wall;
    private final EventHandler eventHandler;
    private final CollisionController collision;
    private String pwUpType;

    public GameBoardImpl(final Wall wall, final GameState state) {
        this.balls = new HashSet<>();
        this.bricks = new HashSet<>();
        this.paddle = new HashSet<>();
        this.pwup = new HashSet<>();
        this.wall = wall;
        this.eventHandler = new EventHandler(state);
        this.collision = new CollisionControllerImpl();
        this.pwUpType = PowerUpUtilities.DEFAULT_PWUP_STRING;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setBalls(final Collection<Ball> balls) {
        this.balls.clear();
        this.balls.addAll(balls);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Set<Ball> getBalls() {
        return Collections.unmodifiableSet(this.balls);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void removeBall(final Ball ball) {
        this.balls.remove(ball);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPaddle(final Paddle paddle) {
        this.paddle.clear();
        this.paddle.add(paddle);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Paddle getpaddle() {
        return paddle.stream().findFirst().get();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setBricks(final Collection<Brick> bricks) {
        this.bricks.clear();
        this.bricks.addAll(bricks);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Set<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void removeBrick(final Brick brick) {
        this.bricks.remove(brick);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setPowerUps(final Collection<PowerUp> pwup) {
        this.pwup.addAll(pwup);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Set<PowerUp> getPowerUps() {
        return this.pwup;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void removePowerUp(final PowerUp pwup) {
        this.pwup.remove(pwup);
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void setTypePowerUp(final String type) {
        this.pwUpType = type;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public String getTypePowerUp() {
        return this.pwUpType;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void clearPowerUps() {
        this.pwup.clear();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Wall getWall() {
        return this.wall;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Optional<Boundaries> checkGameObjCollisionsWithWall(final GameObject obj) {
        return this.collision.checkGameObjCollisionsWithWall(this.wall, obj);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<Brick, Boundaries>> checkBallCollisionsWithBrick(final Ball ball) {
        Optional<Pair<Brick, Boundaries>> result = Optional.empty();
        for (final var b : this.bricks) {
            result = this.collision.checkBallCollisionsWithBrick(ball, b);
            if (!result.isEmpty()) {
                return result;
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Pair<Optional<Boundaries>, Optional<Angle>> checkBallCollisionsWithPaddle(final Ball ball) {
        Optional<Boundaries> result;
        for (final var paddle : this.paddle) {
            result = this.collision.checkBallCollisionsWithPaddle(ball, paddle);
            if (result.isPresent() && result.get().equals(Boundaries.UPPER)) {
                final double centerBall = ball.getPos().getX() + (ball.getWidth() / 2);
                final double zonePlayer = paddle.getWidth() / Angle.values().length;
                for (int i = 0; i < Angle.values().length; i++) {
                    if (centerBall > paddle.getPos().getX() + (i * zonePlayer) 
                            && centerBall < paddle.getPos().getX() + ((i + 1) * zonePlayer)) {
                        return new Pair<>(Optional.of(Boundaries.UPPER), Optional.of(Angle.values()[i]));
                    }
                }
            } else if (result.isPresent() && (result.get().equals(Boundaries.SIDE_LEFT) || result.get().equals(Boundaries.SIDE_RIGHT))) {
                return new Pair<>(result, Optional.empty());
            }
        }
        return new Pair<>(Optional.empty(), Optional.empty());
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<PowerUp, Boundaries>> checkPowerUpCollisionsWithPaddle(final PowerUp pwUp) {
        Optional<Pair<PowerUp, Boundaries>> result = Optional.empty();
        for (final var paddle : this.paddle) {
            result = this.collision.checkPwUpCollisionWithPaddle(pwUp, paddle);
            if (!result.isEmpty()) {
                return result;
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getSceneEntities() {
        final Set<GameObject> entities = new HashSet<>();
        entities.addAll(this.balls);
        entities.addAll(this.bricks);
        entities.addAll(this.paddle);
        entities.addAll(this.pwup);
        return Collections.unmodifiableSet(entities);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void updateState(final int timeElapsed) {
        this.getSceneEntities().forEach(i -> i.updatePhysics(timeElapsed, this));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void movePaddle(final ControllerInput controllerInput) {
        this.paddle.forEach(e -> e.updateInput(controllerInput));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public EventHandler getEventHanlder() {
        return this.eventHandler;
    }

    /**
     * Add an event on EventQueue.
     * @param e Event that is added to the queue
     */
    public void eventListener(final Event e) {
        this.eventHandler.addEvent(e);
    }
}
