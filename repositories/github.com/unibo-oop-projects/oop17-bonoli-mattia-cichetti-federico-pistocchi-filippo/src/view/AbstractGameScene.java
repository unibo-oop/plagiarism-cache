package view;

import javafx.scene.Parent;
/**
 * template class for game scene.
 */
public abstract class AbstractGameScene extends AbstractScene {

    public AbstractGameScene(Parent root, double width, double height) {
        super(root, width, height);
    }

    /**
     * empty implementation.
     */
    public void initialize() {    }

    /**
     * scene initialization with arguments.
     * @param args args to initialize scene.
     */
    public abstract void initialize(String... args);

}
