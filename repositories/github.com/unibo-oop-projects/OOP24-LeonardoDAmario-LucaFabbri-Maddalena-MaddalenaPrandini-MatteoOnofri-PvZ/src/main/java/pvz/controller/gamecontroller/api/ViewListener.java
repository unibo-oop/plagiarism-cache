package pvz.controller.gamecontroller.api;

import pvz.utilities.Position;

/**
 * The {@code ViewListener} interface defines callbacks for user interaction from the game view.
 * It allows communication from the UI to the controller by handling events such as plant selection and grid clicks.
 */
public interface ViewListener {

    /**
     * Enum representing the available plant types a user can select.
     */
    enum UserInputRoaster {
        /** Represents the Peashooter plant selection. */
        PEASHOOTER,
        /** Represents the Sunflower plant selection. */
        SUNFLOWER,
        /** Represents the Wall-nut plant selection. */
        WALLNUT
    }

    /**
     * Handles user selection of a plant type from the roaster.
     *
     * @param input the selected plant type
     */
    void processInputRoaster(UserInputRoaster input);

    /**
     * Handles user input when a grid cell is selected.
     *
     * @param position the position clicked on the game grid
     */
    void processInputGrid(Position position);

}
