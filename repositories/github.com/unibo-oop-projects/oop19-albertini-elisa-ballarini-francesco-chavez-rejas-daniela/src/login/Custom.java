package login;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import pair.Pair;

/**
 * Interface of a custom piece object.
 */
@JsonDeserialize(as = CustomImpl.class)
public interface Custom {

    /**
     * @return the RGB int that identifies the color of the custom piece.
     */
    int getColor();

    /**
     * @param newColor : the RGB int that identifies which color will be set for the
     *                 custom piece.
     */
    void setColor(int newColor);

    /**
     * @return the list of coordinates of the piece.
     */
    List<Pair<Integer, Integer>> getCoords();

    /**
     * @return the center of the piece.
     */
    Pair<Integer, Integer> getCenter();

    /**
     * @param coords : list of coordinates of which is composed the piece.
     */
    void setCoords(List<Pair<Integer, Integer>> coords);

    /**
     * @param center : the coordinates of the center of the piece.
     */
    void setCenter(Pair<Integer, Integer> center);
}
