package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.platformbuilder.impl.PlatformBuilderImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.platformphysic.impl.HorizontalMovementBehavior;
import it.unibo.model.physics.platformphysic.impl.OnTouchDestroyBehavior;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Director;

/**
 * Implementation of the Director interface.
 * Uses the PlatformBuilder to construct specific platform variants (Normal, Moving, etc.).
 */
public class DirectorImpl implements Director {

    /**
     * The width of the platforms to be built. 
     */
    private final double width;

    /**
     * The height of the platforms to be built.
     */
    private final double height;

    /**
     * Constructs a new DirectorImpl.
     * 
     * @param width the default width for platforms
     * @param height the default height for platforms
     */
    public DirectorImpl(final double width, final double height) {
        this.width = width;
        this.height = height;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Platform normalPlatform(final Vector2d position) {
        return new PlatformBuilderImpl()
                .at(position)
                .size(width, height)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform movingOnTouchPlatform(final Vector2d position) {
                return new PlatformBuilderImpl()
                .at(position)
                .size(width, height)
                .addJumpBehaviour(new OnTouchDestroyBehavior())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Platform movingPlatform(final Vector2d position) {
        return new PlatformBuilderImpl()
                .at(position)
                .size(width, height)
                .addMovementBehaviour(new HorizontalMovementBehavior(100))
                .build();
    }

}
