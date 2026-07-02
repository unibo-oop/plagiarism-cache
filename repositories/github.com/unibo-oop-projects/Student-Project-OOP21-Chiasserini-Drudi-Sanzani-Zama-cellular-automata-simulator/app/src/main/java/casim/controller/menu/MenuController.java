package casim.controller.menu;

import java.util.List;

import casim.model.Automata;

/**
 * Interface for the menu controller.
 *
 */
public interface MenuController {
    /**
     * Get the list of the automatons.
     * 
     * @return the list of the automatons.
     */
    List<Automata> getMenuItems();
}
