package view;

/**
 * Adapts all view components to make this application
 * available to multiple resolution.
 */
public interface AdaptableResolution {

    /**
     * Resizes and reposition all elements when the related FXML
     * file is loaded to adapt them to the resolution of the game.
     */
    void draw();
}
