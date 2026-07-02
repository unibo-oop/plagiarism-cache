package fargoal.model.map.api;

import fargoal.commons.api.Position;
/**
 * An interface that models the map of the floor.
 */
public interface FloorMap {

    /**
     * returns whether or not the given position is a walkable tile or not.
     * 
     * @param pos - the position to check
     * @return {@code true} if the inserted positon is a tile, {@code false} otherwise
     */
    boolean isTile(Position pos);

    /**
     * Method to obtain the dimension of the floor.
     * 
     * @return - the size of the floor {@link Dimension}
     */
    Dimension getSize();

    /**
     * A method that returns a random tile of the map.
     * @return - the randomly selected tile {@link Position}
     */
    Position getRandomTile();

    /**
     * A method that renders the given position.
     * @param pos - the position to be rendered
     */
    void render(Position pos);
}
