package it.unibo.modularcheckers.view.components;

import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.modularcheckers.model.PlayerType;

/**
 * GUI component for player selection.
 */
public class PlayerSelection extends JPanel {

    private static final int COLUMNS = 20;

    private final JLabel label;
    private final JTextField textField;
    private final JComboBox<?> comboBox;

    /**
     * Constructs the panel with a FlowLayout.
     *
     * @param label the JLabel's text
     */
    public PlayerSelection(final String label) {
        super(new FlowLayout());
        this.label = new JLabel(label);
        this.textField = new JTextField("", COLUMNS);
        this.comboBox = new JComboBox<>(Arrays.stream(PlayerType.values()).filter(test -> !test.equals(PlayerType.HISTORY_PLAYER)).toArray());
        init();
    }

    /**
     * Returns the text field.
     *
     * @return JTextField
     */
    public JTextField getTextField() {
        return this.textField;
    }

    /**
     * Returns the JComboBox for the selection.
     *
     * @return JComboBox
     */
    public JComboBox<?> getComboBox() {
        return this.comboBox;
    }

    /**
     * Returns directly the PlayerType selected.
     *
     * @return PlayerType
     */
    public PlayerType getPlayerType() {
        return (PlayerType) this.comboBox.getSelectedItem();
    }

    private void init() {
        add(this.label);
        add(this.textField);
        add(this.comboBox);
    }
}
