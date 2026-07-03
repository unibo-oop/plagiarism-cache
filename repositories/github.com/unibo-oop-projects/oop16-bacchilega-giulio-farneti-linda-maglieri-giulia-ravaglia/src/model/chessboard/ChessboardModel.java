package model.chessboard;

import java.util.List;


import java.util.Map;
import java.util.Set;

import utilities.Pair;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnTypes;
import utilities.Players;
import model.utilities.pawns.SimplePawn;

/**
 * @author : Giulio Bacchilega
 */
public interface ChessboardModel {

    /**
     * @return the map that associate each players with the list of pawns
     */
    Map<Players, List<Pawn>> getMapOfPlayers();

    /**
     * @param pair the given pair to control
     * @return the pawn that is contained in given pair
     */
    Pawn getPawnByCoordinate(final Pair<Integer, Integer> pair);

    /**
     * @param pair that is the coordinate to control
     * @return true if the given pair contains a pawn, false otherwise
     */
    boolean isEmpty(final Pair<Integer, Integer> pair);

    /**
     * @param pair that is the coordinate of chessboard to control 
     * @return true if the given pair is included in the chessboard edge, false otherwise
     */
    boolean isIncluded(final Pair<Integer, Integer> pair);

    /**
     * @param p the player that we want to get the threatened path
     * @return a set that contains all the positions that are threatened by all pawns of player p
     */
    Set<Pair<Integer, Integer>> threatenedCoordinates(Players p);

    /**
     * @return a set that contains the coordinate of the pawns that are contained in the given collection
     */
    Set<Pair<Integer, Integer>> threatenedPawnsInCoordinates();

    /**
     * @return a set that contains the positions of all pawns in the chessboard
     */
    Set<Pair<Integer, Integer>> getPlayersCoordinates();

    /**
     * moves the given pawn on the new given position.
     * @param pawn the paw that is moved
     * @param next the next position(coordinate) of this pawn
     */
    void setMove(Pawn pawn, Pair<Integer, Integer> next);

    /**
     * puts the given pawn in the given coordinate, under the control of player p.
     * @param pawn the pawn that's put in the chessboard
     * @param position the position that contains the pawn
     * @param p the pawn's owner
     */
    void putPawn(Pawn pawn, Pair<Integer, Integer> position, Players p);

    /**
     * removes the given pawn from the game.
     * @param pair the cordinate that contains the pawn to reemove
     * @param p the pawn's owner 
     */
     void removePawn(final Pair<Integer, Integer> pair, Players p);

     /**
      * Change the SimplePawn that has reached the limit with another types.
      * @param sp , the given simple pawn
      * @param type , the type of the new pawn
      */
     void pawnInLimit(SimplePawn sp, PawnTypes type);

     /**
      * Return the integer that specifyies the limit of the pawn of thi player.
      * @param p the given player
      * @return Integer
      */
     Integer getLimit(final Players p);

     /**
      * @param p , the given player
      * @return Pawn , the king of this player
      */
     Pawn getKingByPlayer(Players p);

     /**
      * Initialize another Chessboard with specific settings.
      */
     void setNewGame();
}
