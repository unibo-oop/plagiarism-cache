package cubecontroller;

/**
 * CubeController factory - Created using Factory pattern.
 */
public class CubeFactory {

    /**
     * Main method of factory class, works just like any other factory.
     * Ask for an object and get it.
     * @param dim - The cube's dimension of the cube who will be get. 
     * @param mode - The game mode to be set in the cube get.
     * @return A {@link CubeController}, the factory result.
     */
    public CubeController getCube(final CubeDimensions dim, final GameMode mode) {
        // Here should be placed a switch to select the dimension of the cube and in the
        // main menu there should be a "dimension select", but we created just 3X3X3 rubik cube.
        if (dim.equals(CubeDimensions.Cube3X3)) {
            return new CubeController3X3(mode);
        }
        throw new RuntimeException("Unknown cube dimension: " + dim.toString());
    }

}
