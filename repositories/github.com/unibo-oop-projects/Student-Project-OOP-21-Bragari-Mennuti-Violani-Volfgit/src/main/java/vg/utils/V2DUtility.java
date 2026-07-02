package vg.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class V2DUtility {

    /**
     * From a list of coordinates extract vertex points.
     * Each point is subtracted to the previous one and is checked the resulting vector if it corresponds
     * to the previous direction.
     * @param coordinates list of point
     * @return list of point that are vertex of polyline
     */
    public static List<V2D> getVertex(final List<V2D> coordinates) {
        List<V2D> vertex = new ArrayList<>();
        if (!coordinates.isEmpty()) {
            Iterator<V2D> it = coordinates.iterator();
            Direction prevDir = Direction.NONE;
            V2D prev = it.next();
            vertex.add(prev);
            while (it.hasNext()) {
                V2D next = it.next();
                //if prev direction and current are different, it means that
                // direction changed so "next" V2D is a vector
                Direction diffDir = V2DUtility.getDirection(next.diff(prev));
                if (prevDir != diffDir) {
                    vertex.add(next.diff(next.diff(prev)));
                    prevDir = diffDir;
                }
                prev = next;
            }
        }
        return vertex;
    }

    /**
     * Utility method to get direction from vector considering coordinate sign.
     * Example: (5,0) is {@link Direction#RIGHT}, (0, -4) is {@link Direction#UP}
     * @param vector {@link V2D} vector where extract direction.
     * @return corresponding {@link Direction}
     */
    public static Direction getDirection(final V2D vector) {
        V2D vec = vector.getSingVector();
        if (vec.equals(Direction.DOWN.getVector())) {
            return Direction.DOWN;
        } else if (vec.equals(Direction.UP.getVector())) {
            return Direction.UP;
        } else if (vec.equals(Direction.LEFT.getVector())) {
            return Direction.LEFT;
        } else if (vec.equals(Direction.RIGHT.getVector())) {
            return Direction.RIGHT;
        } else {
            return Direction.NONE;
        }
    }
}
