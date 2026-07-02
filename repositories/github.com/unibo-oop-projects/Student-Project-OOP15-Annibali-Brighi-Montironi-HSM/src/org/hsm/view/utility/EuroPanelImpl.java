package org.hsm.view.utility;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * A specific Panel for euros.
 *
 */
public class EuroPanelImpl implements EuroPanel {

    private static final double STEP = 0.01;
    private static final int CENT_FACTOR = 100;
    private static final int WIDTH = 100;
    private final JPanel panel;
    private final JSpinner euros;

    /**
     * Create the euro panel.
     * 
     * @param maxValue
     *            the max value to show
     */
    public EuroPanelImpl(final double maxValue) {
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        this.euros = new JSpinner(new SpinnerNumberModel(0, 0, maxValue, STEP));
        final JComponent field = ((JSpinner.DefaultEditor) this.euros.getEditor());
        final Dimension prefSize = field.getPreferredSize();
        final Dimension newSize = new Dimension(WIDTH, prefSize.height);
        field.setPreferredSize(newSize);
        this.panel.add(this.euros);
    }

    @Override
    public int getValue() {
        return (int) (((SpinnerNumberModel) this.euros.getModel()).getNumber().doubleValue() * CENT_FACTOR);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

}
