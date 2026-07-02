package it.unibo.scat.view.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.components.CustomLabel;
import it.unibo.scat.view.menu.api.MenuPanelInterface;
import it.unibo.scat.view.util.Audio;
import it.unibo.scat.view.util.AudioManager;
import it.unibo.scat.view.util.AudioTrack;

/**
 * Panel that displays the main menu options and handles user interactions.
 */
@SuppressFBWarnings(value = { "SE_TRANSIENT_FIELD_NOT_RESTORED",
        "EI_EXPOSE_REP2" }, justification = "Component not intended for serialization;Reference intentionally shared")
public final class SettingsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int VERTICAL_GAP = 50;
    private final transient ViewActionsInterface viewInterface;
    private final transient MenuPanelInterface menuInterface;
    private final transient Audio effectSound;

    /**
     * Creates the settings panel and initializes all menu options.
     *
     * @param viewInterface interface used to trigger application-level actions
     * @param menuInterface interface used to switch menu screens
     */
    public SettingsPanel(final ViewActionsInterface viewInterface, final MenuPanelInterface menuInterface) {
        this.viewInterface = viewInterface;
        this.menuInterface = menuInterface;
        effectSound = new AudioManager();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initPlayLabel();
        initCreditsLabel();
        initLeaderboardLabel();
        initQuitGameLabel();
    }

    /**
     * Initializes the "Play" option and its mouse interactions.
     * Navigates to the username screen when selected.
     */
    private void initPlayLabel() {
        final CustomLabel playLabel = new CustomLabel("PLAY");
        playLabel.setAlignmentX(CENTER_ALIGNMENT);
        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showUsernamePanel();
                effectSound.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalGlue());
        add(playLabel);
    }

    /**
     * Initializes the "Scores" option and its mouse interactions.
     * Navigates to the leaderboard screen when selected.
     */
    private void initLeaderboardLabel() {
        final CustomLabel leaderboardLabel = new CustomLabel("SCORES");
        leaderboardLabel.setAlignmentX(CENTER_ALIGNMENT);
        leaderboardLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showLeaderboardPanel();
                effectSound.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalStrut(VERTICAL_GAP));
        add(leaderboardLabel);
    }

    /**
     * Initializes the "About" option and its mouse interactions.
     * Navigates to the credits screen when selected.
     */
    private void initCreditsLabel() {
        final CustomLabel creditsLabel = new CustomLabel("ABOUT");
        creditsLabel.setAlignmentX(CENTER_ALIGNMENT);
        creditsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showCreditsPanel();
                effectSound.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalStrut(VERTICAL_GAP));
        add(creditsLabel);
    }

    /**
     * Initializes the "Quit" option and its mouse interactions.
     * Triggers application shutdown when selected.
     */
    private void initQuitGameLabel() {
        final CustomLabel quitGameLabel = new CustomLabel("QUIT");
        quitGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        quitGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                viewInterface.quitGame();
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                effectSound.play(AudioTrack.MOUSE_OVER, false);
            }
        });

        add(Box.createVerticalStrut(VERTICAL_GAP));
        add(quitGameLabel);
        add(Box.createVerticalGlue());
    }

}
