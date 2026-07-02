package controller.Input;

import javafx.scene.Scene;
import model.world_state.World;

public interface KeyListenerController {
    /**
     * 
     * @param world
     *      Used to notify the inputs
     *
     * @param scene
     *      Where to activate the keyListener
     *
     */
    void initialize(World world, Scene scene);
}
