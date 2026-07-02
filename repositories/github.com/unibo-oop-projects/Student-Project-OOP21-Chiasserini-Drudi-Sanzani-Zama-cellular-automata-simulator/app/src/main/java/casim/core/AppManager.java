package casim.core;

import casim.model.Automata;
import casim.ui.components.page.PageContainer;
import casim.utils.Empty;
import casim.utils.Result;
import casim.utils.automaton.config.BaseConfig;

/**
 * App manager that allows to display the correct view on screen.
 */
public interface AppManager {

    /**
     * Return the application container.
     * 
     * @return the application's {@link PageContainer}.
     */
    PageContainer getContainer();

    /**
     * Creates the main menu and shows it.
     */
    void showMainMenu();

    /**
     * Shows the right configuration menu for the chosen automaton.
     * 
     * @param automata the automaton to configure.
     * @return {@link Result} holding {@link Empty} if there is no error, an exception otherwise.
     */
    Result<Empty> showConfigMenu(Automata automata);

    /**
     * Shows the simulation view for the chosen automaton.
     * 
     * @param automata the automata to simulate.
     * @param config the configuration object.
     * @return {@link Result} holding {@link Empty} if there is no error, an exception otherwise.
     */
    Result<Empty> showSimulation(Automata automata, BaseConfig config);

}
