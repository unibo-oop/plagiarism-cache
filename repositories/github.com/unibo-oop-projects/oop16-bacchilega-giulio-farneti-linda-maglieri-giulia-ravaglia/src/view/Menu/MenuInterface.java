package view.Menu;

import javafx.stage.Stage;

/**
 * 
 * Author: Linda Farneti.
 *
 */
public interface MenuInterface {

    /**
     * Method that initialize the stage.
     * 
     * @param primaryStage to initialize
     */
    void start(Stage primaryStage);

    /**
     * Method that return the primaryStage.
     * 
     * @return stage
     */
    Stage getPrimaryStage();

}
