package powpaw.map.view.api;

import java.util.List;

import javafx.scene.layout.Pane;
import powpaw.map.model.impl.BlockImpl;

/**
 * Interface for MapRederImpl class that creates a pane and draws blocks on it
 * using a ImagePattern.
 * 
 * @author Giacomo Grassetti
 */
public interface MapRender {

    /**
     * Method that creates a Pane and draws blocks on it.
     * 
     * @return A Pane object.
     */
    Pane createPane();

    /**
     * Getters for an ArrayList of BlockImpl representing terrains.
     * 
     * @return An ArrayList of BlockImpl
     */
    List<BlockImpl> getTerrains();

}
