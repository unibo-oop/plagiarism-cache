package view;

import java.awt.LayoutManager;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Abstract Factory interface to created GUI component.
 *
 */
public interface GUIFactory {

    /**
     * @param layout
     *            The layout of the panel
     * @return An opaque panel with specified layout
     */
    JPanel createPanel(Optional<LayoutManager> layout);

    /**
     * @param text
     *            The button text
     * @return A button with custom style and specified text
     */
    JButton createButton(String text);

    /**
     * @param text
     *            The label text
     * @return A label with custom style and specified text
     */
    JLabel createLabel(String text);

    /**
     * @param elements
     *            The list of combobox elements
     * @return A combobox with custom style and specified text
     */
    JComboBox<String> createComboBox(List<String> elements);

}