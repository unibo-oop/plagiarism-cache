package it.tbt.controller.modelmanager;

import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import it.tbt.controller.modelmanager.transitionmanager.impl.TransitionManagerImpl;
import it.tbt.model.GameState;
import it.tbt.model.menu.impl.MenuModelImpl;
import it.tbt.model.party.IParty;
import it.tbt.model.world.api.World;

/**
 * Default implementation of the GameStateManager class for the management of the logic aspect of the application.
 */
public final class GameStateManager implements IGameStateManager {

    private final TransitionManager transitionManager;

    /**
     * @param world world object used for the game
     * @param party party object for the player
     * @param mainMenu mainMenu object for the mainMenu state
     * @param pauseMenu pauseMenu object for the pause state
     */
    public GameStateManager(final World world, final IParty party, final MenuModelImpl mainMenu,
            final MenuModelImpl pauseMenu) {
        this.transitionManager = new TransitionManagerImpl(world, party, mainMenu, pauseMenu);
        this.transitionManager.init();
        party.setCurrentRoom(world.getStartRoom().get());
        transitionManager.onMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelState getStateModel() {
        return this.transitionManager.getCurrentModelState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        return this.transitionManager.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean hasStateChanged() {
        return this.transitionManager.hasStateChanged();
    }

}
