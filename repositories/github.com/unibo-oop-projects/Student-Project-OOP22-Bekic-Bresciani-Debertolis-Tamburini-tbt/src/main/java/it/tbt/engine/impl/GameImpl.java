package it.tbt.engine.impl;

import it.tbt.model.menu.impl.MenuFactory;
import it.tbt.controller.modelmanager.GameStateManager;
import it.tbt.controller.modelmanager.IGameStateManager;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.controller.viewcontrollermanager.impl.GameViewManagerImpl;
import it.tbt.model.party.PartyFactory;
import it.tbt.model.world.impl.WorldFactory;
import it.tbt.view.api.GameViewFactory;
import it.tbt.engine.api.Game;

/**
 * Default implementation of the Game interface.
 */
public final class GameImpl implements Game {
    private final ViewControllerManager viewControllerManager;
    private final IGameStateManager gameStateManager;
    private Boolean initialized = false;

    /**
     * This implementation uses a ViewControllerManager and an GameStateManager as
     * helper classes to delegate responsibility.
     * 
     * @param gvf the GameViewFactory which is used to create views different based
     *            on the graphical framework chosen.
     *            This implementation uses a ViewControllerManager and an
     *            GameStateManager as
     *            helper classes to delegate responsibility.
     *            Creates both the World, the IParty and the Menus object with
     *            default
     *            implementations.
     */
    public GameImpl(final GameViewFactory gvf) {
        viewControllerManager = new GameViewManagerImpl(gvf);
        gameStateManager = new GameStateManager(WorldFactory.createWorldDefault(),
                PartyFactory.createDefaultParty(),
                MenuFactory.getMainMenu(),
                MenuFactory.getPauseMenu());
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void initialize() {
        this.viewControllerManager.renderView(
                this.gameStateManager.getState(),
                this.gameStateManager.getStateModel(),
                true);
        this.initialized = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        checkInit();
        this.viewControllerManager.renderView(
                this.gameStateManager.getState(),
                this.gameStateManager.getStateModel(),
                this.gameStateManager.hasStateChanged());
        this.gameStateManager.hasStateChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean handleInput() {
        checkInit();
        final Boolean r = this.viewControllerManager.getCommands().isEmpty();
        if (!r) {
            this.viewControllerManager.getCommands().get().stream().forEach(l -> l.execute());
            this.viewControllerManager.cleanCommands();
        }
        return !r;
    }

    /**
     * Checks if the game has been initialized. Throws an IllegalStateException if not.
     */
    private void checkInit() {
        if (!this.initialized) {
            throw new IllegalStateException("Game object has not been initialized.");
        }
    }

}
