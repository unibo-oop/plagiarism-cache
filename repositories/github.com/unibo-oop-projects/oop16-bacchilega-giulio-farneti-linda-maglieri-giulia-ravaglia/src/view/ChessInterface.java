package view;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.Node;
import model.utilities.pawns.Pawn;
import utilities.Pair;
import utilities.Players;

/**
 * 
 * Author: Giulia Maglieri.
 *
 */
public interface ChessInterface {
    /**
     * 
     * @param title String
     * @return Node
     */
    Node display(final String title);
    /**
     * 
     */
     void reset();
    /**
     * @param map parameter passed from the model.
     */
    void setPawnsIntoChessBoard(final Map<Players, List<Pawn>> map);
    /**
     * 
     */
     void setPawn();
    /**
     * @param p 
     * @param startPosition 
     * @param endPosition position where the selected pawn have to move.
     */
     void setNextMove(final Pawn p, final Pair<Integer, Integer> startPosition, final Pair<Integer, Integer> endPosition);
     /**
      * @param endPosition position 
      */
     void removePawn(final Pair<Integer, Integer> endPosition);
     /** 
      * @param s chosen style for the pawn
      */
     void setPawnsStyle(final String s);
     /**
      * @return String
      */
     String getPawnStyle();
      /**
       * @param set set of the threatened pawns
       */
      void showThreatenedPawns(final Set<Pair<Integer, Integer>> set);
      /**
       * @param set1 set of all the possible moves for one chosen pawn
       */
      void showAllowedMoves(final Set<Pair<Integer, Integer>> set1);
      /**
       * 
       */
      void repaintTable();
      /**
       * @param p player
       */
      void checkMate(final Players p);
      /**
       * @param p player
       */
       void checkMated(final Players p);
       /**
        * 
        */
       void changePawnWith();
       /**
        * @param p player
        */
       void timeIsUp(final Players p);
       /**
        * @param p player
        * @return String
        */
       String actualTurn(final Players p);
       /**
        * @return Players
        */
       Players getActualPlayer();
}