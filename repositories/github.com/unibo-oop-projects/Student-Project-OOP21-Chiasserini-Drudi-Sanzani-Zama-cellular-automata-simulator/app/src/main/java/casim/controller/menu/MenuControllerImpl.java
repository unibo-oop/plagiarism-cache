package casim.controller.menu;

import java.util.Arrays;
import java.util.List;

import casim.model.Automata;

/**
 * Implementation of {@link MenuController}.
 *
 */
public class MenuControllerImpl implements MenuController {

    @Override
    public List<Automata> getMenuItems() {
        return Arrays.asList(Automata.values());
    }

}
