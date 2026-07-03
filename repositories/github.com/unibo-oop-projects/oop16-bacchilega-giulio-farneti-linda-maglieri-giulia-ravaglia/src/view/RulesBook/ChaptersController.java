package view.RulesBook;

import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import controller.RulesBook.BookControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.Utilities.Utilities;

/**
 * Class that allows you to move through the chapters of the rule book
 * 
 * Author: Linda Farneti.
 *
 */
public class ChaptersController implements ChaptersInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    private List<Button> buttons = new ArrayList<>(); 
    private Map<String, Integer> map = new HashMap<>();

    @FXML
    private void initialize() {
        for (int i = 0; i < Utilities.CHAPTERS_TOT; i++) {
            Button btn = new Button();
            buttons.add(btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    try {
                        doSetPage(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            populateMap();
        }

        for (int i = 0; i < Utilities.IDX_CHAPTERS; i++) {
            assert "btn" + i != null : "fx:id=\"btn" + i + "\" was not injected check your FXML file 'Index.fxml'.";
        }

        for (int i = Utilities.IDX_CHAPTERS; i < Utilities.IDX_CHAPTWO + Utilities.IDX_CHAPTERS; i++) {
            assert "btn" + i != null : "fx:id=\"btn" + i + "\" was not injected check your FXML file 'Chapter2.fxml'.";
        }

        for (int i = Utilities.IDX_CHAPTWO + Utilities.IDX_CHAPTERS; i < Utilities.IDX_CHAPTWO + Utilities.IDX_CHAPTERS + Utilities.IDX_CHAPTHREE; i++) {
            assert "btn" + i != null : "fx:id=\"btn" + i + "\" was not injected check your FXML file 'Chapter3.fxml'.";
        }
    }

    private void populateMap() {
        map.put("1", 1);
        map.put("2", 2);
        map.put("2.1", 3);
        map.put("2.2", 4);
        map.put("2.3", 5);
        map.put("2.4", 6);
        map.put("2.5", 7);
        map.put("2.6", 8);
        map.put("3", 9);
        map.put("3.1", 10);
        map.put("3.2", 11);
        map.put("4", 12);
        map.put("5", 13);
        map.put("6", 14);
        map.put("6.1", 15);
        map.put("6.2", 16);
    }

    /**
     * Method that uses the name of the chapter to set the page.
     * 
     * @param event button pressed
     * @throws Exception if loading fails
     */
    @FXML
    public void doSetPage(final ActionEvent event) throws Exception {
        int n;
        String s = ((Button) event.getSource()).getText().substring(0, 3);
        if (s.endsWith(" ")) {
            s = s.substring(0, 1);
        }
        n = map.get(s);
        BookControl.getLog().setPage(n);
    }
}

