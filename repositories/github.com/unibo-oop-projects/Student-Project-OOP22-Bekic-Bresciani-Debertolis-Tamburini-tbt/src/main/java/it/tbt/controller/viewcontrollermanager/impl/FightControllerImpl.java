package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.model.command.api.Command;

import java.awt.event.KeyEvent;

/**
 * Implementation of the {@link ViewController} interface.
 * This class handles user input during a fight and triggers corresponding
 * actions in the fight model.
 */
public final class FightControllerImpl extends AbstractViewController {

    private final FightState model;

    /**
     * Constructs a new instance of the {@link FightControllerImpl} class with the
     * specified {@link FightState}.
     *
     * @param model the {@link FightState} representing the fight state
     */
    /**
     * Constructs a new instance of the {@link FightControllerImpl} class with the
     * specified {@link FightState}.
     *
     * @param model the {@link FightState} representing the fight state
     */
    public FightControllerImpl(final FightState model) {
        super();
        if (model == null) {
            throw new IllegalArgumentException(
                    "è stato passato un argomento non lecito alla creazione di FightControllerImpl");
        }
        this.model = model;
        this.clean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                // controller.handlePreviousTarget();
                this.addCommand(new Command() {

                    @Override
                    public void execute() {
                        model.handlePreviousTarget();
                    }

                });
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                // controller.handleNextTarget();
                this.addCommand(new Command() {

                    @Override
                    public void execute() {
                        model.handleNextTarget();
                    }

                });
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                // controller.handleCycleAction(true);
                this.addCommand(new Command() {

                    @Override
                    public void execute() {
                        model.handleCycleAction(true);
                    }

                });
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                // controller.handleCycleAction(false);
                this.addCommand(new Command() {

                    @Override
                    public void execute() {
                        model.handleCycleAction(false);
                    }

                });
                break;
            case KeyEvent.VK_E:
            case KeyEvent.VK_ENTER:
                // controller.handlePerformAction();
                this.addCommand(new Command() {

                    @Override
                    public void execute() {
                        model.handlePerformAction();
                    }

                });
                break;
            default:
                break;
        }
    }
}
