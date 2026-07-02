package javagotchi.view.home;

import javafx.fxml.FXMLLoader;

/**
 * View Interface. 
 * 
 * @author elisa
 *
 */
public interface GameView {

    /** 
     * Method to set the FXML Loader.
     * @param loader the FXML Loader.
     */
    void setFXMLLoader(FXMLLoader loader);

    /**
     * Method to set the stage and the scene.
     */
    void setStageAndScene();

    /**
     * Method to initialize the home view.
     */
    void init();

    /**
     * Method to show the view.
     */
    void show();

}
