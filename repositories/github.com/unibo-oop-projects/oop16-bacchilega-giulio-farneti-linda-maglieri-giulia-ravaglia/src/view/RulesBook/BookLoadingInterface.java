package view.RulesBook;

import javafx.stage.Stage;

/**
 * Interface of rule book
 * 
 * Author: Linda Farneti.
 *
 */
public interface BookLoadingInterface {
 
    /**
     * Initializes the stage.
     * 
     * @param primaryStage stage
     */
    void start(Stage primaryStage);

    /**
     * Opens the required chapters.
     * 
     * @param chapterNum to open
     */
    void openChapter(int chapterNum);

    /**
     * Returns the main stage.
     * @return primaryStage
     */
    Stage getPrimaryStage();

}
