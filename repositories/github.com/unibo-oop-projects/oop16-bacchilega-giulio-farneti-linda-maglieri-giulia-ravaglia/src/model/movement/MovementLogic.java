package model.movement;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import utilities.Directions;
import utilities.Pair;
/**
 * @author : Giulio Bacchilega.
 */
public interface MovementLogic {

    /**
     * @param dir the list that contains all the directions of movement allowed for this pawn
     * @param distance the optional distance to cover 
     * @param actual the actual position of the pawn
     * @return the possible movements obtained by all directions contained in dir, from the actual position, of a given distance
     */
    Set<Pair<Integer, Integer>> getMoves(List<Directions> dir, Optional<Integer> distance, Pair<Integer, Integer> actual);

    /**
     * @param singleD the single direction to compute
     * @param distance the distance to cover
     * @param actual the actual position of the pawn
     * @return a single path of movement, specifying the direction, the actual position and the distance to cover
     */
    Set<Pair<Integer, Integer>> getPath(Directions singleD, Optional<Integer> distance, Pair<Integer, Integer> actual);

    /**
     * Return true if the specifyed pair is accessible in the chessboard, false otherwise.
     * @param pair the pair to control
     * @return boolean
     */
    boolean isAccessible(final Pair<Integer, Integer> pair);
}
