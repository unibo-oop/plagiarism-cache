package view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.basic_component.StaticPoint2D;
import model.basic_component.StaticPoint2DImpl;

/**
 * This class permits is used to manage a grid of button.
 */
public final class ButtonGridManager {

    private ButtonGridManager() {
    }

    /**
     * Builds the button grid, each {@link Button} is identified via it position.
     * @param xSize the x dimension
     * @param ySize the y dimension
     * @return a map that represent the buttons identified via their position
     */
    public static Map<StaticPoint2D, Button> buttonMap(final int xSize, final int ySize) {
        final Map<StaticPoint2D, Button> map = new HashMap<>();
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                final int xGrid = x;
                final int yGrid = y;
                final Button button = new Button();
                button.setPrefHeight(Integer.MAX_VALUE);
                button.setPrefWidth(Integer.MAX_VALUE);
                map.put(new StaticPoint2DImpl(xGrid, yGrid), button);
            }
        }
        return map;
    }

    /**
     * Load the "logical button" in a "real" {@link GridPane}.
     * @param buttonMap the "logical object"
     * @param gridPane is the showed object.
     */
    public static void associateWithUIGrid(final Map<StaticPoint2D, Button> buttonMap, final GridPane gridPane) {
        buttonMap.entrySet().forEach(element -> gridPane.add(element.getValue(), element.getKey().getX(), element.getKey().getY()));
    }
}
