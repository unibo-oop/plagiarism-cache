package tmw.view.menu;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;

/**
 * Interface to represent a generic element of the menu view.
 */
public interface GenericMenuView {

    /**
     * Method to initialize the view class.
     */
    void init();

    /**
     * @return the scene generated with the root previously received
     */
    Scene getScene();

    /**
     * @return the FXMLLoader which contains the fxml file of the view
     */
    FXMLLoader getLoader();

    /**
     * @return the minimum windows size
     */
    Dimension2D getMinSize();
}

