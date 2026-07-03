package breakout.model;

import breakout.model.entities.Ball;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickStructure;
import breakout.model.entities.Paddle;
import breakout.model.entities.Wall;
import breakout.model.physics.BounceHandler;
import breakout.model.physics.GameObject;

/**
 * Enum that defines different types of bounce performed by the ball.
 */
public enum BallBouncer implements BounceHandler<Ball> {

    /**
     * Provides a classic game bounce behaviour against game objects.<br/>
     * The collision with the paddle is handled by
     * {@link PaddleBounce#classicPaddleBounce(Ball, GameObject)}
     */
    CLASSIC_BOUNCE((ball, object) -> {
        if (object instanceof Paddle) {
            Bounces.classicPaddleBounce(ball, object);
        } else {
            Bounces.standardBounce(ball, object);
        }
    }),

    /**
     * Provides an advanced game bounce behaviour against game objects.<br/>
     * The collision with the paddle is handled by
     * {@link PaddleBounce#advancedPaddleBounce(Ball, GameObject)}
     */
    ADVANCED_BOUNCE((ball, object) -> {
        if (object instanceof Paddle) {
            Bounces.advancedPaddleBounce(ball, object);
        } else {
            Bounces.standardBounce(ball, object);
        }
    }),

    /**
     * The ball bounces only against paddles,walls and UNBREAKABLE Bricks.
     */
    FIRE_BOUNCE((ball, object) -> {
        if (object instanceof Paddle) {
            Bounces.advancedPaddleBounce(ball, object);
        } else if ((object instanceof Wall) || ((object instanceof Brick)
                && ((Brick) object).getType().getStructure().equals(BrickStructure.UNBREAKABLE))) {
            Bounces.standardBounce(ball, object);
        }
    }),

    /**
     * The ball bounces randomly when hits walls or bricks.
     */
    RANDOM_BOUNCE((ball, object) -> {
        if (object instanceof Paddle) {
            Bounces.advancedPaddleBounce(ball, object);
        } else {
            Bounces.randomBounce(ball, object);
        }
    });

    private BounceHandler<Ball> bouncer;

    /**
     * Constructor for a bouncer
     * 
     * @param b
     *            The bounce handler to compute bounces
     */
    BallBouncer(final BounceHandler<Ball> b) {
        this.bouncer = b;
    }

    /**
     * Function that calculate bounce effect on the ball.
     * 
     * @param ball
     *            the ball that is bouncing
     * @param which
     *            the object that the ball is hitting
     */
    public void computeBounce(final Ball ball, final GameObject which) {
        this.bouncer.computeBounce(ball, which);
    }

    /**
     * Defines some functions used in the enum.
     * Specifies how to ball's velocity vector change after a bounce is performed.
     */
    private static final class Bounces { 

        private static final int CLASSIC_SEPARATION = 4;
        private static final int CLASSIC_MAX_ANGLE = -150;
        private static final int CLASSIC_ANGLE = CLASSIC_MAX_ANGLE / CLASSIC_SEPARATION;
        private static final double VIRTUAL_POINT_Y_OFFSET = 15;

        /**
         * Private constructor
         */
        private Bounces() {
        }

        /**
         * Each collision point generates a different ball angle.<br/>
         * This function determines the bounce angle by creating a virtual point
         * under the paddle and then calculating the slope of the line that
         * links it with the collision point.
         * 
         * @param the
         *            bouncing ball
         * @param the
         *            colliding paddle
         */
        public static void advancedPaddleBounce(final Ball ball, final GameObject paddle) {
            final double collisionPointX = ball.getPosition().getX() + ball.getRadius();
            final double collisionPointY = ball.getBounds().getMaxY();

            final double virtualPointX = paddle.getPosition().getX() + paddle.getBounds().getWidth() / 2;
            final double virtualPointY = paddle.getBounds().getMaxY() + VIRTUAL_POINT_Y_OFFSET;

            final double angle = Math.atan((virtualPointY - collisionPointY) / (virtualPointX - collisionPointX));
            ball.getVelocity().setAngle(Math.toDegrees(angle - (collisionPointX <= virtualPointX ? Math.PI : 0)));
        }

        /**
         * The paddle is logically divided into {@link #CLASSIC_SEPARATION}
         * parts.<br/>
         * Each part generate a different bouncing angle
         * 
         * @param the
         *            bouncing ball
         * @param the
         *            colliding paddle
         */
        public static void classicPaddleBounce(final Ball ball, final GameObject paddle) {
            double paddleDivisionSize = paddle.getPosition().getX();
            for (int i = 0; i < Bounces.CLASSIC_SEPARATION; i++) {
                paddleDivisionSize += paddle.getBounds().getWidth() / Bounces.CLASSIC_SEPARATION;
                if (ball.getPosition().getX() < paddleDivisionSize) {
                    ball.getVelocity().setAngle((Bounces.CLASSIC_SEPARATION - i) * Bounces.CLASSIC_ANGLE);
                    break;
                }
            }
        }

        /**
         * Defines the standard bounce when the ball collides with any
         * gameobject
         * 
         * @param ball
         *            the bouncing ball
         * @param obj
         *            the collided object
         */
        public static void standardBounce(final Ball ball, final GameObject obj) {
            if (Math.abs(ball.getBounds().overlapX(obj.getBounds())) < Math.abs(
                    ball.getBounds().overlapY(obj.getBounds()))) {
                ball.setVelocity(-ball.getVelocity().getX(), ball.getVelocity().getY());
            } else {
                ball.setVelocity(ball.getVelocity().getX(), -ball.getVelocity().getY());
            }
        }

        /**
         * The ball randomly bounce on objects.
         * 
         * @param ball
         *            the bouncing ball
         * @param obj
         *            the collided object
         */
        public static void randomBounce(final Ball ball, final GameObject obj) {
            // TODO Implemets random bounce;
        }

    }

}
