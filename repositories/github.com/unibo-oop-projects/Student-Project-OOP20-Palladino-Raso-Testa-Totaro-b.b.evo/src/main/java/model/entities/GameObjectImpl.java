package model.entities;

import java.io.Serializable;

import controller.input.ComponentInput;
import controller.input.ControllerInput;
import model.utilities.Position;
import model.physics.ComponentPhysics;
import model.utilities.DirVector;
import view.graphics.AdapterGraphics;
import view.graphics.ComponentGraphics;

public abstract class GameObjectImpl implements GameObject, Serializable {

    private static final long serialVersionUID = 9139431443544241120L;

    private Position pos;
    private DirVector dirVel;
    private double speed;
    private int height;
    private int width;
    private final ComponentPhysics physics;
    private final ComponentInput input;
    private final ComponentGraphics graphics;

    public GameObjectImpl(final Position pos, final DirVector dirVel, final double speed, final int height, final int width, final ComponentPhysics physics,
            final ComponentInput input, final ComponentGraphics graphics) {
        this.pos = pos;
        this.dirVel = dirVel;
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.physics = physics;
        this.input = input;
        this.graphics = graphics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final Position pos) {
        this.pos = pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirVector getDirVector() {
        return this.dirVel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirVector(final DirVector dirVel) {
        this.dirVel = dirVel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * @return component Physics
     */
    protected ComponentPhysics getComponentPhysics() {
        return this.physics;
    }

    /**
     * @return component Input
     */
    protected ComponentInput getComponentInput() {
        return this.input;
    }

    /**
     * @return component Graphics
     */
    protected ComponentGraphics getComponentGraphics() {
        return this.graphics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updatePhysics(int timeElapsed, GameBoardImpl board);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateInput(ControllerInput controller);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateGraphics(AdapterGraphics adapterGraphics);

}
