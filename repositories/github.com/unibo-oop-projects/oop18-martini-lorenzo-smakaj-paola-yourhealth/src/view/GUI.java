package view;
import javax.swing.*;


public interface GUI {
    /**
     * @return
     * returns a JPanel with BoxLayout.
     */
    JPanel createBoxPanel();

    /**
     * @return
     * returns a JPanel with GridLayout.
     */
    JPanel createGridPanel();

    /**
     * @return
     * returns a JPanel with FlowLayout.
     */
    JPanel createFlowPanel();

    /**
     * 
     * @param text
     * the text of the button.
     * @return
     * returns a JButton with text text on it.
     */
    JButton createButton(String text);

    /**
     * 
     * @param text
     * the text of the label
     * @param font
     * the font of the label
     * @return
     * returns a JLabel with given text and font.
     */
    JLabel createLabel(String text, Float font);

    /**
     * 
     * @param text
     * the text of the label
     * @param font
     * the font of the label
     * @return
     * returns a right-aligned JLabel with given text and font.
     */
    JLabel createLabelRight(String text, Float font);


    /**
     * 
     * @return
     * returns an empty text field.
     */
    JTextField createTextField();

    /**
     * 
     * @param text
     * the text of the area.
     * @return
     * returns a text area with the given text in it.
     */
    JTextArea createTextArea(String text);

    /**
     * 
     * @param string
     * the elements to be added to the combo box.
     * @return
     * returns a combo box with the given items in it:
     */
    JComboBox<String> createCombo(String[] string);
}