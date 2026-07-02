package projection;

import java.awt.Color;
import java.util.Set;

import pair.Pair;

/**
 * This class has methods for the projection of the piece on the playground. The
 * projection represent the "final" position of the piece orthogonally to the
 * playground.
 */
public interface Projection {

    /**
     * Alpha for the projection color.
     */
    int TRANSPARENCY = 130;

    /**
     * Calculates the new Projection.
     * 
     * @return the coordinates of the projection of the piece on the playground. In
     *         case the projection is disabled it returns an empty set.
     */
    Set<Pair<Integer, Integer>> newProjection();

    /**
     * Calculates the color of the projection.
     * 
     * @return the color of the projection (the color is the same of the original
     *         piece but more transparent)
     */
    Color getColor();

    /**
     * Sets the projection enable or disable.
     * 
     * @param isEnabled specifies if the projection must be enabled or disabled.
     */
    void setEnable(boolean isEnabled);

    /**
     * Check if projection is enabled.
     * 
     * @return true if projection is enabled, otherwise it returns false.
     */
    boolean isEnabled();
}
