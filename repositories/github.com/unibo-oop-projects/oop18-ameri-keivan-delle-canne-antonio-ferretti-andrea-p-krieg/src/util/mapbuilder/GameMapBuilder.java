package util.mapbuilder;

import util.Coordinates;

/**
 * it builds a map on top of a preset background, setting the variable layers.
 * @param <X> the type of the of the returned Map
 */
public interface GameMapBuilder<X> {

    /**
     * sets the top element of the case in position cords to id.
     * @param cords the position of the case
     * @param id to assign to the case
     * @return the builder
     */
    GameMapBuilder<X> setTop(Coordinates cords, String id);

    /**
     * sets the top element of the case in position cords to id.
     * @param cords the position of the case
     * @param id to assign to the case
     * @return the builder
     */
    GameMapBuilder<X> setBottom(Coordinates cords, String id);

    /**
     * sets the top element of the case in position cords to id.
     * @param cords the position of the case
     * @param id to assign to the case
     * @return the builder
     */
    GameMapBuilder<X> setBorder(Coordinates cords, String id);

    /**
     * the build method.
     * @return the view of the map.
     */
    X build();
}
