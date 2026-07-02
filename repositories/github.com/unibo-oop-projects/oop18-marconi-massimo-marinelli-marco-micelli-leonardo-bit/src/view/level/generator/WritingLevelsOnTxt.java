package view.level.generator;

/**
 * Models the class that deals with writing on files.
 */
public interface WritingLevelsOnTxt {

    /**
     * This method writes the matrix into the txt called with the name of the level.
     * 
     * @param level
     *                  level name.
     * @param matrix
     *                  a char matrix to be parsed into the game map.
     */
    void writeOnTxt(Integer level, char[][] matrix);
}
