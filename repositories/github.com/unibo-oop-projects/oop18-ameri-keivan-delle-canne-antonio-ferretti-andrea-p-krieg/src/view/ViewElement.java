package view;

import javafx.scene.layout.Region;

/**
 * Describes an object that returns a Region representing its state.
 */
public interface ViewElement {
    /**
     * 
     * @return the actual view
     */
    Region get();
}
