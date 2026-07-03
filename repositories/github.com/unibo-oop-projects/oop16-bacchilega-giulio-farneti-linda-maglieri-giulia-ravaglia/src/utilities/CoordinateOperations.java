package utilities;

/**
 * @author : Giulio Bacchilega
 */
public final class CoordinateOperations {

    private static final CoordinateOperations CO = new CoordinateOperations();

    private CoordinateOperations() { }

    /**
     * @param d the given direction
     * @param actual the actual coordinate
     * @return Pair<Integer,Integer>
     */
    public Pair<Integer, Integer> getNext(final Directions d, final Pair<Integer, Integer> actual) {
        int x = actual.getX();
        int y = actual.getY();
        Pair<Integer, Integer> next = actual;
        switch(d) {
            case NORTH:
                next = new Pair<>(x, ++y);
                break;
            case SOUTH:
                next = new Pair<>(x, --y);
                break;
            case WEST:
                next = new Pair<>(--x, y);
                break;
            case EAST:
                next = new Pair<>(++x, y);
                break;
            case NW:
                next = new Pair<>(--x, ++y);
                break;
            case NE:
                next = new Pair<>(++x, ++y);
                break;
            case SW:
                next = new Pair<>(--x, --y);
                break;
            case SE:
                next = new Pair<>(++x, --y);
                break;
            default: 
                break; 
        }
        return next;
    }

    /**
     * Return the singleton of this class.
     * @return CoordinateOpration
     */
    public static CoordinateOperations getLog() {
        return CO;
    }
}
