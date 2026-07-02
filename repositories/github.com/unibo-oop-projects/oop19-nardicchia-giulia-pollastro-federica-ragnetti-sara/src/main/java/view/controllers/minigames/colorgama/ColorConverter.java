package view.controllers.minigames.colorgama;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Triple;

import javafx.scene.paint.Color;

/**
 * This interface represents a color converter from color values.
 *
 */
public interface ColorConverter {

    /**
     * Converts numerical values of colors to color.
     * 
     * @param values
     *          values of color
     * @return color
     *          a color with given values 
     */
    Optional<Color> convertToColor(Triple<Number, Number, Number> values);
}
