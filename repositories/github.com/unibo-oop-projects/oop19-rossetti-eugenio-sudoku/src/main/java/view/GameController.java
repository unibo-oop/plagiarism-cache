package view;

import java.util.List;

import com.google.common.base.Optional;

import javafx.util.Pair;

/**
 * 
 * Interface for GameController.
 *
 */

public interface GameController extends SceneController {

    /**
     * Update the game view.
     * @param list of values for the grid
     */
    void viewUpdate(List<Pair<Optional<Integer>, Boolean>> list);

}
