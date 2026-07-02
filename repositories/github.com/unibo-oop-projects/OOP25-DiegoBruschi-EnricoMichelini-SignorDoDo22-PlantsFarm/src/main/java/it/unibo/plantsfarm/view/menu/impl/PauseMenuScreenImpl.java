package it.unibo.plantsfarm.view.menu.impl;

import it.unibo.plantsfarm.view.menu.api.PauseMenuScreen;
import it.unibo.plantsfarm.view.utility.ButtonFactory;
import it.unibo.plantsfarm.view.utility.Texture;
import it.unibo.plantsfarm.view.utility.WindowFactory;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

/**
 * View for the Pause Menu.
 */
public final class PauseMenuScreenImpl implements PauseMenuScreen {

    private static final String TITLE = "Pause Menu";
    private static final Color BG_COLOR = new Color(245, 245, 220);

    private static final int ROWS = 3;
    private static final int COLS = 2;

    private static final double GAP_RATIO = 0.02;
    private static final double PADDING_RATIO = 0.02;
    private static final double BTN_WIDTH_RATIO = 0.10;
    private static final double BTN_HEIGHT_RATIO = 0.05;

    private final JDialog screen;
    private final JPanel buttonPanel;
    private final int btnWidth;
    private final int btnHeight;

    /**
     * Creates the Pause Menu Screen.
     */
    public PauseMenuScreenImpl() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int gap = (int) (screenSize.height * GAP_RATIO);
        final int padding = (int) (screenSize.height * PADDING_RATIO);
        this.btnWidth = (int) (screenSize.width * BTN_WIDTH_RATIO);
        this.btnHeight = (int) (screenSize.height * BTN_HEIGHT_RATIO);

        this.screen = WindowFactory.createMenuWindow(TITLE);

        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridLayout(ROWS, COLS, gap, gap));
        this.buttonPanel.setBackground(BG_COLOR);
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        this.screen.add(this.buttonPanel);
    }

    private void addButton(final String text, final ImageIcon icon, final ActionListener listener) {
        final JButton button = ButtonFactory.createButton(text);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(this.btnWidth, this.btnHeight));
        button.addActionListener(listener);
        this.buttonPanel.add(button);
    }

    /**
     * Sets the action for the Resume button.
     *
     * @param listener The action listener.
     */
    @Override
    public void setResumeButton(final ActionListener listener) {
        addButton("RESUME", Texture.RESUME_ICON, listener);
    }

    /**
     * Sets the action for the Commands button.
     *
     * @param listener The action listener.
     */
    @Override
    public void setCommandsButton(final ActionListener listener) {
        addButton("COMMANDS", Texture.COMMANDS_ICON, listener);
    }

    /**
     * Sets the action for the Reset button.
     *
     * @param listener The action listener.
     */
    @Override
    public void setResetButton(final ActionListener listener) {
        addButton("RESET DATA", Texture.RESET_ICON, listener);
    }

    /**
     * Sets the action for the Extra button.
     *
     * @param listener The action listener.
     */
    @Override
    public void setExtraButton(final ActionListener listener) {
        addButton("EXTRA", Texture.EXTRA_ICON, listener);
    }

    /**
     * Sets the action for the Credits button.
     *
     * @param listener The action listener.
     */
    @Override
    public void setCreditsButton(final ActionListener listener) {
        addButton("CREDITS", Texture.CREDITS_ICON, listener);
    }

    /**
     * Sets the action for the Exit button.
     *
     * @param listener The action listener.
     */
    @Override
    public void setExitButton(final ActionListener listener) {
        addButton("EXIT GAME", Texture.EXIT_ICON, listener);
    }

    /**
     * Shows the pause menu window.
     */
    @Override
    public void show() {
        this.screen.setVisible(true);
    }

    /**
     * Closes the pause menu window.
     */
    @Override
    public void close() {
        this.screen.dispose();
    }
}
