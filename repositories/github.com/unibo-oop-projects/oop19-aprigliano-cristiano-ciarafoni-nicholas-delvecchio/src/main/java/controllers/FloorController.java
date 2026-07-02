package controllers;

import model.world.FloorModel;
import view.FloorView;

/**
 * This interface is used to control the floor object.
 */
public interface FloorController {
    /**
     * This function has to start the view of the floor object.
     */
    void startFloorView();

    /**
     * This function is used to get the View of the Floor object.
     * @return the view of the floor object
     */
    FloorView getFloorView();

    /**
     * This function is used to get the Model of the Floor.
     * @return the model of the floor object
     */
    FloorModel getFloorModel();
}
