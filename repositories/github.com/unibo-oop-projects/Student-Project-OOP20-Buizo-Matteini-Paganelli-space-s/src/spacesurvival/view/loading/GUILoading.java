package spacesurvival.view.loading;

import spacesurvival.view.GUI;
import spacesurvival.view.utilities.GraphicsText;

/**
 * Interface that implements the loading functionalities.
 */
public interface GUILoading extends GUI, GraphicsText {

    /**
     * Set loading progress.
     * @param value set progess.
     */
    void setLoading(int value);

}
