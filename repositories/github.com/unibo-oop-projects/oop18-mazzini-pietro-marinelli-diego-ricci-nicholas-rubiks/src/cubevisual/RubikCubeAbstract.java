package cubevisual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import movestructure.Face;

/**
 * Abstraction of {@link RubikCube}.
 * This class should work well for all the odds Rubik Cubes. 
 */
public abstract class RubikCubeAbstract implements RubikCube {

    private final Map<Group, Face> centersColorMap;
    private final Map<Integer, Group> centersMap;
    private final Map<Integer, Cube> cubeMap;
    private final Group cubeGroup;

    /**
     * This constructor initializes RubikCube variables.
     */
    public RubikCubeAbstract() {
        centersColorMap = new HashMap<Group, Face>();
        centersMap = new HashMap<Integer, Group>();
        cubeMap = new HashMap<Integer, Cube>();
        cubeGroup = new Group();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getCubeGroup() {
        return cubeGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Cube> getCubeMap() {
        return cubeMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Group, Face> getCentersColorMap() {
        return centersColorMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, Group> getCentersMap() {
        return centersMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getRotationGroup(final List<Integer> ids) {
        final Group rotationGroup = new Group();
        ids.forEach(e -> rotationGroup.getChildren().add(cubeMap.get(e).getCube()));
        return rotationGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group getSingleCube(final int id) {
        return cubeMap.get(id).getCube();
    }

}
