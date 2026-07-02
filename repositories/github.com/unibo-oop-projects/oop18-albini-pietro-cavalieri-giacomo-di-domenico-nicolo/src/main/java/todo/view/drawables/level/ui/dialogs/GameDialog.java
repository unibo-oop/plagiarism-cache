package todo.view.drawables.level.ui.dialogs;

import java.util.Optional;

/**
 * This interface represents a dialog in the game.
 */
public interface GameDialog {
    /**
     * @return the user's last response when closing the dialog
     */
    Optional<DialogResponse> getResponse();

    /**
     * Show the dialog.
     */
    void show();
}
