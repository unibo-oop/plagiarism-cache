package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.utilities.pawns.PawnTypes;

/**
 * Class StartingCoordinate.
 * @author : Giulio Bacchilega
 */
public final class StartingCoordinate {

    private static final int X_5 = 5;
    private static final int X_6 = 6;
    private static final int X_7 = 7;

    private StartingCoordinate() { }

    private static final Map<PawnTypes, List<Pair<Integer, Integer>>> MAP = new HashMap<PawnTypes, List<Pair<Integer, Integer>>>() {
        private static final long serialVersionUID = 1L;
        {
            put(PawnTypes.SimplePawn, new ArrayList<>(Arrays.asList(new Pair<>(0, 1), new Pair<>(1, 1), new Pair<>(2, 1), new Pair<>(3, 1),
                                                                   new Pair<>(4, 1), new Pair<>(X_5, 1), new Pair<>(X_6, 1), new Pair<>(X_7, 1))));
            put(PawnTypes.Bishop, new ArrayList<>(Arrays.asList(new Pair<>(2, 0), new Pair<>(X_5, 0))));
            put(PawnTypes.Knight, new ArrayList<>(Arrays.asList(new Pair<>(1, 0), new Pair<>(X_6, 0))));
            put(PawnTypes.Rook, new ArrayList<>(Arrays.asList(new Pair<>(0, 0), new Pair<>(X_7, 0))));
            put(PawnTypes.King, new ArrayList<>(Arrays.asList(new Pair<>(4, 0))));
            put(PawnTypes.Queen, new ArrayList<>(Arrays.asList(new Pair<>(3, 0))));
        }
    };

    /**
     * @return MAP
     */
    public static Map<PawnTypes, List<Pair<Integer, Integer>>> getMap() {
        return MAP;
    }

    /**
     * 
     * @param type the type of the given pawn
     * @return List<Pawn>
     */
    public static List<Pair<Integer, Integer>> get(final PawnTypes type) {
        return MAP.get(type);
    }
}
