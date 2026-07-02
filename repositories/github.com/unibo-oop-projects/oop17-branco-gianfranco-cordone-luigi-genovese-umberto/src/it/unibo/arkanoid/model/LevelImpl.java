package it.unibo.arkanoid.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.arkanoid.event.CollisionInfo;
import it.unibo.arkanoid.event.Event;
import it.unibo.arkanoid.event.EventSource;
import it.unibo.arkanoid.subject.Brick;
import it.unibo.arkanoid.subject.BrickType;
import it.unibo.arkanoid.subject.Subject;
import it.unibo.arkanoid.subject.SubjectType;
import it.unibo.arkanoid.utility.Pair;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * 
 * This class represent a level in the game's world.
 *
 */
public final class LevelImpl implements Level {

    private static final double INSETS = 5;
    private static final double BRICK_GRID_HEIGHT = 40;
    private static final int MULTIPLEBRICK_LIVES = 3;
    private static final double BALL_START_HEIGHT = 1.9;
    private static final Vector2D BALL_START_VELOCITY = new Vector2D(0, 60);
    private static final double WALL_THICKNESS = 50;

    private final List<Subject> subjectList;
    private final Event<Integer> scoreChangedEvent;
    private final PowerUpGenerator powerUpGenerator;
    private final Event<LevelState> levelFinishedEvent;
    private int brickCount;
    private int ballCount;

    /**
     * 
     * @param width
     *            level width
     * @param height
     *            level height
     * @param brickGrid
     *            grid that contains different type of brick
     * @param powerUpGenerator
     *            generator for powerUp
     */
    public LevelImpl(final double width, final double height, final BrickType[][] brickGrid,
            final PowerUpGenerator powerUpGenerator) {
        this.subjectList = new LinkedList<>();
        this.scoreChangedEvent = new EventSource<>();
        this.levelFinishedEvent = new EventSource<>();
        this.powerUpGenerator = powerUpGenerator;
        final SubjectFactory factory = new SubjectFactoryImpl(this);
        final Subject paddle = factory.createPaddle();
        this.resetPosition();

        final double worldBase = -paddle.getHeight() / 2 - INSETS;

        final double brickGridWidth = width - INSETS * 2;

        final double brickWidth = brickGridWidth / brickGrid.length;
        final double brickHeight = BRICK_GRID_HEIGHT / brickGrid[0].length;

        final double gridBaseY = worldBase + height - BRICK_GRID_HEIGHT - INSETS + brickHeight / 2;
        final double gridBaseX = -brickGridWidth / 2 + brickWidth / 2;

        final Vector2D topBlockPosition = new Vector2D(0, worldBase + height + WALL_THICKNESS / 2);
        final Vector2D leftBlockPosition = new Vector2D(-width / 2 - WALL_THICKNESS / 2, worldBase + height / 2);
        final Vector2D rightBlockPosition = new Vector2D(width / 2 + WALL_THICKNESS / 2, worldBase + height / 2);

        factory.createBlock(topBlockPosition, width + 2 * WALL_THICKNESS, WALL_THICKNESS);
        factory.createBlock(leftBlockPosition, WALL_THICKNESS, height);
        factory.createBlock(rightBlockPosition, WALL_THICKNESS, height);

        for (int x = 0; x < brickGrid.length; x++) {
            for (int y = 0; y < brickGrid[x].length; y++) {

                final Vector2D position = new Vector2D(gridBaseX + x * brickWidth, gridBaseY + y * brickHeight);
                final Optional<Subject> brick;

                switch (brickGrid[x][y]) {
                case SIMPLE:
                    brick = Optional.of(factory.createSimpleBrick(position, brickWidth, brickHeight));
                    brickCount++;
                    break;
                case MULTIPLE:
                    brick = Optional
                            .of(factory.createMultipleBrick(position, brickWidth, brickHeight, MULTIPLEBRICK_LIVES));
                    brickCount++;
                    break;
                case INDESTRUCTIBLE:
                    brick = Optional.of(factory.createIndestructibleBrick(position, brickWidth, brickHeight));
                    break;
                default:
                    brick = Optional.empty();
                    break;
                }

                brick.ifPresent(b -> b.getDestroyedEvent().register(s -> this.onBrickDestroyed((Brick) s)));
            }
        }

    }

    private void onBrickDestroyed(final Brick brick) {
        this.powerUpGenerator.onBrickDestroyed(brick, this);
        this.scoreChangedEvent.trigger(brick.getBrickValue());
        brickCount--;
        if (brickCount == 0) {
            this.levelFinishedEvent.trigger(LevelState.WIN);
        }
    }

    @Override
    public void addSubject(final Subject subject) {
        this.subjectList.add(subject);
        subject.getDestroyedEvent().register(s -> this.removeSubject(s));
        if (subject.getSubjectType() == SubjectType.BALL) {
            ballCount++;
        }
    }

    @Override
    public void removeSubject(final Subject subject) {
        this.subjectList.remove(subject);
        if (subject.getSubjectType() == SubjectType.BALL) {
            ballCount--;
        }
        if (ballCount == 0) {
            this.levelFinishedEvent.trigger(LevelState.LOSE);
        }
    }

    @Override
    public Stream<Subject> getAllSubjects() {
        return this.subjectList.stream();
    }

    @Override
    public void checkCollisions() {
        final List<Pair<CollisionInfo>> collisions = new ArrayList<>();
        for (int i = 0; i < this.subjectList.size(); i++) {
            for (int j = i + 1; j < this.subjectList.size(); j++) {
                final Subject s1 = this.subjectList.get(i);
                final Subject s2 = this.subjectList.get(j);
                if (SubjectType.pairCollides(s1.getSubjectType(), s2.getSubjectType())) {
                    s1.intersect(s2).ifPresent(v -> {
                        collisions.add(new Pair<>(new CollisionInfo(v, s2), new CollisionInfo(v.inverse(), s1)));
                    });
                }

            }
        }
        collisions.forEach(p -> {
            p.getFirst().getSubject().notifyCollision(p.getSecond());
            p.getSecond().getSubject().notifyCollision(p.getFirst());
        });
    }

    @Override
    public void updateAll(final double deltaTime) {
        new ArrayList<>(this.subjectList).forEach(s -> s.update(deltaTime));
    }

    @Override
    public Subject getPaddle() {
        return this.subjectList.get(0);
    }

    @Override
    public SubjectFactory getSubjectFactory() {
        return new SubjectFactoryImpl(this);
    }

    @Override
    public Event<Integer> getScoreChangedEvent() {
        return this.scoreChangedEvent;
    }

    @Override
    public Event<LevelState> getLevelFinishedEvent() {
        return this.levelFinishedEvent;
    }

    @Override
    public void resetPosition() {
        this.getSubjectFactory().createBall(new Vector2D(0, this.getPaddle().getHeight() / 2 + BALL_START_HEIGHT), BALL_START_VELOCITY);
        this.getPaddle().setPosition(new Vector2D(0, 0));
        this.subjectList.stream()
                        .filter(s -> s.getSubjectType() == SubjectType.POWER_UP)
                        .collect(Collectors.toList())
                        .forEach(this::removeSubject);
    }

}
