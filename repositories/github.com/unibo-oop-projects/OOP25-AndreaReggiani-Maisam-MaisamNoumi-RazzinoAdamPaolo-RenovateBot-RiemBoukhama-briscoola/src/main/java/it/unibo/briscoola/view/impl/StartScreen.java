package it.unibo.briscoola.view.impl;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.CardLayout;
import java.awt.Color;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.view.impl.menu.DifficultySelectionPanel;
import it.unibo.briscoola.view.impl.menu.MainMenu;
import it.unibo.briscoola.view.impl.menu.PlayerNamePanel;
import it.unibo.briscoola.view.impl.menu.RulesDialog;

import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

/**
 * Screen is the component that manages the configuration before starting the game.
 * Uses a {@link CardLayout} to change between the main menu, the player count selection,
 * and the difficulty selection panels.
 * 
 * @author Andrea Reggiani
 */
public final class StartScreen extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final String MAIN_MENU = "MAIN";
    private static final String NAME_SELECTION = "SELECTION";
    private static final String DIFFICULTY_SELECTION = "DIFFICULTY";

    private static final String DEFAULT_NAME = "player";
    private static final int BG_R = 30;
    private static final int BG_G = 100;
    private static final int BG_B = 72;

    private final CardLayout cardLayout;
    private String temporaryPlayerName = DEFAULT_NAME;

    /**
     * creates a new StartScreen.
     * 
     * @param onSetupComplete callback triggered when both players count and difficulty are choosen
     * @param onQuit callback triggered when the user wants to exit the application
     */
    public StartScreen(final BiConsumer<String, Difficulty> onSetupComplete, final ActionListener onQuit) {
        this.cardLayout = new CardLayout();
        this.initScreenLayout(onSetupComplete, onQuit);

    }

    private void initScreenLayout(final BiConsumer<String, Difficulty> onSetupComplete, final ActionListener onQuit) {
        this.setLayout(this.cardLayout);
        this.setBackground(new Color(BG_R, BG_G, BG_B));

        final JPanel mainMenu = new MainMenu(
            e -> cardLayout.show(this, NAME_SELECTION), 
            onQuit,
            e -> new RulesDialog(SwingUtilities.getWindowAncestor(this))
        );

        final JPanel nameSelection = new PlayerNamePanel(
            name -> {
                this.temporaryPlayerName = name;
                cardLayout.show(this, DIFFICULTY_SELECTION);
            },
            e -> cardLayout.show(this, MAIN_MENU)
        );

        final JPanel difficultySelection = new DifficultySelectionPanel(
            diff -> onSetupComplete.accept(this.temporaryPlayerName, diff),
            e -> cardLayout.show(this, NAME_SELECTION)
        );

        this.add(mainMenu, MAIN_MENU);
        this.add(nameSelection, NAME_SELECTION);
        this.add(difficultySelection, DIFFICULTY_SELECTION);
    }

    /**
     * Resets the internal sub-panels back to the very first step (Main Menu).
     */
    public void resetToMainMenu() {
        this.cardLayout.show(this, MAIN_MENU);
    }
}
