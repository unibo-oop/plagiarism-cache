package it.unibo.michelito.controller.gamecontroller.movecommandbuilder.impl;

import it.unibo.michelito.controller.gamecontroller.movecommandbuilder.api.MoveCommandBuilder;
import it.unibo.michelito.controller.playercommand.impl.MoveCommand;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

import java.util.Arrays;

/**
 * Implementation of the {@link MoveCommandBuilder}.
 */
public class MoveCommandBuilderImpl implements MoveCommandBuilder {
    private double x;
    private double y;

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveCommandBuilder addDirection(final Direction direction) {
        if (direction.isDiagonal()) {
            throw new IllegalArgumentException("Direction cannot be diagonal");
        } else {
            this.x = this.x + direction.toPosition().x();
            this.y = this.y + direction.toPosition().y();
            if (this.x > 1 || this.y > 1 || this.x < -1 || this.y < -1) {
                throw new IllegalArgumentException("Cannot give the same direction more than once");
            }
            return this;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoveCommand build() {
        final double magnitude = Math.sqrt(x * x + y * y);
        if (magnitude == 0) {
            return new MoveCommand(Direction.NONE);
        }
        if (Math.abs(x) == 1 && Math.abs(y) == 1) {
            final double sqr = Math.sqrt(0.5);
            x = x * sqr;
            y = y * sqr;
        }
        final Position position = new Position(this.x, this.y);
        final var direction = Arrays.stream(Direction.values())
                .filter(p -> p.toPosition().equals(position))
                .findAny();
        if (direction.isPresent()) {
            return new MoveCommand(direction.get());
        } else {
            throw new IllegalArgumentException("Direction does not exist");
        }
    }
}
