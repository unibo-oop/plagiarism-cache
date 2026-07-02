package levelgenerator;

import java.io.IOException;
import java.util.Map;

import util.Pair;

/**
 * Controller of the levelGenerator.
 */
public interface Controller {

    /**
     * It changes the selection with the param color.
     * 
     * @param state The state for selecting.
     */
    void selectState(States state);

    /**
     * Return the current state in a specific coordinate.
     * 
     * @param coordinate The coordinate to be checked.
     * @return the current state.
     */
    States state(Pair<Integer, Integer> coordinate);

    /**
     * Return the states of all coordinates.
     * 
     * @return a map with all states.
     */
    Map<Pair<Integer, Integer>, States> values();

    /**
     * Perform the touch in a specific coordinate. It performs state change.
     * 
     * @param coordinate to change state.
     */
    void touch(Pair<Integer, Integer> coordinate);

    /**
     * It wrote the JSON file.
     * 
     * @param name File name
     * @throws IOException If file isn't accessible.
     */
    void generateJSON(String name) throws IOException;
}
