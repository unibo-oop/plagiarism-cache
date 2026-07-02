package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 * template class for application Scene.
 */
public abstract class AbstractScene extends Scene {

    public AbstractScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    /**
     * initialize scene data.
     */
    public abstract void initialize();

}
