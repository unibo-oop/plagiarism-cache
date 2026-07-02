package migglione.view.api.scenes;

import javax.swing.JPanel;

import migglione.controller.api.Controller;
import migglione.view.impl.SwingViewImpl;

/**
 * Factory needed to create scenes for CardLayout more easily.
 */
@FunctionalInterface
public interface SceneFactory {
    /**
     * Method used to create the scenes of CardLayout.
     * 
     * @param view is the parent View from which CardLayout is initialized
     * @param scenes will be used as a parameter to decide what scene it to be created
     * @param controller is the controller of the application
     * @return a JPanel rapresenting the new Scene
     */
    JPanel createScene(SwingViewImpl view, Scenes scenes, Controller controller);
}
