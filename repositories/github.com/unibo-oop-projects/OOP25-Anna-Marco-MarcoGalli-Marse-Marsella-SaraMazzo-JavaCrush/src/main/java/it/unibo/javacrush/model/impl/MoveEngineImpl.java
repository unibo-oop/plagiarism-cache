package it.unibo.javacrush.model.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.Match;

/**
 * Implementation of the MoveEngine interface.
 * This class is responsible for determining if a swap between two positions on the board can result in a match,
 * and for keeping track of the matches found during the last swap operation.
 */
public final class MoveEngineImpl implements MoveEngine {

    private final MatchManager detector = new MatchManagerImpl();
    private final Set<Match> matches = new HashSet<>();

    @Override
    public boolean canSwap(final Board board, final Position pos1, final Position pos2) {

        final Set<Match> currentMatches = new HashSet<>();
        matches.clear();

        if (!isAdjacent(pos1, pos2)) {
            return false;
        }

        board.swapCells(pos1, pos2);
        final Match matches1 = detector.findMatchesAt(board, pos1);
        final Match matches2 = detector.findMatchesAt(board, pos2);

        if (matches1 != null) {
            currentMatches.add(matches1);
        }
        if (matches2 != null) {
            currentMatches.add(matches2);
        }

        board.swapCells(pos1, pos2);
        matches.addAll(currentMatches);
        currentMatches.clear();

        return !matches.isEmpty();
    }

    private boolean isAdjacent(final Position pos1, final Position pos2) {
        return Math.abs(pos1.x() - pos2.x()) == 1 && pos1.y() == pos2.y() 
                || Math.abs(pos1.y() - pos2.y()) == 1 && pos1.x() == pos2.x();
    }

    @Override
    public Set<Match> getMatches() {
        return Set.copyOf(matches);
    }

}
