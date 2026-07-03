package model.utilities.pawns;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.chessboard.Chessboard;
import model.logic.GameLogic;
import model.movement.Movement;
import utilities.Directions;
import utilities.Pair;
import utilities.Players;

/**
 * @author : Giulio Bacchilega
 */
public class SimplePawn extends Pawn {

    private static final GameLogic GL = GameLogic.getLog();
    private List<Directions> directions = new ArrayList<>();

    /**
     * Contructor of this class.
     * @param type the type of this pawn
     * @param p the player owner of this pawn
     * @param actualPosition the current position of this pawn
     */
    public SimplePawn(final PawnTypes type, final Players p, final Pair<Integer, Integer> actualPosition) {
        super(type, p, actualPosition);
    }

    @Override
    public Set<Pair<Integer, Integer>> getPossibleMoves() {
        Set<Pair<Integer, Integer>> moves = new HashSet<>();
        if (!this.isMoved()) {
            moves = Movement.getLog().getMoves(directions, Optional.of(2), this.getActualPosition());
        } else {
            moves = Movement.getLog().getMoves(directions, Optional.of(1), this.getActualPosition());
        }
        moves = moves.stream()
                        .filter(a-> Chessboard.getLog().isEmpty(a))
                        .collect(Collectors.toSet());
      for (Pair<Integer, Integer> p : this.getDirectionsToEat()) {
          if (!Chessboard.getLog().isEmpty(p)) {
              if (!Chessboard.getLog().getPawnByCoordinate(p).getPlayer().equals(GL.getActualPlayer())) {
                  moves.add(p);
              }
          }
      }
      return moves;
    }

    /**
     * Return the possible eating coordinates.
     * @return Set<Pair<Integer,Integer>>
     */
    public Set<Pair<Integer, Integer>> getDirectionsToEat() {
        List<Directions> list = this.getPlayer().equals(Players.playerWhite) ? new ArrayList<>(Arrays.asList(Directions.NE, Directions.NW)) 
                                                                               :
                                                                               new ArrayList<>(Arrays.asList(Directions.SE, Directions.SW));
        return Movement.getLog().getMoves(list, Optional.of(1), this.getActualPosition());
    }

    /**
     * @return true if this pawn has reached the limit of the chessboard, false otherwise
     */
    public boolean checkChange() {
        return this.getActualPosition().getY().equals(Chessboard.getLog().getLimit(this.getPlayer()));
    }

    /**
     * Set the directions of movement of this pawn.
     */
    public void setDirection() {
        if (this.getPlayer().equals(Players.playerWhite)) {
            directions.add(Directions.NORTH);
        } else {
            directions.add(Directions.SOUTH);
        }
    }

    @Override
    public void setAfterCreation() {
        this.setDirection();
    }
}
