package cubevisual;

import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import movestructure.Face;

/**
 * This code models a group of {@link Cube} structured like a Rubik Cube, as the name points to.
 */
public interface RubikCube {

    /**
     * Group of cubes getter ("Rubik Cube getter").
     * @return The Rubik Cube ad a JavaFX Group.
     */
    Group getCubeGroup();

    /**
     * Map of cubes getter.
     * @return A Map (ID to Cube).
     */
    Map<Integer, Cube> getCubeMap();

    /**
     * Map of centers color getter.
     * @return A Map (Group (Cube) to Face (contains color)).
     */
    Map<Group, Face> getCentersColorMap();

    /**
     * Map of centers getter.
     * @return A Map (ID to Group (cube)).
     */
    Map<Integer, Group> getCentersMap();

    /**
     * Retrieve a face of cubes by its IDs.
     * @param ids - Face's id list.
     * @return A face as a JavaFX cube Group.
     */
    Group getRotationGroup(List<Integer> ids);

    /**
     * Retrieve a cube by its ID.
     * @param id - Cube's ID.
     * @return A cube as a JavaFX Group.
     */
    Group getSingleCube(int id);

}
