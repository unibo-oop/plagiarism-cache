package justanotherchessgame.view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Class used to create the container of a menu.
 */
public abstract class MenuContainer extends VBox implements ResizableGraphicComponent {

    private final List<Line> list;

    /**
     * Class constructor.
     * @param items is the list of menu elements that will be added the the menu.
     */
    public MenuContainer(final MenuLine... items) {
        final int number = items.length;
        list = new ArrayList<Line>();
        //add one separator for each menuitem
        for (int i = 0; i < number; i++) {
            list.add(createSeperator());
        }
        //add the first separator
        list.add(createSeperator());
        //now add all the separator to the view
        getChildren().add(list.get(list.size() - 1));
        int index = 0;
        for (final MenuLine item : items) {
            getChildren().addAll(item, list.get(index++));
        }
    }
    /**
     * Function used to create a separator between each element of the menu.
     * @return a line used to separate menu items.
     */
    private Line createSeperator() {
        final Line sep = new Line();
        sep.setStroke(Color.rgb(188, 202, 224));
        sep.setStrokeWidth(2);
        return sep;
    }

    /**
     * Getter of the elements of the menu.
     * @return the list containing all the elements of the menu.
     */
    public List<Line> getList() {
        return this.list;
    }
}
