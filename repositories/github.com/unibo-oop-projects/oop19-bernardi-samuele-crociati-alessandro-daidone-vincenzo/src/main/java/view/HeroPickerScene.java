package view;

import java.util.List;

import javafx.scene.Parent;
import javafx.scene.Scene;
import uicontrollers.HeroPickerController;

/**
 * Implementation of the scene where the player creates the team of heroes.
 * 
 */
public class HeroPickerScene extends Scene {

    /**
     * 
     * @param root
     *            the parent used to load the window
     * @param heroPool
     *            the list of the paths of the hero images
     * @param hpc
     *            the controller of the scene
     * @param initialPlayerTurn
     *            the initial player turn
     */
    public HeroPickerScene(final Parent root, final List<String> heroPool, final HeroPickerController hpc,
            final String initialPlayerTurn) {
        super(root);
        hpc.initData(heroPool, hpc, initialPlayerTurn);
    }
}
