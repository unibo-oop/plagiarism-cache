package fargoal.controller.input.api;

import fargoal.model.manager.api.SceneManager;

/**
 * Interface that update the position of FloorElements involved 
 * in changes based on inputs.
 */
public interface InputComponent {
    /**
     * Method that update the position/status of the FloorElements involved
     * in input's changes.
     * 
     * @param manager - the manager from which the component takes its information
     * @param c - to check which input to process
     */
    void update(SceneManager manager, InputController c);
}
