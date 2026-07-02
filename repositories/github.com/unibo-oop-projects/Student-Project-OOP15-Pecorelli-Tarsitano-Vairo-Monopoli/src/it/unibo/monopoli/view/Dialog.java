package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * class that creates JDialog used during the game.
 *
 */
public class Dialog extends JDialog implements ActionListener {
    /**
     * Dialog class constructor.
     * 
     * @param parent
     *            -frame parent
     * @param title
     *            -frame title
     * @param message
     *            -message to be displayed
     */
    public Dialog(final JFrame parent, final String title, final String message) {
        super(parent, title, true);
        if (parent != null) {
            setLocation(C.X_LOCATION_JDIALOG, C.Y_LOCATION_JDIALOG);
        }
        final JPanel messagePane = new JPanel();
        messagePane.add(new JLabel(message));
        getContentPane().add(messagePane);
        final JPanel buttonPane = new JPanel();
        final JButton button = new JButton("OK");
        buttonPane.add(button);
        button.addActionListener(this);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * listener su botton JDialog.
     * 
     * @param e
     * 
     */
    public void actionPerformed(final ActionEvent e) {
        setVisible(false);
        dispose();
    }

}
