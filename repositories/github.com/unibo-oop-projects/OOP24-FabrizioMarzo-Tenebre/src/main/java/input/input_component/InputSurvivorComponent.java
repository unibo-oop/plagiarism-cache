package input.input_component;

import input.input_controller.InputController;
import model.entities.survivor.Survivor;

/**
 * Interface for components that handle input processing for a {@link Survivor}.
 * 
 * <p>Implementations of this interface are responsible for updating a Survivor
 * based on input received through an {@link InputController}.</p>
 */
public interface InputSurvivorComponent {

    /**
     * Updates the given {@link Survivor} using the provided {@link InputController}.
     *
     * @param sur the {@link Survivor} to be updated
     * @param c the {@link InputController} providing the input data
     * 
     * <p>This method is intended to be called regularly, typically once per update cycle,
     * to apply input-driven behavior to the Survivor.</p>
     */
    void update(final Survivor sur,final InputController c);
}
