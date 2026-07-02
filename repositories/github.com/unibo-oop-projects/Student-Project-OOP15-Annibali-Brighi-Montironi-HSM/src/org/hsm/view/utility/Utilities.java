package org.hsm.view.utility;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * An Utilities class for the app.
 *
 */
public final class Utilities {

    private static final String SAVE = "Save";

    private Utilities() {
    }

    /**
     * Show an error message.
     * 
     * @param component
     *            the component covered by the message
     * @param text
     *            the text of the message
     */
    public static void errorMessage(final Component component, final String text) {
        JOptionPane.showMessageDialog(component, text, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Show the message for saving greenhouse.
     * 
     * @param component
     *            the component covered by the message
     * @return true if the user wants to save otherwise false
     */
    public static boolean saveGreenhouseMessage(final Component component) {
        return JOptionPane.showConfirmDialog(component, "Do you want to save the Greenhouse?", SAVE,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Show the message for saving database.
     * 
     * @param component
     *            the component covered by the message
     * @return true if the user wants to save otherwise false
     */
    public static boolean saveDatabaseMessage(final Component component) {
        return JOptionPane.showConfirmDialog(component, "Do you want to save the Database?", SAVE,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Show the message for saving the database and the greenhouse.
     * 
     * @param component
     *            the component covered by the message
     * @return true if the user wants to save otherwise false
     */
    public static boolean saveGreenhouseAndDBMessage(final Component component) {
        return JOptionPane.showConfirmDialog(component, "Do you want to save the Greenhouse and the Database?", SAVE,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Get the ImageIcon of the structure.
     * 
     * @param type
     *            the type of structure
     * @return the ImageIcon of the structure
     */
    public static ImageIcon getStructIcon(final String type) {
        switch (type) {
        case "Linear":
            return new ImageIcon(Utilities.class.getClass().getResource("/linear.jpg"));
        case "Grid":
            return new ImageIcon(Utilities.class.getClass().getResource("/reticular.jpg"));
        case "Pyramidal":
            return new ImageIcon(Utilities.class.getClass().getResource("/pyramidal.jpg"));
        case "Circular":
            return new ImageIcon(Utilities.class.getClass().getResource("/circular.jpg"));
        default:
            return new ImageIcon(Utilities.class.getClass().getResource("/linear.jpg"));
        }
    }

    /**
     * Get a string with a customized format.
     * 
     * @param number
     *            the number to customize
     * @return the string of the number
     */
    public static String customFormat(final double number) {
        final DecimalFormat myFormatter = new DecimalFormat("###,###,##0.00");
        return myFormatter.format(number);
    }

}
