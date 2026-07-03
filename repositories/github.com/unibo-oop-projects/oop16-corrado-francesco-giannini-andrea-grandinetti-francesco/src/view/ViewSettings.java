package view;

import java.io.IOException;

import controller.Controller;
import controller.file.ViewFile;
import javafx.stage.Stage;

/**
 * Interface of view where there are all method for set the game.
 */
public interface ViewSettings {

    /**
     * Start menu of the game.
     * @param stage of application
     * @throws IOException 
     */
    void startMenu(Stage stage) throws IOException;

    /**
     * Set controller in view.
     * @param ctr controller of application
     */
    void setController(Controller ctr);
    /**
     * Receive a class that contains all the files the view needs.
     * @param fileContainer : class with all file  and data that the game need
     */
    void setViewFile(ViewFile fileContainer);

}
