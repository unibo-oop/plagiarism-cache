package boxhead.view;

import java.util.Optional;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Method that permits to create Scenes and swap between them.
 */
public interface SceneSwapper {

    /**
     * Create a new Scene and add it to the collection.
     * @param name
     * @param width
     * @param height
     * @param root
     */
    void buildScene(String name, int width, int height, Parent root);
    
    /**
     * Method to add the given scene to the collection.
     * @param name
     * @param scene
     */
    void addScene(String name, Scene scene);
    
    /**
     * Method to swap between Scenes.
     * @param name
     * @return boolean - True if the scene was successfull updated, False if for no given reason the swapping failed.
     */
    boolean swapTo(String name);
    
    /**
     * Method to get the current visible scene.
     */
    Scene getCurrentScene();
    
    /**
     * Method to get the stage associated with the swapper.
     * @return - mainStage
     */
    Stage getMainStage();
    
    /**
     * Method to load scenes from FXML files.
     * @param filename
     */
    void loadFromFile(String filename);
    
    /**
     * Method to get the Controller of a FXML generated scene.
     * @param filename
     * @return Optional<Initializable> - optional used because possibly an FXML could not have a controller associated.
     */
    Optional<Initializable> getFXMLController(String filename);
}
