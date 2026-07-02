package org.hsm.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hsm.view.gui.VisibleComponent;

/**
 * The abstract dialog for every window which that has the task of adding
 * something.
 *
 */
public abstract class AbstractAddDialog implements VisibleComponent {

    private static final double DIM_FACTOR = 1.1;
    private final JDialog dialog;

    /**
     * Create an AbstractAddDialog.
     * 
     * @param frame
     *            the frame of the dialog
     * @param name
     *            the name of the dialog
     * @param type
     *            the type of dialog
     */
    public AbstractAddDialog(final JFrame frame, final String name, final Dialog.ModalityType type) {
        this.dialog = new JDialog(frame, name, type);
        this.dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.dialog.setLayout(new BorderLayout());
        // buttons
        final JButton addButton = new JButton("Add");
        final JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(addButton);
        addButton.addActionListener(e -> addAction());
        this.dialog.getRootPane().setDefaultButton(addButton);
        this.dialog.getContentPane().add(southPanel, BorderLayout.SOUTH);
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
        final Dimension dimensione = new Dimension((int) (this.dialog.getWidth() * DIM_FACTOR),
                (int) (this.dialog.getHeight() * DIM_FACTOR));
        this.dialog.setSize(dimensione);
        this.dialog.setLocationByPlatform(true);
        this.dialog.setVisible(true);
    }

    /**
     * Add a specific action for the add button.
     */
    protected abstract void addAction();

}
