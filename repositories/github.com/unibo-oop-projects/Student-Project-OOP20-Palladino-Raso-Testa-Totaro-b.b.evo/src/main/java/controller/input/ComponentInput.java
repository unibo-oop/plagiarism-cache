package controller.input;

import model.entities.GameObject;

/**
 * Interface that updates the gameObject based on user input.
 */
public interface ComponentInput {

    /**
     * Update input component of selected entity.
     * @param obj to update
     * @param controlInput input control via an input device
     */
    void update(GameObject obj, ControllerInput controlInput);
}
