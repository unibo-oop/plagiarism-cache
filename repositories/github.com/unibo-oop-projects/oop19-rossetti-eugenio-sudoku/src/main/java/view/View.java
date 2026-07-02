package view;

import java.util.List;
import com.google.common.base.Optional;
import javafx.util.Pair;
import utilities.Scenes;

public interface View {

    /**
     * Initialize the view.
     */
    void init();

    /**
     * Set the next scene.
     * @param nextScene
     */
    void setScene(Scenes nextScene);

    /**
     * Update the game board.
     * @param list of new values for the board
     */
    void boardUpdate(List<Pair<Optional<Integer>, Boolean>> list);
}
