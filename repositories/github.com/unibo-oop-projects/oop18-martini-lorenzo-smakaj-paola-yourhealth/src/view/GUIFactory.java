package view;
import java.awt.*;

import javax.swing.*;

public class GUIFactory implements GUI {
    private final int gridHeight = 3;
    private final int gridWidth = 3;
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
    public JButton createButton(final String text) {
        JButton button = new JButton();
        button.setText(text);
        button.setFont(new Font("Calibri", Font.PLAIN,18));
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
        label.setFont(new Font("Calibri", Font.PLAIN,18));
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
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


}