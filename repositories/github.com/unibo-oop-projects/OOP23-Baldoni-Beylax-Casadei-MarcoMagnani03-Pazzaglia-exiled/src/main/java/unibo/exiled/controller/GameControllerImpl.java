package unibo.exiled.controller;

import unibo.exiled.model.game.GameModel;

/**
 * The implementation of the game main controller.
 */
public final class GameControllerImpl implements GameController {

    private final MapController mapController;
    private final ItemsController itemsController;
    private final CharacterController characterController;
    private final CombatController combatController;

    /**
     * The constructor of the game main controller.
     *
     * @param model The game model to manage the game.
     */
    public GameControllerImpl(final GameModel model) {
        this.mapController = new MapControllerImpl(model.getMapModel());
        this.itemsController = new ItemsControllerImpl(model.getItemsModel());
        this.characterController = new CharacterControllerImpl(model.getCharacterModel());
        this.combatController = new CombatControllerImpl(model.getCombatModel());
    }

    @Override
    public MapController getMapController() {
        return this.mapController;
    }

    @Override
    public ItemsController getItemsController() {
        return this.itemsController;
    }

    @Override
    public CharacterController getCharacterController() {
        return this.characterController;
    }

    @Override
    public CombatController getCombatController() {
        return this.combatController;
    }

    @Override
    public boolean isOver() {
        return getCharacterController().getPlayerHealth() <= 0;
    }

    @Override
    public boolean isWon() {
        return getCharacterController().checkWin();
    }
}
