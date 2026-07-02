package zombieversity.view;


import java.util.Optional;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * Guidelines to develop a sceneSwapper, it's porpouse is to improve the swap of scenes and centralize all scenes needed.
 *
 */
public interface SceneSwapper {

    /**
     * Create and add to the collection of Scenes the new one.
     * @param name - identifier for the new scene, it will be it's reference.
     * @param w - width of the scene.
     * @param h - height of the scene.
     * @param root - the root of the scene.
     */
    void buildScene(String name, int w, int h, Parent root);
    /**
     * Method to add the given scene to the collection.
     * @param name - identifier of the scene.
     * @param scene - the value
     */
    void addScene(String name, Scene scene);
    /**
     * An unused or useless scene can be removed to improve search.
     * @param name
     */
    void removeScene(String name);
    /**
     * Method to change the current scene visible.
     * @param name - identifier of the wanted scene.
     * @return boolean - True if the scene was successfull updated, False if for no given reason the swapping failed.
     */
    boolean swapTo(String name);
    /**
     * Method to get the current visible scene.
     * @return Scene - current.
     */
    Scene getCurrent();
    /**
     * Method to get the stage associated with the swapper.
     * @return Stage
     */
    Stage getMain();
    /**
     * Method to load scenes from FXML files.
     * @param filename - only the name without the extension.
     * @return boolean - if loading occurs.
     */
    boolean loadFromFile(String filename);
    /**
     * Method to get the Controller of a FXML generated scene.
     * @param filename - identifier of the wanted scene.
     * @return Optional<Initializable> - optional used because possibly an FXML could not have a controller associated.
     */
    Optional<Initializable> getFXMLController(String filename);
    /**
     * Method to swap specifically to a FXML generated scene.
     * @param name - identifier of the scene wanted.
     */
    void swapToFXML(String name);
    /**
     * On top of the current scene could be loaded a SubScene with this method.
     * @param name - of the subscene(could be a scene used as a subscene).
     */
    void addSubScene(String name);

}
