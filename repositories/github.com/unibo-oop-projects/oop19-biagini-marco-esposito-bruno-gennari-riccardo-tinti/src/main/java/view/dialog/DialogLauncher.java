package view.dialog;

import java.util.Optional;

/**
 * This interface offers a static method to launch dialogs.
 */
public interface DialogLauncher {

    /**
     * Launch a dialog using the passed parameters.
     * Parameters you don't care about can be passed as null.
     * @param type - the type of dialog to launch.
     * @param title - dialog's title
     * @param header - dialog's header
     * @param description - dialog's description
     * @return the result of the dialog's operations, if any.
     */
    static Optional<String> launchDialog(final DialogType type, final String title, final String header, final String description) {
        return type.getConcreteClass().launch(type, title, header, description);
    }
}
