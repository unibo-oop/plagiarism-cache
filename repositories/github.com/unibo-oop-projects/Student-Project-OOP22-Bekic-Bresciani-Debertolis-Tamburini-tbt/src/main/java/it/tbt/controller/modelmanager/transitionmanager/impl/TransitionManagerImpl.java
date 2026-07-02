package it.tbt.controller.modelmanager.transitionmanager.impl;

import it.tbt.controller.modelmanager.InventoryStateImpl;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.EndStateImpl;
import it.tbt.controller.modelmanager.ExploreStateImpl;
import it.tbt.controller.modelmanager.FightStateImpl;
import it.tbt.controller.modelmanager.MenuStateImpl;
import it.tbt.controller.modelmanager.shop.ShopStateImpl;
import it.tbt.controller.modelmanager.transitionmanager.api.TransitionManager;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.GameState;
import it.tbt.model.entities.npc.api.ShopNPC;
import it.tbt.model.fight.api.FightModel;
import it.tbt.model.entities.npc.api.FightNPC;
import it.tbt.model.menu.impl.MenuModelImpl;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.InventoryTrigger;
import it.tbt.model.statechange.PauseTrigger;
import it.tbt.model.shop.Shop;
import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.api.World;
import java.util.Optional;

/**
 * Default implementation of a TransitionManager.
 */

public final class TransitionManagerImpl implements TransitionManager {

    private Optional<GameState> currentGameState;
    private final World world;
    private final IParty party;
    private final MenuModelImpl mainMenu;
    private final MenuModelImpl pauseMenu;
    private Optional<ModelState> currentModelState;
    private Boolean stateChanged = false;

    /**
     * @param world
     * @param party
     * @param mainMenu
     * @param pauseMenu
     */
    @SuppressFBWarnings(value = "EI2", justification = "This is the class which contains all the model,"
            + " and performs operations on them, so it should have their references.")
    public TransitionManagerImpl(final World world, final IParty party, final MenuModelImpl mainMenu,
            final MenuModelImpl pauseMenu) {
        this.world = world;
        this.party = party;
        this.mainMenu = mainMenu;
        this.pauseMenu = pauseMenu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        if (world == null || party == null) {
            throw new IllegalStateException("Null objects have been passed to the transition manager.");
        } else {
            this.startObserving();
        }
    }

    /**
     * Subscribes to model objects who can trigger a GameState change.
     */
    private void startObserving() {
        if (this.party instanceof StateTrigger) {
            ((StateTrigger) this.party).setStateObserver(this);
        }
        for (final var x : this.world.getListRoom()) {
            for (final var y : x.getEntities()) {
                if (y instanceof StateTrigger) {
                    ((StateTrigger) y).setStateObserver(this);
                    if (y instanceof FightNPC) {
                        (((FightNPC) y).getFightModel()).setStateObserver(this);
                    }
                    if (y instanceof ShopNPC) {
                        ((StateTrigger) ((ShopNPC) y).getShop()).setStateObserver(this);
                    }
                }
            }
        }
        pauseMenu.setStateObserver(this);
        for (final var x : this.mainMenu.getItems()) {
            if (x instanceof StateTrigger) {
                ((StateTrigger) x).setStateObserver(this);
            }
        }
        for (final var x : this.pauseMenu.getItems()) {
            if (x instanceof StateTrigger) {
                ((StateTrigger) x).setStateObserver(this);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getState() {
        if (this.currentGameState.isEmpty()) {
            throw new IllegalStateException("Game Transition Manager not initialized properly. GameState not present.");
        }
        return this.currentGameState.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelState getCurrentModelState() {
        if (this.currentModelState.isEmpty()) {
            throw new IllegalStateException(
                    "Game Transition Manager not initialized properly. ModelState not present.");
        }
        return this.currentModelState.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean hasStateChanged() {
        var x = false;
        if (this.stateChanged) {
            x = true;
            this.stateChanged = false;
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExplore() {
        stateUpdate(GameState.EXPLORE, new ExploreStateImpl(this.party.getCurrentRoom(),
                this.party,
                new PauseTrigger(this),
                new InventoryTrigger(this)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFight(final FightModel fightModel) {
        stateUpdate(GameState.FIGHT, new FightStateImpl(fightModel));
    }

    @Override
    public void onMenu() {
        stateUpdate(GameState.MENU, new MenuStateImpl(mainMenu));
    }

    @Override
    public void onPause() {
        stateUpdate(GameState.PAUSE, new MenuStateImpl(pauseMenu));
    }

    @Override
    public void onInventory() {
        stateUpdate(GameState.INVENTORY, new InventoryStateImpl(this.party));
        if (this.getCurrentModelState() instanceof StateTrigger) {
            ((StateTrigger) this.getCurrentModelState()).setStateObserver(this);
        }
    }

    @Override
    public void onShop(final Shop shop) {
        stateUpdate(GameState.SHOP, new ShopStateImpl(shop));
    }

    @Override
    public void onEnding(final String message) {
        stateUpdate(GameState.ENDING, new EndStateImpl(message));
        if (this.getCurrentModelState() instanceof StateTrigger) {
            ((StateTrigger) this.getCurrentModelState()).setStateObserver(this);
        }
    }

    private void stateUpdate(final GameState gameState, final ModelState modelState) {
        stateChanged = true;
        this.currentGameState = Optional.of(gameState);
        this.currentModelState = Optional.of(modelState);
    }

}
