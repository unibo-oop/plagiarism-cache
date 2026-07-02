package it.unibo.pacman.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.pacman.view.utilities.BackImagePanel;
import it.unibo.pacman.view.utilities.PacFont;
/**
 * An implementation of {@link GUIFactory}.
 */
public class GUIFactoryImpl implements GUIFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel createImageLabel(final String path) {
        return new JLabel(new ImageIcon(path));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JButton createMenuButton(final String text, final ActionListener al) {
        final JButton jb = new JButton(text);
        jb.setFont(new PacFont().getFont());
        jb.setFocusPainted(false);
        jb.setForeground(Color.BLACK);
        jb.setBackground(Color.YELLOW);
        jb.setBorderPainted(false);
        jb.setOpaque(true);
        jb.addActionListener(al);
        return jb;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createJPanel(final LayoutManager layout, final Color backgroundColor) {
        final JPanel jp = new JPanel(layout);
        jp.setBackground(backgroundColor);
        return jp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel createMainMenuOptionPanel(final LayoutManager layout, final String backgroundPath, 
            final int width, final int height) {
        final JPanel jp = new BackImagePanel(layout, backgroundPath);
        jp.setPreferredSize(new Dimension(width, height));
        return jp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GridBagConstraints createConstraints(final int insets) {
        final GridBagConstraints costr = new GridBagConstraints();
        costr.gridy = 0;
        costr.insets = new Insets(insets, insets, insets, insets);
        costr.fill = GridBagConstraints.HORIZONTAL;
        return costr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JComboBox<String> createSelector(final List<String> items) {
        final JComboBox<String> selector = new JComboBox<>();
        items.forEach(s -> selector.addItem(s));
        return selector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JTextField createTextInputField(final boolean isEditable, final int columns) {
        final JTextField textField = new JTextField();
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setColumns(columns);
        textField.setFont(new PacFont().getFont());
        textField.setEditable(isEditable);
        return textField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame createFrame() {
        final JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        return frame;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel createLabel() {
        final JLabel label = new JLabel();
        label.setFont(new PacFont().getFont());
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        return label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Canvas createCanvas(final int width, final int height) {
        final Canvas canvas = new Canvas();
        canvas.setSize(width, height);
        return canvas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BackImagePanel createBackImagePanel(final LayoutManager layout, final String imagePath) {
        return new BackImagePanel(layout, imagePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JTextArea createTextArea(final Color bg, final Color fg, final boolean isEditable) {
        final JTextArea jta = new JTextArea();
        jta.setEditable(isEditable);
        jta.setForeground(fg);
        jta.setBackground(bg);
        jta.setFont(new PacFont().getFont());
        return jta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JTextField createTextField(final String text, final Color background, final Color foreground) {
        final JTextField jtf = new JTextField(text);
        jtf.setForeground(foreground);
        jtf.setBackground(background);
        jtf.setFont(new PacFont().getFont());
        return jtf;
    }


}
