package model.character;

import model.ball.Ball;
import model.ball.BallImpl;
import model.world.GameWorld;
import model.world.WorldSettings;

import org.apache.commons.lang3.tuple.Pair;

/**
 * This is an implementation of {@link Character} interface.
 */
public class Abra implements Character {

    private static final double TP_RATIO = 0.8;
    private final String name;

    public Abra() {
        this.name = "Abra";
    }

    @Override
    public final void usePower(final GameWorld world) {
        final Ball originalBall = world.getBall();
        final Ball abraBall = new BallImpl();
        abraBall.move(Pair.of(originalBall.getPosition().getLeft(), WorldSettings.WORLD_HEIGHT * TP_RATIO));
        world.setBall(abraBall);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void deletePower(final GameWorld gameWorld) { }
}
