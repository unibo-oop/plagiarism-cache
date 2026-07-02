package it.unibo.javacrush.model.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MatchManager;

/**
 * Implementation of the MatchManager interface,
 * responsible for finding and removing matches on the board.
 */
public final class MatchManagerImpl implements MatchManager {

    @Override
    public Match findMatchesAt(final Board board, final Position pos) {

        final Set<Position> matches = new HashSet<>();
        final Set<Position> horizontal = checkHorizontal(board, pos);
        final Set<Position> vertical = checkVertical(board, pos);

        if (!horizontal.isEmpty()) {
            matches.addAll(horizontal);
        }
        if (!vertical.isEmpty()) {
            matches.addAll(vertical);
        }

        if (matches.isEmpty()) {
            return null; 
        }

        return new MatchImpl(matches, board.getCellAt(pos).get().getType());
    }

    @Override
    public Set<Match> findAllMatches(final Board board) {

        final Set<Match> matches = new HashSet<>();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                final Position pos = new Position(j, i);
                if (!board.getCellAt(pos).isEmpty()) {

                    final var match = findMatchesAt(board, pos);
                    if (match != null) {
                    matches.add(match);
                    }

                }
            }
        }

        return matches;
    }

    @Override
    public void removeMatch(final Board board, final Match match) {
        for (final Position pos : match.getPositions()) {
            board.removeCell(pos);
        }
    }

    private boolean isInBounds(final Board board, final int col, final int row) {
        return col >= 0 && col < board.getCols() && row >= 0 && row < board.getRows();
    }

    private Set<Position> checkHorizontal(final Board board, final Position pos) {

        final Set<Position> matches = new HashSet<>();
        matches.add(pos);
        final CellType matchType = board.getCellAt(pos).get().getType();

        final int y = pos.y();
        int x = pos.x() - 1;
        while (isInBounds(board, x, y) 
                && board.getCellAt(new Position(x, y))
                        .map(cell -> cell != null ? cell.getType() : null)
                        .filter(type -> type == matchType)
                        .isPresent()) {

            matches.add(new Position(x, y));
            x--;
        }

        x = pos.x() + 1;
        while (isInBounds(board, x, y) 
                && board.getCellAt(new Position(x, y))
                        .map(cell -> cell != null ? cell.getType() : null)
                        .filter(type -> type == matchType)
                        .isPresent()) {

            matches.add(new Position(x, y));
            x++;
        }

        return matches.size() >= 3 ? matches : Collections.emptySet();
    }

    private Set<Position> checkVertical(final Board board, final Position pos) {

        final Set<Position> matches = new HashSet<>();
        matches.add(pos);
        final CellType matchType = board.getCellAt(pos).get().getType();

        final int x = pos.x();
        int y = pos.y() - 1;
        while (isInBounds(board, x, y) 
                && board.getCellAt(new Position(x, y))
                        .map(cell -> cell != null ? cell.getType() : null)
                        .filter(type -> type == matchType)
                        .isPresent()) {

            matches.add(new Position(x, y));
            y--;
        }

        y = pos.y() + 1;
        while (isInBounds(board, x, y) 
                && board.getCellAt(new Position(x, y))
                        .map(cell -> cell != null ? cell.getType() : null)
                        .filter(type -> type == matchType)
                        .isPresent()) {

            matches.add(new Position(x, y));
            y++;
        }

        return matches.size() >= 3 ? matches : Collections.emptySet();
    }

}
