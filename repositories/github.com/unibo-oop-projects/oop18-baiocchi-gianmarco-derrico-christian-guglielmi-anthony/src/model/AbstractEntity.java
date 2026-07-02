package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.engine.GameEngineImpl;
import graphics.SpriteSheet;
import input.Command;
import physics.Direction;
import utilities.FrameSizeUtils;
import utilities.Pair;
import utilities.Position;

/**
 * This class is an abstract implementation of {@link DynamicObject}.
 * It defines a generic dynamic game entity.
 */
public abstract class AbstractEntity implements DynamicObject {

    private static final double MILLISECONDS_TO_SECONDS = 0.001;
    private static final int UPPER_BOUND = FrameSizeUtils.getEdgeLength() - SpriteSheet.SPRITE_SIZE_IN_GAME;
    private static final int LOWER_BOUND = 0 + SpriteSheet.SPRITE_SIZE_IN_GAME;
    private Position previousPos;
    private final List<Command> commandQueue;
    private final Position entityPosition;
    private final boolean solid;
    private final boolean breakable;
    private Direction direction;
    private double spaceX;
    private double spaceY;

    /**
     * Creates AbstractEntity.
     * @param position : initial entity position.
     * @param solid : a boolean value that says if entity is solid or not.
     * @param breakable : a boolean value that says if entity is breakable or not.
     */
    public AbstractEntity(final Position position, final boolean solid, final boolean breakable) {
        this.commandQueue = new ArrayList<>();
        this.entityPosition = new Position(position.getX(), position.getY());
        this.solid = solid;
        this.breakable = breakable;
        this.direction = Direction.STOP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return new Position(this.entityPosition.getX(), this.entityPosition.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSolid() {
        return this.solid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBreakable() {
        return this.breakable;
    }

    /**
     * to be added Dynamic Obj.
     * @param nValue : new value for spaceX.
     */
    public void setSpaceX(final double nValue) {
        this.spaceX = nValue;
    }

    /**
     * to be added to Dynamic Obj!!!!
     * @param spaceY : new value for spaceY.
     */
    public void setSpaceY(final double spaceY) {
        this.spaceY = spaceY;
    }

    /**
     * to be added to DynamicObj!!!!
     * @return spaceX.
     */
    public double getSpaceX() {
        return this.spaceX;
    }

    /**
     * to be added DynamicObj interface!!!!!
     * @return spaceY.
     */
    public double getSpaceY() {
        return this.spaceY;
    }

    private Pair<Double, Double> getDeltas(final Pair<Double, Double> velocity, final double time) {
        double deltaX = velocity.getFirst() * time;
        double deltaY = velocity.getSecond() * time;
        if (this.previousPos.getX() + deltaX >= UPPER_BOUND) {
            deltaX = UPPER_BOUND - previousPos.getX();
        } else if (this.previousPos.getX() + deltaX <= LOWER_BOUND) {
            deltaX = LOWER_BOUND - previousPos.getX();
        }
        if (this.previousPos.getY() + deltaY >= UPPER_BOUND) {
            deltaY = UPPER_BOUND - this.previousPos.getY();
        } else if (this.previousPos.getY() + deltaY <= LOWER_BOUND) {
            deltaY = LOWER_BOUND - this.previousPos.getY();
        }
        return new Pair<>(deltaX, deltaY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final long elapsedTime) {
        this.previousPos = this.getPosition();
        final double time = elapsedTime * MILLISECONDS_TO_SECONDS;
        double velX = 0;
        double velY = 0;
        if (elapsedTime > 0) {
            velX = this.getSpaceX() / (elapsedTime * GameEngineImpl.FPS);
            velY = this.getSpaceY() / (elapsedTime * GameEngineImpl.FPS);
        }
        final Pair<Double, Double> deltas = this.getDeltas(new Pair<>(velX, velY), time);
        this.getPosition().setX(this.previousPos.getX() + deltas.getFirst());
        this.getPosition().setY(this.previousPos.getY() + deltas.getSecond());
    }

    /**
     * Gets the command queue.
     * @return entity's command queue.
     */
    public List<Command> getCommandQueue() {
        return Collections.unmodifiableList(this.commandQueue);
    }

    /**
     * Direction getter.
     * @return current direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Direction setter.
     * @param way to set
     */
    public void setDirection(final Direction way) {
        this.direction = way;
    }

}
