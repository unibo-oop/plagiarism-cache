package view.RulesBook;

import javafx.event.ActionEvent;

/**
 * Interface useful for setting a page in rule book
 * 
 * Author: Linda Farneti.
 *
 */
public interface ChaptersInterface {

    /**
     * Method that uses the name of the chapter to set the page.
     * 
     * @param event button pressed
     * @throws Exception if loading fails
     */
    void doSetPage(ActionEvent event) throws Exception;

}
