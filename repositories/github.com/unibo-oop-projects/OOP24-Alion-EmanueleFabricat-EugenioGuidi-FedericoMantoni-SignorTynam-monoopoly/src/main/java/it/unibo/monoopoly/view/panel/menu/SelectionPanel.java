package it.unibo.monoopoly.view.panel.menu;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Panel for handle input of the number of players decided by the
 * user.
 */
public final class SelectionPanel extends JPanel {

    private static final int FONT_SIZE = 15;
    private static final double NUMBER_LABEL_WEIGHT = 0.4;
    private static final double SPINNER_WEIGHT = 0.3;
    private static final double BUTTON_WEIGHT = 0.3;

    private static final long serialVersionUID = 1L;

    /**
     * Construct and inizialize the SelectionPanel.
     * 
     * @param listener   the method called when confirm selection of the number of
     *                   the players
     */
    public SelectionPanel(final Consumer<Integer> listener) {
        super();

        this.setLayout(new GridBagLayout());
        final JLabel nPlayerTextSelection = new JLabel("Scegli il numero di giocatori");
        nPlayerTextSelection.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));

        final JSpinner spinnerSelection = new JSpinner(new SpinnerNumberModel(4, 2, 4, 1));
        final JButton confirmSelection = new JButton("OK");

        confirmSelection.addActionListener(ev -> {
            try {
                spinnerSelection.commitEdit();
                this.setVisible(false);
                listener.accept((Integer) spinnerSelection.getValue());
            } catch (final ParseException ex) {
                JOptionPane.showMessageDialog(this,
                        "Input non valido: " + spinnerSelection.getValue() + " è un numero di giocatori non consentito",
                        "Errore numero giocatori",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        this.add(nPlayerTextSelection, getLabelConstraints());
        this.add(spinnerSelection, getSpinnerConstraints());
        this.add(confirmSelection, getButtonConstraints());
    }

    private GridBagConstraints getLabelConstraints() {
        final GridBagConstraints gbc = getBasicConstraints();
        gbc.weightx = NUMBER_LABEL_WEIGHT;
        return gbc;
    }

    private GridBagConstraints getSpinnerConstraints() {
        final GridBagConstraints gbc = getBasicConstraints();
        gbc.weightx = SPINNER_WEIGHT;
        return gbc;
    }

    private GridBagConstraints getButtonConstraints() {
        final GridBagConstraints gbc = getBasicConstraints();
        gbc.weightx = BUTTON_WEIGHT;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        return gbc;
    }

    private GridBagConstraints getBasicConstraints() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

}
