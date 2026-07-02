package javawulf.controller;

import java.util.Optional;

import javawulf.model.Direction;

/**
 * Implementation of PlayerController.
 */
public final class PlayerControllerImpl implements PlayerController {

    private Optional<Direction> movementDirection = Optional.empty();
    private boolean attack;

    @Override
    public Optional<Direction> getDirection() {
        return this.movementDirection;
    }

    @Override
    public boolean isAttack() {
        return this.attack;
    }

    @Override
    public void updatePlayerStatus(final boolean up, final boolean down, final boolean left, final boolean right) {
        if (!(up && down || left && right)) {
            if (up) {
                if (right) {
                    this.movementDirection = Optional.of(Direction.UP_RIGHT);
                } else if (left) {
                    this.movementDirection = Optional.of(Direction.UP_LEFT);
                } else {
                    this.movementDirection = Optional.of(Direction.UP);
                }
            } else if (down) {
                if (right) {
                    this.movementDirection = Optional.of(Direction.DOWN_RIGHT);
                } else if (left) {
                    this.movementDirection = Optional.of(Direction.DOWN_LEFT);
                } else {
                    this.movementDirection = Optional.of(Direction.DOWN);
                }
            } else if (right) {
                this.movementDirection = Optional.of(Direction.RIGHT);
            } else if (left) {
                this.movementDirection = Optional.of(Direction.LEFT);
            } else {
                this.movementDirection = Optional.empty();
            }
        } else {
            this.movementDirection = Optional.empty();
        }
    }

    @Override
    public void updateSwordStatus(final boolean attack) {
        this.attack = attack;
    }

}
