package it.oop.project.view;

import java.awt.Color;

/**
 * Background and font color for a component of 2048 view.
 *
 */
public interface GameColor {

    /**
     * Returns background color for this component.
     * 
     * @return background color for this component.
     */
    Color getBgColor();

    /**
     * Returns font color for this component.
     * 
     * @return font color for this component.
     */
    Color getFontColor();

}
