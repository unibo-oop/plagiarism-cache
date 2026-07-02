package view.dialog;

import java.util.Optional;

/**
 * This class offers an abstraction for other dialog classes.
 */
public abstract class AbstractDialog implements DialogLauncher {

    /**
     * Launch a dialog using the passed parameters.
     * Parameters you don't care about can be passed as null.
     * @param type - the type of dialog to launch.
     * @param title - dialog's title
     * @param header - dialog's header
     * @param description - dialog's description
     * @return the result of the dialog's operations, if any.
     */
    protected abstract Optional<String> launch(DialogType type, String title, String header, String description);

}
