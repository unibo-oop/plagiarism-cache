package view.gameplay;

import model.basic_component.StaticPoint2D;
import view.interfaces.BasicView;

/**
 * Interface for the gameplay ui.
 */
public interface GameplayUI extends BasicView {
    /**
     * Manages the interaction with the UI.
     * @param location is the location of the interaction
     */
    void interact(StaticPoint2D location);
}
