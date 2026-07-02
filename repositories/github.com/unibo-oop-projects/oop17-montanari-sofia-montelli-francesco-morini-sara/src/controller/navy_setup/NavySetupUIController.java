package controller.navy_setup;

import java.util.List;

import controller.SpecificController;
import model.ship.Ship;

/**
 * Interface for the {@link NavySetupUI} user interface.
 */
public interface NavySetupUIController extends SpecificController {
    /**
     * Checks if the provided setting are valid or not.
     * @param sizeList
     *                  An integer list representing the ships's sizes
     * @param gridSize
     *                  The game grid dimension
     */
    void inputData(List<Integer> sizeList, int gridSize);

    /**
     * Getter for the max ship size.
     * @return 
     *          The max size of a ship
     */
    default int getShipSize() {
        return Ship.MAX_SHIP_SIZE;
    }

    /**
     * Setter for the information needed by {@link NavyBuilder}.
     * @param sizeList
     *                  An integer list representing the ships's sizes
     * @param gridSize
     *                  The game grid dimension
     */
    void setDimension(List<Integer> sizeList, int gridSize);
}
