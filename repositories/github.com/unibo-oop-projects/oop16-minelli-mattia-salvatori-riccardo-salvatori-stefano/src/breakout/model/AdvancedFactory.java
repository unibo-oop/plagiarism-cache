package breakout.model;

import java.util.Objects;

import breakout.model.entities.Ball;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickImpl;
import breakout.model.entities.BrickType;
import breakout.model.entities.Paddle;
import breakout.model.entities.PowerUp;
import breakout.model.entities.PowerUpEffect;
import breakout.model.entities.Projectile;
import breakout.model.entities.Wall;
import breakout.model.entities.Wall.WallPos;
import breakout.model.physics.Vector2D;
import breakout.view.utils.Utils;
import javafx.geometry.Point2D;
import javafx.util.Pair;

/**
 * Creates advanced game objects.
 *
 */
public final class AdvancedFactory implements GameObjectFactory {

    private static final double SCREEN_WIDTH = Utils.STAGE_WIDTH;
    private static final double SCREEN_HEIGHT = Utils.STAGE_HEIGHT;

    private enum AdvancedConstant {
        /**
         * Advanced ball constants.
         */
        BALL_RADIUS(10), BALL_SPEED(780), BALL_START_ANGLE(90), BALL_POS_X(635), BALL_POS_Y(384),
        /**
         * Advanced paddle constants.
         */
        PADDLE_SPEED(1450), PADDLE_WIDTH(200), PADDLE_HEIGHT(18), PADDLE_POS_X(545), PADDLE_POS_Y(560),
        /**
         * Projectile constants.
         */
        PROJECTILE_SPEED(430), PROJECTILE_ANGLE(-90), PROJECTILE_WIDTH(8), PROJECTILE_HEIGHT(43),
        /**
         * PowerUp constants.
         */
        POWERUP_SPEED(273), POWERUP_WIDTH(17), POWERUP_HEIGHT(17),
        /**
         * Advanced brick constants.
         */
        BRICK_WIDTH(60), BRICK_HEIGHT(25.5);

        private double value;

        /**
         * Creates a constant.
         * 
         * @param value
         *            the value of the constant
         */
        AdvancedConstant(final double value) {
            this.value = value;
        }

        private double value() {
            return this.value;
        }
    }

    /**
     * Singleton.
     */
    private static AdvancedFactory advancedFactoryInstance = new AdvancedFactory();

    /**
     * Private constructor.
     */
    private AdvancedFactory() {
    }

    /**
     * @return an advanced factory;
     */
    public static AdvancedFactory get() {
        if (Objects.isNull(advancedFactoryInstance)) {
            advancedFactoryInstance = new AdvancedFactory();
        }
        return advancedFactoryInstance;
    }

    /**
     * {@inheritDoc}.
     */
    public Ball createBall() {
        final Ball ball = new Ball(
                new Point2D(AdvancedConstant.BALL_POS_X.value(), AdvancedConstant.BALL_POS_Y.value()),
                Vector2D.valueOfPolar(AdvancedConstant.BALL_SPEED.value(), AdvancedConstant.BALL_START_ANGLE.value()),
                AdvancedConstant.BALL_RADIUS.value());
        ball.setBounce(BallBouncer.ADVANCED_BOUNCE);
        return ball;
    }

    /**
     * {@inheritDoc}.
     */
    public Paddle createPaddle() {
        return new Paddle(new Point2D(AdvancedConstant.PADDLE_POS_X.value(), AdvancedConstant.PADDLE_POS_Y.value()),
                AdvancedConstant.PADDLE_SPEED.value(), AdvancedConstant.PADDLE_WIDTH.value(),
                AdvancedConstant.PADDLE_HEIGHT.value());
    }

    @Override
    public Wall createWall(final WallPos pos) {
        switch (pos) {
        case UP:
            return new Wall(pos, 1, 0, SCREEN_WIDTH, 0);
        case RIGHT:
            return new Wall(pos, 0, 0, 0, SCREEN_HEIGHT);
        case LEFT:
            return new Wall(pos, SCREEN_WIDTH, 0, 0, SCREEN_HEIGHT);
        default:
            return null;
        }
    }

    /**
     * 
     * @param puType
     *            a thype from {@link PowerUpEffect}
     * @param position
     *            position of the object
     * @return a power up in the given position
     */
    public PowerUp createPowerUp(final PowerUpEffect puType, final Point2D position) {
        return new PowerUp(puType, position, AdvancedConstant.POWERUP_SPEED.value(),
                AdvancedConstant.POWERUP_WIDTH.value(), AdvancedConstant.POWERUP_HEIGHT.value());
    }

    /**
     * 
     * @param position
     *            the position on wich create the projectile.
     * @return a projectile
     */
    public Projectile createProjectile(final Point2D position) {
        return new Projectile(position,
                Vector2D.valueOfPolar(AdvancedConstant.PROJECTILE_SPEED.value(),
                        AdvancedConstant.PROJECTILE_ANGLE.value()),
                AdvancedConstant.PROJECTILE_WIDTH.value(), AdvancedConstant.PROJECTILE_HEIGHT.value());
    }

    /**
     * @param type
     *            a brick type from {@link BrickType}
     * @param position
     *            a pair (row, column) in the level grid
     * @return the brick
     */
    public Brick createBrick(final BrickType type, final Pair<Integer, Integer> position) {
        return new BrickImpl(type,
                new Point2D(position.getValue() * AdvancedConstant.BRICK_WIDTH.value(),
                        position.getKey() * AdvancedConstant.BRICK_HEIGHT.value()),
                AdvancedConstant.BRICK_WIDTH.value(), AdvancedConstant.BRICK_HEIGHT.value());
    }

}
