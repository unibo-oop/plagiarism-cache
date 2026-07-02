package it.unibo.bmbman.view;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * interface for abstract factory pattern.
 *
 */
public interface GUIFactory {
    /**
     * Method to generate a Button.
     * @param text the button's text
     * @return a JButton.
     */
    JButton createButton(String text);
    /**
     * Method to generate a Frame.
     * @return a JFrame.
     */
    JFrame createFrame();
    /**
     * Generate a panel and a "return to main menu" button.
     * @param frame the frame used
     * @return the button created
     */
    JButton createReturnButton(JFrame frame);
    /**
     * Method to generate a RadioButtonMenuItem.
     * @param text the button's text
     * @return the button created
     */
    JRadioButton createRadioButton(String text);
    /**
     * Method to generate a Label.
     * @param text the Label text
     * @return a new label
     */
    JLabel createLabel(String text);
    /**
     * Method to generate a TextField.
     * @return a new JTextField
     */
    JTextField createTextField();
    /**
     * Method to generate a black Canvas.
     * @return {@link JFrame}
     */
    JFrame createFrameWithCanvas();
    /**
     * Method to scale the insets according to screen resolution.
     * @param insets the right insets in FHD
     * @return a new insets scaled according to screen resolution
     */
    Insets createScaledInsets(Insets insets);
}
