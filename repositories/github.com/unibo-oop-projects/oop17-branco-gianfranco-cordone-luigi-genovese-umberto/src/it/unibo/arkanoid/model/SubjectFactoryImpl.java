package it.unibo.arkanoid.model;

import it.unibo.arkanoid.subject.Ball;
import it.unibo.arkanoid.subject.Block;
import it.unibo.arkanoid.subject.GravitySubject;
import it.unibo.arkanoid.subject.IndestructibleBrick;
import it.unibo.arkanoid.subject.MultipleBrick;
import it.unibo.arkanoid.subject.Paddle;
import it.unibo.arkanoid.subject.PowerUp;
import it.unibo.arkanoid.subject.PowerUpType;
import it.unibo.arkanoid.subject.SimpleBrick;
import it.unibo.arkanoid.subject.Subject;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * Class that returns different {@link Subject}.
 */
public final class SubjectFactoryImpl implements SubjectFactory {

    private static final double PADDLE_HEIGHT = 5;
    private static final double PADDLE_WIDHT = 25;
    private static final double POWERUP_SIZE = 10;
    private static final Vector2D GRAVITY = new Vector2D(0, -30);
    private final Level level;

    /**
     * 
     * @param level
     *          the level that contains this subject
     */
    public SubjectFactoryImpl(final Level level) {
        this.level = level;
    }

    private Subject addSubject(final Subject subject) {
        this.level.addSubject(subject);
        return subject;
    }

    @Override
    public Subject createBall(final Vector2D position, final Vector2D velocity) {
        return this.addSubject(new Ball(position.getX(), position.getY(), velocity, this.level));
    }

    @Override
    public Subject createIndestructibleBrick(final Vector2D position, final double width, final double height) {
        return this.addSubject(new IndestructibleBrick(position.getX(), position.getY(), width, height, this.level));
    }

    @Override
    public Subject createSimpleBrick(final Vector2D position, final double width, final double height) {
        return this.addSubject(new SimpleBrick(position.getX(), position.getY(), width, height, this.level));
    }

    @Override
    public Subject createMultipleBrick(final Vector2D position, final double width, final double height,
            final int lives) {
        return this.addSubject(new MultipleBrick(lives, position.getX(), position.getY(), width, height, this.level));
    }

    @Override
    public Subject createPaddle() {
        return this.addSubject(new Paddle(0, 0, PADDLE_WIDHT, PADDLE_HEIGHT, new Vector2D(0, 0), this.level));
    }

    @Override
    public Subject createPowerUp(final Vector2D position, final PowerUpType type) {
        return this.addSubject(
                new GravitySubject(new PowerUp(position.getX(), position.getY(), POWERUP_SIZE, POWERUP_SIZE, new Vector2D(0, 0), this.level, type),
                GRAVITY));
    }

    @Override
    public Subject createBlock(final Vector2D position, final double width, final double height) {
        return this.addSubject(new Block(position.getX(), position.getY(), width, height, this.level));
    }

}
