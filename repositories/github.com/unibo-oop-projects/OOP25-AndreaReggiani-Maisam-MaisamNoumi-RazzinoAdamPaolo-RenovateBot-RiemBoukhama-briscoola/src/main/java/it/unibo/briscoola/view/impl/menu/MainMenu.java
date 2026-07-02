package it.unibo.briscoola.view.impl.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Main menu panel representing the initial welcome screen.
 * Contains the game title and the core interaction buttons to play or exit.
 * 
 * @author Andrea Reggiani
 */
public final class MainMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int INSET_PADDING = 15;
    private static final int FONT_SIZE = 70;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int GRIDX0 = 0;
    private static final int GRIDY0 = 0;
    private static final int GRIDY1 = 1;
    private static final int GRIDY2 = 2;
    private static final int GRIDY3 = 3;

    /**
     * Constructs the MainMenu panel with listeners for navigation and control.
     *
     * @param choosePlay action listener to play
     * @param chooseExit action listener to quit
     * @param chooseRules action listener to read rules
     */
    public MainMenu(final ActionListener choosePlay, final ActionListener chooseExit, final ActionListener chooseRules) {

        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        final GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING); 

        gbc.gridx = GRIDX0; 

        final JLabel title = new JLabel("BRISCOOLA");
        title.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));
        title.setForeground(Color.YELLOW);
        gbc.gridy = GRIDY0;
        this.add(title, gbc);

        final JButton btnPlay = new JButton("Play");
        btnPlay.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnPlay.addActionListener(choosePlay);

        btnPlay.setFocusPainted(false);
        gbc.gridy = GRIDY1;
        add(btnPlay, gbc);

        final JButton btnRules = new JButton("Rules");
        btnRules.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnRules.addActionListener(chooseRules);

        btnRules.setFocusPainted(false);
        gbc.gridy = GRIDY2; 
        add(btnRules, gbc);

        final JButton btnQuit = new JButton("Exit Game");
        btnQuit.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        btnQuit.addActionListener(chooseExit);

        btnQuit.setFocusPainted(false);
        gbc.gridy = GRIDY3;
        add(btnQuit, gbc);
    }
}
