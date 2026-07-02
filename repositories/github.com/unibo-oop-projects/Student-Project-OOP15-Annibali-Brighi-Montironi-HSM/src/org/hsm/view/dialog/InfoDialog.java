package org.hsm.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.hsm.view.gui.VisibleComponent;
import org.hsm.view.utility.MyGUIFactory;

/**
 * The dialog that contains information about the app.
 *
 */
public class InfoDialog implements VisibleComponent {

    private final JDialog dialog;

    /**
     * Create the dialog.
     * 
     * @param frame
     *            the frame for the dialog
     */
    public InfoDialog(final JFrame frame) {
        this.dialog = new JDialog(frame, "About HSM", Dialog.ModalityType.APPLICATION_MODAL);
        this.dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.dialog.setLayout(new BorderLayout());
        final JLabel title = new MyGUIFactory().createLabel("Hydrophonic System Manager");
        title.setIcon(new ImageIcon(this.getClass().getResource("/plant.png")));
        title.setHorizontalAlignment(JLabel.CENTER);
        final String str = new StringBuffer()
                .append("<html>An application designed to manage an hydroponic greenhouse ")
                .append("and a database of plants.<br>The software was developed ")
                .append("by Silvio Annibali, Marco Brighi and Andrea Montironi.").append("<br>Version 1.0<html>")
                .toString();
        final JLabel label = new JLabel(str);
        this.dialog.add(title, BorderLayout.NORTH);
        this.dialog.add(label);
    }

    @Override
    public void start() {
        this.dialog.pack();
        this.dialog.setLocationByPlatform(true);
        this.dialog.setVisible(true);
    }

}
