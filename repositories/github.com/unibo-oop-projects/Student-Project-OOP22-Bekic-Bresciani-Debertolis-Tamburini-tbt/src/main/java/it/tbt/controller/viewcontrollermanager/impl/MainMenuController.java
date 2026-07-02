package it.tbt.controller.viewcontrollermanager.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.model.command.api.Command;
import it.tbt.model.menu.api.MenuButton;
import it.tbt.model.menu.api.MenuSelect;
import java.awt.event.KeyEvent;

/**
 * The {@code MainMenuController} class represents the view controller for the main menu state.
 * It handles user input and triggers actions associated with the main menu state.
 */
public class MainMenuController extends AbstractViewController {

    private final MenuState modelState;

    /**
     * Constructs a new {@code MainMenuController} with the specified menu state.
     *
     * @param menuStateImpl the menu state implementation
     */
    public MainMenuController(final MenuState menuStateImpl) {
        super();
        this.modelState = menuStateImpl;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
            value = "Dm",
            justification = "Menu needs to kill the application"
    )
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> this.addCommand(() -> modelState.previousElement());
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> this.addCommand(() -> modelState.nextElement());
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> {
                if (modelState.getItems().get(modelState.getFocus()) instanceof MenuButton) {
                    this.addCommand(((MenuButton) modelState.getItems().get(modelState.getFocus())).getAction());
                }
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                if (modelState.getItems().get(modelState.getFocus()) instanceof MenuSelect<?>) {
                    this.addCommand(((MenuSelect) modelState.getItems().get(modelState.getFocus())).nextOption());
                }
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                if (modelState.getItems().get(modelState.getFocus()) instanceof MenuSelect<?>) {
                    this.addCommand(((MenuSelect) modelState.getItems().get(modelState.getFocus())).previousOption());
                }
            }
            case KeyEvent.VK_ESCAPE -> this.addCommand(new Command() {
                @SuppressFBWarnings(
                        value = "Dm",
                        justification = "Menu needs to kill the application"
                )
                @Override
                public void execute() {
                    System.exit(0);
                }
            });
            default -> { }
        }
    }
}
