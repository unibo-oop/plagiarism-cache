package it.unibo.javacrush.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.RefillEngine;

/**
 * Implementation of the {@link StallEngine} interface.
 */
public class StallEngineImpl implements StallEngine {

    private static final int MAX_ITERATIONS = 500;

    private final MatchManager manager = new MatchManagerImpl();
    private final RefillEngine refill = new AdaptiveRefill(null);
    private final Random ran = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStall(final Board board) {
        return this.possibleMatches(board).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resolveStall(final Board board) {

        final List<Cell> tmp = new ArrayList<>();
        int index;
        int count;

        count = 0;

        while (this.isStall(board) || !manager.findAllMatches(board).isEmpty() && count < MAX_ITERATIONS) {

            tmp.clear();
            for (int y = 0; y < board.getRows(); y++) {
                for (int x = 0; x < board.getCols(); x++) {
                    tmp.add(board.getCellAt(new Position(x, y)).get());
                }
            }
            Collections.shuffle(tmp);
            index = 0;

            for (int j = 0; j < board.getRows(); j++) {
                for (int i = 0; i < board.getCols(); i++) {
                    board.setCell(new Position(i, j), Optional.of(tmp.get(index)));
                    index++;
                }
            }

            count++;
        }

        if (count >= MAX_ITERATIONS) {
            this.resolveIrreversibleStall(board);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Position> getHint(final Board board) {
        final Set<Position> resultSet = new HashSet<>();
        final Match currentMatch;
        boolean found = false;

        if (!this.isStall(board)) {
            currentMatch = this.possibleMatches(board).get(this.ran.nextInt(this.possibleMatches(board).size()));

            for (final var pos : currentMatch.getPositions()) {
                if (board.getCellAt(pos).get().getType() != currentMatch.getType() && !found) {

                    if (manager.findMatchesAt(this.swapRight(board, pos), pos) != null
                        && manager.findMatchesAt(board, pos).getType() == currentMatch.getType()
                        && manager.findMatchesAt(board, pos).equals(currentMatch)) {
                        resultSet.add(new Position(pos.x() + 1, pos.y()));
                        found = true;
                    }
                    this.swapRight(board, pos);

                    if (manager.findMatchesAt(this.swapLeft(board, pos), pos) != null
                        && manager.findMatchesAt(board, pos).getType() == currentMatch.getType()
                        && manager.findMatchesAt(board, pos).equals(currentMatch)
                        && !found) {
                        resultSet.add(new Position(pos.x() - 1, pos.y()));
                        found = true;
                    }
                    this.swapLeft(board, pos);

                    if (manager.findMatchesAt(this.swapDown(board, pos), pos) != null
                        && manager.findMatchesAt(board, pos).getType() == currentMatch.getType()
                        && manager.findMatchesAt(board, pos).equals(currentMatch)
                        && !found) {
                        resultSet.add(new Position(pos.x(), pos.y() + 1));
                        found = true;
                    }
                    this.swapDown(board, pos);

                    if (manager.findMatchesAt(this.swapUp(board, pos), pos) != null
                        && manager.findMatchesAt(board, pos).getType() == currentMatch.getType()
                        && manager.findMatchesAt(board, pos).equals(currentMatch)
                        && !found) {
                        resultSet.add(new Position(pos.x(), pos.y() - 1));
                        found = true;
                    }
                    this.swapUp(board, pos);

                } else {
                    resultSet.add(pos);
                }
            }
        }

        return resultSet;
    }

    private Board swapRight(final Board tmp, final Position p) {

        if (p.x() + 1 < tmp.getCols()) {
            tmp.swapCells(p, new Position(p.x() + 1, p.y()));
        }
        return tmp;
    }

    private Board swapLeft(final Board tmp, final Position p) {

        if (p.x() - 1 >= 0) {
            tmp.swapCells(p, new Position(p.x() - 1, p.y()));
        }
        return tmp;
    }

    private Board swapDown(final Board tmp, final Position p) {

        if (p.y() + 1 < tmp.getRows()) {
            tmp.swapCells(p, new Position(p.x(), p.y() + 1));
        }
        return tmp;
    }

    private Board swapUp(final Board tmp, final Position p) {

        if (p.y() - 1 >= 0) {
            tmp.swapCells(p, new Position(p.x(), p.y() - 1));
        }
        return tmp;
    }

    private List<Match> possibleMatches(final Board board) {

        final Board tmp = new BoardImpl(board.getRows(), board.getCols());
        final List<Match> resultList = new ArrayList<>();

        Position p;

        for (int y = 0; y < board.getRows(); y++) {
            for (int x = 0; x < board.getCols(); x++) {
                p = new Position(x, y);
                if (board.getCellAt(p).isEmpty()) {
                    resultList.add(new MatchImpl(Set.of(), null));
                    return resultList;
                }
                tmp.setCell(p, board.getCellAt(p));
            }
        }

        for (int y = 0; y < tmp.getRows(); y++) {
            for (int x = 0; x < tmp.getCols(); x++) {

                p = new Position(x, y);
                if (manager.findMatchesAt(this.swapRight(tmp, p), p) != null) {
                    resultList.add(manager.findMatchesAt(tmp, p));
                }
                this.swapRight(tmp, p);

                if (manager.findMatchesAt(this.swapDown(tmp, p), p) != null) {
                    resultList.add(manager.findMatchesAt(tmp, p));
                }
                this.swapDown(tmp, p);

                if (manager.findMatchesAt(this.swapLeft(tmp, p), p) != null) {
                    resultList.add(manager.findMatchesAt(tmp, p));
                }
                this.swapLeft(tmp, p);

                if (manager.findMatchesAt(this.swapUp(tmp, p), p) != null) {
                    resultList.add(manager.findMatchesAt(tmp, p));
                }
                this.swapUp(tmp, p);

            }
        }

        return resultList.stream()
                        .filter(a -> a != null)
                        .distinct()
                        .toList();
    }

    private void resolveIrreversibleStall(final Board board) {
        for (int j = 0; j < board.getRows(); j++) {
            for (int i = 0; i < board.getCols(); i++) {
                board.removeCell(new Position(i, j));
            }
        }

        this.refill.refillAll(board);
        this.resolveStall(board);
    }

}
