package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl;

import java.util.Random;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformPositionGenerator;

/**
 * Implementation of {@link PlatformPositionGenerator}.
 */
public class PlatformPositionGeneratorImpl implements PlatformPositionGenerator {

    /**
     * The parameters for the distance between platforms.
     */
    private Distance distance;

    /**
     * The position of the previous platform, used to generate the next platform
     * position.
     */
    private Vector2d previousPlatformPosition;

    /**
     * Random number generator used to generate the position of the platforms.
     */
    private final Random randomNumber;

    /**
     * The boundary of the game world in the x-axis, used to generate the position
     * of the platforms.
     */
    private final Boundary boundX;

    /**
     * Constructs a new PlatformPositionGeneratorImpl.
     * 
     * @param boundWorld  the BoundWorld of the game, used to get the boundary of
     *                    the game world.
     * @param platformPos the position of the first platform, used to generate the
     *                    position of the next platforms.
     * @param distance    the parameters for the distance between platforms, used to
     *                    generate the position of the next platforms.
     */
    public PlatformPositionGeneratorImpl(final BoundWorld boundWorld, final Vector2d platformPos,
            final Distance distance) {
        this.randomNumber = new Random();
        previousPlatformPosition = new Vector2dImpl(platformPos.getX(), platformPos.getY());
        this.distance = distance;
        this.boundX = boundWorld.getBoundX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2d generatePosition(final double width, final double height, final Vector2d pP) {
        setPreviousPosition(pP);
        return new Vector2dImpl(genPosX(width), genPosY(height));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDistance(final Distance distance) {
        this.distance = distance;
    }

    private double genPosX(final double width) {
        final double x;
        double xMin;
        final double xMax;

        if (previousPlatformPosition.getX() - distance.maxDistanceX() < boundX.x0()) {
            xMin = boundX.x0();
        } else {
            xMin = previousPlatformPosition.getX() - distance.maxDistanceX();
        }
        if (previousPlatformPosition.getX() + distance.maxDistanceX() + width > boundX.x1()) {
            xMax = boundX.x1() - width;
        } else {
            xMax = previousPlatformPosition.getX() + distance.maxDistanceX();
        }
        if (xMax < xMin) {
            xMin = xMax - 1;
        }

        x = randomNumber.nextDouble(xMin, xMax);
        return x;

    }

    private double genPosY(final double height) {
        final double y;

        y = randomNumber.nextDouble(previousPlatformPosition.getY() - distance.maxDistanceY(),
                previousPlatformPosition.getY() - distance.minDistanceY() - height);
        return y;
    }

    private void setPreviousPosition(final Vector2d pos) {
        this.previousPlatformPosition = pos;
    }

}
