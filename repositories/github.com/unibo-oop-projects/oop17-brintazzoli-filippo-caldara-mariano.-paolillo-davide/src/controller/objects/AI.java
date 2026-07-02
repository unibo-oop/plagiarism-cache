package controller.objects;

import java.util.Comparator;

import java.util.List;

import java.util.stream.Collectors;

import model.input.Input;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Calculate;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Class AI implements methods to make enemy tank act like a player.
 */
public final class AI {

    private static final int CRITICAL_DISTANCE = 300;
    private static Pair<Double, Double> worldBounds;
    private static Input enemyInput;
    private static final double MIN_DISTANCE = 70;

    /**
     * Constructor.
     */
    private AI() {
        super();
    }
    /**
     * Initialize the fields of the class.
     * 
     * @param dimension
     *            the dimension of the {@link World}.
     * @param input
     *            the enemy {@link Input}.
     */
    public static void initialize(final Pair<Double, Double> dimension, final Input input) {
        worldBounds = dimension;
        enemyInput = input;
    }

    /**
     * The method act manage the movement of the enemy {@link Tank} and
     * {@link Cannon}.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param p
     *            the {@link List} of {@link Projectile}.
     * @return the enemy tank {@link Input}.
     */
    public static Input act(final Tank enemy, final Tank player, final List<Projectile> p) {
        move(enemy, player, p);
        target(enemy, player, p);
        return enemyInput;

    }

    /**
     * Private method that manage the enemy {@link Tank} movement.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param p
     *            the {@link List} of {@link Projectile}.
     */
    private static void move(final Tank enemy, final Tank player, final List<Projectile> projectiles) {
        if (projectiles.isEmpty()) {
            if ((int) Calculate.distance(enemy.getPosition(), player.getPosition()) > CRITICAL_DISTANCE) {
                moveEnemy(enemy, player, true);
            } else {
                moveEnemy(enemy, player, false);
            }
        } else {
            goAway(enemy, getNearest(projectiles, enemy));
        }
    }

    /**
     * Method that manage the enemy {@link Cannon} movement.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param p
     *            the {@link List} of {@link Projectile}.
     */
    private static void target(final Tank enemy, final Tank player, final List<Projectile> p) {
        if (p.isEmpty()) {
            enemyInput.setTarget(player.getPosition());
        } else {
            enemyInput.setTarget(getNearest(p, enemy).getPosition());
        }
    }

    /**
     * Move the enemy {@link Tank} closer or farther than the player {@link Tank}
     * according to a boolean field.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param player
     *            the player {@link Tank}.
     * @param b
     *            a {@link Boolean}. It's true if the enemy tank has to go closer,
     *            false otherwise.
     */
    private static void moveEnemy(final Tank enemy, final Tank player, final boolean b) {
        // RIGHT and LEFT
        if (enemy.getPosition().getFirst() > player.getPosition().getFirst()) {
            enemyInput.getMovement().put(Direction.LEFT, b);
            enemyInput.getMovement().put(Direction.RIGHT, !b);
        } else if (enemy.getPosition().getFirst() < player.getPosition().getFirst()) {
            enemyInput.getMovement().put(Direction.RIGHT, b);
            enemyInput.getMovement().put(Direction.LEFT, !b);
        } else if (enemy.getPosition().getFirst() == player.getPosition().getFirst()) {
            enemyInput.getMovement().put(Direction.RIGHT, false);
            enemyInput.getMovement().put(Direction.LEFT, false);
        }
        // UP and DOWN
        if (enemy.getPosition().getSecond() - player.getPosition().getSecond() > 0) {
            enemyInput.getMovement().put(Direction.UP, b);
            enemyInput.getMovement().put(Direction.DOWN, !b);
        } else if (enemy.getPosition().getSecond() - player.getPosition().getSecond() < 0) {
            enemyInput.getMovement().put(Direction.DOWN, b);
            enemyInput.getMovement().put(Direction.UP, !b);
        } else if (enemy.getPosition().getSecond() == player.getPosition().getSecond()) {
            enemyInput.getMovement().put(Direction.DOWN, false);
            enemyInput.getMovement().put(Direction.UP, false);
        }
    }

    /**
     * Method that allows to the enemy {@link Tank} to dodge the {@link Projectile}.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @param p
     *            the {@link Projectile}.
     */
    private static void goAway(final Tank enemy, final Projectile p) {
        // RIGHT and LEFT
        if ((enemy.getPosition().getFirst().intValue() - p.getPosition().getFirst().intValue()) < MIN_DISTANCE
                && p.getPosition().getFirst().intValue() < enemy.getPosition().getFirst().intValue()) {
            enemyInput.getMovement().put(Direction.LEFT, false);
            enemyInput.getMovement().put(Direction.RIGHT, true);
        } else if ((p.getPosition().getFirst().intValue() - (enemy.getPosition().getFirst().intValue()
                + Tank.getDimension().getFirst().intValue())) < MIN_DISTANCE
                && p.getPosition().getFirst().intValue() > enemy.getPosition().getFirst().intValue()) {
            enemyInput.getMovement().put(Direction.RIGHT, false);
            enemyInput.getMovement().put(Direction.LEFT, true);
        }
        // UP and DOWN
        if ((enemy.getPosition().getSecond().intValue() - p.getPosition().getSecond().intValue()) < MIN_DISTANCE
                && p.getPosition().getSecond().intValue() < enemy.getPosition().getSecond().intValue()
                || enemy.getPosition().getSecond().intValue() <= 0) {
            enemyInput.getMovement().put(Direction.DOWN, true);
            enemyInput.getMovement().put(Direction.UP, false);
        } else if ((p.getPosition().getSecond().intValue() - (enemy.getPosition().getSecond().intValue()
                + Tank.getDimension().getSecond().intValue())) < MIN_DISTANCE
                && p.getPosition().getSecond().intValue() > enemy.getPosition().getSecond().intValue()
                || enemy.getPosition().getSecond().intValue() + Tank.getDimension().getSecond().intValue() > worldBounds
                        .getSecond()) {
            enemyInput.getMovement().put(Direction.UP, true);
            enemyInput.getMovement().put(Direction.DOWN, false);
        }
    }

    /**
     * Method that make the enemy {@link Tank} shot.
     * 
     * @param enemy
     *            the enemy {@link Tank}.
     * @return a new {@link Projectile}.
     */
    public static Projectile shotEnemy(final Tank enemy) {
        return enemy.shot();

    }

    /**
     * Getter of the nearest {@link Projectile} to the enemy {@link Tank}.
     * 
     * @param projectiles
     *            the {@link List} of {@link Projectile}.
     * @param enemy
     *            the enemy {@link Tank}.
     * @return the nearest projectile.
     */
    private static Projectile getNearest(final List<Projectile> projectiles, final Tank enemy) {
        return projectiles.stream().sorted(new Comparator<Projectile>() {
            @Override
            public int compare(final Projectile o1, final Projectile o2) {
                if ((int) Calculate.distance(o1.getPosition(), enemy.getPosition()) < (int) Calculate
                        .distance(o2.getPosition(), enemy.getPosition())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }).collect(Collectors.toList()).get(0);
    }
}
