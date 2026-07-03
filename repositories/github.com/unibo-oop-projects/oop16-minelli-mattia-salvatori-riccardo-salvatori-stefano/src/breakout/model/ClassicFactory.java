package breakout.model;

import java.util.Objects;

import breakout.model.entities.Ball;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickImpl;
import breakout.model.entities.BrickType;
import breakout.model.entities.Paddle;
import breakout.model.entities.Wall;
import breakout.model.entities.Wall.WallPos;
import breakout.model.physics.Vector2D;
import breakout.view.utils.Utils;
import javafx.geometry.Point2D;
import javafx.util.Pair;

/**
 * Creates classic game objects.
 *
 */
public final class ClassicFactory implements GameObjectFactory {

    private static final double SCREEN_WIDTH = Utils.STAGE_WIDTH;

    private enum ClassicConstant {
        /**
         * Classic ball constants.
         */
        BALL_RADIUS(7), BALL_SPEED(450), BALL_START_ANGLE(90), BALL_POS_X(635), BALL_POS_Y(384),
        /**
         * Classic paddle constants.
         */
        PADDLE_SPEED(800), PADDLE_WIDTH(175), PADDLE_HEIGHT(14), PADDLE_POS_X(470), PADDLE_POS_Y(530),
        /**
         * Classic brick constants.
         */
        BRICK_WIDTH(60), BRICK_HEIGHT(25.5),
        /**
         * Wall constants
         */
        WALL_WIDTH(60), WALL_HEIGHT(600);

        private double value;

        /**
         * Creates a costant.
         * @param value
         */
        ClassicConstant(final double value) {
            this.value = value;
        }

        private double value() {
            return this.value;
        }
    }

    /**
     * Singleton.
     */
    private static ClassicFactory classicFactoryInstance;

    /**
     * Private constructor.
     */
    private ClassicFactory() {

    }

    /**
     * @return a classic factory;
     */
    public static ClassicFactory get() {
        if (Objects.isNull(classicFactoryInstance)) {
            classicFactoryInstance = new ClassicFactory();
        }
        return classicFactoryInstance;
    }

    /**
     * [{@inheritDoc}.
     */
    public Ball createBall() {
        final Ball ball = new Ball(new Point2D(ClassicConstant.BALL_POS_X.value(), ClassicConstant.BALL_POS_Y.value()),
                Vector2D.valueOfPolar(ClassicConstant.BALL_SPEED.value(), ClassicConstant.BALL_START_ANGLE.value()),
                ClassicConstant.BALL_RADIUS.value());
        ball.setBounce(BallBouncer.CLASSIC_BOUNCE);
        return ball;
    }

    /**
     * [{@inheritDoc}.
     */
    public Paddle createPaddle() {
        return new Paddle(new Point2D(ClassicConstant.PADDLE_POS_X.value(), ClassicConstant.PADDLE_POS_Y.value()),
                ClassicConstant.PADDLE_SPEED.value(), ClassicConstant.PADDLE_WIDTH.value(),
                ClassicConstant.PADDLE_HEIGHT.value());
    }

    /**
     * [{@inheritDoc}.
     */
    public Wall createWall(final WallPos pos) throws IllegalStateException {
        switch (pos) {
        case UP:
            return new Wall(pos, 0, 0, SCREEN_WIDTH - ClassicConstant.WALL_WIDTH.value(),
                    ClassicConstant.WALL_WIDTH.value());
        case RIGHT:
            return new Wall(pos, SCREEN_WIDTH - ClassicConstant.WALL_WIDTH.value(), 0,
                    ClassicConstant.WALL_WIDTH.value(), ClassicConstant.WALL_HEIGHT.value());
        case LEFT:
            return new Wall(pos, 0, 0, ClassicConstant.WALL_WIDTH.value(), ClassicConstant.WALL_HEIGHT.value());
        default:
             throw new IllegalStateException();
        }
    }

    /**
     * [{@inheritDoc}.
     */
    public Brick createBrick(final BrickType type, final Pair<Integer, Integer> position) {
        return new BrickImpl(type,
                new Point2D(position.getKey() * ClassicConstant.BRICK_WIDTH.value(),
                        position.getValue() * ClassicConstant.BRICK_HEIGHT.value()),
                ClassicConstant.BRICK_WIDTH.value(), ClassicConstant.BRICK_HEIGHT.value());
    }

}
