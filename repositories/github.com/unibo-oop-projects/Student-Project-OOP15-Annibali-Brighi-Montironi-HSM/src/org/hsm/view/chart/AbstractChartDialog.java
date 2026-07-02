package org.hsm.view.chart;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.hsm.view.gui.VisibleComponent;

/**
 * This class provides a skeletal implementation of a chart dialog.
 *
 */
public abstract class AbstractChartDialog implements VisibleComponent {

    private final JDialog dialog;

    /**
     * Create the chart dialog.
     * 
     * @param characteristic
     *            the name of the characteristic
     */
    public AbstractChartDialog(final String characteristic) {
        this.dialog = new JDialog();
        this.dialog.setTitle(characteristic + " Chart");
        this.dialog.setLayout(new BorderLayout());
        // buttons
        final JButton exit = new JButton("Exit");
        exit.addActionListener(e -> this.dialog.dispose());
        this.dialog.add(exit, BorderLayout.SOUTH);
    }

    /**
     * Get the JDialog component.
     * 
     * @return the JDialog component
     */
    protected JDialog getJDialog() {
        return this.dialog;
    }

    @Override
    public void start() {
        this.dialog.pack();
        this.dialog.setLocationByPlatform(true);
        this.dialog.setVisible(true);
    }

}
