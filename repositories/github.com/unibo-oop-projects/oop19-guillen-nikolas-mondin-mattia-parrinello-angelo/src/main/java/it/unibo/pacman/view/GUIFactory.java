package it.unibo.pacman.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.pacman.view.utilities.BackImagePanel;

/**
 * 
 * GUI Factory interface.
 *
 */
public interface GUIFactory {
    /**
     * Creates a JLabel with the image given by the path.
     * 
     * @param path of the image
     * @return a JLabel
     */
    JLabel createImageLabel(String path);

    /**
     * Creates a JButton for a menu.
     * 
     * @param text of the button
     * @param al is the action listener
     * @return a JButton
     */
    JButton createMenuButton(String text, ActionListener al);

    /**
     * Creates a JPanel.
     * 
     * @param layout of the JPanel
     * @param backgroundColor color of the background
     * @return a JPanel
     */
    JPanel createJPanel(LayoutManager layout, Color backgroundColor);

    /**
     * Creates a option JPanel for the main menu. 
     * 
     * @param layout of the JPanel
     * @param backgroundPath path of the background
     * @param width of the JPanel
     * @param height of the JPanel
     * @return a JPanel
     */
    JPanel createMainMenuOptionPanel(LayoutManager layout, String backgroundPath, int width, int height);

    /**
     * Creates a GridBagConstraints.
     * 
     * @param insets for right, left, top and bottom insets
     * @return a GridBagConstraints
     */
    GridBagConstraints createConstraints(int insets);

    /**
     * Creates a JComboBox.
     * 
     * @param items of the drop down menu
     * @return a JComboBox
     */
    JComboBox<String> createSelector(List<String> items);

    /**
     * Creates a text input field.
     * 
     * @param isEditable decides if the text field is editable or not
     * @param columns    number of the text field
     * @return a JTextField
     */
    JTextField createTextInputField(boolean isEditable, int columns);

    /**
     * Creates a simple JFrame.
     * 
     * @return a JFrame
     */
    JFrame createFrame();

    /**
     * Creates a simple JLabel.
     * 
     * @return a JLable
     */
    JLabel createLabel();

    /**
     * Creates a Canvas.
     * 
     * @param width  of the canvas
     * @param height of the canvas
     * @return a Canvas
     */
    Canvas createCanvas(int width, int height);

    /**
     * Creates a BackImagePanel.
     * 
     * @param layout of the panel
     * @param imagePath path of background image
     * @return a BackImagePanel
     */
    BackImagePanel createBackImagePanel(LayoutManager layout, String imagePath);

    /**
     * Creates a JTextArea.
     * 
     * @param background sets area's background color
     * @param foreground sets area's foreground color
     * @param isEditable sets area editable or not
     * @return a JTextArea
     */
    JTextArea createTextArea(Color background, Color foreground, boolean isEditable);

    /**
     * Creates a JTextField.
     * 
     * @param text sets the default text of the TextField
     * @param background sets area's background color
     * @param foreground sets area's foreground color
     * @return a JTextField
     */
    JTextField createTextField(String text, Color background, Color foreground);

}
