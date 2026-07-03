package utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author : Giulio Bacchilega
 */
public enum Directions {

    /**
     * Possible directions.
     */
    SOUTH, NORTH, EAST, WEST, SE, SW, NE, NW, knight_moves, VERTICAL, HORIZONTAL, DIAGONAL_1, DIAGONAL_2;

    private static final Map<Directions, List<Directions>> MAP = new HashMap<Directions, List<Directions>>() {
        private static final long serialVersionUID = 1L;
        {
            put(VERTICAL, Arrays.asList(NORTH, SOUTH));
            put(HORIZONTAL, Arrays.asList(WEST, EAST));
            put(DIAGONAL_1, Arrays.asList(NE, SW));
            put(DIAGONAL_2, Arrays.asList(NW, SE));
        }
    };

    /**
     * Given a direction line, return the single direction which it's made by.
     * @param dir the given line
     * @return List<Directions>
     */
    public static List<Directions> get(final Directions dir) {
        if (!MAP.containsKey(dir)) {
            return Arrays.asList(dir);
        }
        return MAP.get(dir);
    }

    /**
     * Return the line that contain this single direction.
     * @param d the given single direction
     * @return Directions 
     */
    public static Directions getLineBySingleDirections(final Directions d) {
        return MAP.keySet().stream().filter(x-> MAP.get(x).contains(d)).findFirst().get();
    }

    /**
     * Given a single direction, return the opposite one.
     * @param dir the given single direction
     * @return Directions
     */
    public static Directions getOpposite(final Directions dir) {
        Optional<Directions> row = Optional.empty();
        row = MAP.keySet().stream().filter(x-> MAP.get(x).contains(dir)).findFirst();
        return MAP.get(row.get()).stream().filter(x-> !x.equals(dir)).findFirst().get();
    }
}
