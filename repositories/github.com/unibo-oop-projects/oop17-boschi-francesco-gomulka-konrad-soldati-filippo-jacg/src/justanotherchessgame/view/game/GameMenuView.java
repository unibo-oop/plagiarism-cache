package justanotherchessgame.view.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * Class used to create the small menu during a game.
 */
public class GameMenuView extends ListView<String> {
    private final ObservableList<String> menuItems;

    /**
     * Class constructor.
     */
    public GameMenuView() {
        menuItems = FXCollections.observableArrayList();
        initializeList();
    }

    /**
     * Function used to initialize the items of the menu list.
     */
    private void initializeList() {
        menuItems.add("Save");
        menuItems.add("Remove Sound");
        menuItems.add("Back");
        //set the type of the cells.
        this.setCellFactory(listview -> new ListCell<String>() {
            @Override
            protected void updateItem(final String name, final boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(name);
                }
            }
        });
        //binding listview to its elements
        this.setItems(menuItems);
    }
}
