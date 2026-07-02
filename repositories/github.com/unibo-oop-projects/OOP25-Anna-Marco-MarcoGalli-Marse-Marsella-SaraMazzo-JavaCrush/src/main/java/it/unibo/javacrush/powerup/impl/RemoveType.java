package it.unibo.javacrush.powerup.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.impl.MatchImpl;

/**
 * This PowerUp removes all the cells on the board with the same type of the given cell.
 */
public class RemoveType extends AbstractPowerUp {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {

        if (this.isAppliable(board, pos)) {

            final Cell type = board.getCellAt(pos).get();
            final Set<Position> resultSet = new HashSet<>();
            Position current;

            for (int y = 0; y < board.getRows(); y++) {
                for (int x = 0; x < board.getCols(); x++) {
                    current = new Position(x, y);

                    if (board.getCellAt(current).isPresent() && board.getCellAt(current).get().getType() == type.getType()) {
                        resultSet.add(current);
                    }
                }
            }
            this.addMatches(new MatchImpl(resultSet, type.getType()));
            return true;
        }

        return false;
    }

}
