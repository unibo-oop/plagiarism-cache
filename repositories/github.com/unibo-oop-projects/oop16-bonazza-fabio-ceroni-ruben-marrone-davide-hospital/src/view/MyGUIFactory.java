package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Implementation of the interface GUIFactory, with my speciications for this project.
 *
 */
public class MyGUIFactory implements GUIFactory {
    private final int gridHeight = 12;
    private final int gridWidth = 1;
    private final int columns = 30;
    private final int rows = 5;
    private final int smallTextSize = 25;

    @Override
    public JPanel createBoxPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        return panel;
    }

    @Override
    public JPanel createFlowPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        return panel;
    }

    @Override
    public JPanel createGridPanel() {
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(gridHeight, gridWidth));
        return grid;
    }

    @Override
    public JPanel createGridBagPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        return panel;
    }

    @Override
    public JScrollPane createScrollPane(final JComponent comp) {
        JScrollPane scroll = new JScrollPane(comp);
        return scroll;
    }

    @Override
    public JButton createButton(final String text) {
        JButton button = new JButton();
        button.setText(text);
        return button;
    }

    @Override
    public JLabel createLabel(final String text, final Float font) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(font));
        return label;
    }

    @Override
    public JLabel createLabelRight(final String text, final Float font) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(font));
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return label;
    }

    @Override
    public JLabel createLabelLeft(final String text, final Float font) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(font));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    @Override
    public JTextField createTextField() {
        JTextField text = new JTextField();
        text.setColumns(smallTextSize);
        return text;
    }

    @Override
    public JTextArea createTextArea(final String text) {
        JTextArea area = new JTextArea();
        area.setColumns(columns);
        area.setRows(rows);
        area.setText(text);
        return area;
    }

    @Override
    public JComboBox<String> createCombo(final String[] string) {
        JComboBox<String> combo = new JComboBox<String>(string);
        return combo;
    }

    @Override
    public JSeparator createSep() {
        return new JSeparator(SwingConstants.HORIZONTAL);
    }

}