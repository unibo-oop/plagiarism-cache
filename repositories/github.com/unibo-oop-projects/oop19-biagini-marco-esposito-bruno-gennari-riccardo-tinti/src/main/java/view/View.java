package view;

import java.util.Optional;

import view.dialog.DialogType;
import view.scene.SceneName;

/**
 * The view of this application from an mvc perspective.
 */
public interface View {

    /**
     * Load a different scene into the application's stage.
     * @param name - the scene's name
     */
    void loadScene(SceneName name);

    /**
     * Launch a dialog using the passed parameters.
     * Parameters you don't care about can be passed as null.
     * @param type - the type of dialog to launch.
     * @param title - dialog's title
     * @param header - dialog's header
     * @param description - dialog's description
     * @return the result of the dialog's operations, if any.
     */
    Optional<String> launchDialog(DialogType type, String title, String header, String description);

}
