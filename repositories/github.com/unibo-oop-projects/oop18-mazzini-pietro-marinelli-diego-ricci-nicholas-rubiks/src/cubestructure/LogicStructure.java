package cubestructure;

/**
 * this code craft an array 3x3 of {@link Cube} Object (Cube[][][]).
 */
public interface LogicStructure {
    /**
     * Method that craft an already solved RubikCube thru use of SideUtils class.
     */
    void setCompletedCube();
    /**
     * Method that craft and shuffle a Completed cube thru a Random use of MoveUtils class.
     */
    void setRandomCube();
    /**
     * Method that set the color of the Central {@link Cube} of every face of the RubikCube.
     */
    void setGivenCube();
    /**
     * Return a reference of the array of {@link Cube}. 
     * @return Cube[][][]
     */
    Cube[][][] getRubikCube();
    /**
     * Return a reference of RubikCube.
     * @return {@link Cube3X3}
     */
    Cube3X3 getCube3X3();
    /**
     * Return a copy of the array of {@link Cube}.
     * @return Cube[][][]
     */
    Cube3X3 getCopyOf();
}
