package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.PlumberControllerImpl;

/**
 * Utility class used to set up JScrollPane and frame layout.
 *
 */
public class UtilityClass extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -9085241374408742269L;

    private String readString = "<html>"; // string used to show gameRules or
                                            // levelStats

    private  JLabel utilityLabel;
    private JScrollPane scrollPane;
    /**Set the string to the new value.
     * 
     * @param value new value to set
     */
    public void setReadString(final String value) {
        this.readString = value;
    }
    /**Get the string.
     * 
     * @return the readesString
     */
    public String getReadedString() {
        return this.readString;
    }
    /**Get the scrollPane.
     * 
     * @return the current scroll pane
     */
    public JScrollPane getPane() {
        return this.scrollPane;
    }
    /**get the label.
     * 
     * @return the label
     */
    public JLabel getLabel() {
        return this.utilityLabel;
    }

    /**
     * Constructor of the class.
     * 
     * @param o
     * @param buttonMessage
     *            text to show on the button
     */
    public UtilityClass(final String buttonMessage) {
        this.setSize(800, 600);
        this.setJMenuBar(null);
        utilityLabel = new JLabel();
        utilityLabel.setVerticalAlignment(JLabel.TOP);

        final JPanel southPanel = new JPanel(new FlowLayout());
        final JButton btnClose = new JButton(buttonMessage);
        southPanel.add(btnClose);
        // readGameRules();

        // set the label inside the scroll panel
        scrollPane = new JScrollPane(utilityLabel);

        // always show the vertical scroll bar
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // window lister to set the default behaviour when the user close the
        // frame
        final WindowListener w = new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                // TODO Auto-generated method stub
                closeWindow();

            }
        };

        this.addWindowListener(w); // add the window listener to this frame
        btnClose.addActionListener(buttonListener());
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(southPanel, BorderLayout.SOUTH);
        PlumberControllerImpl.centreWindow(this); // ask the controller method to
                                                 // center the window
        this.setVisible(true); // show the frame
    }
    /**Method used for obtaining the listener.
     * 
     * @return the button behavior
     */
    protected ActionListener buttonListener() {
        return e -> {
        };
    }

    /**Method that closes the window.
     * 
     */
    protected void closeWindow() {

    }

}
