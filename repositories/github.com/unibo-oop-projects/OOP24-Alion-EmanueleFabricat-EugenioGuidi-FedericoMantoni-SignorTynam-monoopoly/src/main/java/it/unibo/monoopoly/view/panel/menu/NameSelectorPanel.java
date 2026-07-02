package it.unibo.monoopoly.view.panel.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import it.unibo.monoopoly.controller.menu.api.MenuController;

/**
 * Panel used for insert the names of the players and start the game.
 */
public final class NameSelectorPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int MAX_NAME_LENGTH = 20;
    private static final double COLOR_WEIGHT = 0.05;
    private static final double NAME_LABEL_WEIGHT = 0.3;
    private static final double TEXTFIELD_WEIGHT = 0.65;
    private static final Dimension PREFERRED_SIZE_COLOR = new Dimension(40, 20);

    /**
     * The list of {@link JTextField}s containing the name of the players.
     */
    private final List<JTextField> players;

    /**
     * Constructor and initialize the NameSelectorPanel.
     * 
     * @param colors         the list of the colors used in the game
     * @param menuController the istance of {@link MenuController}.
     * @param backListener   the {@link ActionListener} used to go back to the
     *                       previous window
     * @param nPlayers       the number of players previously selected
     */
    public NameSelectorPanel(final List<Color> colors, final MenuController menuController,
            final ActionListener backListener, final int nPlayers) {
        this.players = new ArrayList<>();

        this.setLayout(new GridBagLayout());
        final Font font = new Font("Arial", Font.BOLD, 15);

        final JButton back = new JButton("Indietro");
        final JButton confirm = new JButton("Via!");
        back.setFont(font);
        confirm.setFont(font);

        confirm.addActionListener(e -> {
            if (legalNames()) {
                ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                menuController.goGame(getPlayersNames());
            } else {
                JOptionPane.showMessageDialog(this,
                        "Ogni giocatore deve avere un nome diverso dagli altri e di lunghezza minore di "
                                + MAX_NAME_LENGTH + " caratteri",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        for (int i = 0; i < nPlayers; i++) {
            final GridBagConstraints gbc = getBasicConstraints();
            final JTextField nameField = new JTextField(MAX_NAME_LENGTH);
            players.add(nameField);
            final JLabel nameLabel = new JLabel("Inserisci il nome del player: ");
            final JPanel color = new JPanel();
            color.setPreferredSize(PREFERRED_SIZE_COLOR);
            color.setBackground(colors.get(i));
            nameLabel.setFont(font);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weighty = 0;
            gbc.weightx = COLOR_WEIGHT;
            this.add(color, gbc);
            gbc.gridx = 1;
            gbc.weightx = NAME_LABEL_WEIGHT;
            this.add(nameLabel, gbc);
            gbc.gridx = 2;
            gbc.weightx = TEXTFIELD_WEIGHT;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            this.add(nameField, gbc);
        }

        final GridBagConstraints gbc = getBasicConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        this.add(back, gbc);
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(confirm, gbc);

        this.revalidate();
        this.repaint();

        back.addActionListener(backListener);
    }

    private List<String> getPlayersNames() {
        return this.players.stream().map(JTextComponent::getText).toList();
    }

    private boolean legalNames() {
        return players.stream().map(JTextComponent::getText).allMatch(
                t -> !t.isBlank() && !t.isEmpty() && t.length() < MAX_NAME_LENGTH)
                && players.stream().map(JTextComponent::getText).count() == players.stream()
                        .map(JTextComponent::getText).distinct().count();
    }

    private GridBagConstraints getBasicConstraints() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

}
