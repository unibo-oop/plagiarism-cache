package utilities.enumerations;

/**
 * Enumeration of game boards.
 */
public enum BoardType {

    /**
     * Default game board.
     */
    DEFAULT_BOARD(19, 19, "/boards/defaultBoard.txt");

    private final int height;

    private final int width;

    private final String boardInitFile;

    BoardType(final int height, final int width, final String boardInitFile) {
        this.height = height;
        this.width = width;
        this.boardInitFile = boardInitFile;
    }

    /**
     * Returns the height of the game board.
     * 
     * @return the height of the game board
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the width of the game board.
     * 
     * @return the width of the game board
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the path for the game board initialization file.
     * 
     * @return the path for the game board initialization file
     */
    public String getBoardInitFile() {
        return this.boardInitFile;
    }
}