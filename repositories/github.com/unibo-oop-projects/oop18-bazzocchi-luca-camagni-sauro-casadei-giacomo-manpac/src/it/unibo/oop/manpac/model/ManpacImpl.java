package it.unibo.oop.manpac.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import it.unibo.oop.manpac.model.collisions.Collisions;
import it.unibo.oop.manpac.model.collisions.CollisionsImpl;
import it.unibo.oop.manpac.model.entities.MobileEntity;
import it.unibo.oop.manpac.model.entities.PacmanModel;
import it.unibo.oop.manpac.model.entities.PhantomModel;
import it.unibo.oop.manpac.model.entities.PhantomModelImpl;
import it.unibo.oop.manpac.model.entities.PillImpl;
import it.unibo.oop.manpac.model.score.Eatable;
import it.unibo.oop.manpac.model.score.PointsImpl;
import it.unibo.oop.manpac.model.score.Points;
import it.unibo.oop.manpac.model.score.ScorePoints;
import it.unibo.oop.manpac.model.score.ScorePointsInteger;
import it.unibo.oop.manpac.utils.Counter;
import it.unibo.oop.manpac.utils.CounterImpl;

/**
 * Implementation Manpac interface, the model of a level.
 */
public final class ManpacImpl implements Manpac, ModelForController {

    // default number of pills
    private static final int NUMBER_PILLS_DEFAULT = 244;
    // default number of Pac-Man's lives
    private static final int NUMBER_LIVES_DEFAULT = 3;
    // default points for eating a Pill
    private static final int PILL_SCORE = 10;
    // default points for eating a power pill
    private static final int POWER_PILL_SCORE = 50;
    // default points for eating a phantom
    private static final int PHANTOM_SCORE = 200;

    private final ScorePoints<Integer> score;
    private Counter pillsCounter;
    private final Counter livesCounter;
    private final MobileEntity pacman;
    private final Eatable pill;
    private final Eatable power;
    private final List<PhantomModel> phantoms;
    private final Collisions collision;

    /**
     * Default constructor of ManpacImpl (MODEL).
     */
    public ManpacImpl() {
        this(NUMBER_LIVES_DEFAULT, NUMBER_PILLS_DEFAULT);
    }

    /**
     * Constructor of ManpacImpl (MODEL).
     * 
     * @param totalLives number of starting lives that the player has
     */
    public ManpacImpl(final int totalLives) {
        this(totalLives, NUMBER_PILLS_DEFAULT);
    }

    /**
     * Constructor of ManpacImpl (MODEL).
     * 
     * @param totalPills number of total pill in this level
     * @param totalLives number of starting lives that the player has
     * @throws IllegalArgumentException if any of input arguments is 0 or less
     */
    public ManpacImpl(final int totalLives, final int totalPills) throws IllegalArgumentException {
        if (totalLives <= 0 || totalPills <= 0) {
            throw new IllegalArgumentException();
        }
        this.score = new ScorePointsInteger();
        this.pillsCounter = new CounterImpl(totalPills);
        this.livesCounter = new CounterImpl(totalLives);
        this.pill = new PillImpl(new PointsImpl<>(PILL_SCORE));
        this.power = new PillImpl(new PointsImpl<>(POWER_PILL_SCORE));
        this.phantoms = new ArrayList<>();
        this.pacman = new PacmanModel();

        this.collision = new CollisionsImpl(this);

        resetGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action pacmanCollision(final Entity entity) throws IllegalArgumentException {
        if (!checkNotNull(entity)) {
            throw new IllegalArgumentException();
        }

        return this.collision.checkPacmanCollisions(entity);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action inputDirection(final Directions direction) throws IllegalArgumentException {
        if (!checkNotNull(direction)) {
            throw new IllegalArgumentException();
        }
        if (this.pacman.getDirection().equals(direction)) {
            return Action.NOTHING_HAPPENS;
        } else {
            return this.pacman.setDirection(direction);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action phantomCollision(final Entity name, final Entity entity) throws IllegalArgumentException {
        if (!checkNotNull(entity) || !checkNotNull(name)) {
            throw new IllegalArgumentException();
        }

        return this.collision.checkPhantomCollisions(name, entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Points<Integer> getHighScore() {
        return this.score.getHighScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Points<Integer> getCurrentScore() {
        return this.score.getCurrentScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetCurrentScore() {
        this.score.resetCurrentScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetScorePoints() {
        this.score.resetScorePoints();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScorePoints<Integer> getScorePoints() {
        return this.score.getCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHighScore(final Points<Integer> highscore) {
        this.score.setHighScore(highscore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.livesCounter.getRemaining();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action stopPacman() {
        return this.pacman.setDirection(Directions.STOP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action pillEaten() {
        this.score.increaseCurrentScore(this.pill.getPoints());
        if (this.pillsCounter.decreaseCount()) {
            return Action.PILL_EATEN;
        } else {
            return Action.WIN;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action powerPillEaten() {
        this.score.increaseCurrentScore(this.power.getPoints());
        this.phantoms.forEach(p -> p.setFear(true));
        if (this.pillsCounter.decreaseCount()) {
            return Action.POWER_PILL_EATEN;
        } else {
            return Action.WIN;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action phantomEaten() {
        this.score.increaseCurrentScore(this.phantoms.get(0).getPoints());
        return Action.PACMAN_ATE_PHANTOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action decreaseLives() {
        if (this.livesCounter.decreaseCount()) {
            this.pacman.setDirection(Directions.STOP);
            return Action.PHANTOM_KILLED_PACMAN;
        } else {
            return Action.GAME_OVER;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean arePhantomsFeared() {
        return this.phantoms.stream().anyMatch(phantom -> phantom.isFeared());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action changePhantomDirection(final Entity name) {
//        int i = 0;
//        for (i = 0; i < this.phantom.size(); i++) {
//            if (this.phantom.get(i).getName().equals(name)) {
//                break;
//            }
//        }
//
//        // the strategy for a new direction is made by generating a random direction with Random
//        return this.phantom.get(i).generateDirection(() -> Directions.values()[new Random().nextInt(this.phantom.size())]);

        // the strategy for a new direction is made by generating a random direction
        // with Random
        return this.phantoms.stream().filter(phantom -> phantom.getName() == name).findFirst()
                .map(phantom -> phantom
                        .generateDirection(() -> Directions.values()[new Random().nextInt(this.phantoms.size())]))
                .orElse(Action.NOTHING_HAPPENS);
    }

    private boolean checkNotNull(final Object obj) {
        return Objects.nonNull(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPills(final int numberPills) throws IllegalStateException {
        if (this.pillsCounter.getRemaining() != this.pillsCounter.getTotal()) {
            throw new IllegalStateException("You can't change the number of pills during the game!!!");
        }
        if (numberPills <= 0) {
            throw new IllegalArgumentException("The number of pills must be greater the zero!");
        }
        this.pillsCounter = new CounterImpl(numberPills);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        resetAndContinue();

        this.score.resetCurrentScore();
        this.livesCounter.resetCount();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetAndContinue() {
        this.pillsCounter.resetCount();
        stopPacman();
        this.phantoms.clear();

        Stream.of(Entity.BLINKY, Entity.INKY, Entity.PINKY, Entity.CLYDE).forEach(
                phantomName -> this.phantoms.add(new PhantomModelImpl(new PointsImpl<>(PHANTOM_SCORE), phantomName)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopPower() {
        this.phantoms.forEach(phantom -> phantom.setFear(false));
    }

}
