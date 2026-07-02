package it.unibo.javacrush.powerup.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.impl.MatchImpl;

/**
 * This PowerUp removes the entire row of cells of the given cell from the board.
 */
public class RemoveRow extends AbstractPowerUp {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {

        if (this.isAppliable(board, pos)) {
            final Set<Position> resultSet = new HashSet<>();
            Position current;

            for (int x = 0; x < board.getCols(); x++) {
                current = new Position(x, pos.y());
                resultSet.add(current);
            }

            for (final var tp : CellType.values()) {
                final var tmp = resultSet.stream()
                                    .filter(p -> board.getCellAt(p).get().getType() == tp)
                                    .toList();

                if (!tmp.isEmpty()) {
                    this.addMatches(new MatchImpl(Set.copyOf(tmp), tp));
                }
            }

            return true;
        }

        return false;
    }

}
