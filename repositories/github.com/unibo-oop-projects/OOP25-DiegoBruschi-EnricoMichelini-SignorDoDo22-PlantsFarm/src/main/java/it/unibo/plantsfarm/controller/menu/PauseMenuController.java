package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.view.menu.api.PauseMenuScreen;
import it.unibo.plantsfarm.view.menu.impl.PauseMenuScreenImpl;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Manages the logic and user interactions for the Pause Menu, including game reset, 
 * command visualization, and exit procedures.
 */
public final class PauseMenuController {

    private static final Logger LOGGER = Logger.getLogger(PauseMenuController.class.getName());
    private static final String RESOURCE_PATH = "extraFiles/";

    private final PauseMenuScreen view;
    private final Runnable onCloseMainScreen;
    private final Runnable onGameReset;

    /**
     * Creates a new PauseMenuController.
     *
     * @param onCloseMainScreen The action to run when the game is exited entirely.
     * @param onGameReset       The action to run when the player confirms a reset.
     */
    public PauseMenuController(final Runnable onCloseMainScreen, final Runnable onGameReset) {
        this.view = new PauseMenuScreenImpl();
        this.onCloseMainScreen = onCloseMainScreen;
        this.onGameReset = onGameReset;
        setupListeners();
    }

    private void setupListeners() {
        this.view.setResumeButton(e -> this.view.close());

        this.view.setCommandsButton(e -> {
            final String commands = readResourceText("commands.txt");
            JOptionPane.showMessageDialog(null, commands, "Commands", JOptionPane.INFORMATION_MESSAGE);
        });

        this.view.setResetButton(e -> {
            final int choice = JOptionPane.showConfirmDialog(
                null,
                "Confirm reset of game data?",
                "Reset Game",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                if (onGameReset != null) {
                    onGameReset.run();
                }
                JOptionPane.showMessageDialog(null,
                    "Game data reset successfully.",
                    "Reset Complete",
                    JOptionPane.INFORMATION_MESSAGE);
                this.view.close();
                if (onCloseMainScreen != null) {
                    onCloseMainScreen.run();
                }
            }
            Runtime.getRuntime().exit(0);
        });

        this.view.setExtraButton(e -> {
            final String message = readResourceText("extra.txt");
            JOptionPane.showMessageDialog(null, message, "Extra Info", JOptionPane.INFORMATION_MESSAGE);
        });

        this.view.setCreditsButton(e -> {
            final String credits = readResourceText("credits.txt");
            JOptionPane.showMessageDialog(null, credits, "Credits", JOptionPane.INFORMATION_MESSAGE);
        });

        this.view.setExitButton(e -> {
            this.view.close();
            if (onCloseMainScreen != null) {
                onCloseMainScreen.run();
            }
            Runtime.getRuntime().exit(0);
        });
    }

    /**
     * Helper method to read text files from resources.
     *
     * @param fileName The name of the file inside resources/extraFiles/
     *
     * @return The content of the file or an error message.
     */
    private String readResourceText(final String fileName) {
        final String fullPath = RESOURCE_PATH + fileName;
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(fullPath);

        if (inputStream == null) {
            return "File not found: " + fullPath;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Error reading file: " + fileName, e);
            return "Error reading content of file: " + fileName;
        }
    }

    /**
     * Shows the pause menu.
     */
    public void start() {
        this.view.show();
    }
}
