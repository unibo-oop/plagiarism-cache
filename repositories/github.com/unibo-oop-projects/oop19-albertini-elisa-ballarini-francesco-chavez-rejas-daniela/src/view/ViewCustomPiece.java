package view;

/**
 * Interface of the view of custom piece build part of the application.
 */
public interface ViewCustomPiece extends View {
    /**
     * Method that set the GUI to its initial status.
     */
    void start();

    /**
     * Method that shows a message dialog when the user tried to save an no-accepted
     * type of piece.
     */
    void incorrectTypePiece();

    /**
     * Method that shows a message dialog when the user tried to save an piece that
     * is already saved.
     */
    void alreayOnCustomPieceList();

    /**
     * Method that shows a message dialog to inform that the piece has been saved.
     */
    void savedPiece();

}
