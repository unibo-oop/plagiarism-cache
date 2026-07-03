package controller;

import java.util.Comparator;

import model.utilities.pawns.Pawn;
import utilities.Pair;
/**
 * a comparator of Pair<Pawn,Iteger>
 * it is useful for order the pawn who will be add in the set of eaten pawn
 * 
 * @author Alex
 *
 */
public class PawnComparator implements Comparator<Pair<Pawn,Integer>> {
    
    /**
     * the pawn are sorted in function of the type of pawn
     */
    @Override
    public int compare(final Pair<Pawn, Integer> pw1, final Pair<Pawn, Integer> pw2) {    
        return pw1.getX().getType().compareTo(pw2.getX().getType());
    }

}
