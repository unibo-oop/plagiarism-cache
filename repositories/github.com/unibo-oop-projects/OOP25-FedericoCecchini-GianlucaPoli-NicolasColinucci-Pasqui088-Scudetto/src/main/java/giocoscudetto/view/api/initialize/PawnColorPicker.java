package giocoscudetto.view.api.initialize;

import java.awt.Color;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Interface used to pick the color of the pawn for each club in the ClubPanel.
 * It is used to disable the colors already taken by other clubs and to get the selected color for each club.
 */
public interface PawnColorPicker {

    /**
     * This method disable the color selected from a team for other clubs.
     * 
     * @param takenByOthers set of color already taken.
     */
    void setTakenColors(Set<Color> takenByOthers);

    /**
     * @return the selected color.
     */
    Color getSelectedColor();

    /**
     * @return if the color of that color picker is selected.
     */
    boolean isColorSelected();

    /**
     * Reset used when clubs number changed in the ClubPanel.
     */
    void reset();

    /**
     * This method is used in ClubPanel to refresh the color taken when a color got picked.
     * 
     * @param callback .
     */
    void setOnColorChanged(Consumer<Color> callback);
}
