package view;

import controller.SudokuGameHandler;

/**
 * 
 * Interface for SceneController.
 *
 */

public interface SceneController {

    /**
     * Set the handler.
     * @param handler
     */
    void setSudokuGameHandler(SudokuGameHandler handler);

    /**
     * Set the view.
     * @param view
     */
    void setView(View view);
}
