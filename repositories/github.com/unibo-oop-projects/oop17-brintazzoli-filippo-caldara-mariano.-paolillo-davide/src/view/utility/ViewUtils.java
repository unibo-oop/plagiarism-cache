package view.utility;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * Implementation of utility methods for the {@link View}.
 *
 */
public final class ViewUtils {

    private static Stage currentStage;

    private ViewUtils() { }

    /**
     * This method allows to know the initial stage of the application.
     * 
     * @param stage
     *            the actual stage.
     */
    public static void setPrimaryStage(final Stage stage) {
        currentStage = stage;
    }

    /**
     * This method allows to get the current stage.
     * 
     * @return the current stage.
     */
    public static Stage getStage() {
        return currentStage;
    }

    /**
     * This method allows to get the current scene.
     * 
     * @return the current scene.
     */
    public static Scene getScene() {
        return currentStage.getScene();
    }

}
