package fargoal.controller.input.api;

import fargoal.model.manager.api.MenuManager;
import fargoal.model.manager.api.SceneManager;

/**
 * Class that work to receive and process the inputs
 * from the TitleScreen.
 */
public class MenuInputComponent implements InputComponent {

    /**
     * Method that updates the TitleScreen based on the input that receives.
     * 
     * @param sceneManager - the manager in which the input operates
     * @param ctrl - to get the inputs
     * @throws IllegalArgumentException if the given manager is not a {@link MenuManager}
     */
    @Override
    public void update(final SceneManager sceneManager, final InputController ctrl) {
        if (!(sceneManager instanceof MenuManager)) {
            throw new IllegalArgumentException("Component given to wrong manager");
        }
        final MenuManager manager = (MenuManager) sceneManager;
        if (ctrl.isInteracting()) {
            manager.select();
        } else if (ctrl.isMoveDown()) {
            manager.increaseSelected();
        } else if (ctrl.isMoveUp()) {
            manager.decreaseSelected();
        }
    }
}
