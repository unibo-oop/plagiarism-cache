package model.utilities.pawns;

import java.util.ArrayList;

import java.util.Arrays;
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
public class Knight extends Pawn {

    private final List<Directions> directions = new ArrayList<>(Arrays.asList(Directions.knight_moves));

     /**
      * @param type the type of this pawn
      * @param p the player owner of this pawn
      * @param actualPosition the starting position of this pawn
      */
    public Knight(final PawnTypes type, final Players p, final Pair<Integer, Integer> actualPosition) {
        super(type, p, actualPosition);
    }

    @Override
    public Set<Pair<Integer, Integer>> getPossibleMoves() {
        return Movement.getLog().getMoves(directions, Optional.empty(), this.getActualPosition());
    }

    @Override
    public void setAfterCreation() { }
}
