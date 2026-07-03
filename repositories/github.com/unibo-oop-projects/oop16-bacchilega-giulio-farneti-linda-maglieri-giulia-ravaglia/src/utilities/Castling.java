package utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Enumeration that contains all type of castlings.
 * @author : Giulio Bacchilega
 */
public enum Castling {

    /**
     * type of castlings.
     */
    shortCastling, longCastling;
    private static final int X_5 = 5;
    private static final int X_6 = 6;
    private static final int X_7 = 7;
    private static final int X_3 = 3;
    private static final  int X_2 = 2;
    /**
     * Return the map of castling path to assign at one King.
     * @param p , the player ofthe King
     * @return map 
     */
    public static Map<Castling, Set<Pair<Integer, Integer>>> getCastlingCoordinate(final Players p) {
        final Map<Castling, Set<Pair<Integer, Integer>>> map = new HashMap<>();
        if (p.equals(Players.playerWhite)) {
            map.put(Castling.shortCastling, new HashSet<>(Arrays.asList(new Pair<>(X_5, 0), new Pair<>(X_6, 0))));
            map.put(Castling.longCastling, new HashSet<>(Arrays.asList(new Pair<>(3, 0), new Pair<>(2, 0))));
        } else {
            map.put(Castling.shortCastling, new HashSet<>(Arrays.asList(new Pair<>(X_5, X_7), new Pair<>(X_6, X_7))));
            map.put(Castling.longCastling, new HashSet<>(Arrays.asList(new Pair<>(3, X_7), new Pair<>(2, X_7))));
        }
        return map;
    }

    /**
     * Return a map containing, for each type of castlings, the Rook reference and the final position o the Rooks.
     * @param p the player of the Rook
     * @return Map<Castling, Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>>
     */
    public static Map<Castling, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getCastlingReference(final Players p) {
        final Map<Castling, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> map = new HashMap<>();
        if (p.equals(Players.playerWhite)) {
            map.put(Castling.shortCastling, new Pair<>(new Pair<>(X_6, 0), new Pair<>(X_7, 0)));
            map.put(Castling.longCastling, new Pair<>(new Pair<>(X_2, 0), new Pair<>(0, 0)));
        } else {
            map.put(Castling.shortCastling, new Pair<>(new Pair<>(X_6, X_7), new Pair<>(X_7, X_7)));
            map.put(Castling.longCastling, new Pair<>(new Pair<>(X_2, X_7), new Pair<>(0, X_7)));
        }
        return map;
    }

    /**
     * Return the set that contains the castling path for this King, specyfying the player owner and the type of castling.
     * @param castling , the type of the castling
     * @param p , the King's player
     * @return Set<Pair<Integer,Integer>>
     */
    public static Set<Pair<Integer, Integer>> pathForCastling(final Castling castling, final Players p) {
        if (castling.equals(longCastling)) {
            if (p.equals(Players.playerBlack)) {
                return new HashSet<>(Arrays.asList(new Pair<>(1, X_7), new Pair<>(X_2, X_7), new Pair<>(X_3, X_7)));
            } else {
                return new HashSet<>(Arrays.asList(new Pair<>(1, 0), new Pair<>(X_2, 0), new Pair<>(X_3, 0)));
            }
        } else {
            if (p.equals(Players.playerWhite)) {
                return new HashSet<>(Arrays.asList(new Pair<>(X_6, 0), new Pair<>(X_5, 0)));
            } else {
                return new HashSet<>(Arrays.asList(new Pair<>(X_6, X_7), new Pair<>(X_5, X_7)));
            }
        }
    }

    /**
     * Return the final position of a Rook, specifying the player owner and the type of the castling.
     * @param castling , the type of the castling
     * @param p , the given player
     * @return Pair<Integer,Integer> , the position coordinate after the castling
     */
    public static Pair<Integer, Integer> getCastlingPosition(final Castling castling, final Players p) {
        if (castling.equals(longCastling)) {
            if (p.equals(Players.playerBlack)) {
                return  new Pair<>(X_3, X_7);
            } else {
                return new Pair<>(X_3, 0);
            }
        } else {
            if (p.equals(Players.playerWhite)) {
                return new Pair<>(X_5, 0);
            } else {
                return new Pair<>(X_5, X_7);
            }
        }
    }
}
