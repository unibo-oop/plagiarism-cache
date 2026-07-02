package zombieversity.controller.entities;

import zombieversity.model.entities.zombie.ZombieModel;
import zombieversity.view.entities.ZombieView;

/**
 * Interface to link ZombieModel and ZombieView.
 *
 */
public interface ZombieController {

    /**
     * Updates zombies model and view.
     */
    void update();

    /**
     * 
     * @return zombie model
     */
    ZombieModel getZombieModel();

    /**
     * 
     * @return zombie view
     */
    ZombieView getZombieView();

}
