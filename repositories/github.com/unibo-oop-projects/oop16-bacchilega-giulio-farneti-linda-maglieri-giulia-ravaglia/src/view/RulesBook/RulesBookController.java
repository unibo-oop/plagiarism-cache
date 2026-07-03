package view.RulesBook;

import java.net.URL;
import java.util.ResourceBundle;

import controller.RulesBook.BookControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Class that implements functions of rule book's base
 * 
 * Author: Linda Farneti.
 *
 */
public class RulesBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button previousPage;

    @FXML
    private Button nextPage;

    @FXML
    private Button returnIndex;

    @FXML
    private Button returnGame;

    @FXML
    private void initialize() {
        assert previousPage != null : "fx:id=\"previousPage\" was not injected: check your FXML file 'RulesBook.fxml'.";
        assert nextPage != null : "fx:id=\"nextPage\" was not injected: check your FXML file 'RulesBook.fxml'."; 
        assert returnIndex != null : "fx:id=\"returnIndex\" was not injected: check your FXML file 'RulesBook.fxml'.";
        assert returnGame != null : "fx:id=\"returnGame\" was not injected: check your FXML file 'RulesBook.fxml'."; 
    }

    @FXML
    private void doButtonNext(final ActionEvent event) {
        BookControl.getLog().nextPage();
    }

    @FXML
    private void doButtonPrevious(final ActionEvent event) {
        BookControl.getLog().previousPage();
    }

    @FXML 
    private void openGame(final ActionEvent event) {
        RulesBookLoading.getLog().getPrimaryStage().close();
    }

    @FXML 
    private void openIndex(final ActionEvent event) {
        BookControl.getLog().setPage(0);
    }

}

