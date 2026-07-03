package model.utilities.pawns;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.movement.Movement;
import utilities.Castling;
import utilities.Directions;
import utilities.Pair;
import utilities.Players;

/**
 * @author : Giulio Bacchilega
 */
public class King extends Pawn {

    private List<Directions> directions = new ArrayList<>(Arrays.asList(Directions.VERTICAL, Directions.HORIZONTAL, Directions.DIAGONAL_1, Directions.DIAGONAL_2));
    private Map<Castling, Set<Pair<Integer, Integer>>> castlingCoordinate = new HashMap<>();
    private Map<Castling, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> castlingReference = new HashMap<>();
    /**
     * @param type the type of this pawn
     * @param p the player owner of this pawn
     * @param actualPosition the starting position of this pawn
     */
    public King(final PawnTypes type, final Players p, final Pair<Integer, Integer> actualPosition) {
        super(type, p, actualPosition);
    }

    private void setCastlingMap() {
        this.castlingCoordinate = Castling.getCastlingCoordinate(this.getPlayer());
        this.castlingReference = Castling.getCastlingReference(this.getPlayer());
    }

    @Override
    public Set<Pair<Integer, Integer>> getPossibleMoves() {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();
        moves.addAll(Movement.getLog().getMoves(directions, Optional.of(1), this.getActualPosition()));
        return moves;
    }

    /**
     * @return the castlingCoordinate map
     */
    public Map<Castling, Set<Pair<Integer, Integer>>>getCastlingMap() {
        return this.castlingCoordinate;
    }

    /**
     * @return the castlingReference map
     */
    public Map<Castling, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getCastlingReference() {
        return this.castlingReference;
    }

    /**
     * @return the directions of movement of the King
     */
    public List<Directions> getDirections() {
        return this.directions;
    }

    @Override
    public void setAfterCreation() {
        this.setCastlingMap();
    }
}
