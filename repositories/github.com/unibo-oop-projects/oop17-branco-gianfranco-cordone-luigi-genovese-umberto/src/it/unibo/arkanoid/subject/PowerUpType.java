package it.unibo.arkanoid.subject;

import it.unibo.arkanoid.model.Level;
import it.unibo.arkanoid.model.SubjectFactory;

/**
 * Enumeration for the Power Up Type.
 *
 */
public enum PowerUpType {

    /**
     * Create three ball in the current level.
     */
    ThreeBall {

        @Override
        public void doAction(final Level level) {
            final SubjectFactory factory = level.getSubjectFactory();
            final Subject ball = level.getAllSubjects().filter(i -> i.getSubjectType().equals(SubjectType.BALL))
                    .findFirst().get();
            factory.createBall(ball.getPosition(), ball.getVelocity().rotate(-Math.PI / CONSTANT_3_BALL));
            factory.createBall(ball.getPosition(), ball.getVelocity().rotate(Math.PI / CONSTANT_3_BALL));
        }

    },

    /**
     * Increase to ten unit's the Velocity of the ball.
     */
    IncreseVelocityBall {

        @Override
        public void doAction(final Level level) {
            if (level.getAllSubjects().filter(i -> i.getSubjectType().equals(SubjectType.BALL)).findFirst().get()
                    .getVelocity().getX() < VELOCITY_MAX) {
                level.getAllSubjects().filter(i -> i.getSubjectType().equals(SubjectType.BALL))
                        .forEach(b -> b.setVelocity(b.getVelocity().multiply(VELOCITY_VALUE_BALL)));

            }

        }

    },

    /**
     * Enlarge the Width of the Paddle.
     */
    EnlargePaddle {

        @Override
        public void doAction(final Level level) {
            if (level.getPaddle().getWidth() < (BALL_START_HEIGHT * (BALL_START_HEIGHT + 1))) {
                level.getPaddle().setWidth(level.getPaddle().getWidth() * WIDTH_VALUE_PADDLE);
            }

        }

    },

    /**
     * Decrease the Width of the Paddle.
     */
    DecreasePaddle {

        @Override
        public void doAction(final Level level) {
            if (level.getPaddle().getWidth() > (BALL_START_HEIGHT * (BALL_START_HEIGHT - 1))) {
                level.getPaddle().setWidth(level.getPaddle().getWidth() * (1 / WIDTH_VALUE_PADDLE));
            }
        }

    };

    private static final double WIDTH_VALUE_PADDLE = 1.3;
    private static final double BALL_START_HEIGHT = 5;
    private static final double VELOCITY_VALUE_BALL = 2;
    private static final double VELOCITY_MAX = 30.0;
    private static final double CONSTANT_3_BALL = 9.0;
    /**
     * Template method.
     * 
     * @param level
     *            Where the action is accomplished.
     */
    public void action(final Level level) {
        this.doAction(level);
    }

    /**
     * Applies the Power Up.
     * 
     * @param level
     *            The current Level where to apply the Power Up.
     */
    public abstract void doAction(Level level);

}
