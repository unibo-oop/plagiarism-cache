package controller.collision;

import java.util.Map;

import model.object.Tank;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Concrete implementation of {@link Collision} interface.
 */
public class TankWithTank implements Collision {

    private static final double MARGINAL_DISTANCE = 5;
    private final Tank playerTank;
    private final Tank enemyTank;
    private final Map<Direction, Boolean> movement;

    /**
     * Constructor.
     * 
     * @param playerTank
     *            the player {@link Tank}.
     * @param enemyTank
     *            the enemy {@link Tank}.
     * @param movement
     *            the player movements.
     */
    public TankWithTank(final Tank playerTank, final Tank enemyTank, final Map<Direction, Boolean> movement) {
        this.playerTank = playerTank;
        this.enemyTank = enemyTank;
        this.movement = movement;
    }

    @Override
    public final void manageCollision() {
        if (movement.get(Direction.RIGHT) && movement.get(Direction.UP)
                && this.playerTank.getPosition().getFirst()
                        + Tank.getDimension().getFirst() >= this.enemyTank.getPosition().getFirst()
                                + MARGINAL_DISTANCE) {
            this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
                    this.enemyTank.getPosition().getSecond() + Tank.getDimension().getSecond()));
        } else if (movement.get(Direction.RIGHT) && movement.get(Direction.DOWN)
                && this.playerTank.getPosition().getFirst()
                        + Tank.getDimension().getFirst() >= this.enemyTank.getPosition().getFirst()
                                + MARGINAL_DISTANCE) {
            this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
                    this.enemyTank.getPosition().getSecond() - Tank.getDimension().getSecond()));
        } else if (movement.get(Direction.LEFT) && movement.get(Direction.UP)
                && this.playerTank.getPosition().getFirst() <= this.enemyTank.getPosition().getFirst()
                        + Tank.getDimension().getFirst() - MARGINAL_DISTANCE) {
            this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
                    this.enemyTank.getPosition().getSecond() + Tank.getDimension().getSecond()));
        } else if (movement.get(Direction.LEFT) && movement.get(Direction.DOWN)
                && this.playerTank.getPosition().getFirst() <= this.enemyTank.getPosition().getFirst()
                        + Tank.getDimension().getFirst() - MARGINAL_DISTANCE) {
            this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
                    this.enemyTank.getPosition().getSecond() - Tank.getDimension().getSecond()));
        } else if (movement.get(Direction.RIGHT)) {
            this.playerTank.setPosition(
                    new Pair<>(this.enemyTank.getPosition().getFirst() - Tank.getDimension().getFirst(),
                            this.playerTank.getPosition().getSecond()));
        } else if (movement.get(Direction.LEFT)) {
            this.playerTank.setPosition(
                    new Pair<>(this.enemyTank.getPosition().getFirst() + Tank.getDimension().getFirst(),
                            this.playerTank.getPosition().getSecond()));
        } else if (movement.get(Direction.DOWN)) {
            this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
                    this.enemyTank.getPosition().getSecond() - Tank.getDimension().getSecond()));
        } else if (movement.get(Direction.UP)) {
            this.playerTank.setPosition(new Pair<>(this.playerTank.getPosition().getFirst(),
                    this.enemyTank.getPosition().getSecond() + Tank.getDimension().getSecond()));
        }
    }

}
