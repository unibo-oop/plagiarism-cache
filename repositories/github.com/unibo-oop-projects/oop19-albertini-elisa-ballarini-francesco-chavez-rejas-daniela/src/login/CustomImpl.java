package login;

import java.awt.Color;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import pair.Pair;

/**
 * Class that implements the interface {@link Custom}.
 * 
 * @see Custom
 */
public class CustomImpl implements Custom {

    private int color;
    private List<Pair<Integer, Integer>> coords;
    private Pair<Integer, Integer> center;

    /**
     * @param newColor : the color of the custom piece.
     * @param newCoords : the list of coordinates of the custom piece.
     * @param newCenter : the center coordinates of the custom piece.
     */
    public CustomImpl(final Color newColor, final List<Pair<Integer, Integer>> newCoords,
            final Pair<Integer, Integer> newCenter) {
        this.color = newColor.getRGB();
        this.coords = newCoords;
        this.center = newCenter;
    }

    /**
     * @param color : RGB color of the piece.
     * @param coords : list of coordinates of the piece.
     * @param center : center coordinates of the piece.
     */
    @JsonCreator
    public CustomImpl(@JsonProperty("color") final int color, @JsonProperty("coords") final List<Pair<Integer, Integer>> coords,
            @JsonProperty("center") final Pair<Integer, Integer> center) {
        this.color = color;
        this.coords = coords;
        this.center = center;
    }

    @Override
    public final int getColor() {
        return this.color;
    }

    @Override
    public final void setColor(final int color) {
        this.color = color;
    }

    @Override
    public final void setCoords(final List<Pair<Integer, Integer>> coords) {
        this.coords = coords;
    }

    @Override
    public final void setCenter(final Pair<Integer, Integer> center) {
        this.center = center;
    }

    @Override
    public final List<Pair<Integer, Integer>> getCoords() {
        return this.coords;
    }

    @Override
    public final Pair<Integer, Integer> getCenter() {
        return this.center;
    }

    @Override
    public final String toString() {
        return "Custom [color = " + this.color + ", coords= " + this.coords + ", center = " + this.center + "]";
    }
}
