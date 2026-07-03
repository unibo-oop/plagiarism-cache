package breakout.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import breakout.model.entities.Ball;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickStructure;
import breakout.model.entities.Paddle;
import breakout.model.entities.Wall;
import breakout.model.levels.BasicLevel;
import breakout.model.physics.GameObject;

/**
 * An abstract implementation of the model interface to summarize the main
 * behavior of a generic game.
 *
 */
abstract class AbstractModel implements Model {

    private static final int PLAYER_LIFE = 3;

    // Level
    private BasicLevel currentLvl;
    private Iterator<BasicLevel> lvlIterator;

    // Entity
    private Paddle paddle;
    private List<Ball> ballList;
    private List<Brick> brickList;
    private List<Wall> wallList;

    // Player
    private int score;
    private int playerLife;

    // Game stats
    private GameStatus gameStatus;
    private GameObjectFactory gameFactory;

    /**
     * An abstract model constructor to initialize the game with the features of
     * the given level.
     * 
     * @param lvlList
     *            The list of levels to be played
     * @throws IllegalArgumentException
     *             if level list is empty
     */
    AbstractModel(final List<BasicLevel> lvlList) throws IllegalArgumentException {
        if (!lvlList.isEmpty()) {

            // Level init
            this.lvlIterator = lvlList.iterator();
            this.currentLvl = this.lvlIterator.next();
            this.gameFactory = this.makeFactory();

            // Entities init
            this.ballList = new ArrayList<>(Arrays.asList(this.gameFactory.createBall()));
            this.paddle = this.gameFactory.createPaddle();
            this.brickList = new ArrayList<>(this.currentLvl.getBricks());
            this.wallList = this.currentLvl.getWalls();

            // Player init
            this.score = 0;
            this.playerLife = PLAYER_LIFE;

            this.gameStatus = GameStatus.Pause;
        } else {
            throw new IllegalArgumentException("You cannot play a game with no level");

        }

    }

    /**
     * {@inheritDoc}.
     */
    public void updateAll(final double time) {
        if (this.gameStatus.equals(GameStatus.Running)) {
            this.paddle.update(time, this.wallList);
            this.updateAndRemove(this.ballList, time, ball -> this.checkFall(ball));
            this.updateAndRemove(this.brickList, time, brick -> brick.isDead());

            if (this.ballList.isEmpty()) {
                this.playerLife--;
                if (!this.gameOver()) {
                    this.ballList.add(this.gameFactory.createBall());
                    this.paddle = this.gameFactory.createPaddle();
                    this.gameStatus = GameStatus.Dead;
                } else {
                    this.gameStatus = GameStatus.Over;
                }
            }

            // If player win
            if (this.getBreakableBricks().isEmpty()) {
                if (this.lvlIterator.hasNext()) {
                    this.setLevel(this.lvlIterator.next());
                } else {
                    // You play always the last level
                    this.setLevel(this.currentLvl);
                }
                this.gameStatus = GameStatus.Won;
            }
        }
    }

    private List<Brick> getBreakableBricks() {
        return this.brickList.stream()
                             .filter(brick -> !brick.getType().getStructure().equals(BrickStructure.UNBREAKABLE))
                             .collect(Collectors.toList());
    }

    /**
     * Update the objects in the given list.<br/>
     * Then,after the update, removes from the list the objects according to the
     * given predicate.
     * 
     * @param list
     *            the list to be update
     * @param time
     *            the elapsed time
     * @param toRemove
     *            the function that determines if an object must be removed
     * @param <T>
     *            the type of the given objects
     */
    protected <T extends GameObject> void updateAndRemove(final List<T> list, final double time,
            final Predicate<T> toRemove) {
        list.forEach(obj -> obj.update(time));
        list.removeIf(toRemove);
    }

    /**
     * {@inheritDoc}.
     */
    public List<GameObject> checkCollisions() {
        final List<GameObject> collisions = new ArrayList<>();

        // check all the ball collisions
        this.ballList.stream().forEach(ball -> {
            collisions.addAll(this.brickCollision(ball));
            collisions.addAll(this.paddleCollision(ball));
            collisions.addAll(this.wallCollision(ball));
        });

        return new ArrayList<>(collisions);
    }

    /**
     * Check if a ball hits any of the remaining bricks. If a brick has been hit
     * it updates the game and calls the abstract method
     * {@link #gameUpdateOnBrickCollision()}.
     * 
     * @param ball
     *            the ball to check
     */
    private List<Brick> brickCollision(final Ball ball) {
        final List<Brick> collidedBricks = new ArrayList<>();
        this.brickList.stream().filter(brick -> ball.collidedWith(brick)).forEach(brick -> {
            collidedBricks.add(brick);
        });

        if (!collidedBricks.isEmpty()) {
            ball.bounce(collidedBricks);

            // The ball can hit just one brick at a time
            final Brick toHitBrick = collidedBricks.get(0);
            toHitBrick.hit();
            this.gameUpdateOnBrickCollision(toHitBrick);
            if (toHitBrick.isDead()) {
                this.score += toHitBrick.getBrickValue() * this.ballList.size();
            }
        }
        return collidedBricks;
    }

    /**
     * Check if a ball hits the paddle. If a collision happens it updates the
     * game and calls the abstract method
     * {@link #gameUpdateOnPaddleCollision()}.
     * 
     * @param ball
     *            the ball to check
     */
    private List<Paddle> paddleCollision(final Ball ball) {
        final List<Paddle> collidedPaddles = new ArrayList<>();

        if (ball.collidedWith(this.paddle)) {
            collidedPaddles.add(this.paddle);
            ball.bounce(collidedPaddles);
            this.gameUpdateOnPaddleCollision();
        }
        return collidedPaddles;
    }

    /**
     * Check if a ball hits a wall. If a collision happens it updates the game
     * and calls the abstract method {@link #gameUpdateOnWallCollision()}.
     * 
     * @param ball
     *            the ball to be check
     */
    private List<Wall> wallCollision(final Ball ball) {
        final List<Wall> collidedWalls = new ArrayList<>();
        this.wallList.stream().filter(w -> w.collidedWith(ball)).forEach(w -> {
            collidedWalls.add(w);
            this.gameUpdateOnWallCollision(w);
        });
        if (!collidedWalls.isEmpty()) {
            ball.bounce(collidedWalls);
        }
        return collidedWalls;
    }

    /**
     * Abstract function that defines the behavior of a particular model when a
     * brick has been hit.
     * 
     * @param brick
     *            the brick hit
     */
    protected abstract void gameUpdateOnBrickCollision(final Brick brick);

    /**
     * Abstract function that defines the behavior of a particular model when
     * the paddle has been hit.
     */
    protected abstract void gameUpdateOnPaddleCollision();

    /**
     * Abstract function that defines the behavior of a particular model when a
     * ball hits a wall.
     * 
     * @param wall
     *            the colliding wall
     */
    protected abstract void gameUpdateOnWallCollision(final Wall wall);

    /**
     * Defines the object to be created.
     * 
     * @return a game factory.
     */
    protected abstract GameObjectFactory makeFactory();

    /**
     * Check if an object is fallen.<br/>
     * <b>note</b>: A "fallen" object is an object that is under the player's
     * paddle.
     * 
     * @param obj
     *            the object to check
     * @return true if the object is under the paddle. <br/>
     *         false otherwise.
     */
    protected boolean checkFall(final GameObject obj) {
        return (obj.getPosition().getY() > this.paddle.getPosition().getY() + this.paddle.getHeight());
    }

    /**
     * Check if the game is over because of the player losing all his lives.
     * 
     * @return true if the player has no remaining lives.<br/>
     *         false otherwise.
     */
    private boolean gameOver() {
        return this.playerLife <= 0;
    }

    /**
     * @return the currently played Level.
     */
    public BasicLevel getCurrentLevel() {
        return this.currentLvl;
    }

    /**
     * Set the game for a new Level
     * 
     * @param lvl
     *            the level to be played
     */
    private void setLevel(final BasicLevel lvl) {
        this.currentLvl = lvl;
        this.brickList = new ArrayList<>(this.currentLvl.getBricks());
        this.wallList = new ArrayList<>(lvl.getWalls());
        this.ballList = new ArrayList<>(Arrays.asList(this.gameFactory.createBall()));
        this.paddle = this.gameFactory.createPaddle();
    }

    /**
     * {@inheritDoc}.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Set the player's score.
     * 
     * @param newScore
     *            the nwe score
     * @throws IllegalArgumentException
     *             if the score is negative
     */
    public void setScore(final int newScore) throws IllegalArgumentException {
        if (newScore >= 0) {
            this.score = newScore;
        } else {
            throw new IllegalArgumentException("The score is to be greater or equal to 0");
        }
    }

    /**
     * {@inheritDoc}.
     */
    public int getLife() {
        return this.playerLife;
    }

    /**
     * Set the player's life.
     * 
     * @param newLife
     *            the new life
     * @throws IllegalArgumentException
     *             if thew new life is negative
     */
    public void setLife(final int newLife) throws IllegalArgumentException {
        if (newLife >= 0) {
            this.playerLife = newLife;
        } else {
            throw new IllegalArgumentException("The life is to be greater or equal to 0");
        }
    }

    /**
     * {@inheritDoc}.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * {@inheritDoc}.
     */
    public List<Brick> getBricks() {
        return Collections.unmodifiableList(this.brickList);
    }

    /**
     * {@inheritDoc}.
     */
    public List<Ball> getBalls() {
        return Collections.unmodifiableList(this.ballList);
    }

    /**
     * {@inheritDoc}.
     */
    public List<Wall> getWalls() {
        return Collections.unmodifiableList(this.wallList);
    }

    /**
     * {@inheritDoc}.
     */
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    /**
     * Adds a ball to the ball list.
     * 
     * @param ball
     *            the ball to add.
     */
    public void addBall(final Ball ball) {
        this.ballList.add(ball);
    }

    /**
     * Pause the game.
     */
    public void pause() {
        this.gameStatus = GameStatus.Pause;
    }

    /**
     * Unpause the game.
     */
    public void start() {
        this.gameStatus = GameStatus.Running;
    }

}
