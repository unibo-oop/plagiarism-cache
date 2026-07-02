package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.model.command.explore.CommandInteract;
import it.tbt.model.command.explore.CommandInventory;
import it.tbt.model.command.explore.CommandMove;
import it.tbt.model.command.explore.CommandPause;
import it.tbt.model.world.interaction.InteractionTrigger;
import java.awt.event.KeyEvent;

/**
 * Default implementation of the Controller of the Explore state.
 */
public final class ExploreControllerImpl extends AbstractViewController {

    private static final int DEFAULT_MOVE_X = 5;
    private static final int DEFAULT_MOVE_Y = 5;
    private final ExploreState modelState;

    /**
     * @param exploreStateImpl the state on which the controller maps the input to the commands to the state.
     */
    public ExploreControllerImpl(final ExploreState exploreStateImpl) {
        super();
        this.modelState = exploreStateImpl;
    }

    /**
     * Moves the IParty in the X-axis by a positive amount.
     */
    private void moveRight() {
        this.addCommand(new CommandMove(this.modelState.getParty(), DEFAULT_MOVE_X, 0));
    }

    /**
     * Moves the IParty in the Y-axis by a positive amount.
     */
    private void moveDown() {
        this.addCommand(new CommandMove(this.modelState.getParty(), 0, DEFAULT_MOVE_Y));
    }

    /**
     * Moves the IParty in the Y-axis by a negative amount.
     */
    private void moveUp() {
        this.addCommand(new CommandMove(this.modelState.getParty(), 0, -DEFAULT_MOVE_Y));
    }

    /**
     * Moves the IParty in the X-axis by a negative amount.
     */
    private void moveLeft() {
        this.addCommand(new CommandMove(this.modelState.getParty(), -DEFAULT_MOVE_X, 0));
    }

    /**
     * The IParty interacts with the environment.
     */
    private void interactWithProximity() {
        if (this.modelState.getParty() instanceof InteractionTrigger) {
            this.addCommand(new CommandInteract((InteractionTrigger) this.modelState.getParty()));
        }
    }

    /**
     * Triggers the {@link it.tbt.model.GameState#PAUSE} GameState.
     */
    private void triggerPause() {
        this.addCommand(new CommandPause(this.modelState.getTriggerPause()));
    }

    /**
     * Trigger the {@link it.tbt.model.GameState#INVENTORY} GameState.
     */
    private void triggerInventory() {
        this.addCommand(new CommandInventory(this.modelState.getTriggerInventory()));
    }

    @Override
    public void onKeyPressed(final int key) {
        if (key == KeyEvent.VK_D) {
            this.moveRight();
        } else if (key == KeyEvent.VK_W) {
            this.moveUp();
        } else if (key == KeyEvent.VK_A) {
            this.moveLeft();
        } else if (key == KeyEvent.VK_S) {
            this.moveDown();
        } else if (key == KeyEvent.VK_E) {
            this.interactWithProximity();
        } else if (key == KeyEvent.VK_ESCAPE) {
            this.triggerPause();
        } else if (key == KeyEvent.VK_I) {
            this.triggerInventory();
        }
    }
}
