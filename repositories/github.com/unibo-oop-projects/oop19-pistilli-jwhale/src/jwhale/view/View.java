package jwhale.view;

import javafx.stage.Stage;
/**
 * View interface.
 *
 */
public interface View {
    /**
     * Launch application's view.
     */
    void launch();
    /**
     * Load a new scene.
     * @param scene
     *          fxml path.
     */
    void loadScene(AppScene scene);
    /**
     * Get primary stage for window operations.
     * @return
     *          primary stage.
     */
    Stage getStage();

}
