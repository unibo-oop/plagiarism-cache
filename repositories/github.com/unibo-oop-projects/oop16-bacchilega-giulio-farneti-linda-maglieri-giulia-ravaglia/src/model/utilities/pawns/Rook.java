package model.utilities.pawns;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
public class Rook extends Pawn {

    private List<Directions> directions = new ArrayList<>(Arrays.asList(Directions.VERTICAL, Directions.HORIZONTAL));
    private Pair<Integer, Integer> castlingPosition;
    private Castling castling;
    private Set<Pair<Integer, Integer>> pathForCastling = new HashSet<>();

    /**
     * Contructor of this class.
     * @param type the type of this pawn
     * @param p the player owner of this pawn
     * @param actualPosition the current position of this pawn
     */
    public Rook(final PawnTypes type, final Players p, final Pair<Integer, Integer> actualPosition) {
        super(type, p, actualPosition);
        this.castling = actualPosition.getX() > 0 ? Castling.shortCastling : Castling.longCastling;
    }

    private void setPath() {
        this.castlingPosition = Castling.getCastlingPosition(this.castling, this.getPlayer());
        this.pathForCastling = Castling.pathForCastling(this.castling, this.getPlayer());
    }

    @Override
    public Set<Pair<Integer, Integer>> getPossibleMoves() {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();
        moves.addAll(Movement.getLog().getMoves(directions, Optional.empty(), this.getActualPosition()));
        return moves;
    }

    /**
     * @return the castling path of this Rook
     */
    public Set<Pair<Integer, Integer>> getCastlingPath() {
        return this.pathForCastling;
    }

    /**
     * @return the castling final position of this Rook
     */
    public Pair<Integer, Integer> getCastlingPosition() {
        return this.castlingPosition;
    }

    /**
     * @return the castling type of this Rook
     */
    public Castling getCastlingType() {
        return this.castling;
    }

    @Override
    public void setAfterCreation() {
        this.setPath();
    }
}
