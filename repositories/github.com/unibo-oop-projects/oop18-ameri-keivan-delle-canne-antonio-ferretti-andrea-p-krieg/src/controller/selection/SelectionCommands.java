package controller.selection;

import java.util.Optional;

import javafx.util.Pair;
import model.objects.GameObject;
import util.Coordinates;

/**
 * handles a type of input based on selecting the cells on the map.
 */
public interface SelectionCommands {

    /**
     * selects an object on a cell in according to the previous selection.
     * 
     * @param cords the position to select
     */
    void selectPosition(Coordinates cords);

    /**
     * @param cords .
     * @return the selection of the case
     */
    Optional<Selection> getCaseSelection(Coordinates cords);

    /**
     * 
     * @return the actually selected entity if any TODO add coordinates
     */
    Optional<Pair<Coordinates, GameObject>> getActualSelection();

    /**
     * selects the chosen object in the specified position regardless of the actual
     * selection.
     * 
     * @param cords     the position to select
     * @param selection the object to select
     */
    void select(Coordinates cords, GameObjectSelection selection);

    /**
     * sets the actual selection to empty.
     */
    void resetSelection();
}
