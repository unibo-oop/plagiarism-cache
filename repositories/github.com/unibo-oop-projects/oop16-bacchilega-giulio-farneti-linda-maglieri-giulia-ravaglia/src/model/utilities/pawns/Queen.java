package model.utilities.pawns;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import model.movement.Movement;
import utilities.Directions;
import utilities.Pair;
import utilities.Players;

/**
 * @author : Giulio Bacchilega
 */
public class Queen extends Pawn {

    private List<Directions> directions = new ArrayList<>(Arrays.asList(Directions.VERTICAL, Directions.HORIZONTAL, Directions.DIAGONAL_1, Directions.DIAGONAL_2));

    /**
     * Contructor of this class.
     * @param type the type of this pawn
     * @param p the player owner of this pawn
     * @param actualPosition the current position of this pawn
     */
    public Queen(final PawnTypes type, final Players p, final Pair<Integer, Integer> actualPosition) {
        super(type, p, actualPosition);
    }

    @Override
    public Set<Pair<Integer, Integer>> getPossibleMoves() {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();
        moves.addAll(Movement.getLog().getMoves(directions, Optional.empty(), this.getActualPosition()));
        return moves;
    }

    @Override
    public void setAfterCreation() { }
}
