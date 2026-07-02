package it.unibo.modularcheckers.view.components;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.modularcheckers.model.GameType;

/**
 * JPanel for game selection.
 */
public class GameSelection extends JPanel {

    private final JLabel label;
    private final JComboBox<GameType> comboBox;

    /**
     * Constructor of the panel.
     */
    public GameSelection() {
        super(new FlowLayout());
        this.label = new JLabel("Select a gamemode:");
        this.comboBox = new JComboBox<>(GameType.values());
        start();
    }

    private void start() {
        add(label);
        add(comboBox);
    }

    /**
     * Returns the GameType selected.
     *
     * @return GameType
     */
    public GameType getSelected() {
        return (GameType) comboBox.getSelectedItem();
    }
}
