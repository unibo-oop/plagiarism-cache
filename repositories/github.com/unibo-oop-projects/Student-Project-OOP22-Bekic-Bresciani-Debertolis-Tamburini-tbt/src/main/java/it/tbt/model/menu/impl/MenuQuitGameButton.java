package it.tbt.model.menu.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.command.api.Command;

/**
 * The {@code MenuQuitGameButton} class represents a menu button for quitting the game.
 * It extends the {@link AbstractMenuButton} class.
 */
public class MenuQuitGameButton extends AbstractMenuButton {
    /**
     * Creates a new instance of {@code MenuQuitGameButton} with the specified text.
     *
     * @param text the text of the button
     */

    public MenuQuitGameButton(final String text) {
        super(text);
    }
    /**
     * {@inheritDoc}
     */

    @SuppressFBWarnings(
            value = { "Dm" },
            justification = "The quit button have to close the app"
    )
    @Override
    public Command getAction() {
        return new Command() {
            @SuppressFBWarnings(
                    value = "Dm",
                    justification = "Menu Quit Game Button needs to kill the application"
            )
            @Override
            public void execute() {
                System.exit(0);
            }
        };
    }
}
