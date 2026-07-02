package cubecontroller;

import cubestructure.Cube3X3;
import cubevisual.RubikCube3X3;

/**
 * Implementing the abstract class {@link CubeControllerAbstract}.
 * Dimension: 3X3X3.
 */
public class CubeController3X3 extends CubeControllerAbstract implements CubeController {

    /**
     * This constructor creates a CubeController by a fixed cubeDimensions and GameMode.
     * @param mode - A {@link GameMode}.
     */
    public CubeController3X3(final GameMode mode) {
        super(mode);

        setLogicCube(new Cube3X3());
        switch (mode) {
            case RANDOM:
                getLogicCube().setRandomCube();
                setVisualCube(new RubikCube3X3(getSize() / CubeDimensions.Cube3X3.getDimension(), getLogicCube()));
                setRotatable();
                break;
            case SOLVE:
                getLogicCube().setGivenCube();
                setVisualCube(new RubikCube3X3(getSize() / CubeDimensions.Cube3X3.getDimension(), getLogicCube()));
                setEditable();
                break;
            default:
                throw new RuntimeException("Unknown game mode: " + mode.toString());
        }
    }

}
