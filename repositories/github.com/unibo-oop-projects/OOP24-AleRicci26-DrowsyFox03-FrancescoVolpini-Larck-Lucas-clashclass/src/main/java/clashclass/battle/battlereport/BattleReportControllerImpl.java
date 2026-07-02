package clashclass.battle.battlereport;

import clashclass.ecs.GameObject;
import clashclass.gamestate.GameStateManager;
import clashclass.resources.ResourceManager;


/**
 * Implementation of the BattleReportController interface.
 * Coordinates between the model and view components of the battle report.
 */
public class BattleReportControllerImpl implements BattleReportController {
    private final BattleReportModel model;
    private final BattleReportView view;

    /**
     * Constructor that takes a model and view to coordinate.
     *
     * @param model The BattleReportModel to update
     * @param view The BattleReportView to update
     */
        public BattleReportControllerImpl(final BattleReportModel model, final BattleReportView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);

        // Initialize the view with the current model data
        updateView();
    }

    /**
     * {@inheritDoc}
     * Increases the destruction percentage in the model and updates the view.
     */
    @Override
    public void increaseDestructionPercentage(final GameObject destroyedBuilding) {
        model.increaseDestructionPercentage(destroyedBuilding);
        updateView();
    }

    /**
     * {@inheritDoc}
     * Adds resources to the stolen resources in the model and updates the view.
     */
    @Override
    public void increaseStolenResources(final ResourceManager resourceManager) {
        model.addStolenResources(resourceManager);
        updateView();
    }

    /**
     * Updates the view with the current model data.
     */
    private void updateView() {
        view.update(model);
    }

    /**
     * Notifies that the Town Hall has been destroyed.
     * This is a convenience method not in the interface but useful for game logic.
     */
    public void notifyTownHallDestroyed() {
        model.setTownHallDestroyed(true);
        updateView();
    }

    /**
     * Gets the current destruction percentage.
     * This is a convenience method not in the interface but useful for game logic.
     *
     * @return The current destruction percentage
     */
    @Override
    public double getDestructionPercentage() {
        return model.getDestructionPercentage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.view.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.view.clearScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.model.setGameStateManager(gameStateManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goBackToVillage() {
        this.model.getGameStateManager().setStatePlayerVillage();
    }

    /**
     * Gets the number of stars earned in the battle.
     * This is a convenience method not in the interface but useful for game logic.
     *
     * @return The number of stars (0-3)
     */
    public int getStars() {
        return model.getStars();
    }

    /**
     * Checks if the battle is a victory (at least 1 star).
     * This is a convenience method not in the interface but useful for game logic.
     *
     * @return true if the battle is a victory, false otherwise
     */
    public boolean isVictory() {
        return model.isVictory();
    }

    /**
     * {@inheritDoc}
     * Sets the total number of troops used in the battle and updates the view.
     */
    @Override
    public void setTroopCount(final int count) {
        model.setTroopCount(count);
        updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTroopCount() {
        return this.model.getTroopCount();
    }
}
