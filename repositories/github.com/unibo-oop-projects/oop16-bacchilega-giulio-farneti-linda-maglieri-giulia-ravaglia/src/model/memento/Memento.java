package model.memento;

import java.util.List;

import utilities.Pair;
import model.utilities.pawns.Pawn;

/**
 * 
 * @author : Giulio Bacchilega.
 *
 */
public interface Memento {

    /**
     * Set for the same turn the movement of the pawn that are contained in the given list.
     * 
     * @param list the list of the pawns moved in the same turn
     */
     void setNewMovements(List<Pair<Pawn, Pair<Integer, Integer>>> list);

     /**
      * Set in the relative map a new removed pawn in a specific turn.
      * @param removed the removed Pawn
      * @param position the last position of the pawn
      */
     void setNewRemoved(Pawn removed, Pair<Integer, Integer> position);

     /**
      * Set in the relative map a new added pawn in a specific turn.
      * @param added the removed Pawn
      * @param position the last position of the pawn
      */
     void setNewPawnAdded(Pawn added, Pair<Integer, Integer> position);

     /**
      * Return the last registered movement.
      * @return list that contains, for each pawn moved in the last turn, the initial position and the last position
      */
     List<Pair<Pawn, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>> getLast();

     /**
      * Return the last removed pawn.
      * @return a pair that contains the last removed pawn and the last position
      */
     Pair<Pawn, Pair<Integer, Integer>> getRemovedPawn();

     /**
      * Return the last added pawn.
      * @return a pair that contains the last removed pawn and the last position
      */
     Pair<Pawn, Pair<Integer, Integer>> getAddedPawn();

     /**
      * Restore initial settings of Memento.
      */
     void resetMemento();
}
