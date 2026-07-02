package it.unibo.javacrush.powerup.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.impl.MatchImpl;

/**
 * This PowerUp removes a specified cell from the board.
 */
public class RemoveCell extends AbstractPowerUp {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {
        final Set<Position> resultSet = new HashSet<>();
        if (this.isAppliable(board, pos)) {
            resultSet.clear();
            resultSet.add(pos);
            this.addMatches(new MatchImpl(resultSet, board.getCellAt(pos).get().getType()));
            return true;
        }

        return false;
    }

}
